/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class PlayerDataBase implements PlayerDataBaseInt{
    private ArrayList<Player> playerList;

    public PlayerDataBase(File file) {
        playerList = new ArrayList();
        
        FileReader reader = null;
        BufferedReader br = null;
        
        try {
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            String line = null;
            while( (line = br.readLine()) != null){
                playerList.add(new Player(line));
            }
            
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public PlayerDataBase(Player[] playerArray) {
        playerList = new ArrayList();
        
        playerList.addAll(Arrays.asList(playerArray));
        
    }

    public PlayerDataBase() {
        playerList = new ArrayList();
    }
    
    public Player[] query(PlayerAttribute field, Object ob){
        ArrayList<Player> matched = new ArrayList();
        
        for (Player player : playerList) {
            if(ob.equals(player.get(field)))
                matched.add(new Player(player));
        }
        
        return /*(Player[])*/ matched.toArray(new Player[0]);
    }
    
    public <T extends Comparable>Player[] queryRange(PlayerAttribute field, T start, T end){
        if(start.getClass() != end.getClass()) return null;
        
        ArrayList<Player> matched = new ArrayList();
        
        for (var player : playerList) {
            try{
                if(player.compare(field, start) >= 0 && player.compare(field, end) <= 0)
                    matched.add(new Player(player));
            }catch(ClassCastException ex){
                return null;
            }
        }
        
        return matched.toArray(new Player[0]);
    }
    
    public Map<String, Integer> counAlltFields(PlayerAttribute field){
        Map<String, Integer> mp = new HashMap();
        
        for (Player player : playerList) {
            String data = null;
            Object ob = player.get(field);
            
            if(ob instanceof String)
                data = (String) ob;
            else
                data = ob.toString();
            
            mp.put(data, mp.getOrDefault(data, 0) + 1);
        }
        
        return mp;
    }
    
   
    public int countField(PlayerAttribute field, Object val){
        int count = 0;
        
        for (var player : playerList) {
            if(player.get(field).equals(val))
                count++;
        }
        
        return count;
    }

    public Player[] getMaxField(PlayerAttribute field){
        if(playerList.isEmpty()) return null;
        
        ArrayList<Player> high = new ArrayList();
        Player p = new Player(playerList.get(0));
        high.add(p);
        
        for (int i = 1; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            
            int result = player.compare(field, p);

            if (result > 0) {
                high.clear();
                p = new Player(player);
                high.add(p);
            } else if (result == 0) {
                high.add(new Player(player));
            }
        }
        
        return high.toArray(new Player[0]);
    }
    
    public Player[] getMinField(PlayerAttribute field){
        if(playerList.isEmpty()) return null;
        
        ArrayList<Player> high = new ArrayList();
        Player p = new Player(playerList.get(0));
        high.add(p);
        
        for (int i = 1; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            
            int result = player.compare(field, p);
            
            if (result < 0) {
                high.clear();
                p = new Player(player);
                high.add(p);
            } else if (result == 0) {
                high.add(new Player(player));
            }
        }
        
        return high.toArray(new Player[0]);
    }
    
    public boolean addRecord(Player p){
        return playerList.add(p);
    }
    
    public boolean removeRecord(Player p){
        return playerList.remove(p);
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (var player : playerList) {
            str = String.join("\n",str, player.toString());
        }
        
        return str;
    }
    
    
    public static void main(String[] args) {
        String fileName = "G:\\Java\\PlayerDataBaseSystem\\src\\main\\java\\com\\asib27\\playerdatabasesystem\\players.txt";
        File file =  new File(fileName);
        
        
        PlayerDataBase pdb = new PlayerDataBase(file);
        System.out.println(pdb);
        
        System.out.println(Arrays.toString(pdb.query(PlayerAttribute.CLUB, "Manchester United")));
        System.out.println("");
        System.out.println(Arrays.toString(pdb.queryRange(PlayerAttribute.AGE, "Asib", "Labiba")));
        System.out.println(pdb.counAlltFields(PlayerAttribute.HEIGHT));
      
        Player p = pdb.query(PlayerAttribute.NAME, "Bruno Fernandes")[0];
        System.out.println(p);
        p.setClub("Real Madrid");
        System.out.println(p);
        System.out.println(pdb.query(PlayerAttribute.NAME, "Bruno Fernandes")[0]);
        
        System.out.println("");
        
        Player[] maxClub = pdb.getMaxField(PlayerAttribute.AGE);
        System.out.println(Arrays.toString(maxClub));
        maxClub[0].setCountry("Bangladesh");
        System.out.println(Arrays.toString(maxClub));
        System.out.println(Arrays.toString(pdb.getMaxField(PlayerAttribute.CLUB)));
    }
    
    public Player[] getAllRecords(){
        Player[] all = new Player[playerList.size()];
        
        for (int i = 0; i < all.length; i++) {
            all[i] = new Player(playerList.get(i));
            
        }
        return all;
    }
    
    public void setPlayers(Player[] players){
        playerList.clear();
        playerList.addAll(Arrays.asList(players));
    }
    
    public void writeTofile(File file){
        try {
            try (var pr = new PrintWriter(file)) {
                for (var player : playerList) {
                    pr.println(player);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
