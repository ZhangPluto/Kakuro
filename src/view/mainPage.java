package view;

import javax.swing.*;

/**
 * @ProjectName Kakuro
 * @ClassName mainPage
 * @Author renhaozhang
 * @Date 2021-04-19 8:28 a.m.
 * @PackageName view
 **/
public class mainPage {
    private JPanel mainPanel;
    private JButton buttonLeaderBoard;
    private JButton buttonIntroduction;
    private JButton buttonBegin;

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainPage");
        frame.setContentPane(new mainPage().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
