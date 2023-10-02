package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.Stack;

public class StackImpl<T> implements Stack<T>{
    /**
     // @param element object to add to the Stack
     */
    private Node<T> head;
    public StackImpl(){
        head = new Node<>(null, null);
    }
    @Override
    public void push(T element){
        if(element != null){
            Node<T> newNode = new Node<>(element,head.getNext());
            head.setNewPointer(newNode);
        }
    }

    /**
     * removes and returns element at the top of the stack
     * @return element at the top of the stack, null if the stack is empty
     */
    @Override
    public T pop(){
        Node<T> ourNode = head.getNext();
        if(ourNode == null){
            return null;
        }
        head.setNewPointer(ourNode.getNext());
        return ourNode.getValue();
    }

    /**
     *
     * @return the element at the top of the stack without removing it
     */
    @Override
    public T peek(){
        Node<T> ourNode = head.getNext();
        if(ourNode == null){
            return null;
        }
        return ourNode.getValue();
    }

    /**
     *
     * @return how many elements are currently in the stack
     */
    @Override
    public int size(){
        Node<T> ourNode = head.getNext();
        int counter = 0;
        while(ourNode != null){
            counter ++;
            ourNode = ourNode.getNext();
        }
        return counter;
    }

    private class Node<T> {
        private T value;
        private Node<T> pointer;
        private Node(T value, Node<T> pointer){
            this.value = value;
            this.pointer = pointer;
        }
        private void setNewPointer(Node<T> n){
            this.pointer = n;
        }
        private T getValue(){
            return this.value;
        }
        private Node<T> getNext(){
            return this.pointer;
        }
    }
}