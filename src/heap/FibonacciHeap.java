package heap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 777 on 07.08.16.
 */
public class FibonacciHeap<T>  implements Heap<T>{

    FibonacciNode minElement;

    List<FibonacciNode<T>> roots;

    public FibonacciHeap (){

    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public T extractMin() {
        return null;
    }

    @Override
    public T deleteMin() {
        return null;
    }

    @Override
    public Heap<T> merge(Heap h1, Heap h2) {
       return null;

    }


}

class FibonacciNode<T> {

    int degree;
    boolean marked = false;
    T value;

    public  FibonacciNode (T elem){
        value = elem;
    }

}