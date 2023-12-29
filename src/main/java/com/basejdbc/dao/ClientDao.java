package com.basejdbc.dao;

import com.basejdbc.emptity.Client;
import com.basejdbc.storage.Storage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@AllArgsConstructor
public class ClientDao {
    private static final String INSERT_CLIENT_PREPARED_STATEMENT = "INSERT INTO client (`NAME`) VALUES (?)";

    public static void save(Client client) {
        try {
            Connection connection = Storage.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_PREPARED_STATEMENT);

            populatePreparedStatement(client, preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void populatePreparedStatement(Client client, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, client.getName());
    }

}
