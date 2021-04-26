package view;

import javax.swing.*;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("EndPage");
        frame.setContentPane(new EndPage().endPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
