/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen.div;

/**
 *
 * @author pieter.mels
 */
public class DIV {
    
    private static DIV instance = new DIV();
    private int nummer = 0;
    
    private DIV() {}

    public static DIV getInstance() {
        return instance;
    }
    
    public Nummerplaat getNummerplaat() {
        nummer++;
        if (nummer == 1000) nummer = 1;
        return new Nummerplaat(String.format("AAA%03d",nummer));
    }
    
}
