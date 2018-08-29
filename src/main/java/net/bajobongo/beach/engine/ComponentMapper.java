package net.bajobongo.beach.engine;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class  ComponentMapper<T, Entity> implements Predicate<Entity> {

    private final Function<Entity, T> getter;
    private final BiConsumer<Entity, T> setter;

    public ComponentMapper(Function<Entity, T> getter) {
        this(getter, null);
    }

    public ComponentMapper(Function<Entity, T> getter, BiConsumer<Entity, T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public T getFrom(Entity e) {
        return getter.apply(e);
    }

    public void to(Entity e, T t) {
        setter.accept(e, t);
    }

    public boolean setOn(Entity e) {
        return getFrom(e) != null;
    }

    @Override
    public boolean test(Entity entity) {
        return setOn(entity);
    }

}
