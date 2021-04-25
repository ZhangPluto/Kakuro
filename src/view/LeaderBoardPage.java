package view;

import controller.sqliteConnectionController;
import model.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ProjectName Kakuro
 * @ClassName LeaderBoardPage
 * @Author renhaozhang
 * @Date 2021-03-28 9:27 p.m.
 * @PackageName view
 **/
public class LeaderBoardPage {
    private JButton buttonBack;
    public JPanel leaderBoardPanel;
    private JTable tableLeaderBoard;
    private JButton buttonQuit;
    public static Connection connection = null;

    public void reloadTable(){
        try {
            String query = "select * from userInformation";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            tableLeaderBoard.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
            rs.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public LeaderBoardPage() {
        sqliteConnectionController conn = new sqliteConnectionController();
        connection = conn.ConnectionController();
        reloadTable();
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.MainFrame.setContentPane(new MainPage().mainPanel);
                MainPage.MainFrame.setTitle("Kakuro");
                MainPage.MainFrame.setSize(170,170);
                MainPage.MainFrame.setVisible(true);
            }
        });
        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
