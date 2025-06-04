import org.mariadb.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class MenyVal {

    //Connection dbConnection = null; // vår koppling
    Statement statement = null;     // vår SQL-fråga
    ResultSet rs = null;            // fångar upp resultatet som vi får tillbaka.

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
            String strSql = "SELECT förnamn, personlig_budget FROM Användare ORDER BY förnamn ASC";
            statement = getConnection(DatabasConnection ).createStatement();
            rs = statement.executeQuery(strSql);
            while (rs.next()){
                System.out.println(rs.getString("förnamn") + ", " + rs.getInt("personlig_budget"));
            }
        } catch (SQLException ex){
            System.err.println("Ett fel har inträffat: " + ex.toString());
        }

    }


    //lägga till resor
    public void laggTillResa() {
        System.out.println("----------------------");
        System.out.println("----LÄGG TILL RESA----");
        System.out.println("");
    }

    public void redigeraResa() {
        System.out.println("---------------------");
        System.out.println("----REDIGERA RESA----");
        System.out.println("Ange platsnummer för det resmål som du vill redigera:");
    }

    public void raderaResa() {
        System.out.println("---------------------");
        System.out.println("-----RADERA RESA-----");
        System.out.println("Ange platsnummer för det resmål som du vill radera:");
    }





}
