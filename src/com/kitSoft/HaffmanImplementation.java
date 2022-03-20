package com.kitSoft;

import java.io.Serializable;
import java.util.*;





public class HaffmanImplementation implements Serializable{

    private String input;
    private Map<Character,Integer> frequency;
    private Node encodingTree;
    private Map<Character,String> paths;

    public void initByInput(){
        frequency = new HashMap<>();
        paths = new HashMap<>();

        input.chars().forEach(c -> {
            Integer count = frequency.get((char)c);
            if(count == null){
                count = 0;
                frequency.put((char)c,count);
            }
            frequency.put((char)c,count + 1);
        });

        if(frequency.size() < 2) throw new IllegalArgumentException("String size must be greater than 1");

        Comparator<Node> pairComparator = Comparator.comparingInt(Node::getValue);

        PriorityQueue<Node> queue = new PriorityQueue<>(pairComparator);

        frequency.forEach((c,i) -> queue.add(new Node(c,i)));

        while (queue.size() > 1){
            Node n1 = queue.remove();
            Node n2 = queue.remove();
            Node newNode = new Node();
            newNode.leftNode = n1;
            newNode.rightNode = n2;
            newNode.value = n1.value + n2.value;
            queue.add(newNode);
        }

        encodingTree = queue.remove();

        exploreTree(encodingTree, "");
    }


    public HaffmanImplementation(Node tree,String encodedInput){
        encodingTree = tree;
        input = decode(encodedInput);
        initByInput();
    }


    public HaffmanImplementation(String input){
        this.input = input;
        initByInput();
    }

    private void exploreTree(Node node,String path){
        if(node == null) return;
        if(node.key != null){
            paths.put(node.key,path);
        }else{
            exploreTree(node.leftNode,path + "0");
            exploreTree(node.rightNode,path + "1");
        }
    }

    public String encode(){
        StringBuilder res = new StringBuilder();
        input.chars().forEach(c -> res.append(paths.get((char)c)));
        return res.toString();
    }

    public String decode(String s){
        Node current = encodingTree;
        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) == '0'){
                current = current.leftNode;
            }else{
                current = current.rightNode;
            }
            if(current.key != null){
                res.append(current.key);
                current = encodingTree;
            }
        }
        return res.toString();
    }

    class Node extends SerializableNode {

        public Node leftNode = null;
        public Node rightNode = null;

        public Node(){
            super();
        }

        public Node(char key, int value){
            super(key,value);
        }

    }

    public Node getEncodingTree(){
        return encodingTree;
    }

    public static void main(String[] args) {
        HaffmanImplementation hi = new HaffmanImplementation("BLalablabla HAHAHAHA aHAH AHA Aasfgasfhjka sdfgjhaddasdhj a asda");
        String encoded =hi.encode();
        System.out.println(encoded);
        System.out.println(hi.decode(encoded));
    }



}
