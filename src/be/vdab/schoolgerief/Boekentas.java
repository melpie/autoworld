/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.schoolgerief;

import be.vdab.util.Volume;
import be.vdab.util.Laadbaar;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pieter.mels
 */
public class Boekentas implements Laadbaar,Serializable {
    
    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume laadvolume) {
        if(!textHasContent(kleur)) throw new IllegalArgumentException("Kleur niet geïnitialiseerd!");
        if(laadvolume == null) throw new IllegalArgumentException("Laadvolume niet geïnitialiseerd!");
              
        this.laadvolume = laadvolume;
        this.kleur = kleur;
    }
    
    private boolean textHasContent(String aText){
        String EMPTY_STRING = "";
        return (aText != null) && (!aText.trim().equals(EMPTY_STRING));
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        if(!textHasContent(kleur)) throw new IllegalArgumentException("Kleur niet geïnitialiseerd!");
        this.kleur = kleur;
    }
    
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        if(laadvolume == null) throw new IllegalArgumentException("Laadvolume niet geïnitialiseerd!");
        this.laadvolume = laadvolume;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.laadvolume);
        hash = 23 * hash + Objects.hashCode(this.kleur);
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
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.laadvolume, other.laadvolume)) {
            return false;
        }
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("boekentas %s %s", this.getKleur(), this.getLaadvolume().toString());
    }
    
}
