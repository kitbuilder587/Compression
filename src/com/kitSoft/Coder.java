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






    public Coder(String input) throws IOException {
        this.input = input;
        HaffmanImplementation haffmanImplementation = new HaffmanImplementation(input);
        tree = haffmanImplementation.getEncodingTree();
        String encodedInput = haffmanImplementation.encode();
        BitArray array = new BitArray(encodedInput);
        buffer = array.byteCoder();
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
