/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asib27.playerdatabasesystem;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Asib27
 */

public class Player {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int jurseyNumber;
    private double salary;
    public  static String[] VALID_POS =  {"Goalkeeper", "Defender", "Midfielder", "Forward"};
    
    static boolean isValidPosition(String position){
        Set<String> valid =  new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        valid.addAll(Arrays.asList(VALID_POS));
        
        return valid.contains(position);
    }

    public Player() {
    }
    
    Player(String line){
        String[] token = line.split(",");
        
        if(token.length == PlayerAttribute.noOfAttribute){
            name = token[0];
            country = token[1];
            age = Integer.parseInt(token[2]);
            height = Double.parseDouble(token[3]);
            club = token[4];
            position = token[5];
            jurseyNumber = Integer.parseInt(token[6]);
            salary = Double.parseDouble(token[7]);
        }
    }

    public Player(Player p) {
        if(p != null){
            name = p.name;
            country = p.country;
            age = p.age;
            height = p.height;
            club = p.club;
            position = p.position;
            jurseyNumber = p.jurseyNumber;
            salary = p.salary;
        }
    }
    
    

    @Override
    public String toString() {
        String str = "";
        
        str += name + ",";
        str += country + ",";
        str += age + ",";
        str += height + ",";
        str += club + ",";
        str += position + ",";
        str += jurseyNumber + ",";
        str += salary;
        
        return str;
    }
    
    public String format(String separator){
        StringBuilder sb = new StringBuilder();
        
        for (var field: PlayerAttribute.values()) {
            sb.append(String.format("%-9s : ", field)).append(get(field)).append(separator);
        }
        
        sb.setLength(sb.length() - separator.length());
        return sb.toString();
    }
    
    
    public Object get(PlayerAttribute field){
        return switch(field){
            case AGE ->       age;
            case CLUB ->      club;
            case COUNTRY ->   country;
            case HEIGHT ->    height;
            case JURSEY ->    jurseyNumber;
            case NAME ->      name;
            case POSITION ->  position;
            case SALARY ->    salary;
            default -> null;
        };
    }
    
    public <T extends Comparable>int compare(PlayerAttribute field, Object c){
        T ob = (T) this.get(field);
        
        if(ob instanceof String){
            return ((String) ob).compareToIgnoreCase((String) c);
        }
        return ob.compareTo(c);
    }
    
    public <T extends Comparable>int compare(PlayerAttribute field, Player p){
        T ob = (T) this.get(field);
        
        if(ob instanceof String){
            return ((String) ob).compareToIgnoreCase((String) p.get(field));
        }
        return ob.compareTo(p.get(field));
    }
    
    public static void main(String[] args) {
        String line = "David de Gea,Spain,30,1.92,Manchester United,Goalkeeper,1,375000.0";
        Player p = new Player(line);
        System.out.println(p);
        
        System.out.println(p.get(PlayerAttribute.CLUB));
        System.out.println(p.compare(PlayerAttribute.AGE, 31));
        
        System.out.println(p.format(" , "));
        
        System.out.println(isValidPosition("Midfielder"));
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJurseyNumber() {
        return jurseyNumber;
    }

    public void setJurseyNumber(int jurseyNumber) {
        this.jurseyNumber = jurseyNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
