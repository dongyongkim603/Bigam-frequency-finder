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

        //checks if the controlInterface successfully performed all opperations
        boolean controlInterface;
        
        //the instance of the Control class
        Control mainControler = new Control();

        //calls the controlInterface method and passes in .txt file path
        controlInterface = mainControler.controlInterface("bigram.txt");
        
        //if controlInterace performs all actions sucessfully...
        if (controlInterface) {
            System.out.println("Counting bigram frequency...\n");
            
            //will store sorted bigrams
            Map<String, Integer> sortedMap = new HashMap<>(); 
            
            //will store the unsorted bigrams loaded in from the Control class
            HashMap<String, Integer> unsortedMap = mainControler.getMap();
            sortedMap = mainControler.sortByValue(unsortedMap);
            int x = 1;
            
            //this will print out the 20 most frequent bigrams
            for (Map.Entry<String, Integer> en : sortedMap.entrySet()) {
                System.out.println("Number " + x + " Bigram: \"" + en.getKey()
                        + "\", Frequency of occurances: " + en.getValue());
                if (x == 20) {
                    break;
                }
                x++;
            }
        }
    }
}
