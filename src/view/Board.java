package view;

import controller.sqliteConnectionController;
import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @ProjectName Kakuro
 * @ClassName Board
 * @Author renhaozhang
 * @Date 2021-04-25 9:35 p.m.
 * @PackageName view
 **/
public class Board extends JFrame{

    private static final long serialVersionUID = 1L;
    EventHandler eh = new EventHandler();
    private JMenuBar menuBar;
    private Cell previousCell;
    private Cell currentSelectedCell;
    private Cell currentCell; // the cursor is above this cell.
    private String buttonPressed;
    private int boardSize;
    private Cell[][] cellMatrix;
    private File file;
    public static String timeUsed;
    public static Connection connection = null;

    StopWatch stopWatch = new StopWatch();


    public void BoardDis() {
        this.dispose();
    }

    public void insert(){
        sqliteConnectionController conn = new sqliteConnectionController();
        connection = conn.ConnectionController();
        try {
            String query = "insert into userInformation (user_name,timeUsed) values (?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,"Player");
            pst.setString(2,Board.timeUsed);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data saved");
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Board(File gameFile) {
        stopWatch.start();
        int theSize = new TextFileHandler(gameFile).getBoardSize();

        setSize(theSize*65, theSize*65);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Kakuro");

        Container c = getContentPane();

        buildMenu();

        c.add(createGamePanel(gameFile));

        setVisible(true);

    }

    public JPanel createGamePanel(File gameFile) {

        JPanel p = new JPanel();

        TextFileHandler tfh = new TextFileHandler(gameFile);

        String[][] board = tfh.getGameLayout();

        boardSize = board[0].length;

        cellMatrix = new Cell[boardSize][boardSize];

        p.setLayout(new GridLayout(board[0].length, board.length));

        Font customFont = new Font("Arial", Font.BOLD, 20);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                cellMatrix[i][j] = new Cell(board[i][j]);

                Cell n = cellMatrix[i][j];

                if(n.getCellType().equals("w") && n.getUserNumber() > 0){
                    //displays it

                    n.displayText(n.getUserNumber()+"");



                }

                n.setRowNumber(i);
                n.setColumnNumber(j);

                n.addMouseListener(eh);
                n.addActionListener(eh);
                n.addKeyListener(eh);

                n.setBorder(new LineBorder(Color.BLACK));
                n.setHorizontalAlignment(JTextField.CENTER);

                n.setFont(customFont);

                p.add(n);
            }

        }

        return p;
    }


