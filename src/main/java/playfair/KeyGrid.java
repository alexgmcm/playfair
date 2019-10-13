package playfair;

import java.util.HashSet;
import java.util.Set;

public class KeyGrid {
    //I and J are interchangeable in the grid lookup
    //Therefore J is removed from the alphabet to fit the 5x5 grid
    static final private String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    private char[][] keyGrid;

    public KeyGrid(String key){
        this.keyGrid=this.generatekeyGrid(key);
    }

    public char[][] getKeyGrid(){
        return this.keyGrid;
    }

    public char[][] generatekeyGrid(String key){
        char[][] keyGrid = new char[5][5];
        key = cleanKeyString(key);
        char[] keyStringArr = removeDuplicateChars( key + ALPHABET,true).toCharArray();
        int i = 0;
        for(int row=0; row<5;row++) {
            for (int col = 0; col < 5; col++) {
                keyGrid[row][col] = keyStringArr[i];
                i++;
            }
        }
        return keyGrid;
    }

    public String cleanKeyString(String key){
        return key.toUpperCase().replaceAll("[^A-Z]","").replaceAll("J","I");
    }

    public String removeDuplicateChars(String str, boolean keepFirst){
        //maybe we never use keepFirst=False?
        Set<Character> alreadySeen;
        StringBuilder outputStr;
        outputStr = new StringBuilder();
        alreadySeen = new HashSet<>();
        for (char c : str.toCharArray()){
            if (!alreadySeen.contains(c)){
                outputStr.append(c);
                alreadySeen.add(c);
            } else if(!keepFirst){
                int index = outputStr.indexOf(Character.toString(c));
                if (index >= 0) {
                    //if has already been removed then index will return as -1 for not found
                    outputStr.deleteCharAt(index);
                }
            }
        }
        return outputStr.toString();
    }

    public int[] searchKeyGrid(char target_ch){
        int j=0;
        int[] char_indices;
        for (char[] subArray : this.keyGrid){
            int i=0;

            for (char candidate_ch : subArray) {
                if (target_ch == candidate_ch) {
                    char_indices = new int[] {j,i};
                    return char_indices;
                }
                else {i++;}
            }
            j++;
        }
        System.out.println("Character " + target_ch + " not found in keygrid.");
        char_indices = new int[] {-1,-1};
        return char_indices;
    }

}
