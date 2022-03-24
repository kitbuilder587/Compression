package com.kitSoft;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class Decoder {
    public Decoder(){

    }



    public String decode(String fileName, String treeFileName) throws IOException, ClassNotFoundException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
        byte[] buffer;
        buffer = inputStream.readAllBytes();
        BitArray array = BitArray.deserealize(buffer);
        String encodedString = array.toString();
        inputStream.close();
        ObjectInputStream treeInputStream =new ObjectInputStream(new BufferedInputStream(new FileInputStream(treeFileName)));
        TreeSerializer treeSerializer = (TreeSerializer) treeInputStream.readObject();
        HaffmanImplementation.Node tree = treeSerializer.getTree();
        treeInputStream.close();

        HaffmanImplementation haffmanImplementation = new HaffmanImplementation(tree,encodedString);
        return haffmanImplementation.decode(encodedString);
    }
}
