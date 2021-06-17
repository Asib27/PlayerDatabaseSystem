/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author USER
 */

public class Menu extends MenuControl{

    public Menu(PlayerDataBaseInt db) {
        super(db, "Main Menu");
        
        String[] menuI = {"Search Players","Search Clubs","Add Player","Exit System"};
        super.setMenuItem(menuI);
    }

    @Override
    protected String executeCommand(int input) {
        String res = "";
        
        switch(input){
            case 1:
                MenuControl playerSearch = new PlayerSearchingMenu(dataBase);
                playerSearch.run();
                break;
               
            case 2:
                MenuControl clubSerach = new ClubSearchingMenu(dataBase);
                clubSerach.run();
                break;
                
            case 3:
                MenuControl playerAdd = new AddPlayerMenu(dataBase);
                playerAdd.run();
                break;
                
            case 4:
                res = "Thank You";
                break;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        String fileName = "G:\\Java\\PlayerDataBaseSystem\\src\\main\\java\\com\\asib27\\playerdatabasesystem\\players.txt";
        File file =  new File(fileName);
        
        
        PlayerDataBaseInt pdb = new PlayerDataBase(file);
        new Menu(pdb).run();
    }
}

