package heap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 777 on 07.08.16.
 */
public class FibonacciHeap<T> {

    FibonacciNode minElement;



    public FibonacciHeap (){

    }

    public T findMin() {
        return null;
    }

    public boolean add() {
        return false;
    }

    public T extractMin() {
        return null;
    }

    public T deleteMin() {
        return null;
    }

    public Heap<T> merge(Heap h1, Heap h2) {
       return null;

    }


}

class FibonacciNode<T> {

    private int degree;
    private boolean marked = false;
    private T value;
    private FibonacciNode<T> left;
    private FibonacciNode<T> right;
    private FibonacciNode<T> parent;
    private FibonacciNode<T> child;

    public int getDegree() {
        return degree;
    }

    public boolean isMarked() {
        return marked;
    }

    public T getValue() {
        return value;
    }

    public FibonacciNode<T> getLeft() {
        return left;
    }

    public FibonacciNode<T> getRight() {
        return right;
    }

    public FibonacciNode<T> getParent() {
        return parent;
    }

    public FibonacciNode<T> getChild() {
        return child;
    }

    public  FibonacciNode (T elem){
        value = elem;
    }

}