package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.Trie;
import java.util.*;
/**
 * FOR STAGE 3
 * @param <Value>
 */
public class TrieImpl<Value> implements Trie<Value> {
    private Node<Value> root = new Node<>(null);
    public TrieImpl(){}
    /**
     * add the given value at the given key
     * @param key
     * @param val
     */
    @Override
    public void put(String key, Value val) throws IllegalArgumentException{
        if(key == null){
            throw new IllegalArgumentException("cannot put null key");
        }
        if(!this.isClean(key)){throw new IllegalArgumentException("cannot put with special characters");}
        char[] ourLetters = key.toCharArray();
        Node<Value> ourNode = root;
        for(int i=0; i<ourLetters.length-1; i++){
            if(ourNode.getPointerNode((int)(ourLetters[i])) == null){
                Node<Value> newNode = new Node<>(null);
                ourNode.setNewPointer((int)(ourLetters[i]), newNode);
                ourNode = newNode;
            }else{
                ourNode = ourNode.getPointerNode((int)(ourLetters[i]));
            }
        }
        if(ourNode.getPointerNode((int)(ourLetters[ourLetters.length-1])) == null){
            Node<Value> newNode = new Node<>(val);
            ourNode.setNewPointer((int)(ourLetters[ourLetters.length-1]), newNode);
        }else{
            ourNode = ourNode.getPointerNode((int)(ourLetters[ourLetters.length-1]));
            ourNode.addNewValue(val);
        }
    }

    private boolean isClean(String word){
        char[] wordArray = word.toCharArray();
        for(char c : wordArray){
            if(!Character.isLetterOrDigit(c)){
                return false;
            }
        }
        return true;
    }
    /**
     * get all exact matches for the given key, sorted in descending order.
     * Search is CASE SENSITIVE.
     * @param key
     * @param comparator used to sort  values
     * @return a List of matching Values, in descending order
     */
    @Override
    public List<Value> getAllSorted(String key, Comparator<Value> comparator){
        if(key == null){
            throw new IllegalArgumentException("cannot put null key");
        }
        if(comparator == null){
            throw new IllegalArgumentException("null comparator");
        }
        char[] ourWord = key.toCharArray();
        ArrayList<Value> returnList = new ArrayList<>();
        if(!this.isClean(key)){return returnList;}
        Node<Value> ourNode = root;
        for(int i = 0; i < ourWord.length; i++){
            if(ourNode.getPointerNode((int)ourWord[i]) == null){
                return returnList;
            }else{
                ourNode = ourNode.getPointerNode((int)ourWord[i]);
            }
        }
        returnList = ourNode.getValues();
        if(returnList.isEmpty()){return returnList;}
        Set<Value> holder = new HashSet<>();
        holder.addAll(returnList);
        returnList.clear();
        for(Value v : holder){
            returnList.add(v);
        }
        returnList.sort(comparator);
        return returnList;
    }

    /**
     * get all matches which contain a String with the given prefix, sorted in descending order.
     * For example, if the key is "Too", you would return any value that contains "Tool", "Too", "Tooth", "Toodle", etc.
     * Search is CASE SENSITIVE.
     * @param prefix
     * @param comparator used to sort values
     * @return a List of all matching Values containing the given prefix, in descending order
     */
    @Override
    public List<Value> getAllWithPrefixSorted(String prefix, Comparator<Value> comparator){
        if(prefix == null){
            throw new IllegalArgumentException("cannot put null key");
        }
        if(comparator == null){
            throw new IllegalArgumentException("null comparator");
        }
        ArrayList<Value> returnList = new ArrayList<>();
        if(!this.isClean(prefix)){return returnList;}
        Node<Value> start = this.getPrefixNode(prefix);
        if(start == null){return returnList;}
        this.gather(returnList, start);
        Set<Value> holder = new HashSet<>();
        holder.addAll(returnList);
        ArrayList<Value> holderList = returnList;
        returnList.clear();
        for(Value v : holder){
            returnList.add(v);
        }
        returnList.sort(comparator);
        return returnList;
    }
    private void gather(ArrayList<Value> returnList, Node<Value> start){
        if(start.areThereValues()){
            returnList.addAll(start.getValues());
        }
        for(Node n : start.getPointersArray()){
            if(n != null){
                this.gather(returnList, n);
            }
        }
    }

