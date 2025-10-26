package io.kosong.adk.agents;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Symbol;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.Callbacks;
import com.google.adk.agents.InvocationContext;
import com.google.adk.events.Event;
import io.reactivex.rxjava3.core.Flowable;

import java.util.LinkedList;
import java.util.List;

public class ClojureAgent extends BaseAgent {


    private IFn runAsyncFn = null;

    private IFn runLiveFn = null;

    private static final IFn datafyFn;

    private static final IFn intoEventFn;

    static {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Symbol.intern("io.kosong.adk.protocols"));
        require.invoke(Symbol.intern("io.kosong.adk.core"));
        datafyFn = Clojure.var("clojure.datafy", "datafy");
        intoEventFn = Clojure.var("io.kosong.adk.protocols", "into-event");
    }

    protected ClojureAgent(ClojureAgent.Builder builder) {
        super(builder.name, builder.description, builder.subAgents,
                builder.beforeAgentCallback, builder.afterAgentCallback);
        this.runAsyncFn = builder.runAsyncFn;
        this.runLiveFn = builder.runLiveFn;
    }

    protected ClojureAgent(String name,
                           String description,
                           List<? extends BaseAgent> subAgents,
                           List<Callbacks.BeforeAgentCallback> beforeAgentCallback,
                           List<Callbacks.AfterAgentCallback> afterAgentCallback,
                           IFn runAsyncFn,
                           IFn runLiveFn) {
        super(name, description, subAgents, beforeAgentCallback, afterAgentCallback);
        this.runAsyncFn = runAsyncFn;
        this.runLiveFn = runLiveFn;
    }

    @Override
    protected Flowable<Event> runAsyncImpl(InvocationContext invocationContext) {
        if (runAsyncFn == null) {
            throw new UnsupportedOperationException("runAsyncImpl is not implemented");
        }

        Object contextMap = datafyFn.invoke(invocationContext);
        Object result = runAsyncFn.invoke(contextMap);

        if (result != null) {
            Event event = (Event) intoEventFn.invoke(result);
            return Flowable.just(event);
        } else {
            return Flowable.empty();
        }
    }

    @Override
    protected Flowable<Event> runLiveImpl(InvocationContext invocationContext) {

        if (runLiveFn == null) {
            throw new UnsupportedOperationException("runLiveImpl is not implemented");
        }

        Object contextMap = datafyFn.invoke(invocationContext);
        Object result = runLiveFn.invoke(contextMap);

        if (result != null) {
            Event event = (Event) intoEventFn.invoke(result);
            return Flowable.just(event);
        } else {
            return Flowable.empty();
        }
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        String name = "";
        String description = "";
        List<? extends BaseAgent> subAgents = new LinkedList<>();
        List<Callbacks.BeforeAgentCallback> beforeAgentCallback = new LinkedList<>();
        List<Callbacks.AfterAgentCallback> afterAgentCallback = new LinkedList<>();
        IFn runAsyncFn = null;
        IFn runLiveFn = null;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder subAgents(List<BaseAgent> subAgents) {
            this.subAgents = subAgents;
            return this;
        }

        public Builder beforeAgentCallback(List<Callbacks.BeforeAgentCallback> callback) {
            this.beforeAgentCallback = callback;
            return this;
        }

        public Builder afterAgentCallback(List<Callbacks.AfterAgentCallback> callback) {
            this.afterAgentCallback = callback;
            return this;
        }

        public Builder runAsyncFn(IFn fn) {
            this.runAsyncFn = fn;
            return this;
        }

        public Builder runLiveFn(IFn fn) {
            this.runLiveFn = fn;
            return this;
        }

        public ClojureAgent build() {
            return new ClojureAgent(this);
        }

    }
}
