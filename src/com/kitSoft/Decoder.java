package com.kitSoft;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class Decoder {
    public Decoder(){

    }

    public String byteDecoder(byte[] buff){
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

    public String decode(String fileName, String treeFileName) throws IOException, ClassNotFoundException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
        byte[] buffer;
        buffer = inputStream.readAllBytes();
        String encodedString = byteDecoder(buffer);
        inputStream.close();
        ObjectInputStream treeInputStream =new ObjectInputStream(new BufferedInputStream(new FileInputStream(treeFileName)));
        TreeSerializer treeSerializer = (TreeSerializer) treeInputStream.readObject();
        HaffmanImplementation.Node tree = treeSerializer.getTree();
        treeInputStream.close();

        HaffmanImplementation haffmanImplementation = new HaffmanImplementation(tree,encodedString);
        return haffmanImplementation.decode(encodedString);
    }
}
