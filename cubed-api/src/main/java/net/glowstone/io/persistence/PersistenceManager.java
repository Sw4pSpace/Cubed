package net.glowstone.io.persistence;

import java.sql.ResultSet;
import java.util.List;

public interface PersistenceManager {

    DatabaseResult<ResultSet> executeQuery(String query, List<Object> parameters);

    DatabaseResult<Integer> executeUpdate(String query, List<Object> parameters);

    void executeQueryCallback(String query, List<Object> parameters, ResultCallback<ResultSet> callback);

    void executeUpdateCallback(String query, List<Object> parameters, ResultCallback<Integer> callback);

    DatabaseStatement execute(String query);

}