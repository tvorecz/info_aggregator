package epam.labs.dzmitry.zorych.entitycreator;

public interface EntityCreator<T> {
    T create(String json) throws DataSourceException;
}
