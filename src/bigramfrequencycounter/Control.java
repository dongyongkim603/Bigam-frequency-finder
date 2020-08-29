/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigramfrequencycounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John
 */
public class Control {

    //class BufferedReader object to load file
    private BufferedReader bufferedReader = null;
    //class ArrayList that hold initial file
    private final ArrayList<String> InitialList = new ArrayList<>();
    //class ArrayList will hold all the bigrams
    private final ArrayList<String> bigramList = new ArrayList<>();
    //class HashMap will store all the bigrams and their occurence frequency
    private final HashMap<String, Integer> frequency = new HashMap<>();

//------------------------Access Method-----------------------------------------
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Integer> getMap() {
        return frequency;
    }

//------------------------------------------------------------------------------
    
    /**
     * Is the main interaction method that can be called outside the class. This
     * method will call all of the internal methods to perform the load, sort, and
     * counting operations.
     * @param fileName the relative or absolute path of the file as a String which
     * is used to pass in as an argument for the bufferedReaderToArrayList method.
     * @return true if all actions are successful
     */
    public boolean controlInterface(String fileName) {
        bufferedReaderToArrayList(fileName);
        if (!InitialList.isEmpty()) {
            System.out.println("Loading file... \n");
            createBigramArrayList(InitialList);
            InitialList.clear();//clear unused ArrayList
            System.out.println("Loading complete\n");
        } else {
            System.out.println("File is empty");
            return false;
        }
        if (!bigramList.isEmpty()) {
            System.out.println("Creating bigram list... \n");
            frequencyCounter(bigramList);
            bigramList.clear();//clear unused ArrayList
            System.out.println("bigram list compiled \n");
        } else {
            System.out.println("This file is incompatable becuase it does not contain any bigrams");
            return false;
        }
        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Utilizes the BufferedReader class to read large text file. The reader will
     * take in the file and produce Strings of each line. The .split method is 
     * used to separate each line into individual words. These words are then
     * added to the InitialList ArrayList.
     * @param fileName the relative or absolute path of the file as a String
     */
    private void bufferedReaderToArrayList(String fileName) {
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
            String available;
            while ((available = bufferedReader.readLine()) != null) {
                String[] lineArray = available.split("\\s+");
                for (int i = 0; i < lineArray.length; i++) {
                    InitialList.add(lineArray[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }//bufferedReaderToArrayList

    /**
     * Goes through the class ArrayList that is storing the file and parses the
     * ArrayList to create bigrams (two word sequences). the firstWord gets the
     * value of the first word of the first bigram. The secondWord gets the
     * value of the second word of the bigram by passing the offset into as an
     * argument in .get. these words are concatenated together to yeld a bigram.
     * To get the second bigram the firstWord is set to the secondWord while the
     * second is set to get offset + 1 from the initialList.
     *
     * @param inintialList should be a text file converted to an ArrayList cast
     * to String type
     */
    public void createBigramArrayList(ArrayList<String> inintialList) {
        String firstWord = "";
        String secondWord = "";
        String bigram = "";
        int offset = 0;//will set the word to be the second word in bigram
        int i = 0;
        while (i < inintialList.size() - 2) { //minus 2 because 1 less than size and 1 less for the offset
            offset = i + 1;
            firstWord = inintialList.get(i);
            secondWord = inintialList.get(offset);
            bigram = firstWord.concat(" " + secondWord);
            bigramList.add(bigram);
            firstWord = secondWord;
            secondWord = inintialList.get(offset + 1);
            bigram = firstWord.concat(" " + secondWord);
            bigramList.add(bigram);
            i = offset + 1;
        }
    }//createBigramArrayList

    /**
     * Reads the data from an ArrayList of String type, and will count the
     * number of occurences of each ArrayList item. Creates a boolean array to
     * keep track of which items have been checked. Uses nested loops to check
     * each element against the previously compared elements. Count will
     * increment if match is found. The ArrayList item String will be added to
     * the key of a HashMap and the count will be added as the value.
     *
     * @param bigramList An ArrayList of bigram Strings
     */
    public void frequencyCounter(ArrayList<String> bigramList) {
        int size = bigramList.size();
        boolean visited[] = new boolean[size];
        Arrays.fill(visited, false);
        for (int i = 0; i < size; i++) {
            if (visited[i] == true) {
                continue;
            }
            int count = 1;
            for (int j = i + 1; j < size; j++) {
                if (bigramList.get(i).equalsIgnoreCase(bigramList.get(j))) {
                    visited[j] = true;
                    count++;
                }
            }
            frequency.put(bigramList.get(i), count);
        }
    }//frequencyCounter

    /**
     * Will take in unsorted HashMap and sort based off of values. The argument
     * data will be put into a list. The list is sorted by calling the sort
     * method from Collections which in turn calls a lambda expression that
     * compares the different list items. The sorted List entries are added to
     * the new HashMap
     *
     * @param unsortedMap unsorted map data must be in String, Integer format
     * @return a sortedMap with values going in descending order
     */
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortedMap) {
        //Takes unsorted data and creates list
        List<Map.Entry<String, Integer>> list
                = new LinkedList<>(unsortedMap.entrySet());
        //calls the sort on list by calling a lambda expression that calls the compareTo method
        Collections.sort(list, (Map.Entry<String, Integer> FirstElement,
                Map.Entry<String, Integer> secondElement)
                -> (secondElement.getValue()).compareTo(FirstElement.getValue()));
        //creates the return HashMap, takes list items, and puts them in sortedMap
        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> listElement : list) {
            sortedMap.put(listElement.getKey(), listElement.getValue());
        }
        frequency.clear();//clear unused class HashMap
        return sortedMap;
    }//sortByValue

}//Control
