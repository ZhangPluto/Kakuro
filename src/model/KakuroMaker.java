package model;

public class KakuroMaker {

    private int[][] Answer;
    private String[][] Game;

    public KakuroMaker(){
        this.Answer = new int[10][10];
        this.Game = new String[10][10];
    }

    private void CrateGameAndAnswer(){
        Game[0][4] = "0.3";
        Game[0][5] = "0.10";
        Game[0][7] = "0.40";
        Game[0][8] = "0.3";
        Game[0][9] = "0.17";

        Game[1][3] = "5.41";
        Game[1][6] = "15.0";

        Game[2][2] = "17.11";
        Game[2][6] = "18.23";

        Game[3][1] = "15.0";
        Game[3][4] = "14.0";

        Game[4][1] = "11.0";
        Game[4][4] = "17.23";

        Game[5][2] = "14.0";
        Game[5][5] = "13.11";
        Game[5][8] = "0.3";

        Game[6][2] = "11.0";
        Game[6][6] = "9.0";

        Game[7][1] = "0.4";
        Game[7][2] = "20.17";
        Game[7][6] = "3.16";

        Game[8][0] = "13.0";
        Game[8][4] = "10.0";

        Game[9][0] = "18.0";
        Game[9][4] = "11.0";

        Answer[1][4] = 1;
        Answer[1][5] = 4;
        Answer[1][7] = 4;
        Answer[1][8] = 2;
        Answer[1][9] = 9;

        Answer[2][3] = 8;
        Answer[2][4] = 2;
        Answer[2][5] = 1;
        Answer[2][7] = 9;
        Answer[2][8] = 1;
        Answer[2][9] = 8;

        Answer[3][2] = 9;
        Answer[3][3] = 6;
        Answer[3][5] = 2;
        Answer[3][6] = 9;
        Answer[3][7] = 3;

        Answer[4][2] = 8;
        Answer[4][3] = 3;
        Answer[4][5] = 3;
        Answer[4][6] = 8;
        Answer[4][7] = 6;

        Answer[5][3] = 5;
        Answer[5][4] = 9;
        Answer[5][6] = 6;
        Answer[5][7] = 7;

        Answer[6][3] = 2;
        Answer[6][4] = 6;
        Answer[6][5] = 3;
        Answer[6][7] = 8;
        Answer[6][8] = 1;

        Answer[7][3] = 7;
        Answer[7][4] = 8;
        Answer[7][5] = 5;
        Answer[7][7] = 1;
        Answer[7][8] = 2;

        Answer[8][1] = 3;
        Answer[8][2] = 9;
        Answer[8][3] = 1;
        Answer[8][5] = 1;
        Answer[8][6] = 7;
        Answer[8][7] = 2;

        Answer[9][1] = 1;
        Answer[9][2] = 8;
        Answer[9][3] = 9;
        Answer[9][5] = 2;
        Answer[9][6] = 9;
    }

    public String[][] getGame(){
        return this.Game;
    }

    public int[][] getAnswer(){
        return this.Answer;
    }
}
