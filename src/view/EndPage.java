package view;

import controller.sqliteConnectionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ProjectName Kakuro
 * @ClassName EndPage
 * @Author renhaozhang
 * @Date 2021-03-28 9:29 p.m.
 * @PackageName view
 **/
public class EndPage {
    private JPanel endPagePanel;
    private JLabel timeLabel;
    private JTextField textFieldId;
    private JButton buttonOk;
    public static Connection connection = null;


    public static void main(String[] args) {
        JFrame frame = new JFrame("EndPage");
        frame.setContentPane(new EndPage().endPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.pack();
        frame.setVisible(true);
    }

    public EndPage() {
        sqliteConnectionController conn = new sqliteConnectionController();
        GamePage gamePage = new GamePage();
        connection = conn.ConnectionController();
        timeLabel.setText(gamePage.getTimeUsed());
        try {
            String query = "insert into userInformation (user_name,timeUsed) values (?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,textFieldId.getText());
            pst.setString(2,gamePage.getTimeUsed());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data saved");
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
