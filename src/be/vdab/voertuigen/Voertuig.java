/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.vdab.voertuigen;

import be.vdab.voertuigen.div.*;
import be.vdab.util.*;
import be.vdab.util.mens.*;
import java.util.TreeSet;
import java.util.Objects;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author pieter.mels
 */
public abstract class Voertuig  implements Serializable, Comparable<Voertuig> {
    
    Nummerplaat nummerplaat = DIV.getInstance().getNummerplaat();
    String merk;
    Datum datumEersteIngebruikname;
    int aankoopprijs;
    Mens bestuurder;
    TreeSet<Mens> ingezetenen;
    final int zitplaatsen;

    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... ingezetenen) {
        
        if (testRijbewijs(bestuurder) == false) throw new MensException("Bestuurder bezit niet het juiste rijbewijs.");
        if (zitplaatsen < 1) throw new IllegalArgumentException("Er is minstens 1 zitplaats.");
        
        this.zitplaatsen = zitplaatsen;
        this.bestuurder = bestuurder;
        this.ingezetenen = new TreeSet<Mens>();
        for (Mens m : ingezetenen) {
            this.addIngezetene(m);
        }   
               
        this.merk = merk;
        this.datumEersteIngebruikname = datumEersteIngebruikname;
        this.aankoopprijs = aankoopprijs;
                      
    }
    
    public boolean isIngezetene(Mens m) {
        return this.getIngezetenen().contains(m);
    }
    
    public boolean testRijbewijs(Mens bestuurder) {
        Rijbewijs[] rijbewijzen = bestuurder.getRijbewijs();
        Rijbewijs[] toegestaneRijbewijzen = this.getToegestaneRijbewijzen();
        boolean toegestaneRB = false;
        
        for (Rijbewijs rb : rijbewijzen) {
            if(Arrays.asList(toegestaneRijbewijzen).contains(rb)) {
                toegestaneRB = true;
                break;
            }
        }
        
        return toegestaneRB;
    }
    
    public boolean isErPlaats() {
        boolean b = !(this.getIngezetenen().size() == this.zitplaatsen);
        return b;
    }

    public void addIngezetene(Mens m) {
        if (this.isIngezetene(m)) {}
        else {
            if (!this.isErPlaats()) throw new MensException("Auto zit vol.");
            else this.ingezetenen.add(m);
        }
    }
    
    public TreeSet<Mens> getIngezetenen() {
        TreeSet<Mens> ingezetenenMetBestuurder = new TreeSet<Mens>();
        ingezetenenMetBestuurder.add(bestuurder);
        ingezetenenMetBestuurder.addAll(ingezetenen);
        return ingezetenenMetBestuurder;
    }
    
    public TreeSet<Mens> getIngezeteneExclusiefBestuurder() {
        return ingezetenen;
    }
    
    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    public String getMerk() {
        return merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public Mens getBestuurder() {
        return bestuurder;
    }

    public int getZitplaatsen() {
        return zitplaatsen;
    }

    public void setNummerplaat(Nummerplaat nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setDatumEersteIngebruikname(Datum datumEersteIngebruikname) {
        this.datumEersteIngebruikname = datumEersteIngebruikname;
    }

    public void setAankoopprijs(int aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public void setBestuurder(Mens bestuurder) {
        if(this.testRijbewijs(bestuurder) == false) throw new MensException("Bestuurder bezit niet het juiste rijbewijs.");
        if(bestuurder.equals(this.bestuurder)){}
        else if (this.getIngezetenen().contains(bestuurder)) {
            this.ingezetenen.remove(bestuurder);
            this.ingezetenen.add(this.bestuurder);
            this.bestuurder = bestuurder;
        }
        else {
            if(!this.isErPlaats()) throw new MensException("Auto zit vol.");
            ingezetenen.add(this.bestuurder);
            this.bestuurder = bestuurder;
        }
    }

    public void setIngezetenen(TreeSet<Mens> ingezetenen) {
        this.ingezetenen = ingezetenen;
    }
    
    protected abstract Rijbewijs[] getToegestaneRijbewijzen();
    
    protected abstract int getMAX_ZITPLAATSEN();
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.nummerplaat);
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
        final Voertuig other = (Voertuig) obj;
        if (!Objects.equals(this.nummerplaat, other.nummerplaat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (ingezetenen.isEmpty()) {
            return this.getNummerplaat().getPlaat() + " " + this.getMerk() + " " + this.getDatumEersteIngebruikname().toString() + " " + this.getAankoopprijs()
                + " " + this.getBestuurder().toString();
        }
        else {
            return this.getNummerplaat().getPlaat() + " " + this.getMerk() + " " + this.getDatumEersteIngebruikname().toString() + " " + this.getAankoopprijs()
                + " " + this.getBestuurder().toString() + " " + "[" + StringUtils.join(ingezetenen,", ") + "]";
        }
    }

    @Override
    public int compareTo(Voertuig v) {
        if (this.equals(v)) {
            return 0;
        }
        else {
            if (this.getNummerplaat().compareTo(v.getNummerplaat()) < 0)
                return -1;
            else
                return 1;
        }
    }
    
    public static Comparator<Voertuig> getMerkComparator() {
        return new MerkComparator();     
    }        

    private static class MerkComparator implements Comparator<Voertuig>,Serializable {

        public MerkComparator() {
        }

        @Override
        public int compare(Voertuig v1, Voertuig v2) {
            if (v1.compareTo(v2) == 0)
                return 0;
            else {
                if (v1.getMerk().compareTo(v2.getMerk()) < 0)
                    return -1;
                else
                    return 1;
                    
            }
        }
        
    }
    
    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return new AankoopprijsComparator();     
    }        

    private static class AankoopprijsComparator implements Comparator<Voertuig>,Serializable {

        public AankoopprijsComparator() {
        }

        @Override
        public int compare(Voertuig v1, Voertuig v2) {
            if (v1.compareTo(v2) == 0)
                return 0;
            else {
                if (v1.getAankoopprijs() < v2.getAankoopprijs())
                    return -1;
                else
                    return 1;
                    
            }
        }
        
    }
   
}
