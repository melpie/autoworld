/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;


import be.vdab.util.*;
import be.vdab.util.mens.*;
import java.awt.Color;

/**
 *
 * @author pieter.mels
 */
public class Personenwagen extends Voertuig {

    private Color kleur;
    private static final int MAX_ZITPLAATSEN = 8;
    
    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Maximaal 8 zitplaatsen!");
        this.kleur = kleur;
    }
    
    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder, Mens... ingezetenen) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, ingezetenen);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Maximaal 8 zitplaatsen!");
        this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
    
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }
    
    @Override 
    public int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + this.getZitplaatsen();
    }
    
}
