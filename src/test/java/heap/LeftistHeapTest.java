package heap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by solovyevt on 05.09.16.
 */
public class LeftistHeapTest {
    @Test
    public void merge() throws Exception {
        int elementsCount = 10000;
        LeftistHeap<String> even = new LeftistHeap<>();
        LeftistHeap<String> odd = new LeftistHeap<>();
        String[] values = new String[elementsCount];

        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            if(i % 2 == 0) even.insert(values[i], i);
            else odd.insert(values[i], i);
        }

        LeftistHeap<String> result = LeftistHeap.merge(even, odd);
        for (String value : values) {
            String min = result.extractMin();
            assert value.equals(min) : min;
        }
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void findMin() throws Exception {

    }

    @Test
    public void extractMin() throws Exception {
        LeftistHeap<String> leftistHeap = new LeftistHeap<>();
        int elementsCount = 10000;
        String[] values = new String[elementsCount];
        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            leftistHeap.insert(values[i], i);
        }
        for (String value : values) {
            assert value.equals(leftistHeap.extractMin()) : value + " AND " + leftistHeap.min();
        }
    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void makeEmpty() throws Exception {

    }

    @Test
    public void getMin() throws Exception {

    }

    @Test
    public void setMin() throws Exception {

    }

}