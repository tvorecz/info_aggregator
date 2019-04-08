package epam.labs.dzmitry.zorych.factory;

import epam.labs.dzmitry.zorych.dao.JsonDao;

import java.lang.reflect.InvocationTargetException;

public interface AbstractDaoFactory {
    <T> JsonDao<T> createDao(String urlTemplate, String apiKey, String apiProvider) throws
                                                                                    ClassNotFoundException,
                                                                                    InvocationTargetException,
                                                                                    InstantiationException,
                                                                                    IllegalAccessException;
}
