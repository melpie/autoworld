/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

/**
 *
 * @author pieter.mels
 */
public enum Rijbewijs {
    
    A,B,BE,C,CE,D,DE;
    
    @Override
    public String toString() {
        String s = super.toString();
    
        if (s.equals("BE")) {
            s = "B+E";
        }
        
        if (s.equals("CE")) {
            s = "C+E";
        }
        
        if (s.equals("DE")) {
            s = "D+E";
        }
        
        return s;
    }
    
    
    
}
