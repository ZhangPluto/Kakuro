package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public static JFrame leaderBoardFrame = new JFrame("LeaderBoardPage");

    public LeaderBoardPage() {
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
