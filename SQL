import java.sql.*;

public class Sql {


    public static void runSelectQuery(){
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("JDBC-drivrutinerna hittades!");

            String url = "jdbc:mariadb://atlantis.informatik.umu.se/svph2426_db_vt2025?sslMode=trust";
            String user = "svph2411";
            //String password = "";
            Password mypw = new Password();
            String password = mypw.getPassword();

            try {
                dbConnection = DriverManager.getConnection(url, user, password);
                System.out.println("Uppkopplingen lyckades!");
                String strSql = "SELECT förnamn, personlig_budget FROM Användare ORDER BY förnamn ASC";
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(strSql);
                while (rs.next()){
                    System.out.println(rs.getString("förnamn") + ", " + rs.getInt("personlig_budget"));
                }
            } catch (SQLException ex){
                System.err.println("Ett fel har inträffat: " + ex.toString());
            }

        } catch (Exception e){
            System.err.println("JDBC-drivrutinerna hittades inte.");
        }

        try{
            if (rs != null){
                rs.close();
            }
            if (statement != null){
                statement.close();
            }
            if (dbConnection != null){
                dbConnection.close();
            }
        } catch (SQLException ex) {
            System.err.println("Ett fel har uppstått: " + ex.toString());
        }


    }



}
