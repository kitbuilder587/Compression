package com.kitSoft;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class StringStyleArrayBinaries implements ArrayBinaries{

    public static final char DEFAULT_DELIMITER = '/';
    public static final int SIZE = 4;

    public StringStyleArrayBinaries(){

    }

    public  String getBinaryCodeForDigit(char d){
        switch (d){
            case DEFAULT_DELIMITER: return "0000";
            case '0': return "0001";
            case '1': return "0010";
            case '2': return "0011";
            case '3': return "0100";
            case '4': return "0101";
            case '5': return "0110";
            case '6': return "0111";
            case '7': return "1000";
            case '8': return "1001";
            case '9': return "1010";
        }
        return "";
    }

    public  String getCodedInteger(int x){
        String string = String.valueOf(x);
        StringBuilder res = new StringBuilder();
        string.chars().forEach(c -> res.append(getBinaryCodeForDigit((char)c)));
        return res.toString();
    }

    public  char getDecodedChar(String bin){
        if(getBinaryCodeForDigit(DEFAULT_DELIMITER).equals(bin)) return DEFAULT_DELIMITER;
        for(int i=0;i<10;i++){
            if(getCodedInteger(i).equals(bin)) return (char)((int)'0' + i);
        }
        throw new IllegalStateException("Not a valuable string to decode");
    }

    @Override
    public BitArray getBinary(ArrayList<Integer> indexes){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<indexes.size();i++){
            res.append(getCodedInteger(indexes.get(i)));
            if(i != indexes.size() - 1) res.append(getBinaryCodeForDigit(DEFAULT_DELIMITER));
        }
        BitArray resArray = new BitArray(res.toString());
        return resArray;
    }

    @Override
    public ArrayList<Integer> getArray(BitArray array){
        ArrayList<ArrayList<Boolean>> chunks = array.spliter(SIZE);
        StringBuilder decodedString = new StringBuilder();
        for(int i=0;i<chunks.size();i++){
            BitArray chunk = new BitArray(chunks.get(i));
            decodedString.append(getDecodedChar(chunk.toString()));
        }
        String[] numbers = decodedString.toString().split(String.valueOf(DEFAULT_DELIMITER));
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.stream(numbers).forEach(str->{
            res.add(Integer.parseInt(str));
        });
        return res;
    }

/*    public static BitArray getBinary(ArrayList<Integer> indexes){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<indexes.size();i++){
            res.append(indexes.get(i));
            if(i != indexes.size() - 1) res.append("a");
        }
        BigInteger num = new BigInteger(res.toString(),11);
        BitArray resArray = new BitArray(num.toString(2));
        return resArray;
    }

    public static ArrayList<Integer> getArray(BitArray array){
        BigInteger resNum = new BigInteger(array.toString(),2);
        StringBuilder decodedString = new StringBuilder(resNum.toString(11));
        String[] numbers = decodedString.toString().split("a");
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.stream(numbers).forEach(str->{
            res.add(Integer.parseInt(str));
        });
        return res;
    }*/


}
