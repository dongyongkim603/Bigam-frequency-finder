# Bigam-frequency-finder
Developed in Java to read in a .txt file, search the file for bigams (two word combinations), and returns the highest frequency bigams.

This program will take files in from the “bigram.txt” file and load them into an unsorted ArrayList of individual words. The 
unsorted list is then parsed through to create the two-word combinations or “bigrams” and these are added to another ArrayList. 
From there the frequency of the bigrams is counted. The frequency utilizes a HashMap to keep track of data by assigning the 
bigram as the key and the frequency as the value. Last step in the control, is to sort the HashMap.

Once these steps are complete the driver class will retrieve the sorted map and loop through the first 20 entries in the HashMap.
