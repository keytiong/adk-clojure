package io.kosong.adk.agents;

import clojure.lang.IFn;
import com.google.adk.agents.Callbacks;
import com.google.adk.tools.BaseTool;
import com.google.adk.tools.ToolContext;
import io.reactivex.rxjava3.core.Maybe;
import java.util.Map;

/**
 * Bridges Clojure functions to ADK tool callback interfaces.
 * 
 * This class works around a Clojure limitation where reify methods with 5+ parameters
 * fail when invoked via Java interface dispatch (e.g., RxJava's concatMapMaybe).
 */
public final class ClojureToolCallback {

    private static final IFn DATAFY_FN;
    private static final IFn STRINGIFY_KEYS_FN;

    static {
        clojure.lang.RT.var("clojure.core", "require").invoke(
            clojure.lang.Symbol.intern("clojure.datafy"),
            clojure.lang.Symbol.intern("clojure.walk")
        );
        DATAFY_FN = clojure.lang.RT.var("clojure.datafy", "datafy");
        STRINGIFY_KEYS_FN = clojure.lang.RT.var("clojure.walk", "stringify-keys");
    }

    private ClojureToolCallback() {}

    /**
     * Creates an AfterToolCallback from a Clojure function.
     * The Clojure function receives: (datafied-invocation-context, tool, input, datafied-tool-context, response)
     */
    public static Callbacks.AfterToolCallback createAfterToolCallback(IFn f) {
        return (invocationContext, tool, input, toolContext, response) -> {
            try {
                Object ctx = DATAFY_FN.invoke(invocationContext);
                Object tc = DATAFY_FN.invoke(toolContext);
                Object r = f.invoke(ctx, tool, input, tc, response);
                if (r == null) {
                    return Maybe.empty();
                }
                @SuppressWarnings("unchecked")
                Map<String, Object> result = (Map<String, Object>) STRINGIFY_KEYS_FN.invoke(r);
                return Maybe.just(result);
            } catch (Throwable t) {
                return Maybe.error(t);
            }
        };
    }
}
