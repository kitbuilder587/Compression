package com.kitSoft;

import java.util.ArrayList;
import java.util.Arrays;

public class DynamicSizeArrayBinaries implements ArrayBinaries{
    @Override
    public BitArray getBinary(ArrayList<Integer> indexes) {
        StringBuilder res = new StringBuilder();
        int curPow = 2;
        int curX = 4;
        for(int i=0;i< indexes.size();i++){
            String result = Integer.toBinaryString(indexes.get(i));
            res.append(String.format("%" + curPow + "s",result).replaceAll(" ","0"));
            int maxEl = i+2;
            if(curX - 1 <= maxEl){
                curX *= 2;
                curPow++;
            }
        }
        return new BitArray(res.toString());
    }

    @Override
    public ArrayList<Integer> getArray(BitArray array) {
        ArrayList<Integer> res = new ArrayList<>();
        int curPow = 2;
        int curX = 4;
        int maxEl = 2;
        for(int i=0;i<array.data.size();){
            StringBuilder num =new StringBuilder();
            for(int j=0;j<curPow;j++){
                num.append(array.data.get(j+i) ? '1' : '0');
            }
            i+= curPow;
            res.add(Integer.parseInt(num.toString(),2));
            if(curX - 1 <= maxEl){
                curX *= 2;
                curPow++;
            }
            maxEl++;
        }
        return res;
    }

    public static void main(String[] args) {
        DynamicSizeArrayBinaries test = new DynamicSizeArrayBinaries();
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        BitArray fdfd = test.getBinary(arrayList);
        System.out.println(Arrays.toString(test.getArray(fdfd).toArray()));
    }
}
