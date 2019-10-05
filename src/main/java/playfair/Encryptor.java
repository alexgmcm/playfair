package playfair;

import java.util.ArrayList;
import java.util.List;

public class Encryptor {

    private String key;
    private String plaintext;

    //I and J are interchangeable in the grid lookup
    //Therefore J is removed from the alphabet to fit the 5x5 grid
    static final private String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    public Encryptor(String key, String plaintext){
        this.key=key;
        this.plaintext=plaintext;
    }

    public char[][] generatekeyGrid(){
        char[][] keyGrid = new char[5][5];
        this.key = this.cleanKeyString();
        char[] keyStringArr = removeDuplicateChars( this.key + ALPHABET,true).toCharArray();
        int i = 0;
        for(int row=0; row<5;row++) {
            for (int col = 0; col < 5; col++) {
                keyGrid[row][col] = keyStringArr[i];
                i++;
            }
        }
        return keyGrid;
    }

    public String cleanKeyString(){
        return this.key.toUpperCase().replaceAll("[^A-Z]","");
    }

    public String removeDuplicateChars(String str, boolean keepFirst){
        //maybe we never use keepFirst=False?
        List<Character> alreadySeen;
        StringBuilder outputStr;
        outputStr = new StringBuilder();
        alreadySeen = new ArrayList<>(); //should be hashmap
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


}
