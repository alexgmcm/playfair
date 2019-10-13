package playfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigramList {

    private String[] bigramList;
    private String plaintext;

    public BigramList(String plaintext){
        this.plaintext=plaintext;
        bigramList=this.plaintextToBigrams();

        }

    public String[] getBigramList(){
        return this.bigramList;
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
        cleanPlaintextArr = this.plaintext.toUpperCase().replaceAll("[^A-Z]","").replaceAll("J","I").toCharArray();
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

}
