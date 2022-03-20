package com.kitSoft;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TreeSerializer implements Serializable {
    ArrayList<SerializableNode> nodes;
    Map<SerializableNode,Integer> nodesIndexMapping;

    public TreeSerializer(HaffmanImplementation.Node tree){
        nodes = new ArrayList<>();
        nodesIndexMapping = new HashMap<>();
        exploreTree(tree);
        buildGraph(tree);
    }

    public void exploreTree(HaffmanImplementation.Node node){
        if(node == null) return;
        nodes.add(node);
        nodesIndexMapping.put(node,nodes.size() - 1);
        exploreTree(node.leftNode);
        exploreTree(node.rightNode);
    }

    public void buildGraph(HaffmanImplementation.Node node){
        if(node.leftNode != null){
            int v=nodesIndexMapping.get(node.leftNode);
            node.leftNodeIndex = v;
        }
        if(node.rightNode != null){
            int v=nodesIndexMapping.get(node.rightNode);
            node.rightNodeIndex= v;
        }
    }


    public void buildTree(HaffmanImplementation.Node node){
        if(node.rightNodeIndex != -1){
            node.rightNode = (HaffmanImplementation.Node) nodes.get(node.rightNodeIndex);
            buildTree(node.rightNode);
        }
        if(node.leftNodeIndex != -1){
            node.leftNode = (HaffmanImplementation.Node) nodes.get(node.leftNodeIndex);
            buildTree(node.leftNode);
        }
    }

    public HaffmanImplementation.Node getTree(){
        HaffmanImplementation.Node tree = (HaffmanImplementation.Node) nodes.get(0);
        buildTree(tree);
        return tree;
    }
}
