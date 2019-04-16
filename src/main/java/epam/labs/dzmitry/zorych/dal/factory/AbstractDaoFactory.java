package epam.labs.dzmitry.zorych.dal.factory;

import epam.labs.dzmitry.zorych.dal.dao.JsonDao;

public interface AbstractDaoFactory {
    <T> JsonDao<T> createDao(String urlTemplate, String apiKey, String apiProvider) throws DaoBuildingFailedException;
}
