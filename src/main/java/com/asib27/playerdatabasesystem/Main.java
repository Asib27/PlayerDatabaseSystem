/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.io.File;

/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        String inputfileName = "G:\\Java\\PlayerDataBaseSystem\\src\\main\\java\\com\\asib27\\playerdatabasesystem\\playersData.txt";
        File inFile =  new File(inputfileName);
        
        PlayerDataBase myDataBase = new PlayerDataBase(inFile);
        Menu myMenu = new Menu(myDataBase);
        myMenu.run();
        
        String outputFileName = "G:\\Java\\PlayerDataBaseSystem\\src\\main\\java\\com\\asib27\\playerdatabasesystem\\playersData.txt";
        File outFile = new File(outputFileName);
        myDataBase.writeTofile(outFile);
    }
}
