import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mariadb://atlantis.informatik.umu.se/svph2415_db2_vt2025?useSSL=true&trustServerCertificate=true";
        String user = "svph2411";
        Password mypw = new Password();
        String password = mypw.getPassword();

        return DriverManager.getConnection(url, user, password);
    }


}
