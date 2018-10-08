package net.glowstone.io.persistence;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.glowstone.GlowServer;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;

public class PersistenceManagerImpl implements PersistenceManager {

    @Inject @Named("DATABASE_URL") private String DATABASE_URL;
    @Inject @Named("DATABASE_USER") private String DATABASE_USER;
    @Inject @Named("DATABASE_PASS") private String DATABASE_PASS;
    @Inject @Named("DATABASE_CLASS") private String DATABASE_CLASS;

    private Connection createConnection() throws SQLException {
        Connection c = null;
        try {
            Class.forName(DATABASE_CLASS);
            c = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
            c.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    private String applyParameters(String query, List<Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder(query);
        for (Object parameter : parameters) {
            int index = queryBuilder.indexOf("?");
            queryBuilder.replace(index, index+1, String.valueOf(parameter));
        }
        return queryBuilder.toString();
    }

    @Override
    public DatabaseResult<ResultSet> executeQuery(String query, List<Object> parameters) {
        Connection c;
        Statement stmt;
        try {
            c = createConnection();
            stmt = c.createStatement();
            query = applyParameters(query, parameters);
            ResultSet resultSet = stmt.executeQuery(query);
            return new DatabaseResult<>(resultSet, stmt, c);
        } catch ( SQLException e ) {
            GlowServer.logger.error("Error executing query " + query, e);
        }
        return null;
    }

    @Override
    public void executeQueryCallback(String query, List<Object> parameters, ResultCallback<ResultSet> callback) {
        Connection c;
        Statement stmt;
        try {
            c = createConnection();
            stmt = c.createStatement();
            query = applyParameters(query, parameters);
            ResultSet resultSet = stmt.executeQuery(query);
            callback.onSuccess(resultSet);
            resultSet.close();
            stmt.close();
            c.close();
        } catch ( SQLException e ) {
            callback.onFailure(e);
        }
    }

    @Override
    public DatabaseResult<Integer> executeUpdate(String query, List<Object> parameters) {
        Connection c;
        Statement stmt;
        try {
            c = createConnection();
            stmt = c.createStatement();
            query = applyParameters(query, parameters);
            int rows = stmt.executeUpdate(query);
            return new DatabaseResult<>(rows, stmt, c);
        } catch ( SQLException e ) {
            GlowServer.logger.error("Error executing update " + query, e);
        }
        return null;
    }

    @Override
    public void executeUpdateCallback(String query, List<Object> parameters, ResultCallback<Integer> callback) {
        Connection c;
        Statement stmt;
        try {
            c = createConnection();
            stmt = c.createStatement();
            query = applyParameters(query, parameters);
            int rows = stmt.executeUpdate(query);
            callback.onSuccess(rows);
            stmt.close();
            c.commit();
            c.close();
        } catch ( SQLException e ) {
            callback.onFailure(e);
        }
    }

    @Override
    public DatabaseStatement execute(String query) {
        try {
            Connection c = createConnection();
            return new DatabaseStatement(c.prepareStatement(query), c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
