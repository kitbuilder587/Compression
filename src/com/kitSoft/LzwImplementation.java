package com.kitSoft;

import java.util.*;

public class LzwImplementation {


    private ArrayBinaries arrayBinaries;

    public LzwImplementation(ArrayBinaries arrayBinaries){
        this.arrayBinaries = arrayBinaries;
    }

    public BitArray code(BitArray array){
        System.out.println("ARRAY SIZE: " + array.data.size());
        Map<String,Integer> table = new HashMap<>();
        table.put("0",1);
        table.put("1",2);
        int lastId = 2;
        StringBuilder cur = new StringBuilder();
        ArrayList<Integer> resIntegers = new ArrayList<>();
        int len = 0;
        for(boolean b : array.data){
            char curB = b? '1' : '0';
            cur.append(curB);
            String newCur = cur.toString();
            cur.deleteCharAt(cur.length() -1);
            if(table.containsKey(newCur)){
                cur.append(curB);
            }else{
                lastId++;
                table.put(newCur,lastId);
                resIntegers.add(table.get(cur.toString()));
                cur = new StringBuilder(String.valueOf(curB));
            }
            len++;
            if(len % 1000 == 0) System.out.println(len + " --------- OUT OF ---------- " + array.data.size());
        }
        resIntegers.add(table.get(cur.toString()));

        return arrayBinaries.getBinary(resIntegers);
    }

    public BitArray decode(BitArray input){
        ArrayList<Integer> array = arrayBinaries.getArray(input);
        System.out.println("ARRAY SIZE: " + array.size());
        Map<Integer,String> table = new HashMap<>();
        Set<String> emittedPhrases = new HashSet<>();
        table.put(1,"0");
        emittedPhrases.add("0");
        table.put(2,"1");
        emittedPhrases.add("1");
        int lastIndex = 2;
        StringBuilder res = new StringBuilder();
        StringBuilder lastEmitted = new StringBuilder();
        for(int i=0;i<array.size();i++){
            int l = array.get(i);
            if(table.containsKey(l)){
                String w = table.get(l);
                res.append(w);
                lastEmitted.append(w.charAt(0));
                if(!emittedPhrases.contains(lastEmitted.toString())) {
                    lastIndex++;
                    table.put(lastIndex, lastEmitted.toString());
                    emittedPhrases.add(lastEmitted.toString());
                }
                lastEmitted = new StringBuilder(w);
            }else{
                lastEmitted.append(lastEmitted.charAt(0));
                String v = lastEmitted.toString();
                res.append(v);
                if(!emittedPhrases.contains(v)) {
                    lastIndex++;
                    table.put(lastIndex, v);
                    emittedPhrases.add(v);
                }
            }
            if(i % 1000 == 0) System.out.println(i + " --------- OUT OF ---------- " + array.size());
        }
        return new BitArray(res.toString());
    }

    public static void main(String[] args) {
        LzwImplementation lzwImplementation = new LzwImplementation(new StringStyleArrayBinaries());
        BitArray array = lzwImplementation.code(new BitArray("00000011000000010110001000000000000000000000000000000000000000000000000000"));
        System.out.println(array.toString());
        System.out.println(lzwImplementation.decode(array));
    }
}
