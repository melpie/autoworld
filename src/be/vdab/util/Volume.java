/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pieter.mels
 */
public class Volume implements Serializable, Comparable<Volume> {
    
    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if (hoogte < 0) {
            throw new VolumeException("Negatieve hoogte!");
        }
        else this.hoogte = hoogte;
        if (breedte < 0) {
            throw new VolumeException("Negatieve breedte!");
        }
        else this.breedte = breedte;
        if (diepte < 0) {
            throw new VolumeException("Negatieve diepte!");
        }
        else this.diepte = diepte;
        this.maat = maat;
    }

    public int getHoogte() {
        
        int h = 0;
        
        switch(maat) {
            case centimeter:
                h = this.hoogte;
                break;
            case decimeter:
                h = 10 * this.hoogte;
                break;
            case meter:
                h = 100 * this.hoogte;
                break;
        }
        
        return h;

    }

    public int getBreedte() {
        
        int b = 0;
        
        switch(maat) {
            case centimeter:
                b = this.breedte;
                break;
            case decimeter:
                b = 10 * this.breedte;
                break;
            case meter:
                b = 100 * this.breedte;
                break;
        }
        
        return b;
        
    }

    public int getDiepte() {
        
        int d = 0;
        
        switch(maat) {
            case centimeter:
                d = this.diepte;
                break;
            case decimeter:
                d = 10 * this.diepte;
                break;
            case meter:
                d = 100 * this.diepte;
                break;
        }
        
        return d;
        
    }

    public Maat getMaat() {
        return maat;
    }
    
    public long getVolume() {
        
        long volume = 0;
        
        switch(this.maat) {
            case centimeter:
                volume = hoogte * breedte * diepte;
                break;
            case decimeter:
                volume = 1000 * hoogte * breedte * diepte;
                break;
            case meter:
                volume = 1000000 * hoogte * breedte * diepte;
                break;
        }
        
        return volume;  
        
    }
    
    @Override
    public int compareTo(Volume v) {
        if (this.equals(v)) {
            return 0;
        }
        else {
            if (this.getVolume() < v.getVolume())
                return -1;
            else
                return 1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.hoogte;
        hash = 67 * hash + this.breedte;
        hash = 67 * hash + this.diepte;
        hash = 67 * hash + Objects.hashCode(this.maat);
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
        final Volume other = (Volume) obj;
        if (this.getHoogte() != other.getHoogte()) {
            return false;
        }
        if (this.getBreedte() != other.getBreedte()) {
            return false;
        }
        if (this.getDiepte() != other.getDiepte()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d(h)x%d(b)x%d(d) %s", this.getHoogte(), this.getBreedte(), this.getDiepte(), this.getMaat().toString());
    }
    
    
    
    
}
