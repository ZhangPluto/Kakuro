package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName Kakuro
 * @ClassName MainPage
 * @Author renhaozhang
 * @Date 2021-04-19 8:28 a.m.
 * @PackageName view
 **/
public class MainPage {
    public JPanel mainPanel;
    private JButton buttonLeaderBoard;
    private JButton buttonIntroduction;
    private JButton buttonBegin;
    private JButton buttonQuit;



    public static void main(String[] args) {
        JFrame MainFrame = new JFrame("MainPage");
        MainFrame.setUndecorated(true);
        MainFrame.setContentPane(new MainPage().mainPanel);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainFrame.setResizable(false);
        MainFrame.pack();
        MainFrame.setVisible(true);
        MainFrame.setLocationRelativeTo(MainFrame.getMostRecentFocusOwner());
        MainFrame.setSize(1280,720);
    }



    public MainPage() {
        buttonBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePage gamePage = new GamePage();
//                GamePage.gamePageFrame.setVisible(true);
//                MainFrame.dispose();

            }
        });
        buttonIntroduction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpPage.helpFrame.setUndecorated(true);
                HelpPage.helpFrame.setVisible(true);
                HelpPage.helpFrame.setSize(1280,720);

            }
        });
        buttonLeaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                LeaderBoardPage.leaderBoardFrame.setUndecorated(true);
                LeaderBoardPage.leaderBoardFrame.setVisible(true);
//                LeaderBoardPage.leaderBoardFrame.setSize(1280,720);
//                MainFrame.dispose();
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
