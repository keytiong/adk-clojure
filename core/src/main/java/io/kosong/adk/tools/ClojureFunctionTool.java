package io.kosong.adk.tools;

import clojure.java.api.Clojure;
import clojure.lang.*;
import com.google.adk.tools.BaseTool;
import com.google.adk.tools.ToolContext;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;
import io.reactivex.rxjava3.core.Single;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ClojureFunctionTool extends BaseTool {


    final static Logger logger = LoggerFactory.getLogger(ClojureFunctionTool.class);

    private static final Schema DEFAULT_PARAMETER_SCHEMA = Schema.builder().type("STRING").build();

    private static Map<String, Type> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put("String", new Type(Type.Known.STRING));
        TYPE_MAP.put("java.lang.String", new Type(Type.Known.STRING));
        TYPE_MAP.put("boolean", new Type(Type.Known.BOOLEAN));
        TYPE_MAP.put("Boolean", new Type(Type.Known.BOOLEAN));
        TYPE_MAP.put("java.lang.Boolean", new Type(Type.Known.BOOLEAN));
        TYPE_MAP.put("int", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("Integer", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("java.lang.Integer", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("long", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("Long", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("java.lang.Long", new Type(Type.Known.INTEGER));
        TYPE_MAP.put("float", new Type(Type.Known.NUMBER));
        TYPE_MAP.put("Float", new Type(Type.Known.NUMBER));
        TYPE_MAP.put("java.lang.Float", new Type(Type.Known.NUMBER));
        TYPE_MAP.put("double", new Type(Type.Known.NUMBER));
        TYPE_MAP.put("Double", new Type(Type.Known.NUMBER));
        TYPE_MAP.put("java.lang.Double", new Type(Type.Known.NUMBER));
    }

    private static final IFn DATAFY_FN;
    private static final IFn STRINGIFY_KEYS_FN;
    private static final IFn MAKE_OBJECT_FN;

    static {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Symbol.intern("io.kosong.adk.protocols"));
        require.invoke(Symbol.intern("io.kosong.adk.core"));
        require.invoke(Symbol.intern("clojure.datafy"));
        require.invoke(Symbol.intern("clojure.walk"));

        DATAFY_FN = Clojure.var("clojure.datafy", "datafy");
        STRINGIFY_KEYS_FN = Clojure.var("clojure.walk", "stringify-keys");
        MAKE_OBJECT_FN = Clojure.var("io.kosong.java", "make-object");
    }


    private final Var func;

    private final List<Symbol> argList;

    private final FunctionDeclaration functionDeclaration;

    protected ClojureFunctionTool(@NotNull String name, @NotNull String description, Var func) {
        this(name, description, func, false);
    }

    protected ClojureFunctionTool(@NotNull String name, @NotNull String description, Var func, boolean isLongRunning) {
        super(name, description, isLongRunning);
        this.func = func;
        this.argList = resolveLongestArgList(func);
        this.functionDeclaration = initFunctionDeclaration(name, description, argList);
    }

    public static ClojureFunctionTool create(String namespace, String name) {
        Var fn = (Var) Clojure.var(namespace, name);
        return ClojureFunctionTool.create(fn);
    }

    public static ClojureFunctionTool create(Var func) {
        IPersistentMap meta = func.meta();
        Symbol name = (Symbol) meta.valAt(Keyword.intern("name"));
        String description = (String) meta.valAt(Keyword.intern("doc"), "");
        return new ClojureFunctionTool(name.getName(), description, func);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Single<Map<String, Object>> runAsync(Map<String, Object> args, ToolContext toolContext) {

        List<Object> funcArgs = new LinkedList<>();

        for (Symbol s : this.argList) {
            String symbolName = s.getName();
            if (symbolName.equals("tool-context")) {
                funcArgs.add(DATAFY_FN.invoke(toolContext));
            } else {
                funcArgs.add(args.getOrDefault(symbolName, null));
            }
        }

        try {
            Object result = this.func.applyTo(ArraySeq.create(funcArgs.toArray()));

            if (result == null) {
                return Single.just(ImmutableMap.of());
            } else if (result instanceof Map) {
                Map<String, Object> m = (Map<String, Object>) STRINGIFY_KEYS_FN.invoke(result);
                return Single.just(m);
            } else {
                return Single.just(ImmutableMap.of("result", result));
            }
        } catch (Exception e) {
            logger.error("Error running function tool", e);
            return Single.just(ImmutableMap.of());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Symbol> resolveLongestArgList(Var fn) {
        IPersistentMap meta = fn.meta();

        List<List<Symbol>> argLists = (List<List<Symbol>>) meta.valAt(Keyword.intern("arglists"));

        List<Symbol> argList = Collections.emptyList();

        for (List<Symbol> xs : argLists) {
            if (xs.size() > argList.size()) {
                argList = xs;
            }
        }

        return argList;
    }

    private static Schema resolveParameterSchema(Symbol s) {
        IPersistentMap meta = s.meta();

        try {
            if (meta != null) {
                Object schemaMap = meta.valAt(Keyword.intern("schema"), null);
                if (schemaMap != null) {
                    return (Schema) MAKE_OBJECT_FN.invoke(Class.forName("com.google.genai.types.Schema"), schemaMap);
                } else {
                    String tag = (String) meta.valAt(Keyword.intern("tag"), null);
                    if (tag != null) {
                        Type t = TYPE_MAP.getOrDefault(tag, null);
                        if (t != null) {
                            return Schema.builder().type(t).build();
                        }
                    }
                }
            }
            return DEFAULT_PARAMETER_SCHEMA;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static FunctionDeclaration initFunctionDeclaration(String name, String
            description, List<Symbol> argList) {

        Map<String, Schema> properties = new LinkedHashMap<>();

        for (Symbol s : argList) {
            if (!"tool-context".equals(s.getName())) {
                String paramName = s.getName();
                Schema paramSchema = resolveParameterSchema(s);
                properties.put(paramName, paramSchema);
            }
        }

        Schema parameters = Schema.builder()
                .properties(properties)
                .type(Type.Known.OBJECT)
                .build();

        return FunctionDeclaration.builder()
                .name(name)
                .description(description)
                .parameters(parameters)
                .build();
    }

    @Override
    public Optional<FunctionDeclaration> declaration() {
        return Optional.ofNullable(functionDeclaration);
    }
}
