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

    public static void main(String[] args) {
        leaderBoardFrame.setContentPane(new LeaderBoardPage().leaderBoardPanel);
        leaderBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leaderBoardFrame.pack();
        leaderBoardFrame.setVisible(true);
        leaderBoardFrame.setSize(1280,720);
    }

    public LeaderBoardPage() {
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.mainPanel.setVisible(true);
                mainPage.mainPanel.setSize(1280,720);
                leaderBoardFrame.dispose();
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
