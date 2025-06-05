import org.mariadb.jdbc.Statement;
import java.sql.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class MenyVal {

    Connection dbConnection = null; // vår koppling
    Statement statement = null;     // vår SQL-fråga
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;            // fångar upp resultatet som vi får tillbaka.

    public Connection getDbConnection() {
        return dbConnection;
    }

    private final Scanner inMatning;

    public MenyVal() {
        inMatning = new Scanner(System.in);
    }

    public int visaMeny() {
        System.out.println("----------------------");
        System.out.println("-----MENY DATABAS-----");
        System.out.println("1. Visa användare ");
        System.out.println("2. Lägg till resa ");
        System.out.println("3. Uppdatera information ");
        System.out.println("4. Radera information ");
        System.out.println("5. Avsluta och spara ");
        System.out.println("\nVälj alternativ: ");

        int valStr = 0;
        while (true) {
            try {
                valStr = inMatning.nextInt();
                if (valStr >= 1 && valStr <= 5) {
                    break;
                } else {
                    System.out.println("Var god ange en siffra 1-5");
                    inMatning.nextLine();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Något gick fel, var god försök igen med en siffra 1-5");
                inMatning.nextLine();
            }
        }
        return valStr;
    }

    //ska visa meny med användare, sen kunna välja användare och se dens resor
    public void visaAnvandare() {
        System.out.println("-------------------");
        System.out.println("-----ANVÄNDARE-----");

        try {
            String strSql = "SELECT rm.r_id, a.förnamn, a.efternamn, rm.stadsnamn, rm.landsnamn, rm.r_typ, rm.res_från, rm.res_till " +
                    "FROM Användare AS a, Resmål AS rm, Resmålsplan AS rmp WHERE a.personnummer = rmp.personnummer " +
                    "AND rm.r_id = rmp.r_id ORDER BY rm.r_id";
            statement = (Statement) DatabasConnection.dbConnection().createStatement();
            rs = statement.executeQuery(strSql);
            while (rs.next()) {
                System.out.println(rs.getInt("rm.r_id") + ". " + rs.getString("a.förnamn") + " " + rs.getString("a.efternamn") + "\n" + rs.getString("rm.stadsnamn") + ", " + rs.getString("rm.landsnamn") + ", " + rs.getString("rm.r_typ") + ", " +
                        rs.getDate("rm.res_från") + " - " + rs.getDate("rm.res_till") + "\n------------------------------------------------------");
            }
        } catch (SQLException ex) {
            System.err.println("Ett fel har inträffat: " + ex);
        }

    }

    //lägga till resor
    public void laggTillResa() {
        System.out.println("----------------------");
        System.out.println("----LÄGG TILL RESA----");
        System.out.println();

        System.out.println("Vilket land reser du till?");
        inMatning.nextLine();
        String land = inMatning.nextLine();

        System.out.println("Vilken stad vill du resa till?");
        String stad = inMatning.nextLine();

        System.out.println("Vilket datum reser du från? (YYYY-MM-DD)");
        String datumFran = inMatning.nextLine();

        System.out.println("Vilket datum reser du till? (YYYY-MM-DD)");
        String datumTill = inMatning.nextLine();

        System.out.println("Vilken typ av resa? \nVälj mellan:\n- Solsemester\n- Stadsresa\n- Affärsresa\n- Friluftsresa\n- Kulturresa\n- Annat ");
        String resetyp = inMatning.nextLine();

        String rID = null;

        try {
            String strSql = "INSERT INTO Resmål (stadsnamn, res_från, res_till, r_typ, r_id, landsnamn) " +
                    "VALUES ('" + stad + "', '" + datumFran + "', '" + datumTill + "', '" + resetyp + "', " + rID + ", '" + land + "');";
            statement = (Statement) DatabasConnection.dbConnection().createStatement();
            rs = statement.executeQuery(strSql);

            System.out.println("\nDu har lagt till resan " + stad + " under datumen " + datumFran + "-" + datumTill);

        } catch (SQLException ex) {
            System.err.println("Ett fel har inträffat: " + ex);
        }

    }

    public void redigeraResa() {
        System.out.println("---------------------");
        System.out.println("----REDIGERA RESA----");
        System.out.println("Ange platsnummer för det resmål som du vill redigera:");
    }
    try {
            String strSql = "SELECT an.förnamn, an.efternamn, rm.stadsnamn, rm.landsnamn, rm.res_från, rm.res_till  " +
                    "FROM Resmål AS rm, Resmålsplan AS rmp, Användare AS an " +
                    "WHERE a.personnummer = rmp.personnummer AND rm.r_id = rmp.r_id ORDER BY rm.r_id";
            statement = (Statement) DatabasConnection.dbConnection().createStatement();
            rs = statement.executeQuery(strSql);

            while (rs.next()) {
                System.out.println(rs.getInt("rm.r_id") + ". " + rs.getString("a.förnamn") + " " + rs.getString("a.efternamn") + "\n" + rs.getString("rm.stadsnamn") + ", " + rs.getString("rm.landsnamn") + ", " + rs.getString("rm.r_typ") + ", " +
                        rs.getDate("rm.res_från") + " - " + rs.getDate("rm.res_till") + "\n------------------------------------------------------");
            }
        }catch (SQLException ex){
                    System.err.println("Ett fel har inträffat: " + ex.toString());
                }

/*
        System.out.println("1. Uppdatera datum du reser från");
        System.out.println("2. Uppdatera datum du reser till");
        System.out.println("\nVänligen välj vad du vill uppdatera: ");





        int valdSiffra = 0;
        while (true) {
            try {
                valdSiffra = inMatning.nextInt();
                if (valdSiffra >= 1 && valdSiffra <= 2) {
                    break;
                } else {
                    System.out.println("Var god ange en siffra 1-5");
                    inMatning.nextLine();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Något gick fel, var god försök igen och ange en siffra mellan 1-5");
                inMatning.nextLine();
            }
        }
*/

    public void raderaAktivitet() {
        System.out.println("---------------------");
        System.out.println("-----RADERA AKTIVITET-----");

        try {
            String strSql = "SELECT ak.r_id, rm.stadsnamn, ak.a_namn, ak.a_budget, ak.a_datum, an.förnamn, an.efternamn " +
                    "FROM Aktivitet AS ak, Resmål AS rm, Resmålsplan AS rmp, Användare AS an " +
                    "WHERE ak.r_id = rm.r_id AND rm.r_id = rmp.r_id AND an.personnummer = rmp.personnummer ORDER BY ak.r_id";
            statement = (Statement) DatabasConnection.dbConnection().createStatement();
            rs = statement.executeQuery(strSql);

            while (rs.next()) {
                System.out.println(rs.getInt("ak.r_id") + ". " + rs.getString("rm.stadsnamn") + " - "
                        + rs.getString("ak.a_namn") + "\n" + rs.getInt("ak.a_budget") + " kr, "
                        + rs.getDate("ak.a_datum") + ", " + rs.getString("an.förnamn") + " " +
                        rs.getString("an.efternamn") + "\n------------------------------------------------------");
            }

            System.out.println("Ange siffran på aktiviteten du du vill radera\n---------------------");
            int r_id = inMatning.nextInt();

            try {

                String stSql = "DELETE FROM Aktivitet WHERE r_id = " + r_id + "; ";
                PreparedStatement preparedStatement = (PreparedStatement) DatabasConnection.dbConnection().prepareStatement(stSql);
                preparedStatement.setInt(1, r_id);
                preparedStatement.executeUpdate();
                System.out.println("Aktiviteten " + r_id + " har raderats.");
                
            } catch (SQLException e) {
                System.err.println("Ett fel har inträffat: " + e);

            }

        } catch (SQLException ex) {
            System.err.println("Ett fel har inträffat: " + ex);


        }
    }

public void avsluta() {
        System.out.println("______________________________");
        System.out.println("PROGRAMMET AVSLUTAS OCH SPARAS");
    }
}
