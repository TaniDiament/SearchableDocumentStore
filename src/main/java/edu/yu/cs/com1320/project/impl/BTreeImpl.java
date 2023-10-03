package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.BTree;
import edu.yu.cs.com1320.project.stage5.PersistenceManager;

import java.io.IOException;

public class BTreeImpl<Key extends Comparable<Key>, Value> implements BTree<Key , Value> {
    private PersistenceManager<Key, Value> pm;
    private Node root = new Node();
    private int height = 0;
    private Node leftMostNode;

    public void BTreeImpl(){
        this.leftMostNode = root;
    }
    public Value get(Key k){
        Object stored =  this.get(this.root, k, this.height);
        if(stored == null){return null;}
        if(k == stored){
            try {
                Value returnval =  pm.deserialize(k);
                Entry ourE = this.getEntry(this.root, k, this.height);
                ourE.value = returnval;
                return returnval;
            }catch (Exception io){
                return null;
            }
        }else {
            try{
                return (Value)stored;
            }catch(Exception c){
                return null;
            }
        }
    }
    private Object get(Node currentNode, Key k, int height){
        Entry[] entries = currentNode.getEntries();
        if(height == 0){
            for(int i = 0; i < currentNode.entrycount; i++){
                if(k.equals(entries[i].key)){
                    Object val = entries[i].value;
                    return val;
                }
            }
            return null;
        }else{
            for(int i = 0; i < currentNode.entrycount; i++){
                if(i+1 == currentNode.entrycount || k.compareTo((Key) entries[i+1].key) < 0){
                    return this.get(entries[i].getChild(), k, height - 1);
                }
            }
        }
        return null;
    }
    private Entry getEntry(Node currentNode, Key k, int height){
        Entry[] entries = currentNode.getEntries();
        if(height == 0){
            for(int i = 0; i < currentNode.entrycount; i++){
                if(k.equals(entries[i].key)){
                    return entries[i];
                }
            }
            return null;
        }else{
            for(int i = 0; i < currentNode.entrycount; i++){
                if(i+1 == currentNode.entrycount || k.compareTo((Key) entries[i+1].key) < 0){
                    return this.getEntry(entries[i].getChild(), k, height - 1);
                }
            }
        }
        return null;
    }
    public Value put(Key k, Value v){
       Value alreadyThere = this.get(k);
       if(alreadyThere != null && alreadyThere == v){
           return alreadyThere;
       }
       Entry ourEntry = this.getEntry(this.root, k, this.height);
       if(ourEntry != null){
           try{
               pm.delete(k);
           }catch(IOException i){
               throw new RuntimeException();
           }
           ourEntry.value = v;
           return alreadyThere;
       }
       Node ourNode = this.put(this.root, k, v, this.height);
       return null;
    }
    private Node put(Node currentNode, Key k, Value v, int height){
        int j;
        Entry newEntry = new Entry(k, v, null);
        if(height == 0){
            for(j = 0; j < currentNode.entrycount; j++){
                if(k.compareTo(currentNode.pointers[j].getKey()) < 0){
                    break;
                }
            }
        }else{
            for(j = 0; j< currentNode.entrycount; j++){
                if(j+1 == currentNode.entrycount || k.compareTo(currentNode.pointers[j+1].getKey())<0){
                    Node newNode = this.put(currentNode.pointers[j++].getChild(), k, v, height-1);
                    if(newNode == null){
                        return null;
                    }
                    newEntry.key = newNode.pointers[0].key;
                    newEntry.value = null;
                    newEntry.child = newNode;
                    break;
                }
            }
        }
        for(int i = currentNode.entrycount; i > j; i--){
            currentNode.pointers[i] = currentNode.pointers[i - 1];
        }
        currentNode.pointers[j] = newEntry;
        currentNode.entrycount++;
        if(currentNode.entrycount < 6){
            return null;
        }
        else{
            return this.splitNode(currentNode);
        }
    }
    private Node splitNode(Node node){
        Node parent = node.getparent();
        Entry<Key>[] ourEntries = node.getEntries();
        Node newNode = new Node();
        newNode.setParent(parent);
        for(int i = 3; i<6; i++){
            newNode.pointers[i-3] = ourEntries[i];
            newNode.entrycount++;
            node.pointers[i] = null;
            node.entrycount--;
        }
        if(parent == null){
            Node newRoot = new Node();
            this.root = newRoot;
            Entry newEntry = new Entry<>(newNode.pointers[0].getKey(), newNode, newNode);
            newRoot.addEntry(newEntry);
            Entry newEntry2 = new Entry<>(node.pointers[0].getKey(), node, node);
            newRoot.addEntry(newEntry2);
            height++;
            newNode.setParent(newRoot);
            node.setParent(newRoot);
            return newNode;
        }
        Entry newEntry = new Entry<>(newNode.pointers[0].getKey(), newNode, newNode);
        parent.addEntry(newEntry);
        return newNode;
    }
    public void moveToDisk(Key k) throws Exception{
        if(pm == null){
            throw new IllegalStateException("need to pass in a PM");
        }
        try{
            Value stored = (Value) this.get(this.root, k, this.height);
            pm.serialize(k, stored);
            this.getEntry(this.root, k, this.height).value = k;
        }catch (ClassCastException c){
            return;
        }

    }
    public void setPersistenceManager(PersistenceManager<Key,Value> pm){
        this.pm = pm;
    }

    private class Node{
        private Entry<Key>[] pointers = new Entry[6];
        private Node parent;
        private int entrycount = 0;

        private Node(){
        }
        private int getCount(){
            return this.entrycount;
        }
        private void setParent(Node n){
            this.parent = n;
        }
        private Node getparent(){
            return this.parent;
        }
        private void setNull(int i){
            pointers[i] = null;
        }
        private Entry[] getEntries(){
            return this.pointers;
        }
        private void addEntry(Entry en){
            if(pointers[4] == null){
                pointers[4] = en;
                int i = 3;
                int j = 4;
                while(i >= 0 && (pointers[i]==null || en.getKey().compareTo(pointers[i].getKey()) < 0)){
                    pointers[j] = pointers[i];
                    pointers[i] = en;
                    i--;
                    j--;
                }
            }else{
                pointers[5] = en;
                int i = 4;
                int j = 5;
                while(en.getKey().compareTo(pointers[i]) < 0){
                    pointers[j] = pointers[i];
                    pointers[i] = en;
                    i--;
                    j--;
                }
                splitNode(this);
            }
            entrycount++;
        }
    }

    private class Entry<Key extends Comparable<Key>>{
        private Key key;
        private Object value;
        private Node child;

        private Entry(Key key, Object value, Node child){
            this.key = key;
            this.value = value;
            this.child = child;
        }
        private Node getChild(){
            return this.child;
        }
        private void setKey(Key k){
            this.key = k;
        }
        private void setChild(Node n){
            this.child = n;
        }
        private Object getValue(){
            return this.value;
        }
        private Key getKey(){
            return this.key;
        }
    }

}