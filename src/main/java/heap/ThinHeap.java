package heap;

import java.util.ArrayList;

/**
 * Created by solovyevt on 03.09.16.
 */
public class ThinHeap<T>{
    public Node<T> first;
    public Node<T> last;

    private boolean isThin(Node<T> x){
        if(x.rank == 1) return x.child == null;
        else return x.child.rank + 1 != x.rank;
    }

    public void insert(Node<T> node){
        if (first == null){
            first = node;
            last = node;
        }
        else {
            if (node.key < first.key){
                node.right = first;
                first = node;
            }
            else {
                last.right = node;
                last = node;
            }
        }
    }

    public void insert(T value, int key){
        insert(new Node<>(value, key));

    }

    public T min(){
        return first.getValue();
    }

    public Node<T> getMin(){
        return first;
    }

    public static <T> ThinHeap<T> merge(ThinHeap<T> heap1, ThinHeap<T> heap2){
        if(heap1.getMin() == null) return heap2;
        if(heap2.getMin() == null) return heap1;
        if(heap1.first.key < heap2.first.key){
            heap1.last.right = heap2.first;
            heap1.last = heap2.last;
            return heap1;
        }
        else {
            heap2.last.right = heap1.first;
            heap2.last = heap1.last;
            return heap2;
        }
    }

    public T extractMin(){
        Node[] aux = new Node[100000];
        Node<T> temp = first;
        first = first.right;
        if(first == null){
            last = null;
        }
        Node<T> x = temp.child;
        Node<T> next = null;
        if(x != null){
            next = x.right;
        }
        while (x != null){
            if(isThin(x)){
                x.rank = x.rank - 1;
            }
            x.left = null;
            insert(x);
            x = next;
        }
        int max = -1;
        x = first;
        while(x != null){
            while(aux[x.rank] != null){
                next = x.right;
                if(aux[x.rank].key < x.key){
                    Node<T> buffer = aux[x.rank];
                    aux[x.rank] = x;
                    x = buffer;
                }
                aux[x.rank].right = x.child;
                x.child.left = aux[x.rank];
                aux[x.rank].left = x;
                x.child = aux[x.rank];
                aux[x.rank] = null;
                x.rank = x.rank + 1;
            }
            aux[x.rank] = x;
            if(x.rank > max){
                max = x.rank;
            }
            x = next;
        }
        ThinHeap<T> result = new ThinHeap<T>();
        for (int i = 0; i <= max; i++) {
            result.insert(aux[i]);
        }
        return temp.getValue();
    }

    private static class Node<T> {
        T value;
        int key;
        int rank;
        Node<T> child;
        Node<T> right;
        Node<T> left;

        Node(T value, int key) {
            this.value = value;
            this.key = key;
            right = null;
            left = null;
            child = null;
            rank = 0;
        }

        public int getKey() {return key;}
        public T getValue() {return value;}

        ArrayList<Node<T>> toList() {
            ArrayList<Node<T>> list = new ArrayList<>();
            Node<T> cur = this;
            while (cur != this){
                list.add(cur);
                if (cur.child != null) list.addAll(cur.child.toList());
                cur = cur.right;
            }
            return list;
        }
    }

}
