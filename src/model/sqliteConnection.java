package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ProjectName Kakuro
 * @ClassName sqliteConnection
 * @Author jinyangliu
 * @Date 2021-04-19 10:36
 * @PackageName model
 **/
public class sqliteConnection {
    public static void getConnection() {

        Connection conn = null;
        try {

            String url = "jdbc:sqlite:Kakuro.sqlite";

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established! ");

        } catch (SQLException e) {
            System.out.println((e.getMessage()));
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    ;
                }
            } catch (SQLException ex) {
                System.out.println((ex.getMessage()));
            }
        }
    }
}