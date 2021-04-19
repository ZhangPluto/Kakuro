

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JTextArea;

public class Player extends JFrame {

	/**
	 * 数独游戏
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea; // 答对提示文本框
	private JTextField[][] numberFields;
	private JButton[] setNumButtons;
	JButton lastButton; // 上一关
	JButton nextButton; // 下一关
	JButton changeButton; // 换一题
	JButton answerButton; // 显示答案
	JButton resetButton; // 重新开始
	JLabel diffLabel; // 难度标签
	JLabel timeLabel; // 时间标签

	private Maker gameMaker;// 数独

	Color green = new Color(93, 200, 138);
	Color pink = new Color(255, 123, 168);
	Color blue = new Color(102, 183, 255);
	Color red = new Color(211, 48, 48);
	Color lightGray = new Color(217, 217, 217);

	int[][] sudokuArray;// 成型的数独数组
	int selectX = 10; // 上锁
	int selectY = 10;
	int grade = 1;// 难度等级

	Timer timer;
	TimerTask task;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//					UIManager.put("RootPane.setupButtonVisible", false);
					SudokuPlayer frame = new SudokuPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SudokuPlayer() {
		setTitle("\u6570\u72EC\u6E38\u620F");
		setResizable(false);

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

		numberFields = new JTextField[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				numberFields[i][j] = new JTextField(Integer.toString(i) + "  " + Integer.toString(j));
				numberFields[i][j].setEditable(false);
				numberFields[i][j].setFont(new Font("微软雅黑", Font.BOLD, 35));
				numberFields[i][j].setHorizontalAlignment(JTextField.CENTER);
				numberFields[i][j].setBackground(Color.WHITE);
				numberFields[i][j].setForeground(Color.BLACK);
				if (i == 2 && j == 2 || i == 2 && j == 5 || i == 5 && j == 2 || i == 5 && j == 5) {
					numberFields[i][j].setBorder(rightAndBottomBorder);
				} else if (j == 2 || j == 5) {
					numberFields[i][j].setBorder(rightBorder);
				} else if (i == 2 || i == 5) {
					numberFields[i][j].setBorder(bottomBorder);
				} else {
					numberFields[i][j].setBorder(centernBorder);
				}

				numberFields[i][j].addFocusListener(new TextFocusHandle());
				numberFields[i][j].setName(Integer.toString(i) + Integer.toString(j));
				bodyPanel.add(numberFields[i][j]);
			}
		}
		newGame(grade);

		setNumButtons = new JButton[10];
		for (int i = 0; i < 10; i++) {

			if (i == 9) {
				setNumButtons[i] = new JButton("清除");
				setNumButtons[i].addActionListener(new ClearNumberActionListener());
			} else {
				setNumButtons[i] = new JButton(Integer.toString(i + 1));
				setNumButtons[i].addActionListener(new PutNumberActionListener());
			}
			setNumButtons[i].setFont(new Font("微软雅黑", Font.PLAIN, 35));
			setNumButtons[i].setBackground(green);
			setNumButtons[i].setForeground(Color.WHITE);
			setNumButtons[i].setFocusPainted(false);

			bottomPanel.add(setNumButtons[i]);
		}

		timer = new Timer();
		task = new TimerTask() {
			int count = 1;

			@Override
			public void run() {

				timeLabel.setText(getFormattime(count));
				count++;
			}
		};
		timer.schedule(task, 1000L, 1000L); // 开始游戏时自动计时

	}

	/**
	 * 开始新游戏
	 */
	private void newGame(int grade) {

		gameMaker = new SudokuMaker(grade);
		sudokuArray = gameMaker.getArr();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				numberFields[i][j].setBackground(Color.WHITE);
				numberFields[i][j].setForeground(Color.BLACK);
				if (sudokuArray[i][j] != 0) {
					numberFields[i][j].setText(Integer.toString(sudokuArray[i][j]));
					numberFields[i][j].setBackground(lightGray);
					// mouseListen[i][j].setUse(false);
				} else {
					numberFields[i][j].setText(null);
					numberFields[i][j].setBackground(Color.WHITE);
					numberFields[i][j].setForeground(Color.BLUE);
					// mouseListen[i][j].setUse(true);
				}
			}
		}
	}

	/**
	 * 将时间格式化输出例如将120秒转换为02:00的字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String getFormattime(int count) {
		String second = null;
		String minute = null;
		if (count / 60 < 10) {
			minute = "0" + (count / 60);
		} else {
			minute = "" + (count / 60);
		}
		if (count % 60 < 10) {
			second = ":0" + count % 60;
		} else {
			second = ":" + count % 60;
		}
		return minute + second;

	}

	/**
	 * 重新计时
	 */
	public void restartTimer() {
		timer.cancel();
		timeLabel.setText("00:00");
		textArea.setVisible(false);
		timer = new Timer();
		task = new TimerTask() {
			int count = 1;

			@Override
			public void run() {

				timeLabel.setText(getFormattime(count));
				count++;
			}
		};
		timer.schedule(task, 1000L, 1000L);
	}

	/**
	 * 检测是否填对所有数字
	 */
	public boolean chickAllright() {

		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {

				if (!numberFields[i][j].getText().equals(Integer.toString(gameMaker.getAnswer()[i][j]))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 检查填数是否符合正确格式
	 */
	public void wrrongNumber() {

		for (int i = (selectX / 3) * 3; i < (selectX / 3 + 1) * 3; ++i) {
			for (int j = (selectY / 3) * 3; j < (selectY / 3 + 1) * 3; ++j) {
				if (numberFields[selectX][selectY].getText().equals(numberFields[i][j].getText()) && selectX != i
						&& selectY != j) {
					numberFields[selectX][selectY].setBackground(red);
					numberFields[i][j].setBackground(red);
				}
			}
		}

		for (int i = 0; i < 9; ++i) {
			if (numberFields[selectX][selectY].getText().equals(numberFields[selectX][i].getText()) && selectY != i) {
				numberFields[selectX][selectY].setBackground(red);
				numberFields[selectX][i].setBackground(red);

			}
			if (numberFields[selectX][selectY].getText().equals(numberFields[i][selectY].getText()) && selectX != i) {
				numberFields[selectX][selectY].setBackground(red);
				numberFields[i][selectY].setBackground(red);
			}
		}
	}
	/**
	 * 回复初始颜色
	 */
	public void reAllColor() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (sudokuArray[i][j] == 0) {
					numberFields[i][j].setBackground(Color.WHITE);
				} else {
					numberFields[i][j].setBackground(lightGray);
				}
			}
		}
	}

	/**
	 * 检查全部方格,有相同的就错误提示
	 */
	public void chickAllWrrongNumber() {

		
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {

				for (int row1 = (i / 3) * 3; row1 < (i / 3 + 1) * 3; ++row1) {
					for (int colum1 = (j / 3) * 3; colum1 < (j / 3 + 1) * 3; ++colum1) {
						if (numberFields[row1][colum1].getText().equals(numberFields[i][j].getText()) && row1 != i
								&& colum1 != j && !numberFields[i][j].getText().equals("")) {
							numberFields[row1][colum1].setBackground(red);
							numberFields[i][j].setBackground(red);
						}
					}
				}
				for (int i1 = 0; i1 < 9; ++i1) {
					if (numberFields[i][j].getText().equals(numberFields[i][i1].getText()) && j != i1
							&& !numberFields[i][j].getText().equals("")) {
						numberFields[i][j].setBackground(red);
						numberFields[i][i1].setBackground(red);

					}
					if (numberFields[i][j].getText().equals(numberFields[i1][j].getText()) && i != i1
							&& !numberFields[i][j].getText().equals("")) {
						numberFields[i][j].setBackground(red);
						numberFields[i1][j].setBackground(red);
					}
				}
			}
		}
	}

	/**
	 * 文本框焦点失去、获取事件
	 * 
	 * @author Jack
	 *
	 */
	class TextFocusHandle implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {// 获得焦点

			JTextField field = (JTextField) e.getSource();
			sameNumber(field, numberFields);

		}

		@Override
		public void focusLost(FocusEvent e) {// 失去焦点

			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j) {

					numberFields[i][j].setForeground(Color.BLACK);
					if (sudokuArray[i][j] != 0) {
						numberFields[i][j].setBackground(lightGray);
					} else {
						numberFields[i][j].setBackground(Color.WHITE);
						numberFields[i][j].setForeground(Color.BLUE);
					}

					for (int row = (i / 3) * 3; row < (i / 3 + 1) * 3; ++row) {
						for (int colum = (j / 3) * 3; colum < (j / 3 + 1) * 3; ++colum) {
							if (numberFields[row][colum].getText().equals(numberFields[i][j].getText()) && row != i
									&& colum != j && !numberFields[i][j].getText().equals("")) {
								numberFields[row][colum].setBackground(red);
								numberFields[i][j].setBackground(red);
							}
						}
					}
				}
			}

		}

		/**
		 * 对数据进行上锁解锁,并设置所选单元格所在的九宫格与行列颜色
		 * 
		 * @param field
		 * @param fields
		 */
		public void sameNumber(JTextField field, JTextField[][] fields) {

			String name = field.getName();
			int row = Integer.parseInt(name.substring(0, 1));
			int colum = Integer.parseInt(name.substring(1));
			String number = field.getText();

			if (sudokuArray[row][colum] != 0) {
				selectX = 10; // 预置数上锁
				selectY = 10;
			} else {
				selectX = row; // 挖空的方格可以解锁
				selectY = colum;
			}

			if (!number.equals("")) { // 所选方格不为空
				for (int i = 0; i < 9; ++i) {
					for (int j = 0; j < 9; ++j) {
						if (fields[i][j].getText().equals(number)) {
							fields[i][j].setBackground(pink);
						}
					}
				}
				chickAllWrrongNumber();

			} else {
				for (int i = 0; i < 9; i++) {
					numberFields[row][i].setBackground(blue);
					numberFields[row][i].setForeground(Color.WHITE);
					numberFields[i][colum].setBackground(blue);
					numberFields[i][colum].setForeground(Color.WHITE);
				}

				for (int i = (row / 3) * 3; i < (row / 3 + 1) * 3; ++i) {
					for (int j = (colum / 3) * 3; j < (colum / 3 + 1) * 3; ++j) {
						numberFields[i][j].setBackground(blue);
						numberFields[i][j].setForeground(Color.WHITE);
					}
				}
				field.setBackground(green);
				field.setForeground(Color.WHITE);

				chickAllWrrongNumber();

			}

		}

	}

	/**
	 * 填数按钮事件监听器
	 * 
	 * @author Jack
	 *
	 */
	class PutNumberActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton numButton = (JButton) e.getSource();
			
			reAllColor();
			if (selectX != 10 && selectY != 10) {
				numberFields[selectX][selectY].setText(numButton.getText());
				// wrrongNumber();
				chickAllWrrongNumber();
				if (chickAllright()) {// 如果全部填对
					timer.cancel();
					textArea.setVisible(true);
				} else {
					textArea.setVisible(false);
				}
			}

			selectX = 10; // 预置数上锁
			selectY = 10;
		}

	}

	/**
	 * 清除填数按钮事件
	 * 
	 * @author Jack
	 *
	 */
	class ClearNumberActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reAllColor();
			if (selectX != 10 && selectY != 10) {
				numberFields[selectX][selectY].setText(null);
				chickAllWrrongNumber();
			}

			selectX = 10; // 预置数上锁
			selectY = 10;
		}

	}

	/**
	 * 功能按钮事件监听器
	 * 
	 * @author Jack
	 *
	 */
	class FunctionActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == changeButton) {
				newGame(grade);
				restartTimer();
			}
			if (e.getSource() == lastButton) {
				if (grade >= 1) {
					grade--;
					newGame(grade);
					diffLabel.setText("难度:" + grade);
					nextButton.setEnabled(true);
					if (grade == 1) {
						lastButton.setEnabled(false);
					}
					restartTimer();
				}
			}
			if (e.getSource() == nextButton) {
				if (grade <= 8) {
					grade++;
					newGame(grade);
					diffLabel.setText("难度:" + grade);
					lastButton.setEnabled(true);
					if (grade == 8) {
						nextButton.setEnabled(false);
					}
					restartTimer();
				}
			}
			if (e.getSource() == resetButton) {
				for (int i = 0; i < 9; ++i) {
					for (int j = 0; j < 9; ++j) {
						numberFields[i][j].setBackground(Color.WHITE);
						numberFields[i][j].setForeground(Color.BLACK);
						if (sudokuArray[i][j] != 0) {
							numberFields[i][j].setText(Integer.toString(sudokuArray[i][j]));
							numberFields[i][j].setBackground(lightGray);
						} else {
							numberFields[i][j].setText(null);
							numberFields[i][j].setBackground(Color.WHITE);
							numberFields[i][j].setForeground(Color.BLUE);
						}
					}
				}
				restartTimer();
			}
			if (e.getSource() == answerButton) {

				int[][] answer = gameMaker.getAnswer();
				for (int i = 0; i < 9; ++i) {
					for (int j = 0; j < 9; ++j) {
						numberFields[i][j].setBackground(Color.WHITE);
						numberFields[i][j].setForeground(Color.BLACK);
						numberFields[i][j].setText(Integer.toString(answer[i][j]));
						if (sudokuArray[i][j] != 0) {
							numberFields[i][j].setBackground(lightGray);
						} else {
							numberFields[i][j].setBackground(Color.WHITE);
							numberFields[i][j].setForeground(Color.BLUE);
						}

					}
				}
				timer.cancel();
			}

		}

	}
}

