/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.io.Serializable;
import be.vdab.util.Laadbaar;

/**
 *
 * @author pieter.mels
 */
public class Vrachtwagen extends Voertuig implements Laadbaar,Serializable {
    
    private Volume laadvolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    private static final int MAX_ZITPLAATSEN = 3;

    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Maximaal 3 zitplaatsen!");
        this.laadvolume = laadvolume;
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
    }
    
    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder, Mens... ingezetenen) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, ingezetenen);
        if (zitplaatsen > MAX_ZITPLAATSEN) throw new IllegalArgumentException("Maximaal 3 zitplaatsen!");
        this.laadvolume = laadvolume;
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
    }
    
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    public int getAantalAssen() {
        return aantalAssen;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        this.laadvolume = laadvolume;
    }

    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }

    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }
   
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.C, Rijbewijs.CE};
    }

    @Override
    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" assen:%d, maximaal toegelaten massa:%d, laadvolume:%s", this.getAantalAssen(), 
            this.getMaximaalToegelatenMassa(), this.getLaadvolume().toString());
    } 
    
}
