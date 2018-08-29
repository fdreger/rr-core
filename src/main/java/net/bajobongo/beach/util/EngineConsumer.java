package net.bajobongo.beach.util;

import net.bajobongo.beach.engine.Engine;

@FunctionalInterface
public interface EngineConsumer<E extends Engine, T> {

    void accept(E engine, T t);

}
