package com.kitSoft;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BitArray {

    ArrayList<Boolean> data;

    public BitArray(ArrayList<Boolean> data){
        this.data = data;
    }

    public BitArray(String binaryString){
        stringInitialization(binaryString);
    }

    private void stringInitialization(String binaryString) {
        data = new ArrayList<>();
        binaryString.chars().forEach(c -> data.add(c == '1'));
    }

    public static BitArray deserealize(byte[] buff){
        String dataString = byteDecoder(buff);
        BitArray bitArray = new BitArray(dataString);
        return bitArray;
    }

    public BitArray(byte[] buff){
        String dataString = pureByteDecoder(buff);
        stringInitialization(dataString);
    }

    public byte[] byteCoder(){
        ArrayList<ArrayList<Boolean>> chunks = spliter(8);
        byte[] res = new byte[chunks.size() + 4];
        byte[] bytes = intToByteArray(data.size());
        res[0] = bytes[0];
        res[1] = bytes[1];
        res[2] = bytes[2];
        res[3] = bytes[3];
        for(int i=0;i< chunks.size();i++){
            BitArray array = new BitArray(chunks.get(i));
            byte el = (byte) Integer.parseInt(array.toString(),2);
            res[i+4] = el;
        }
        return res;
    }

    public byte[] getPureBytes(){
        if(data.size() % 8 != 0) throw new IllegalArgumentException("Not contains pure byte information");
        byte[] buff = new byte[data.size() / 8];
        ArrayList<ArrayList<Boolean>> chunks = spliter(8);
        for(int i=0;i<chunks.size();i++){
            BitArray array = new BitArray(chunks.get(i));
            buff[i] = (byte) Integer.parseInt(array.toString(),2);
        }
        return buff;
    }

    //smart conversion (deserializer) from byte array to BitArray
    public static String byteDecoder(byte[] buff){
        ByteBuffer wrapped = ByteBuffer.wrap(buff,0,4);
        int size = wrapped.getInt();
        StringBuilder res = new StringBuilder();
        for(int i=4;i<buff.length && res.length() < size;i++){
            String s1 = String.format("%8s", Integer.toBinaryString(buff[i] & 0xFF)).replace(' ', '0');
            for(int j=0;j<8 && res.length() < size;j++){
                res.append(s1.charAt(j));
            }
        }
        return res.toString();
    }

    //simple conversion from byte array to binary array
    public static String pureByteDecoder(byte[] buff){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<buff.length;i++){
            String s1 = String.format("%8s", Integer.toBinaryString(buff[i] & 0xFF)).replace(' ', '0');
            res.append(s1);
        }
        return res.toString();
    }

    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

    public ArrayList<ArrayList<Boolean>> spliter(int len){
        int l=0;
        ArrayList<ArrayList<Boolean>> chunks = new ArrayList<>();
        while(l<data.size()){
            ArrayList<Boolean> chunk = new ArrayList<>();
            for(int i=0;i<len;i++){
                if(data.size() <= i+l){
                    chunk.add(false);
                }else {
                    chunk.add(data.get(i + l));
                }

            }
            l += len;
            chunks.add(chunk);
        }
        return chunks;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        data.forEach(b -> res.append(b ? '1' : '0'));
        return res.toString();
    }


}
