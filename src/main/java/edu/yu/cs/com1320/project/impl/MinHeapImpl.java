<<<<<<< HEAD
package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.MinHeap;
import java.util.NoSuchElementException;

public class MinHeapImpl<E extends Comparable<E>> extends MinHeap<E> {

    public MinHeapImpl(){
        this.elements = (E[]) new Comparable[2];
    }


    @Override
    protected int getArrayIndex(E element){
        for(int i = 1; i< elements.length; i++){
            if(elements[i] != null){
                if(elements[i].equals(element)){
                    return i;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void reHeapify(E element) {
        try{
            int ourplace = this.getArrayIndex(element);
        }catch(NoSuchElementException e){
            throw new NoSuchElementException();
        }
        this.downHeap(this.getArrayIndex(element));
        this.upHeap(this.getArrayIndex(element));
    }
    @Override
    protected void doubleArraySize(){
        Comparable<E>[] newArray;
        newArray = (E[]) new Comparable[elements.length*2];
        for(int i = 0; i < elements.length; i++){
            newArray[i] = elements[i];
        }
        elements = (E[]) newArray;
    }

}
=======
package edu.yu.cs.com1320.project.impl;
import edu.yu.cs.com1320.project.MinHeap;
import java.util.NoSuchElementException;

public class MinHeapImpl<E extends Comparable<E>> extends MinHeap<E> {

    public MinHeapImpl(){
        this.elements = (E[]) new Comparable[2];
    }


    @Override
    protected int getArrayIndex(E element){
        for(int i = 1; i< elements.length; i++){
            if(elements[i] != null){
                if(elements[i].equals(element)){
                    return i;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void reHeapify(E element) {
        try{
            int ourplace = this.getArrayIndex(element);
        }catch(NoSuchElementException e){
            throw new NoSuchElementException();
        }
        this.downHeap(this.getArrayIndex(element));
        this.upHeap(this.getArrayIndex(element));
    }
    @Override
    protected void doubleArraySize(){
        Comparable<E>[] newArray;
        newArray = (E[]) new Comparable[elements.length*2];
        for(int i = 0; i < elements.length; i++){
            newArray[i] = elements[i];
        }
        elements = (E[]) newArray;
    }

}
>>>>>>> fd77063a3d4afb76b6777a38f3f83134aeed8ddf
