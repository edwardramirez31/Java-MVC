package access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.ClientModel;
import utils.ConnectionDB;

public class ClientDAO {
    private Connection conn = null;

    public void createClient(ClientModel client) {
        try {
            if (conn == null) {
                conn = ConnectionDB.getConnection();
            }
            String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getName());
            statement.setString(3, client.getLastname());
            statement.setString(4, client.getEmail());
            statement.setLong(5, client.getCellphone());
            statement.setString(6, client.getPassword());
            statement.setString(7, client.getDate());
            int result = statement.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Cliente agregado en la base de datos");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }

    }

    public ClientModel getClientByLogin(String login) {
        ClientModel client = null;

        try {
            // 1. Get connection
            if (conn == null)
                conn = ConnectionDB.getConnection();
            // 2. create statement
            String statement = "SELECT * FROM cliente where cli_login = ?";
            // 3. prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, login);
            // 4. execute query
            ResultSet result = preparedStatement.executeQuery();
            // 5. loop the result set
            while (result.next()) {
                String name = result.getString(2);
                String lastname = result.getString(3);
                String email = result.getString(4);
                long cellphone = result.getLong(5);
                String password = result.getString(6);
                String date = result.getString(7);
                client = new ClientModel(login, name, lastname, email, cellphone, password, date);
                break;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
        return client;
    }

    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        ClientModel result = clientDAO.getClientByLogin("ninja");
        System.out.println(result);
    }
}
