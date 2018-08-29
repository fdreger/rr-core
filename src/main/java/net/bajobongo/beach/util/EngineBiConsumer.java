package net.bajobongo.beach.util;

import net.bajobongo.beach.engine.Engine;

@FunctionalInterface
public interface EngineBiConsumer<E extends Engine, T, U> {

    void accept(E e, T t, U u);

}
