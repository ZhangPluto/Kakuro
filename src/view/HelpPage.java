package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName Kakuro
 * @ClassName HelpPage
 * @Author renhaozhang
 * @Date 2021-03-28 9:28 p.m.
 * @PackageName view
 **/
public class HelpPage {
    public JPanel helpPanel;
    private JButton buttonBack;
    private JLabel infoLabel;
    private JButton buttonQuit;
    public static JFrame helpFrame = new JFrame("HelpPage");

    public static void main(String[] args) {
        helpFrame.setUndecorated(true);
        helpFrame.setContentPane(new HelpPage().helpPanel);
        helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helpFrame.pack();
        helpFrame.setVisible(true);
        helpFrame.setSize(1280,720);
    }

    public HelpPage() {
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.mainPanel.setVisible(true);
                mainPage.mainPanel.setSize(1280,720);
                helpFrame.dispose();
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
