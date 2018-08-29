package net.bajobongo.beach.util;

import net.bajobongo.beach.engine.Engine;

@FunctionalInterface
public interface EngineTriConsumer<E extends Engine, T, U, I> {

    void accept(E e, T t, U u, I i);

}
