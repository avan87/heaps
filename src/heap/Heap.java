package heap;

import trees.Tree;

import java.util.Collection;

/**
 * Created by 777 on 07.08.16.
 */
public  interface Heap<T>  {

    T findMin();
    boolean  add();
    T extractMin();
    T deleteMin();
    T merge(T t1, T t2);




}