    /**
     * Delete the subtree rooted at the last character of the prefix.
     * Search is CASE SENSITIVE.
     * @param prefix
     * @return a Set of all Values that were deleted.
     */
    @Override
    public Set<Value> deleteAllWithPrefix(String prefix){
        if(prefix == null){
            throw new IllegalArgumentException("null prefix inputted");
        }
        HashSet<Value> returnSet = new HashSet<>();
        if(!this.isClean(prefix)){return returnSet;}
        Node<Value> start = this.getPrefixNode(prefix);
        if(start == null){return returnSet;}
        ArrayList<Value> holder = new ArrayList<>();
        this.gather(holder, start);
        for(Value v : holder){
            returnSet.add(v);
        }
        start.emptyArray();
        return returnSet;
    }

    /**
     * Delete all values from the node of the given key (do not remove the values from other nodes in the Trie)
     * @param key
     * @return a Set of all Values that were deleted.
     */
    @Override
    public Set<Value> deleteAll(String key){
        if(key == null){
            throw new IllegalArgumentException("cannot put null key");
        }
        Node start = this.getPrefixNode(key);
        ArrayList<Value> ourList = start.getValues();
        HashSet<Value> returnSet = new HashSet<>();
        if(!this.isClean(key)){return returnSet;}
        for(Value v : ourList){
            returnSet.add(v);
        }
        start.emptyValues();
        if(!start.areTherePointers()){
            this.cleanAncestors(key, root, 0);
        }
        return returnSet;
    }

    /**
     * Remove the given value from the node of the given key (do not remove the value from other nodes in the Trie)
     * @param key
     * @param val
     * @return the value which was deleted. If the key did not contain the given value, return null.
     */
    @Override
    public Value delete(String key, Value val){
        if(key == null){
            throw new IllegalArgumentException("cannot put null key");
        }
        if(!this.isClean(key)){return null;}
        Node ourNode = this.getPrefixNode(key);
        if(ourNode == null){return null;}
        if(!ourNode.containsValue(val)){return null;}
        ourNode.deleteValue(val);
        if(!ourNode.areThereValues() && !ourNode.areTherePointers()){
            this.cleanAncestors(key, root, 0);
        }
        return val;
    }
    private Node getPrefixNode(String key){
        Node<Value> ourNode = root;
        char[] ourWord = key.toCharArray();
        for(char c : ourWord) {
            ourNode = ourNode.getPointerNode((int)c);
            if(ourNode == null){return null;}
        }
        return ourNode;
    }
    private Node cleanAncestors(String key, Node ourNode, int count){
        if(ourNode == null){
            return null;
        }
        if(count == key.length()){
            ourNode = null;
        }else{
            char c = key.charAt(count);
            Node next = ourNode.getPointerNode((int)c);
            this.cleanAncestors(key, next, count+1);
        }
        if(ourNode == null){return null;}
        for(int i = 0; i < 128; i++){
            if(ourNode.getPointerNode(i) != null){
                return ourNode;
            }
        }
        if(!ourNode.areTherePointers() && !ourNode.areThereValues()){
            ourNode = null;
        }
        ourNode.emptyArray();
        return null;
    }

    private class Node<Value> {
        private ArrayList<Value> values = new ArrayList<>();
        private Node[] pointers;

        private Node(Value value){
            if(value != null){
                this.values.add(value);
            }
            this.pointers = new Node[128];
        }
        private void addNewValue(Value v){
            values.add(v);
        }
        private void deleteValue(Value v){
            values.remove(v);
        }
        private boolean areTherePointers(){
            for(int i = 0; i<128; i++){
                if(pointers[i] != null){
                    return true;
                }
            }
            return false;
        }
        private ArrayList<Value> getValues(){
            return this.values;
        }
        private boolean containsValue(Value v){
            if(values.contains(v)){
                return true;
            }else{
                return false;
            }
        }
        private void setNewPointer(int place, Node node) {
            pointers[place] = node;
        }
        private Node getPointerNode(int index){
            return pointers[index];
        }
        private Node[] getPointersArray(){
            return this.pointers;
        }
        private boolean areThereValues(){
            if(values.isEmpty()){
                return false;
            }else{
                return true;
            }
        }
        private void emptyArray(){
            this.pointers = new Node[128];
        }
        private void emptyValues(){
            this.values = new ArrayList<>();
        }
    }
}
