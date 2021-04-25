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
    public JPanel gamePanel;
    private JButton buttonQuit;
    private JButton buttonBack;
    public String timeUsed;


    public String getTimeUsed(){
        return timeUsed;
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
                MainPage.MainFrame.setContentPane(new MainPage().mainPanel);
                MainPage.MainFrame.setTitle("Kakuro");
                MainPage.MainFrame.setSize(170,170);
                MainPage.MainFrame.setVisible(true);
            }
        });
    }



}
