package epam.labs.dzmitry.zorych.entitybuilder;

public interface EntityBuilder<T> {
    T build(String json);
}
