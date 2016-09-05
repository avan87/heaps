package heap;

import org.junit.Test;

import java.util.List;

/**
 * Created by solovyevt on 05.09.16.
 */
public class BinomialHeapTest {
    @Test
    public void insert() throws Exception {
        BinomialHeap<String> binomialHeap = new BinomialHeap<>();
        binomialHeap.insert("FIRST", 0);
        assert "FIRST".equals(binomialHeap.min()) : binomialHeap.min();
    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void merge() throws Exception {
        int elementsCount = 10000;
        BinomialHeap<String> even = new BinomialHeap<>();
        BinomialHeap<String> odd = new BinomialHeap<>();
        String[] values = new String[elementsCount];

        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            if(i % 2 == 0) even.insert(values[i], i);
            else odd.insert(values[i], i);
        }

        BinomialHeap<String> result = BinomialHeap.merge(even, odd);
        for (String value : values) {
            String min = result.extractMin();
            assert value.equals(min) : min;
        }
    }

    @Test
    public void extractMin() throws Exception {
        BinomialHeap<String> binomialHeap = new BinomialHeap<>();
        int elementsCount = 1000;
        String[] values = new String[elementsCount];
        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            binomialHeap.insert(values[i], i);
        }
        for (String value : values) {
            assert value.equals(binomialHeap.extractMin());
        }
    }

    @Test
    public void decreaseKey() throws Exception {
        BinomialHeap<String> binomialHeap = new BinomialHeap<>();
        int elementsCount = 10;
        for(int i = 1; i <= elementsCount; i++){
            binomialHeap.insert(String.valueOf(i), i);
        }
        binomialHeap.decreaseKey(String.valueOf(elementsCount), 0);
        assert String.valueOf(elementsCount).equals(binomialHeap.min()) : binomialHeap.min() + " AND " + String.valueOf(elementsCount);
    }

    @Test
    public void toList() throws Exception {
        BinomialHeap<String> binomialHeap = new BinomialHeap<>();
        int elementsCount = 1000;
        String[] values = new String[elementsCount];
        for(int i = 0; i < values.length; i++){
            values[i] = String.valueOf(i);
            binomialHeap.insert(values[i], i);
        }
        List<String> result = binomialHeap.toList();
        for(int i = 0; i < result.size(); i++){
            assert values[i].equals(result.get(i)) : values[i] + " AND " + result.get(i);
//            System.out.println(result.get(i));
        }
    }


}