
package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

//Essa classe servirá como link entre a aplicação e o banco de dados.
public class CriaConexao {

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conectando ao banco");
            return DriverManager.getConnection("jdbc:postgresql:agendabd", "postgres", "123456");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

}