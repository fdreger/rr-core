package net.bajobongo.beach.util;

@FunctionalInterface
public interface TriConsumer<T, U, I> {

    void accept(T t, U u, I i);

}
