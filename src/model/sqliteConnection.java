package model;
import javax.swing.*;
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
    public static Connection dbConnector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Kakuro.sqlite");
            return conn;

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}