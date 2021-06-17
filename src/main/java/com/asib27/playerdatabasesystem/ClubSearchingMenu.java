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
public class ClubSearchingMenu extends MenuControl{

    public ClubSearchingMenu(PlayerDataBaseInt db) {
        super(db, "Club Searching Menu");
        String[] menuItem = {"Player(s) with the maximum salary of a club","Player(s) with the maximum age of a club","Player(s) with the maximum height of a club","Total yearly salary of a club","Back to Main Menu"};
        super.setMenuItem(menuItem);
    }

    @Override
    protected String executeCommand(int input) {
        String res = "";
        
        switch(input){
            case 1:
                processMaxQuery(PlayerAttribute.SALARY);
                break;
            case 2:
                processMaxQuery(PlayerAttribute.AGE);
                break;
            case 3:
                processMaxQuery(PlayerAttribute.HEIGHT);
                break;
            case 4:
                findTotalSalary();
                break;
            case 5:
                res = "Back to Main Menu............";
                break;
        }
        
        return res;
    }
    
    private void printPlayers(Player[] pl){
        System.out.println("Players Found : " + pl.length);
        for (Player player : pl) {
            System.out.println(player);
        }
    }
    
    private PlayerDataBaseInt getClubDataBase(){
        System.out.println("Enter name of club : ");
        String clubName = sc.nextLine();
        clubName = clubName.trim();
        
        if(dataBase.countField(PlayerAttribute.CLUB, clubName) != 0){
            Player[] clubPlayers = dataBase.query(PlayerAttribute.CLUB, clubName);
            PlayerDataBaseInt clubDataBase = new PlayerDataBase(clubPlayers);
            return clubDataBase;
        }
        else{
            return null;
        }
    }
    
    private void processMaxQuery(PlayerAttribute field){
        PlayerDataBaseInt clubDataBase = getClubDataBase();
        
        if(clubDataBase != null){
            System.out.println("Finding Player.......");
            Player[] max = clubDataBase.getMaxField(field);
            printPlayers(max);
        }
        else{
            System.out.println("Sorry No CLub found with this name");
        }
    }
    
    private void findTotalSalary(){
        PlayerDataBaseInt clubDataBase = getClubDataBase();
        double sal = 0;
        
        if(clubDataBase != null){
            for (var player: clubDataBase.getAllRecords()) {
                sal += (double)player.get(PlayerAttribute.SALARY);
            }
            
            System.out.println("Total salary of players : " + sal * 52);
        }
        else{
            System.out.println("Sorry No CLub found with this name");
        }
    }
    
    public static void main(String[] args) {
        new ClubSearchingMenu(null).run();
    }
}
