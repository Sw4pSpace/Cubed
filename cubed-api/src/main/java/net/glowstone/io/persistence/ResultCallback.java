package net.glowstone.io.persistence;

import java.sql.SQLException;

public interface ResultCallback<T> {

    void onSuccess(T resultSet);

    void onFailure(SQLException exception);

}
