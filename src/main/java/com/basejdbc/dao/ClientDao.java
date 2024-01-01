package com.basejdbc.dao;

import com.basejdbc.emptity.Client;
import com.basejdbc.storage.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class ClientDao {
    private ClientDao() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static final String INSERT_CLIENT_PREPARED_STATEMENT = "INSERT INTO client (`NAME`) VALUES (?)";

    public static void save(List<Client> client) {
        try
        {
            Connection connection = Storage.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_PREPARED_STATEMENT);
            connection.setAutoCommit(false);
            for(Client cl: client) {
                populatePreparedStatement(cl, preparedStatement);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void populatePreparedStatement(Client client, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, client.getName());
    }
}