    @SuppressWarnings("unchecked")
    public void checkRow(Cell c) {
        int correctRowSum = 0;
        int currentRowSum = 0;

        int rowNum = c.getRowNumber();
        int colNum = c.getColumnNumber();

        ArrayList<Cell> cellList = new ArrayList<Cell>();
        for (int i = colNum; i >= 0; i--) {
            if (cellMatrix[rowNum][i].getCellType().equals("w")) {
                cellList.add(cellMatrix[rowNum][i]);

            }
            if (cellMatrix[rowNum][i].getCellType().equals("b")) {

                cellList.add(0, cellMatrix[rowNum][i]);
                break;
            }

        }
        for (int i = colNum + 1; i < cellMatrix[rowNum].length; i++) {
            if (cellMatrix[rowNum][i].getCellType().equals("w")) {
                cellList.add(cellMatrix[rowNum][i]);

            }
            if (cellMatrix[rowNum][i].getCellType().equals("b")) {
                break;
            }
            if (cellMatrix[rowNum][i].getCellType().equals("e")) {
                break;
            }

        }

        correctRowSum = cellList.get(0).getUpperNumber();

        for (Cell cl : cellList) {

            if (c.getCellType().equals("w")) {
                currentRowSum += cl.getUserNumber();
            }
        }

        if (currentRowSum == correctRowSum && currentRowSum > 0) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setUpperGreen();
            repaint();
        }
        if (currentRowSum != correctRowSum && currentRowSum > 0) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setUpperRed();
            repaint();
        }

        if (hasDuplicates(cellList) && currentRowSum > 0) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setUpperWhite();
            repaint();
        }



    }

    public void checkColumn(Cell c) {


        int correctColSum = 0;
        int currentColSum = 0;

        int rowNum = c.getRowNumber();
        int colNum = c.getColumnNumber();

        ArrayList<Cell> cellList = new ArrayList<Cell>();

        for (int i = rowNum; i >= 0; i--) {
            if (cellMatrix[i][colNum].getCellType().equals("w")) {
                cellList.add(cellMatrix[i][colNum]);

            }
            if (cellMatrix[i][colNum].getCellType().equals("b")) {

                cellList.add(0, cellMatrix[i][colNum]);

                break;
            }

        }

        for (int i = rowNum + 1; i < cellMatrix[rowNum].length; i++) {
            if (cellMatrix[i][colNum].getCellType().equals("w")) {
                cellList.add(cellMatrix[i][colNum]);

            }
            if (cellMatrix[i][colNum].getCellType().equals("b")) {
                break;
            }
            if (cellMatrix[i][colNum].getCellType().equals("e")) {
                break;
            }

        }

        correctColSum = cellList.get(0).getLowerNumber();

        for (Cell cl : cellList) {

            if (c.getCellType().equals("w")) {
                currentColSum += cl.getUserNumber();
            }
        }

        if (currentColSum == correctColSum) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setLowerGreen();
            repaint();
        }
        if (currentColSum != correctColSum && currentColSum > 0) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setLowerRed();
            repaint();
        }

        if (hasDuplicates(cellList)) {
            cellMatrix[cellList.get(0).getRowNumber()][cellList.get(0).getColumnNumber()].setLowerWhite();
            repaint();
        }


    }



    public int doMath(Stack<Integer> s) {

        int sum = 0;

        while (!s.empty()) {

            sum = sum + s.pop();
        }



        return sum;
    }


    public boolean hasDuplicates(ArrayList<Cell> cellList) {
        for (Cell c : cellList) {
            for (Cell c1 : cellList) {
                if (c.getUserNumber() == c1.getUserNumber() && c.getColumnNumber() != c1.getColumnNumber()
                        && c.getRowNumber() != c1.getColumnNumber()) {
                    return true;
                }

                if (c.getUserNumber() == c1.getUserNumber() && c.getRowNumber() != c1.getRowNumber()) {
                    return true;
                }

            }
        }

        return false;

    }
    public boolean stackHasDuplicates(Stack<Integer> s) {


        HashSet<Integer> set = new HashSet<Integer>();
        while (!s.empty()) {

            int num = s.pop();
            System.out.println(num);
            if (num != 0) {

                boolean inserted = set.add(num);
                if (inserted == false)
                    return true;

            }

        }

        return false;
    }


    public void buildMenu() {

        menuBar = new JMenuBar();

        menuBar.setLayout(new FlowLayout());
        String[] menuItems = {"Back"};
        menuBar.add(buildMenuCategory("File", menuItems));

        String[] optionItems = {"Reset" };
        menuBar.add(buildMenuCategory("Options", optionItems));

        menuBar.setLayout(new FlowLayout());

        int buttonWidth = 40;
        int buttonHeight = 20;

        JButton blank = new JButton("");
        blank.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        blank.addActionListener(eh);
        menuBar.add(blank);

        for (int i = 1; i < 10; i++) {
            JButton b = new JButton(String.valueOf(i));
            b.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            b.addActionListener(eh);
            menuBar.add(b);
        }

        setJMenuBar(menuBar);

    }

    public JMenu buildMenuCategory(String name, String[] menuItems) {
        JMenu menu = new JMenu(name);
        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem menuItem = new JMenuItem(menuItems[i]);
            menuItem.addActionListener(eh);
            menu.add(menuItem);
        }
        return menu;
    }

    private class EventHandler implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

        @Override
        public void mouseDragged(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseMoved(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseClicked(MouseEvent arg0) {


            Cell c = (Cell) arg0.getComponent();

            currentSelectedCell = c;

            currentSelectedCell.setBackground(new Color(122,215,229));

            checkRow(c);
            checkColumn(c);

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {

            Cell c = (Cell) arg0.getComponent();

            currentCell = c;

            c.setBackground(new Color(206,227,232));

        }

        @Override
        public void mouseExited(MouseEvent arg0) {

            currentCell.setBackground(Color.WHITE);

        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub

            Cell c = (Cell) arg0.getComponent();
            previousCell = c;

        }

        @Override
        public void actionPerformed(ActionEvent arg0) {

            if (arg0.getActionCommand().equals("Back")) {
                stopWatch.stop();
                timeUsed = stopWatch.toString();
                System.out.println(timeUsed);
                BoardDis();
                MainPage.MainFrame.setContentPane(new LeaderBoardPage().leaderBoardPanel);
                MainPage.MainFrame.setTitle("Leader Board");
                MainPage.MainFrame.setSize(1280,720);
                MainPage.MainFrame.setVisible(true);
                insert();
            }

            if (arg0.getActionCommand().equals("Reset")) {

                for (int v = 0;v < boardSize;v++) {
                    for(int w = 0; w < boardSize; w++) {
                        Cell thisCell = cellMatrix[v][w];

                        if(thisCell.getCellType().equals("w")) {
                            thisCell.displayText("");
                            thisCell.setNumber("0");
                            checkRow(thisCell);
                            checkColumn(thisCell);
                        }



                    }
                }

            }





            buttonPressed = arg0.getActionCommand();

            if(buttonPressed.length()<2) {
                currentSelectedCell.displayText(buttonPressed);
                currentSelectedCell.setNumber(buttonPressed);
            }

            checkRow(currentSelectedCell);
            checkColumn(currentSelectedCell);

        }




        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

            Cell cell = (Cell) e.getComponent();
            char c = e.getKeyChar();
            String cs = String.valueOf(c);
            currentSelectedCell.displayText(cs);
            currentSelectedCell.setNumber(cs);

            checkRow(cell);
            checkColumn(cell);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }

    private void setFile(File gameFile) {
        this.file = gameFile;

    }
}
