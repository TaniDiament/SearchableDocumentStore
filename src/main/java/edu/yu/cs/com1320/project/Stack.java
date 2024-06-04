<<<<<<< HEAD
package edu.yu.cs.com1320.project;

/**
 * @param <T>
 */
public interface Stack<T>
{
    /**
     * @param element object to add to the Stack
     */
    void push(T element);

    /**
     * removes and returns element at the top of the stack
     * @return element at the top of the stack, null if the stack is empty
     */
    T pop();

    /**
     *
     * @return the element at the top of the stack without removing it
     */
    T peek();

    /**
     *
     * @return how many elements are currently in the stack
     */
    int size();
=======
package edu.yu.cs.com1320.project;

/**
 * @param <T>
 */
public interface Stack<T>
{
    /**
     * @param element object to add to the Stack
     */
    void push(T element);

    /**
     * removes and returns element at the top of the stack
     * @return element at the top of the stack, null if the stack is empty
     */
    T pop();

    /**
     *
     * @return the element at the top of the stack without removing it
     */
    T peek();

    /**
     *
     * @return how many elements are currently in the stack
     */
    int size();
>>>>>>> fd77063a3d4afb76b6777a38f3f83134aeed8ddf
}