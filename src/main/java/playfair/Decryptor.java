package playfair;

import java.util.ArrayList;
import java.util.List;

public class Decryptor {

    private String ciphertext;
    private KeyGrid keyGrid;

    public Decryptor(String key, String ciphertext){
        this.ciphertext=ciphertext;
        this.keyGrid = new KeyGrid(key);
    }

    public String decrypt(){
        List<String> bigramArray;
        bigramArray=new ArrayList<>();
                String[] plainBigrams=new BigramList(this.ciphertext).getBigramList();
        for (String bigram : plainBigrams){
            bigramArray.add(new String(this.decryptBigram(bigram)));
        }
        return String.join(" ",bigramArray);
    }

    public char[] decryptBigram(String encBigramStr){
        /* Rule 1:
          If the letters appear on the same row of your table,
          replace them with the letters to their immediate right respectively,
          wrapping around to the left side of the row if a letter in the original pair
          was on the right side of the row).
         */

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

        //TODO: this will also change when return type of plainTextToBigrams() changes

        char[][] keyGrid = this.keyGrid.getKeyGrid();
        char[] encryptedBigram = encBigramStr.toCharArray();
        char[] plainBigram = new char[2];
        //indices are [row,col]
        int[] firstCharIndices = this.keyGrid.searchKeyGrid(encryptedBigram[0]);
        int[] secondCharIndices = this.keyGrid.searchKeyGrid(encryptedBigram[1]);

        if (firstCharIndices[0] == secondCharIndices[0]){
            plainBigram[0] = keyGrid[firstCharIndices[0]][(firstCharIndices[1] - 1)>=0 ? (firstCharIndices[1] - 1) : (keyGrid[0].length-1)];
            plainBigram[1] = keyGrid[secondCharIndices[0]][(secondCharIndices[1] - 1)>=0 ? (secondCharIndices[1] - 1) : (keyGrid[0].length-1)];
        }
        else if (firstCharIndices[1] == secondCharIndices[1]){
            plainBigram[0] = keyGrid[(firstCharIndices[0] - 1)>=0 ? (firstCharIndices[0] - 1) : (keyGrid.length-1)][firstCharIndices[1]];
            plainBigram[1] = keyGrid[(secondCharIndices[0] - 1)>=0 ? (secondCharIndices[0] - 1) : (keyGrid.length-1)][secondCharIndices[1]];
        }
        else{
            plainBigram[0]=keyGrid[firstCharIndices[0]][secondCharIndices[1]];
            plainBigram[1]=keyGrid[secondCharIndices[0]][firstCharIndices[1]];

        }

        return plainBigram;

    }

}
