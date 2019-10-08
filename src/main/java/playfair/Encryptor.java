package playfair;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void setPlaintext(String plaintext){
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



    public String[] plaintextToBigrams(){
        char[] cleanPlaintextArr;
        int bigramIndex;
        int bigramListIndex;
        int plaintextIndex;
        List<StringBuilder> bigramBuilderList;
        char prevCharInBigram;
        char curChar;
        List<String> bigramList;


        prevCharInBigram=' ';
        cleanPlaintextArr = this.plaintext.toUpperCase().replaceAll("[^A-Z]","").toCharArray();
        bigramBuilderList = new ArrayList<>();
        bigramIndex = 0;
        bigramListIndex = 0;
        plaintextIndex=0;
        bigramList = new ArrayList<>();

        while(plaintextIndex<cleanPlaintextArr.length){
            curChar = cleanPlaintextArr[plaintextIndex];

            if (bigramIndex%2==0){ //init new bigram
                bigramBuilderList.add(new StringBuilder(2));
                prevCharInBigram = ' ';
            }

            if(curChar == prevCharInBigram){
                //Add X to current bigram
                bigramBuilderList.get(bigramListIndex).append('X');
                //increase bigram index
                bigramIndex++;
            }
            else {
                //add cur
                bigramBuilderList.get(bigramListIndex).append(curChar);
                bigramIndex++;
                //char processed, move to next one
                prevCharInBigram=curChar;
                plaintextIndex++;
            }
            bigramListIndex = bigramIndex/2;
        }

        //if final bigram is not complete, append X
        StringBuilder finalBigram = bigramBuilderList.get(bigramBuilderList.size()-1);
        if(finalBigram.length()==1){
            finalBigram.append('X');
        }

        for (StringBuilder sb : bigramBuilderList){
            bigramList.add(sb.toString());
        }

        Object[] bigramArray = bigramList.toArray();
        return Arrays.copyOf(bigramArray,bigramArray.length,String[].class);
        //TODO: return array of char arrays (i.e. each bigram is char array not string)
        //
    }

   // public String encryptBigram(String plainBigram, char[][] keyGrid){
        /* Rule 1:
          If the letters appear on the same row of your table,
          replace them with the letters to their immediate right respectively,
          wrapping around to the left side of the row if a letter in the original pair
          was on the right side of the row).
         */

//        char[] bigramCharArray = plainBigram.toCharArray();




        /* Rule 2:
            If the letters appear on the same column of your table,
            replace them with the letters immediately below respectively,
             wrapping around to the top side of the column if a letter in the original pair
             was on the bottom side of the column
         */



        /*  Rule 3:
            If the letters are not on the same row or column,
             replace them with the letters on the same row respectively
              but at the other pair of corners of the rectangle defined by the original pair.
               The order is important – the first letter of the encrypted pair is the one
               that lies on the same row as the first letter of the plaintext pair.
         */

    //}


    public int[] searchKeyGrid(char[][] keyGrid,char target_ch){
        int j=0;
        int[] char_indices;
        for (char[] subArray : keyGrid){
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

    public String removeDuplicateChars(String str, boolean keepFirst){
        //maybe we never use keepFirst=False?
        List<Character> alreadySeen;
        StringBuilder outputStr;
        outputStr = new StringBuilder();
        alreadySeen = new ArrayList<>();
        //TODO: should be hashmap
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
