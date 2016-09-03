package heap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by solovyevt on 02.09.16.
 */
public class FibonacciHeapTest {
    @Test
    public void toList() throws Exception {
        FibonacciHeap<String> fibonacciHeap = new FibonacciHeap<>();
        int elementsCount = 100000;
        String[] values = new String[elementsCount];
        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            fibonacciHeap.insert(values[i], i);
        }
        ArrayList<String> result = fibonacciHeap.toList();
        for(int i = 0; i < result.size(); i++){
            assert values[i].equals(result.get(i)) : values[i] + " AND " + result.get(i);
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        FibonacciHeap<String> fibonacciHeap = new FibonacciHeap<>();
        fibonacciHeap.insert("FIRST", 0);
        assert "FIRST".equals(fibonacciHeap.min()) : fibonacciHeap.min();
    }

    @Test
    public void extractMin() throws Exception {
        FibonacciHeap<String> fibonacciHeap = new FibonacciHeap<>();
        int elementsCount = 100000;
        String[] values = new String[elementsCount];
        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            fibonacciHeap.insert(values[i], i);
        }
        for (String value : values) {
            assert value.equals(fibonacciHeap.extractMin());
        }
    }

    @Test
    public void merge() throws Exception {
        int elementsCount = 100000;
        FibonacciHeap<String> even = new FibonacciHeap<>();
        FibonacciHeap<String> odd = new FibonacciHeap<>();
        String[] values = new String[elementsCount];

        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            if(i % 2 == 0) even.insert(values[i], i);
            else odd.insert(values[i], i);
        }

        FibonacciHeap<String> result = FibonacciHeap.merge(even, odd);
        for (String value : values) {
            String min = result.extractMin();
            assert value.equals(min) : min;
        }
    }



}