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
    public static final JFrame MainFrame = new JFrame("Kakuro");

    public static void main(String[] args) {
        MainFrame.setContentPane(new MainPage().mainPanel);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setResizable(false);
        MainFrame.pack();
        MainFrame.setBackground(Color.GRAY);
        MainFrame.setSize(170,170);
        MainFrame.setVisible(true);
    }



    public MainPage() {
        buttonBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setTitle("Game Page");
                MainFrame.setContentPane(new GamePage().gamePanel);
                MainFrame.setSize(1280,720);
                MainFrame.setVisible(true);
                MainFrame.setResizable(false);
            }
        });
        buttonIntroduction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setTitle("Help!");
                MainFrame.setContentPane(new HelpPage().helpPanel);
                MainFrame.setSize(1280,720);
                MainFrame.setVisible(true);
                MainFrame.setResizable(false);
            }
        });
        buttonLeaderBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.setTitle("Leader Board");
                MainFrame.setContentPane(new LeaderBoardPage().leaderBoardPanel);
                MainFrame.setSize(1280,720);
                MainFrame.setVisible(true);
                MainFrame.setResizable(false);
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
