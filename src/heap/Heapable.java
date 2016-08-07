package heap;

/**
 * Created by 777 on 07.08.16.
 */
public interface Heapable<T> {

    T findMin();
    boolean  insert();
    T extractMin();
    T deleteMin();


}
