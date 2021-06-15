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
public class PlayerSearchingMenu extends MenuControl{

    public PlayerSearchingMenu(PlayerDataBase dataBase) {
        super(dataBase, "Player Search Menu");
        
        String[] mItem = {"By Player Name","By Club and Country","By Position","By Salary Range","Country-wise player count","Back to Main Menu"};
        super.setMenuItem(mItem);
    };
    

    @Override
    protected String executeCommand(int input) {
        String res = "";
        switch(input){
            case 1:
                searchByPlayer();
                break;

            case 2:
                searchByClubAndCountry();
                break;
            case 3:
                seachByPosition();
                break;
            case 4:
                searchBySalaryRange();
                break;
            case 5:
                countryWisePlayer();
                break;
            case 6:
                res = "Back to main menu.......";
                break;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        new PlayerSearchingMenu(null).run();
    }

    private void searchByPlayer() {
        System.out.println("Search by player name\nEnter Player Name");
        
        String name = sc.nextLine();
        Player[] sameName = dataBase.query(PlayerAttribute.NAME, name);
        
        if(sameName.length == 0)
            System.out.println("Sorry No player found with this name");
        else{
            System.out.println("Player FOund " + sameName.length);
            System.out.println(sameName[0]);
        }
    }

    private void seachByPosition() {
        System.out.println("Search by player position\nEnter Player Position");
        
        String name = sc.nextLine();
        Player[] samePos = dataBase.query(PlayerAttribute.POSITION, name);
        
        printPlayers(samePos);
    }

    private void searchByClubAndCountry() {
        System.out.println("Search by club and country");
        
        System.out.println("Enter Country Name");
        String countryName = sc.nextLine();
        
        System.out.println("Enter Club Name");
        String clubName = sc.nextLine();
        
        Player[] all = dataBase.getPlayers();
        
        if(!countryName.equalsIgnoreCase("any")){
            all = new PlayerDataBase(all).query(PlayerAttribute.COUNTRY, countryName);
        }
        if(!clubName.equalsIgnoreCase("any")){
            all = new PlayerDataBase(all).query(PlayerAttribute.CLUB, clubName);
        }
        
        printPlayers(all);
        
    }

    private void searchBySalaryRange() {
        System.out.println("Search by Salary range");
        
        System.out.println("Enter starting and ending salary");
        
        double salStart = sc.nextDouble();
        double salEnd = sc.nextDouble();
        sc.skip("\n");
        
        Player[] ans = dataBase.queryRange(PlayerAttribute.SALARY, salStart, salEnd);
        printPlayers(ans);
    }

    private void countryWisePlayer() {
        System.out.println("Country wise player count");
        
        var mp = dataBase.counAlltFields(PlayerAttribute.COUNTRY);
        System.out.println("Country Wise Player List");
        
        for (String str : mp.keySet()) {
            System.out.println(str + " : " + mp.get(str));
        }
    }
    
    private void printPlayers(Player[] pl){
        if(pl.length == 0)
            System.out.println("Sorry no player found");
        else{
            System.out.println("Players Found : " + pl.length);
            for (Player player : pl) {
                System.out.println(player);
            }
        }
    }
    
}
