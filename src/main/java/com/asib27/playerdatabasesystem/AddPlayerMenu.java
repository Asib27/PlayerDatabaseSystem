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
public class AddPlayerMenu extends MenuControl{
    public AddPlayerMenu(PlayerDataBaseInt db) {
        super(db, "Add Player Menu ");
        //String[] menuItem = {"Add More Player", "Return to Main Menu"};
        //super.setMenuItem(menuItem);
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
        field = sc.nextLine();
        if( dataBase.countField(PlayerAttribute.NAME, field) > 0){
            return "Player Name Already Exists, Please choose an unique name";
        }
        p.setName(field);
        
        
        //Club
        System.out.println("Player Club : ");
        field = sc.nextLine();
        if( dataBase.countField(PlayerAttribute.CLUB, field) >= 7){
            return "A club can have maximum 7 field";
        }
        p.setClub(field);
        
        //Country
        System.out.println("Player Country: ");
        field = sc.nextLine();
        p.setCountry(field);
        
        
        //age
        int age = -1;
        System.out.println("Player Age: ");
        field = sc.nextLine();
        while(age < 0){
            try{
                age = Integer.parseInt(field);
                if(age < 0){
                    System.out.println("Age cannot be negative...");
                    System.out.println("Please input again ");
                    field = sc.nextLine();
                }
            }catch(NumberFormatException ex){
                System.out.println("Please enter a valid number : ");
                System.out.println("Please input again ");
                field = sc.nextLine();
            }
            
        }
        p.setAge(age);
        
        
        //height
        double height = -1;
        System.out.println("Player Height: ");
        field = sc.nextLine();
        while(height < 0){
            try{
                height = Double.parseDouble(field);
                if(height < 0){
                    System.out.println("Height cannot be negative...");
                    System.out.println("Please input again ");
                    field = sc.nextLine();
                }
            }catch(NumberFormatException ex){
                System.out.println("Please enter a  number......");
                System.out.println("Please input again ");
                field = sc.nextLine();
            }
            
        }
        p.setHeight(height);
        
        
        //pos
        System.out.println("Player Position: ");
        field = sc.nextLine();
        p.setPosition(field);
        
        
        //jerssey
        int JurseyNumber = -1;
        System.out.println("Player JurseyNumber: ");
        field = sc.nextLine();
        while(JurseyNumber < 0){
            try{
                JurseyNumber = Integer.parseInt(field);
                if(JurseyNumber < 0){
                    System.out.println("JurseyNumber cannot be negative...");
                    System.out.println("Please input again ");
                    field = sc.nextLine();
                }
            }catch(NumberFormatException ex){
                System.out.println("Please enter a valid number : ");
                System.out.println("Please input again ");
                field = sc.nextLine();
            }
            
        }
        p.setJurseyNumber(JurseyNumber);
        
        //salary
        double salary = -1;
        System.out.println("Player Salary: ");
        field = sc.nextLine();
        while(salary < 0){
            try{
                salary = Double.parseDouble(field);
                if(salary < 0){
                    System.out.println("Salary cannot be negative...");
                    System.out.println("Please input again ");
                    field = sc.nextLine();
                }
            }catch(NumberFormatException ex){
                System.out.println("Please enter a  number......");
                System.out.println("Please input again ");
                field = sc.nextLine();
            }
            
        }
        p.setSalary(salary);
        
        if(dataBase.addRecord(p))
            return "Adding Player to Database.......\nPlayer Added Successfully";
        else
            return "Sorry Player cannot be added to database.";
    }

    public static void main(String[] args) {
        new AddPlayerMenu(null).run();
    }
}
