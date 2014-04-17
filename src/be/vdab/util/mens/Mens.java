/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import org.apache.commons.lang3.*;

/**
 *
 * @author pieter.mels
 */
public class Mens implements Serializable, Comparable<Mens> {
    
    private String naam;
    private Set<Rijbewijs> rijbewijzen;
    
    public Mens(String naam, Rijbewijs... rbn) {
        this.naam = naam;
        rijbewijzen = new TreeSet<Rijbewijs>();
        for (Rijbewijs rb : rbn) {
            rijbewijzen.add(rb);
        }
    }

    public String getNaam() {
        return naam;
    }
    
    /*
    public Object[] getRijbewijs() {
        return rijbewijzen.toArray();
    }
    */
    public Rijbewijs[] getRijbewijs() {
        return rijbewijzen.toArray(new Rijbewijs[rijbewijzen.size()]);
    }

    @Override
    public String toString() {
        
        if(rijbewijzen.isEmpty())
            return naam;
        else {
            return naam + "(" + StringUtils.join(rijbewijzen,", ") + ")";
        }    
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.naam);
        hash = 97 * hash + Objects.hashCode(this.rijbewijzen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mens other = (Mens) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.rijbewijzen, other.rijbewijzen)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Mens m) {
        if (this.equals(m)) {
            return 0;
        }
        else {
            if (this.getNaam().compareTo(m.getNaam()) < 0)
                return -1;
            else
                return 1;
        }
    }
    
    
    
   
}
