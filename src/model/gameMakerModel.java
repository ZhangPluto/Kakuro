package model;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ProjectName Kakuro
 * @ClassName gameMakerModel
 * @Author renhaozhang
 * @Date 2021-03-28 9:36 p.m.
 * @PackageName model
 **/
public class gameMakerModel {

    private KakuroMaker game;

    private void newGame(){
        game = new KakuroMaker();

        String[][] gameArr = game.getGame();
        int[][] answerArr = game.getAnswer();

        for(int i=0; i<9; ++i){
            for(int j=0; j<9 ;++j){
                if(gameArr[i][j]!= null){

                }else{

                }
            }
        }

    }

    public boolean checkAllright(){
        for(int i=0; i<9; ++i){
            for(int j=0; j<9; ++j){
                //if current nubmer field equal game.getAnswer corresponding field game.getAnswer()[i][j] {
                //return false;
                //}
            }
        }
        return true;
    }

    public static String getFormattime(int count) {
        String second = null;
        String minute = null;
        if(count / 60 < 10){
            minute = "0" + (count / 60);
        }else{
            minute = "" + (count / 60);
        }
        if(count % 60 < 10){
            second = ":0" + count % 60;
        }else{
            second = ":" + count % 60;
        }

        return minute + second;
    }



//    Timer timer;
//    TimerTask task;
//    timer = new Timer();
//    task = new TimerTask() {
//        int count = 1;
//        @Override public void run() {
//            timeLabel.setText(getFormattime(count));
//            count++;
//        }
//    };
//    timer.schedule(task, 1000L, 1000L); // 开始游戏时自动计时

//重新计时
//    public void restartTimer() {
//        timer.cancel();
//        timeLabel.setText("00:00");
//        textArea.setVisible(false);
//        timer = new Timer();
//        task = new TimerTask() {
//            int count = 1;
//            @Override void run() {
//                timeLabel.setText(getFormattime(count));
//                count++;
//            }
//        };
//        timer.schedule(task, 1000L, 1000L);
//    }



}
