package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName Kakuro
 * @ClassName GamePage
 * @Author renhaozhang
 * @Date 2021-03-28 9:28 p.m.
 * @PackageName view
 **/
public class GamePage {
    private JPanel gamePanel;
    private JButton buttonQuit;
    private JButton buttonBack;

    public static void main(String[] args) {
        JFrame gamePageFrame = new JFrame("GamePage");
        gamePageFrame.setSize(1280,720);
        gamePageFrame.setContentPane(new GamePage().gamePanel);
        gamePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePageFrame.pack();
        gamePageFrame.setVisible(true);
    }
    public GamePage() {
        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.mainPanel.setVisible(true);
                mainPage.mainPanel.setSize(1280,720);
//                gamePageFrame.dispose();
            }
        });
    }



}
