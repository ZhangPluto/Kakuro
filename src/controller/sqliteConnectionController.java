package controller;

import model.sqliteConnection;

/**
 * @ProjectName Kakuro
 * @ClassName sqliteConnectionController
 * @Author renhaozhang
 * @Date 2021-04-24 8:23 p.m.
 * @PackageName controller
 **/
public class sqliteConnectionController {
    public void ConnectionController(){
        sqliteConnection.getConnection();
    }
}
