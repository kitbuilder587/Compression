package com.kitSoft;

import java.io.Serializable;

public class SerializableNode implements Serializable {
    public Character key = null;
    public int value;
    int leftNodeIndex = -1;
    int rightNodeIndex = -1;
    public SerializableNode(){

    }

    public SerializableNode(char key, int value){
        this.key = key;
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
