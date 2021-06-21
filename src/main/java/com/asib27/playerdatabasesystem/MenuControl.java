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
    PlayerDataBaseInt dataBase;
    private int exitOp;
    static Scanner sc =  new Scanner(System.in);

    public MenuControl(PlayerDataBaseInt dataBase, String menuName) {
        this.dataBase = dataBase;
        this.menuName = menuName;
    }

    public MenuControl(PlayerDataBaseInt dataBase, String menuName, String[] menuItem) {
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
            
        }while(input != getExitOp());
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
        
        int input = getIntInput(sc);
        while(input <= 0 || input > menuItem.length){
            System.out.println("Input should be within 1-" + menuItem.length);
            System.out.println("Your Input");
            
            input = getIntInput(sc);
        }
        
        sc.nextLine();
        return input;
    }
    
    abstract protected String executeCommand(int input);
    
    protected int getIntInput(Scanner sc){
        while(!sc.hasNextInt()){
            sc.nextLine();
            System.out.println("Please enter a integer value");
        }
        
        return sc.nextInt();
    }
    
    protected double getDoubleInput(Scanner sc){
        while(!sc.hasNextDouble()){
            sc.nextLine();
            System.out.println("Please enter a fractional value");
        }
        return sc.nextDouble();
    }
    
    protected void showResult(String result){
        System.out.println(result);
    }

    public void setMenuItem(String[] menuItem) {
        this.menuItem = menuItem;
        this.exitOp = menuItem.length;
    }
    
    public int getExitOp() {
        return exitOp;
    }

    public void setExitOp(int exitOp) {
        this.exitOp = exitOp;
    }
    
    
}

