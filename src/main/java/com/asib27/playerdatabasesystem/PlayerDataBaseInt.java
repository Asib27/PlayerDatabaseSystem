/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.io.File;
import java.util.Map;

/**
 *
 * @author USER
 */
public interface PlayerDataBaseInt {

    /**
     * Adds a record to the player database
     * @param P
     * @return true if insertion is successful, false otherwise
     */
    public boolean addRecord(Player P);

    /**
     * Deletes the given player from the database, if the player exists.
     * @param p
     * @return true if deletion is successful i.e database contains the player, 
     * false otherwise
     */
    public boolean removeRecord(Player p);
    
    /**
     * No of times the specific value occurs in the database in the given field.
     * @param field
     * @return string representation of field and no of time each value in the 
     * field occurs
     */
    public Map<String, Integer> counAlltFields(PlayerAttribute field);
    
    /**
     * How many times val occurs in that field in database
     * @param field
     * @param val
     * @return
     */
    public int countField(PlayerAttribute field, Object val);
    
    /**
     * returns Player[] containing maximum value of the given field
     * @param field
     * @return
     */
    public Player[] getMaxField(PlayerAttribute field);
    
    /**
     * returns Player[] containing maximum value of the given field
     * @param field
     * @return
     */
    public Player[] getMinField(PlayerAttribute field);
    
    /**
     * returns all the records as Player[]
     * @return
     */
    public Player[] getAllRecords();
    
    /**
     * returns Player[] which contains ob in the given field
     * @param field
     * @param ob
     * @return
     */
    public Player[] query(PlayerAttribute field, Object ob);
    
    /**
     * returns Player[] which contains  field value in range start to end
     * @param field
     * @param start
     * @param end
     * @return
     */
    public Player[] queryRange(PlayerAttribute field, Comparable<?> start, Comparable<?> end);
    
    /**
     * resets database and initialize it with players
     * @param players
     */
    public void setPlayers(Player[] players);
    
    /**
     * write records in the database to the file
     * @param file
     */
    public void writeTofile(File file);
}
