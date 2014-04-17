/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author pieter.mels
 */
public class Datum implements Serializable,Comparable<Datum> {
    
    private final int dag;
    private final int maand;
    private final int jaar;
    
    private final int[] dagen = {31,29,31,30,31,30,31,31,30,31,30,31};
    
    public Datum(int dag, int maand, int jaar) throws DatumException {
        
        if (jaar < 1583 | jaar > 4099) throw new DatumException("Verkeerd jaar");
        else if (maand < 1 | maand > 12) throw new DatumException("Verkeerde maand");
        else if (dag > dagen[maand-1] | dag < 1) throw new DatumException("Verkeerde dag");
        else if (jaar%4 != 0 & maand == 2 & dag == 29) throw new DatumException("Geen schrikkeljaar");
        else if (jaar%100 == 0 & jaar%400 != 0 & maand == 2 & dag == 29) throw new DatumException("Geen schrikkeljaar");       
        
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
        
    }

    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dag, maand, jaar);
    }

    public int toInt(){
        return jaar*10000+maand*100+dag;
    }
    
    @Override
    public int hashCode() {
        return toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Datum other = (Datum) obj;
        if (this.dag != other.dag) {
            return false;
        }
        if (this.maand != other.maand) {
            return false;
        }
        if (this.jaar != other.jaar) {
            return false;
        }
        return true;
    }
       
    @Override
    public int compareTo(Datum d) {
        if (this.equals(d)) {
            return 0;
        }
        else {
            //if (this.getJaar() < d.getJaar() | (this.getJaar() == d.getJaar() & this.getMaand() < d.getMaand()) | (this.getJaar() == d.getJaar() & this.getMaand() == d.getMaand()) & this.getDag() < d.getDag())
            if(this.toInt() < d.toInt())  
                return -1;
            else
                return 1;
        }
    }

}
