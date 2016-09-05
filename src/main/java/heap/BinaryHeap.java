package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by solovyevt on 20.08.16.
 */
public class BinaryHeap<T extends Comparable> implements Heap<T> {
    private int currentSize;
    private List<T> items;
    private int maxSize;


    public BinaryHeap(int capacity) {
        currentSize = 0;
        items = new ArrayList<>(capacity);
        maxSize = capacity;
    }

    @Override
    public T findMin() {
        if(isEmpty())
            return null;
        return items.get(0);
    }

    @Override
    public boolean insert(T item) {
        if(isFull())
            return false;
        int hole = currentSize++;
        while(hole > 0 && item.compareTo(items.get(hole/2)) < 0){
            items.set(hole, items.get(hole / 2));
            hole /= 2;
        }
        items.add(hole, item);
        return true;
    }

    @Override
    public T extractMin() {
        if(isEmpty())
            return null;
        T minItem = findMin();
        currentSize--;
        items.add(0, items.get(currentSize));

        //@TODO Здесь должно быть процеживание вниз

        return minItem;
    }

    @Override
    public T deleteMin() {
        return null;
    }

    @Override
    public Heap<T> merge(Heap heap) {
        return null;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    private void percolateDown(int hole){
      int child;
      T tmp = items.get(hole);

      while(hole * 2 <= currentSize) {
          child = hole * 2;
          if(child != currentSize && items.get(child + 1).compareTo(items.get(child)) < 0)
              child++;
          if( items.get(child).compareTo(tmp) < 0 ){
              items.set(hole, items.get(child));
              hole = child;
          }
          else
              break;
      }
      items.set(hole, tmp);
    }

    public static void main(String... main){
        BinaryHeap<String> heap = new BinaryHeap<>(3);
        heap.insert("first");
        heap.insert("second");
        heap.insert("third");
        while(!heap.isEmpty()){
            System.out.println(heap.extractMin());
        }
    }
}
