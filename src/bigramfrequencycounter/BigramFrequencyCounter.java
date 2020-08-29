/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigramfrequencycounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author John
 */
public class BigramFrequencyCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Control mainControler = new Control();
        //mainControler.readInFileToArrayList1("bigram.txt");
        mainControler.controlInterface("bigram.txt");
        Map<String, Integer> sortedMap = new HashMap<>();
        HashMap<String, Integer> unsortedMap = mainControler.getMap();
        sortedMap = mainControler.sortByValue(unsortedMap);
        for (Map.Entry<String, Integer> en : sortedMap.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        //System.out.println(tempInt.toString());
    }
 
}
