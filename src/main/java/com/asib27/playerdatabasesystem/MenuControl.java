/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

/**
 *
 * @author USER
 */
import java.util.Scanner;

/**
 *
 * @author USER
 */
abstract class MenuControl{
    private String menuName;
    private String[] menuItem;
    PlayerDataBase dataBase;
    private int exitOp;
    static Scanner sc =  new Scanner(System.in);

    public MenuControl(PlayerDataBase dataBase, String menuName) {
        this.dataBase = dataBase;
        this.menuName = menuName;
    }

    public MenuControl(PlayerDataBase dataBase, String menuName, String[] menuItem) {
        this.menuName = menuName;
        this.menuItem = menuItem;
        this.dataBase = dataBase;
        exitOp = menuItem.length;
    }
    
    
    public void run(){
        int input;
        
        do{
            showCommand();
            input = takeInput();
            String result = executeCommand(input);
            showResult(result);

        }while(input != menuItem.length);
    }
    
    protected void showCommand(){
        if(menuName != null)
            System.out.println(menuName);
        else
            System.out.println("No menu name specified");
        
        if(menuItem != null){
            for (int i = 0; i < menuItem.length; i++) {
                String string = menuItem[i];
                System.out.printf("(%d) %s", i+1, string);
                System.out.println("");
            }
        }
    }
    
    protected int takeInput(){
        System.out.println("Your input");
        //System.out.flush();
        
        int input = sc.nextInt();
        while(input <= 0 || input > menuItem.length){
            System.out.println("Input should be within 1-" + menuItem.length);
            System.out.println("Your Input");
            //System.out.flush();
            
            input = sc.nextInt();
        }
        
        sc.skip("\n");
        return input;
    }
    
    abstract protected String executeCommand(int input);
    protected void showResult(String result){
        System.out.println(result);
    }

    public void setMenuItem(String[] menuItem) {
        this.menuItem = menuItem;
    }
    
    public int getExitOp() {
        return exitOp;
    }

    public void setExitOp(int exitOp) {
        this.exitOp = exitOp;
    }
    
    
}

