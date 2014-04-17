/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.vdab.voertuigen.*;
import be.vdab.util.mens.Mens;
import static be.vdab.util.mens.Rijbewijs.*;
import be.vdab.util.*;
import static be.vdab.util.Maat.*;
import java.util.SortedSet;
import java.util.TreeSet;
import java.awt.Color;
import java.io.*;


/**
 *
 * @author pieter.mels
 */
public class Programma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // MENSEN
        Mens BESTUURDER_A = new Mens("Andree",A);
        Mens BESTUURDER_AB = new Mens("Amadeus",A,B);
        Mens BESTUURDER_B = new Mens("Bernard",B);
        Mens BESTUURDER_BC = new Mens("Beatrice-Clothilde",B,C);
        Mens BESTUURDER_C = new Mens("Catherina",C);
        Mens BESTUURDER_D = new Mens("Didier",D);
        Mens BESTUURDER_BE = new Mens("Beatrice-Emanuella",BE);
        Mens BESTUURDER_BBE = new Mens("Babette-Emanuella",B,BE);
        Mens BESTUURDER_CE = new Mens("Cederic-Eduard",CE);
        Mens BESTUURDER_DE = new Mens("Dominique-Emille",CE);
        Mens BESTUURDER_BBECCE = new Mens("Ammelie",B,BE,C,CE);
        Mens INGEZETENE_A = new Mens("Anita");
        Mens INGEZETENE_B = new Mens("Bert");
        Mens INGEZETENE_C = new Mens("Christina");
        Mens INGEZETENE_D = new Mens("Duts");
        Mens INGEZETENE_E = new Mens("Elsa");
        Mens INGEZETENE_F = new Mens("Fred");
        Mens INGEZETENE_G = new Mens("Gerda");
        Mens INGEZETENE_H = new Mens("Hedwig");
        Mens INGEZETENE_I = new Mens("Ingrid");
        
        
        // SortedSet van voertuigen gesorteerd op nummerplaat
        SortedSet<Voertuig> voertuigen = new TreeSet<Voertuig>();
        Personenwagen pw1 = null;
        Personenwagen pw2 = null;
        Vrachtwagen vw1 = null;
        Vrachtwagen vw2 = null;
        Pickup pu1 = null;
        Pickup pu2 = null;
        
        try {
            pw1 = new Personenwagen("AUDI", new Datum(10,10,2010), 25000, 5, Color.PINK, BESTUURDER_B, INGEZETENE_A); 
            pw2 = new Personenwagen("VOLVO", new Datum(8,1,2220), 18000, 5, Color.RED, BESTUURDER_AB, INGEZETENE_B, INGEZETENE_I, INGEZETENE_G); 
            vw1 = new Vrachtwagen("MAN", new Datum(5,3,2025), 50000, 2, new Volume(3,4,10,meter), 50, 2, BESTUURDER_CE);
            vw2 = new Vrachtwagen("IVECO", new Datum(6,9,2220), 35000, 2, new Volume(3,4,8,meter), 100, 2, BESTUURDER_BBECCE);
            pu1 = new Pickup("TOYOTA", new Datum(5,5,1950), 25000, 4, Color.GREEN, new Volume(1,1,1,meter), BESTUURDER_BBE, INGEZETENE_H, INGEZETENE_F, INGEZETENE_C);
            pu2 = new Pickup("TOYOTA", new Datum(6,7,1975), 27000, 4, Color.GRAY, new Volume(1,2,1,meter), BESTUURDER_BE, INGEZETENE_E, INGEZETENE_D, INGEZETENE_C);
        }
        catch (DatumException ex) {
            System.out.println(ex.getMessage());
        }
        catch (VolumeException ex) {
            System.out.println(ex.getMessage());
        }
        
        voertuigen.add(pw1);
        voertuigen.add(pw2);
        voertuigen.add(vw1);
        voertuigen.add(vw2);
        voertuigen.add(pu1);
        voertuigen.add(pu2);
                
        System.out.println("Voertuigen gesorteerd op nummerplaat:");
        System.out.println("-------------------------------------");
        for(Voertuig v : voertuigen) {
            System.out.println(v.toString());
        }
        
        
        // SortedSet van voertuigen gesorteerd op aankoopprijs
        SortedSet<Voertuig> voertuigen2 = new TreeSet<Voertuig>(Voertuig.getAankoopprijsComparator());
        voertuigen2.addAll(voertuigen);
        
        System.out.println("Voertuigen gesorteerd op aankoopprijs:");
        System.out.println("--------------------------------------");
        for(Voertuig v : voertuigen2) {
            System.out.println(v.toString());
        }
        
        
        // SortedSet van voertuigen gesorteerd op merk
        SortedSet<Voertuig> voertuigen3 = new TreeSet<Voertuig>(Voertuig.getMerkComparator());
        voertuigen3.addAll(voertuigen);
        
        System.out.println("Voertuigen gesorteerd op merk:");
        System.out.println("------------------------------");
        for(Voertuig v : voertuigen3) {
            System.out.println(v.toString());
        }
        
        
        // Voertuigen wegschrijven naar 'wagenpark.ser'
        FileOutputStream fileOut = null;
        ObjectOutputStream objOut = null;
        
        try {
            fileOut = new FileOutputStream("wagenpark.ser");
            objOut = new ObjectOutputStream(fileOut);
            //objOut.writeObject(voertuigen.toArray(new Voertuig[voertuigen.size()]));
            objOut.writeObject(voertuigen);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            if (objOut != null) {
                try {
                    objOut.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
        // Voertuigen inlezen uit 'wagenpark.ser'
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        SortedSet<Voertuig> voertuigen4 = new TreeSet<Voertuig>();
        
        try {
            fileIn = new FileInputStream("wagenpark.ser");
            objIn = new ObjectInputStream(fileIn);
            voertuigen4 = (SortedSet<Voertuig>) objIn.readObject();
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            if (objIn != null) {
                try {
                    objIn.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
             
        System.out.println("Voertuigen ingelezen uit 'wagenpark.ser':");
        System.out.println("-----------------------------------------");
        for(Voertuig i : voertuigen4) {
            System.out.println(i.toString());
        }
        
    }
    
}
