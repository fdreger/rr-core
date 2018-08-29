package net.bajobongo.beach.engine;

import net.bajobongo.beach.util.EngineBiConsumer;
import net.bajobongo.beach.util.EngineConsumer;
import net.bajobongo.beach.util.EngineTriConsumer;
import net.bajobongo.beach.util.TriConsumer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Engine<Entity> {

    private final Deque<Entity> entityStack = new ArrayDeque<>();
    private final List<Entity> entitiesToRemove = new ArrayList<>();
    private final List<Entity> entities = new ArrayList<Entity>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entitiesToRemove.add(entity);
        doRemovals();
    }

    private void doRemovals() {
        if (entitiesToRemove.isEmpty() || !entityStack.isEmpty()) {
            return;
        }

        for (Entity entity : entitiesToRemove) {
            entities.remove(entity);
            if (entity instanceof AutoCloseable) {
                try {
                    ((AutoCloseable)entity).close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        entitiesToRemove.clear();
    }

    public void forEachEntity(Predicate<Entity> predicate, Consumer<Entity> consumer) {
        entities.stream().filter(predicate).forEach((e) -> {
            entityStack.push(e);
            consumer.accept(e);
            entityStack.pop();
        });
        doRemovals();
    }

    public <T> void forEach(ComponentMapper<T, Entity> predicate, Consumer<T> consumer) {
        entities.stream().filter(predicate).forEach(e -> {
            entityStack.push(e);
            consumer.accept(predicate.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <T, Y> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, BiConsumer<T, Y> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).forEach(e -> {
            entityStack.push(e);
            consumer.accept(predicateA.getFrom(e), predicateB.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <T, Y, U> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, ComponentMapper<U, Entity> predicateC, TriConsumer<T, Y, U> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).filter(predicateC).forEach(e -> {
            entityStack.push(e);
            consumer.accept(predicateA.getFrom(e), predicateB.getFrom(e), predicateC.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <T, Y, U> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, ComponentMapper<U, Entity> predicateC, EngineTriConsumer<Engine<Entity>, T, Y, U> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).filter(predicateC).forEach(e -> {
            entityStack.push(e);
            consumer.accept(this, predicateA.getFrom(e), predicateB.getFrom(e), predicateC.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <T, Y> void forEach(ComponentMapper<T, Entity> predicateA, ComponentMapper<Y, Entity> predicateB, ComponentMapper<Y, Entity> predicateC, EngineBiConsumer<Engine<Entity>, T, Y> consumer) {
        entities.stream().filter(predicateA).filter(predicateB).forEach(e -> {
            entityStack.push(e);
            consumer.accept(this, predicateA.getFrom(e), predicateB.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <Y> void forEach(ComponentMapper<Y, Entity> predicateA, EngineConsumer<Engine<Entity>, Y> consumer) {
        entities.stream().filter(predicateA).forEach(e -> {
            entityStack.push(e);
            consumer.accept(this, predicateA.getFrom(e));
            entityStack.pop();
        });
        doRemovals();
    }

    public <T> Entity findSingleEntity(ComponentMapper<T, Entity> predicateA) {
        return entities
                .stream()
                .filter(predicateA)
                .findFirst()
                .orElse(null);
    }

    public long count(Predicate<Entity> predicate) {
        return entities.stream().filter(predicate).count();
    }

    public Entity currentEntity() {
        return entityStack.peekFirst();
    }

    public void removeCurrentEntity() {
        removeEntity(currentEntity());
    }

}
