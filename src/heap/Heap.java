package heap;


import java.util.Collection;

/**
 * Created by 777 on 07.08.16.
 */
public  interface Heap<T>  {

    T findMin();
    boolean  add();
    T extractMin();
    T deleteMin();
    Heap<T> merge(Heap t1, Heap t2);




}
