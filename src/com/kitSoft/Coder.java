package com.kitSoft;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class Coder {
    private String input;
    private byte[] buffer;
    private HaffmanImplementation.Node tree;


    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

    public String[] spliter(String s,int len){
        int l=0;
        ArrayList<String> chunks = new ArrayList<>();
        while(l<s.length()){
            StringBuilder chunk = new StringBuilder();
            for(int i=0;i<len;i++){
                if(s.length() <= i+l){
                    chunk.append('0');
                }else {
                    chunk.append(s.charAt(i + l));
                }

            }
            l += len;
            chunks.add(chunk.toString());
        }
        return chunks.toArray(new String[0]);
    }

    public byte[] byteCoder(String input){
        String[] chunks = spliter(input,8);
        byte[] res = new byte[chunks.length + 4];
        byte[] bytes = intToByteArray(input.length());
        res[0] = bytes[0];
        res[1] = bytes[1];
        res[2] = bytes[2];
        res[3] = bytes[3];
        for(int i=0;i< chunks.length;i++){
            byte el = (byte) Integer.parseInt(chunks[i],2);
            res[i+4] = el;
        }
        return res;
    }

    public Coder(String input) throws IOException {
        this.input = input;
        HaffmanImplementation haffmanImplementation = new HaffmanImplementation(input);
        tree = haffmanImplementation.getEncodingTree();
        String encodedInput = haffmanImplementation.encode();
        buffer = byteCoder(encodedInput);
    }

    public void writeToFile(String fileName,String treeFileName) throws IOException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName));
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();

        ObjectOutputStream treeOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(treeFileName)));
        TreeSerializer treeSerializer = new TreeSerializer(tree);
        treeOutputStream.writeObject(treeSerializer);
        treeOutputStream.flush();
        treeOutputStream.close();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Coder coder = new Coder("BLalablabla HAHAHAHA aHAH AHA Aasfgasfhjka sdfgjhaddasdhj a asda");
        coder.writeToFile("string.bin","stringTree.bin");

        Decoder decoder = new Decoder();
        System.out.println(decoder.decode("string.bin","stringTree.bin"));
    }
}
