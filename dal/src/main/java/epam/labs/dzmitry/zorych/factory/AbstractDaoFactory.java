package epam.labs.dzmitry.zorych.factory;

import epam.labs.dzmitry.zorych.dao.JsonDao;

public interface AbstractDaoFactory {
    <T> JsonDao<T> createDao(String urlTemplate, String apiKey, String apiProvider) throws DaoBuildingFailedException;
}
