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
public enum PlayerAttribute {
    NAME("Name"), COUNTRY("Country"), AGE("Age"), HEIGHT("Height"), 
    CLUB("Club"), POSITION("Position"), JURSEY("Jursey"), SALARY("Salary");
    
    static int noOfAttribute = 8;
    private final String str;

    private PlayerAttribute(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
    
}

