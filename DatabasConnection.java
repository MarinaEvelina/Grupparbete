import java.sql.Connection;
import java.sql.DriverManager;

public class DatabasConnection {

    public static Connection dbConnection() {
        Connection dbConnection = null;

        try {
            String url = "jdbc:mariadb://atlantis.informatik.umu.se/svph2426_db_vt2025?sslMode=trust";

            String user = "svph2426";

            Password mypw = new Password();

            String password = mypw.getPassword();
            dbConnection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.err.println("Fel");

        }

        return dbConnection;
    }
}
