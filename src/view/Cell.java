package view;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JTextField;

/**
 * @ProjectName Kakuro
 * @ClassName Cell
 * @Author renhaozhang
 * @Date 2021-04-25 9:35 p.m.
 * @PackageName view
 **/
public class Cell extends JTextField {

    private static final long serialVersionUID = 1L;
    private static int width = 100, height = 100;
    private String cellType;

    private String upperNum;
    private String lowerNum;

    private String upperOriginal;
    private String lowerOriginal;

    private int numberEntered = 0;

    private int rowNumber;
    private int columnNumber;

    private String buttonLabel;

    private Color upperColor = Color.WHITE;
    private Color lowerColor = Color.WHITE;



    public Cell(String label) {


        String c = label.charAt(0) + "";

        String upper = label.substring(1,3) + "";
        String lower = label.substring(3,5) + "";


        if(!upper.equals("00") && c.equals("w")) {
            numberEntered = Integer.parseInt(upper.charAt(1)+"");
        }


        if(c.equals("b")) {
            upperOriginal = upper;
            lowerOriginal = lower;

        }


        if (upper.equals("00")) {
            upperNum = "0";

        } else {


            if(upper.startsWith("0")) {

                upperNum = upper.charAt(1) + "";


            } else upperNum = upper;

        }




        if (lower.equals("00")) {
            lowerNum = "0";
        } else {

            if (lower.startsWith("0")) {

                lowerNum = lower.charAt(1) + "";

            } else lowerNum = lower;
        }






        //setText(c);
        setCellType(c);

        setPreferredSize(new Dimension(width, height));




    }


    public String toString() {

        String body;
        String tail;

        if(cellType.equals("w")) {

            if(numberEntered == 0) {

                body = "00";
            } else body = "0"+numberEntered;

            tail = "00";

        } else if (cellType.equals("e")) {
            body = "00";
            tail = "00";

        }else {

            body = upperOriginal;
            tail = lowerOriginal;
        }


        return cellType + body + tail;
    }


    public void setNumber(String s) {

        if (s == "") {

            numberEntered = 0;
        } else {

            numberEntered = Integer.parseInt(s);
        }
    }

    public void paint(Graphics g) {


        super.paint(g);
        if(this.getCellType().equals("b")) {


            if(upperNum.equals("0")) {

                g.setColor(Color.WHITE);

                g.drawLine(0, 0, width, height);

                g.setColor(Color.BLACK);

                g.drawString(upperNum, 34, 25);

                g.setColor(lowerColor);
                g.drawString(lowerNum, 10, 50);

                this.setBackground(Color.BLACK);
                this.setOpaque(true);

            } else if (lowerNum.equals("0")) {


                g.setColor(Color.WHITE);

                g.drawLine(0, 0, width, height);

                g.setColor(upperColor);

                g.drawString(upperNum, 34, 25);

                g.setColor(Color.BLACK);
                g.drawString(lowerNum, 10, 50);
                this.setBackground(Color.BLACK);
                this.setOpaque(true);
            }

            else {


                g.setColor(Color.WHITE);
                g.drawLine(0, 0, width, height);

                g.setColor(upperColor);

                g.drawString(upperNum, 34, 25);

                g.setColor(lowerColor);
                g.drawString(lowerNum, 10, 50);

                this.setBackground(Color.BLACK);
                this.setOpaque(true);

            }



        }




        if(this.getCellType().equals("e")) {
            this.setBackground(Color.BLACK);
            this.setOpaque(true);


        }


        this.setEditable(false);

        this.setOpaque(true);

    }

    public int getUserNumber() {


        return numberEntered;
    }

    public void numberEntered(int n) {

        numberEntered = n;
    }

    public int getUpperNumber() {

        return Integer.parseInt(upperNum);
    }

    public int getLowerNumber() {


        return Integer.parseInt(lowerNum);
    }

    public String getCellType() {

        return cellType;
    }

    public void setCellType(String t) {
        this.cellType = t;
    }

    public boolean isWhiteCell() {

        if(this.cellType.equals("w")) {
            return true;
        } return false;
    }

    public void displayText(String numberEntered) {

        buttonLabel = numberEntered;

        if(cellType.equals("w")) this.setText(buttonLabel);
    }

    public void setRowNumber(int row) {
        rowNumber = row;

    }

    public void setColumnNumber(int col) {

        columnNumber = col;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setUpperRed() {
        upperColor = Color.RED;
    }

    public void setUpperGreen() {

        upperColor = Color.GREEN;
    }

    public void setUpperWhite() {
        upperColor = Color.WHITE;
    }

    public void setLowerRed() {

        lowerColor = Color.RED;
    }

    public void setLowerGreen() {
        lowerColor = Color.GREEN;
    }

    public void setLowerWhite() {

        lowerColor = Color.WHITE;
    }

    public void setButtonLabel(String buttonLabel) {

        this.buttonLabel = buttonLabel;
    }

}
