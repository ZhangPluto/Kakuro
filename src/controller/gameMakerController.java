package controller;

import model.gameMakerModel;

/**
 * @ProjectName Kakuro
 * @ClassName gameMakerController
 * @Author renhaozhang
 * @Date 2021-03-28 9:37 p.m.
 * @PackageName controller
 **/
public class gameMakerController {
    public String getFormattime(int count){
        gameMakerModel.getFormattime(count);
    }
}
