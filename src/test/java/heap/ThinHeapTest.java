package heap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by solovyevt on 05.09.16.
 */
public class ThinHeapTest {
    @Test
    public void insert() throws Exception {

    }

    @Test
    public void insert1() throws Exception {

    }

    @Test
    public void min() throws Exception {

    }

    @Test
    public void getMin() throws Exception {

    }

    @Test
    public void merge() throws Exception {
        int elementsCount = 10;
        ThinHeap<String> even = new ThinHeap<>();
        ThinHeap<String> odd = new  ThinHeap<>();
        String[] values = new String[elementsCount];

        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            if(i % 2 == 0) even.insert(values[i], i);
            else odd.insert(values[i], i);
        }

        ThinHeap<String> result = ThinHeap.merge(even, odd);
        for (String value : values) {
            String min = result.extractMin();
            assert value.equals(min) : value + " AND " + min;
        }
    }

    @Test
    public void extractMin() throws Exception {

    }

}