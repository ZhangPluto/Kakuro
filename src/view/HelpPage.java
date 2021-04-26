package view;

import javax.swing.*;
import java.awt.*;
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
    private JLabel infoLabel1;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JLabel infoLabel4;
    private JLabel infoLabel5;
    private static final JFrame helpPage = new JFrame();

    public HelpPage() {

        infoLabel1.setText("Kakuro puzzles are similar with crosswords, but instead of letters the board is filled with digits (from 1 to 9).");
        infoLabel2.setText("The board's squares need to be filled in with these digits in order to sum up to the specified numbers.");
        infoLabel5.setText("You are not allowed to use the same digit more than once to obtain a given sum.");
        infoLabel3.setText("Each Kakuro puzzle has an unique solution.");
        infoLabel4.setText("You need to double click the white block then click the number button.\n Good Luck!");
        helpPanel.setBackground(Color.black);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.MainFrame.setContentPane(new MainPage().mainPanel);
                MainPage.MainFrame.setTitle("Kakuro");
                MainPage.MainFrame.setSize(170,170);
                MainPage.MainFrame.setVisible(true);
            }
        });
    }
}
