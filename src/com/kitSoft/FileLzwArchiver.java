package com.kitSoft;

import java.io.*;

public class FileLzwArchiver {
    public static void compress(String pathToCompress,String pathToFile) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(pathToFile));
        byte[] buffer = inputStream.readAllBytes();
        inputStream.close();
        BitArray fileData = new BitArray(buffer);
        LzwImplementation lzwImplementation = new LzwImplementation(new DynamicSizeArrayBinaries());
        BitArray codedFile = lzwImplementation.code(fileData);

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathToCompress));
        outputStream.write(codedFile.byteCoder());
        outputStream.flush();
        outputStream.close();
    }

    public static void decompress(String pathToDecompress,String pathToFile) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(pathToFile));
        byte[] buffer = inputStream.readAllBytes();
        inputStream.close();
        BitArray fileData = BitArray.deserealize(buffer);
        LzwImplementation lzwImplementation = new LzwImplementation(new DynamicSizeArrayBinaries());
        BitArray decodedFile = lzwImplementation.decode(fileData);

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathToDecompress));
        outputStream.write(decodedFile.getPureBytes());
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        compress("testC.txt","test.txt");
        decompress("testD.txt","testC.txt");

        compress("imagesC.jpg","images.jpg");
        decompress("imagesD.jpg","imagesC.jpg");

        compress("ggggC.bmp","gggg.bmp");
        decompress("ggggD.bmp","ggggC.bmp");

        compress("rawC.bmp","raw.bmp");
        decompress("rawD.bmp","rawC.bmp");

        compress("soundC.wav","sound.wav");
        decompress("soundD.wav","soundC.wav");
    }
}
