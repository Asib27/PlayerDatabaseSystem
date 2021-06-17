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
    public boolean addRecord(Player P);
    public boolean removeRecord(Player p);
    public Map<String, Integer> counAlltFields(PlayerAttribute field);
    public int countField(PlayerAttribute field, Object val);
    public Player[] getMaxField(PlayerAttribute field);
    public Player[] getMinField(PlayerAttribute field);
    public Player[] getAllRecords();
    public Player[] query(PlayerAttribute field, Object ob);
    public <T extends Comparable>Player[] queryRange(PlayerAttribute field, T start, T end);
    public void setPlayers(Player[] players);
    public void writeTofile(File file);
}
