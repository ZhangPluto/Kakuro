package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @ProjectName Kakuro
 * @ClassName TextFileHandler
 * @Author renhaozhang
 * @Date 2021-04-25 9:35 p.m.
 * @PackageName view
 **/
public class TextFileHandler {

    private String gameLayout [][];
    private int gridSize;

    public TextFileHandler(File gameFile) {




        try {


            Scanner reader = new Scanner(gameFile);

            String [] sampleLine = reader.next().split(",");

            gridSize = sampleLine.length;

            gameLayout = new String [gridSize] [gridSize];

            for(int i = 0; i < sampleLine.length; i ++) {
                gameLayout[0][i] = sampleLine[i];
            }


            int i = 1;

            while(reader.hasNext()) {

                String line = reader.next();

                String [] lineSegments = line.split(",");

                for (int j = 0; j < gridSize; j++) {

                    gameLayout[i][j] = lineSegments[j];

                }

                i++;
            }

            reader.close();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public String toString() {

        String outPutString = "";

        for (int i = 0 ; i < gridSize; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                outPutString += gameLayout[i][j] + "  ";
            }
            outPutString += "\n";
        }
        return outPutString;
    }

    public int getGridSize() {
        return gridSize;
    }

    public String [][] getGameLayout(){

        return gameLayout;
    }

    public int getBoardSize() {
        return gridSize;
    }



}
