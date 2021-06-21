/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class AddPlayerMenu extends MenuControl{
    public AddPlayerMenu(PlayerDataBaseInt db) {
        super(db, "Add Player Menu ");
    }

    @Override
    public void run() {
        int input;
        
        showCommand();
        String result = executeCommand(0);
        showResult(result);

    }
    
    

    @Override
    protected String executeCommand(int input) {
        System.out.println("Enter the following inforation of the player: ");
        Player p = new Player();
        String field;
        
        //Name
        System.out.println("Player Name : ");
        field = sc.nextLine().trim();
        if( dataBase.countField(PlayerAttribute.NAME, field) > 0){
            return "Player Name Already Exists, Please choose an unique name";
        }
        p.setName(field);
        
        
        //Club
        System.out.println("Player Club : ");
        field = sc.nextLine().trim();
        if( dataBase.countField(PlayerAttribute.CLUB, field) >= 7){
            return "A club can have maximum 7 field";
        }
        p.setClub(field);
        
        //Country
        System.out.println("Player Country: ");
        field = sc.nextLine().trim();
        p.setCountry(field);
        
        
        //jerssey
        PlayerDataBaseInt clubDataBase = new PlayerDataBase(dataBase.query(PlayerAttribute.CLUB, p.get(PlayerAttribute.CLUB))); 
        int jursey = inputJurseyNumber(clubDataBase, sc);
        p.setJurseyNumber(jursey);

        
        //age
        System.out.println("Player Age: ");
        int age = 0 ;
        while((age = getIntInput(sc)) < 0){
            System.out.println("Age cannot be negative");
        }
        sc.nextLine();
        p.setAge(age);
        
        
        //height
        System.out.println("Player Height: ");
        double height = 0;
        while((height = getDoubleInput(sc)) <= 0){
            System.out.println("Height cannot be negative");
        }
        sc.nextLine();
        p.setHeight(height);
        
        
        //pos
        p.setPosition(inputPosition(sc));
        
        //salary
        System.out.println("Player Salary: ");
        double salary = 0;
        while((salary = getDoubleInput(sc)) < 0){
            System.out.println("Salary cannot be negative");
        }
        sc.nextLine();
        p.setSalary(salary);
        
        
        if(dataBase.addRecord(p))
            return "Adding Player to Database.......\nPlayer Added Successfully";
        else
            return "Sorry Player cannot be added to database.";
    }
    
    private int inputJurseyNumber(PlayerDataBaseInt clubRecord, Scanner sc){
        System.out.println("Player Jursey Number: ");
        int jursey = inputJurseyNumberHelper(sc);
        
        while(clubRecord.countField(PlayerAttribute.JURSEY, jursey) != 0){
            System.out.println("Sorry jursey number already exists ");
            System.out.println("Jursey Number taken : ");
            
            //show already taken jursey number
            clubRecord.counAlltFields(PlayerAttribute.JURSEY).keySet().forEach((var number) -> {
                System.out.print(number + " ");
            });
            System.out.println("\n");
            
            //take input again
            System.out.println("Your input ");
            jursey = inputJurseyNumberHelper(sc);
        }
        
        return jursey;
    }
    
    private int inputJurseyNumberHelper(Scanner sc){
        
        int jursey = 0 ;
        while((jursey = getIntInput(sc)) < 0){
            System.out.println("Jursey cannot be negative");
        }
        sc.nextLine();
        
        return jursey;
    }
    
    private String inputPosition(Scanner sc){
        System.out.println("Player Position: ");
        
        String field = sc.nextLine().trim();
        
        while( ! Player.isValidPosition(field)){
            System.out.println(field + " is not a valid position");
            
            //show valid positions
            System.out.print("Valid positions are: ");
            for (var pos : Player.VALID_POS) {
                System.out.print(pos + " ");
            }
            System.out.println("\nYour input");
            
            field = sc.nextLine();
            field = field.trim();
        }
        
        return field;
    }

    public static void main(String[] args) {
        new AddPlayerMenu(null).run();
    }
}
