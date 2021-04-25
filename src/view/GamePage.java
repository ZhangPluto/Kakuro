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

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 0, 1100, 1000);
    contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(null);
    setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

    JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.WHITE);
		contentPane.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new GridLayout(9, 9, 0, 0));

    JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(green);
		bottomPanel.setPreferredSize(new Dimension(1333, 100));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(0, 10, 0, 0));

    JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(230, 230, 230));
		rightPanel.setPreferredSize(new Dimension(200, 789));
		contentPane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(null);

    timeLabel = new JLabel("00:00");
		timeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		timeLabel.setBounds(60, 485, 90, 50);
		rightPanel.add(timeLabel);

    lastButton = new JButton("\u4E0A\u4E00\u5173");
		lastButton.setEnabled(false);
		lastButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lastButton.setBackground(lightGray);
		lastButton.setForeground(Color.BLACK);
		lastButton.setFocusPainted(false);
		lastButton.setBounds(47, 150, 112, 50);
		lastButton.addActionListener(new FunctionActionListener());
		rightPanel.add(lastButton);

    answerButton = new JButton("\u7B54\u6848");
		answerButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		answerButton.setBackground(lightGray);
		answerButton.setForeground(Color.BLACK);
		answerButton.setFocusPainted(false);
		answerButton.setBounds(47, 420, 112, 50);
		answerButton.addActionListener(new FunctionActionListener());
		rightPanel.add(answerButton);

    nextButton = new JButton("\u4E0B\u4E00\u5173");
		nextButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		nextButton.setBackground(lightGray);
		nextButton.setForeground(Color.BLACK);
		nextButton.setFocusPainted(false);
		nextButton.setBounds(47, 222, 112, 50);
		nextButton.addActionListener(new FunctionActionListener());
		rightPanel.add(nextButton);

    changeButton = new JButton("\u6362\u4E00\u9898");
		changeButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		changeButton.setBackground(lightGray);
		changeButton.setForeground(Color.BLACK);
		changeButton.setFocusPainted(false);
		changeButton.setBounds(47, 287, 112, 50);
		changeButton.addActionListener(new FunctionActionListener());
		rightPanel.add(changeButton);

    diffLabel = new JLabel("\u96BE\u5EA6:1");
		diffLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		diffLabel.setBounds(60, 64, 90, 50);
		rightPanel.add(diffLabel);

    resetButton = new JButton("\u91CD\u73A9");
		resetButton.setForeground(Color.BLACK);
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		resetButton.setFocusPainted(false);
		resetButton.setBackground(new Color(217, 217, 217));
		resetButton.setBounds(47, 355, 112, 50);
		resetButton.addActionListener(new FunctionActionListener());
		rightPanel.add(resetButton);

    textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setVisible(false);
		textArea.setText("\u606D\u559C,\u5168\u90E8\u7B54\u5BF9!");
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		textArea.setBounds(15, 564, 170, 146);
		rightPanel.add(textArea);

    Border centernBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
    Border rightAndBottomBorder = BorderFactory.createMatteBorder(1, 1, 4, 4, Color.GRAY);
    Border bottomBorder = BorderFactory.createMatteBorder(1, 1, 4, 1, Color.GRAY);
    Border rightBorder = BorderFactory.createMatteBorder(1, 1, 1, 4, Color.GRAY);

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
