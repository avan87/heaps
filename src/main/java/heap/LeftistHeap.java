package heap;


public class LeftistHeap<T> {
    public LeftistHeap( ) {
        min = null;
    }

    public static <T> LeftistHeap<T> merge(LeftistHeap<T> heap1, LeftistHeap<T> heap2 ) {
        if(heap1 == null)
            return heap2;
        if(heap2 == null)
            return heap1;
        LeftistHeap<T> result = new LeftistHeap<T>();
        result.setMin(merge(heap1.min, heap2.min));
        return result;
    }


    private static <T> Node<T> merge(Node<T> h1, Node<T> h2 ){
        if( h1 == null )
            return h2;
        if( h2 == null )
            return h1;
        if(h1.key < h2.key)
            return mergeLeft( h1, h2 );
        else
            return mergeLeft( h2, h1 );
    }

    private static <T> Node<T> mergeLeft(Node<T> h1, Node<T> h2 ) {
        if(h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge( h1.right, h2 );
            if( h1.left.npl < h1.right.npl )
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }


    private static <T> void swapChildren( Node<T> t ) {
        Node<T> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }


    public void insert(T value, int key) {
        min = merge( new Node<>(value, key), min);
    }


    public T findMin( ) {
        if(isEmpty())
            return null;
        return min.value;
    }


    public T extractMin( ) {
        if( isEmpty( ) )
            return null;

        T minItem = min.value;
        min = merge( min.left, min.right );
        return minItem;
    }


    public boolean isEmpty( ) {
        return min == null;
    }

    public void makeEmpty( ) {
        min = null;
    }

    public Node<T> getMin() {
        return min;
    }

    public T min(){
        return min.value;
    }

    public void setMin(Node<T> min) {
        this.min = min;
    }

    private Node<T> min;    // min

    private static class Node<T> {
        Node(T value, int key) {
            this(value, key, null, null);
        }

        Node(T value, int key, Node<T> lt, Node<T> rt) {
            this.value = value;
            this.key = key;
            left = lt;
            right = rt;
            npl = 0;
        }

        T value;
        int key;
        Node<T> left;
        Node<T> right;
        int npl;
    }
}