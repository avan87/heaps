package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BinomialHeap<T> {

    public Node<T> getMin() {
        return min;
    }

    public void setMin(Node<T> min) {
        this.min = min;
    }

    private Node<T> min;

    public T min(){
        return min.value;
    }

    public BinomialHeap()
    {
        min = null;
    }

    /**
     *
     */
    public void insert(T value, int key) {
        Node<T> node = new Node<>(value, key);
        BinomialHeap<T> h = new BinomialHeap<>();
        h.min = node;
        BinomialHeap<T> newH = merge(this, h);
        min = newH.min;
    }

    public boolean isEmpty(){
        return min == null;
    }

    /**
     */
    private static <T> Node<T> mergeRootLists(BinomialHeap<T> h1, BinomialHeap<T> h2) {
//        BinomialHeap<T> h = new BinomialHeap<>();
        if (h1.getMin() == null)
            return h2.getMin();
        else if (h2.getMin() == null)
            return h1.getMin();
        Node<T> min;
        Node<T> last;
        Node<T> h1Next = h1.getMin(), h2Next = h2.getMin();
        if (h1.getMin().degree <= h2.getMin().degree) {
            min = h1.getMin();
            h1Next = h1Next.sibling;
        } else {
            min = h2.getMin();
            h2Next = h2Next.sibling;
        }

        last = min;
//        h.setMin(min);
        while (h1Next != null && h2Next != null) {
            if (h1Next.degree <= h2Next.degree) {
                last.sibling = h1Next;
                h1Next = h1Next.sibling;
            } else {
                last.sibling = h2Next;
                h2Next = h2Next.sibling;
            }

            last = last.sibling;
        }
        if (h1Next != null)
            last.sibling = h1Next;
        else
            last.sibling = h2Next;

        return min;
    }

    private static <T> Node<T> mergeRootList(BinomialHeap<T> h1, BinomialHeap<T> h2){
        // If either root list is empty, just return the other.
        if (h1.getMin() == null)
            return h2.getMin();
        else if (h2.getMin() == null)
            return h1.getMin();
        else {
            Node<T> head;        // head of merged list
            Node<T> tail;        // last node added to merged list
            Node<T> h1Next = h1.getMin(),
                    h2Next = h2.getMin(); // next nodes to be examined in h1 and h2

            //Initialize for comparision
            //head contains smallest one
            if (h1.getMin().degree <= h2.getMin().degree) {
                head = h1.getMin();
                h1Next = h1Next.sibling;
            }
            else {
                head = h2.getMin();
                h2Next = h2Next.sibling;
            }

            tail = head;
            // Go through both root lists until one of them is
            // exhausted. merge them in to a sorted list based on degree

            while (h1Next != null && h2Next != null) {
                if (h1Next.degree <= h2Next.degree) {
                    tail.sibling = h1Next;
                    h1Next = h1Next.sibling;
                }
                else {
                    tail.sibling = h2Next;
                    h2Next = h2Next.sibling;
                }

                tail = tail.sibling;
            }

            //Merge remaining trees from other list that didn't
            //run out first.
            if (h1Next != null)
                tail.sibling = h1Next;
            else
                tail.sibling = h2Next;

            return head;
        }
    }

    private static <T> Node<T> mergeRootList(Node<T> min, Node<T> x, Node<T> y){
        if (x == null && y == null) return min;
        else if (x == null) min.sibling = mergeRootList(y, null, y.sibling);
        else if (y == null) min.sibling = mergeRootList(x, x.sibling, null);
        else if (x.degree < y.degree) min.sibling = mergeRootList(x, x.sibling, y);
        else                        min.sibling = mergeRootList(y, x, y.sibling);
        return min;
    }

    public static <T> BinomialHeap<T> merge(BinomialHeap<T> h1, BinomialHeap<T> h2) {
//        BinomialHeap<T> h = new BinomialHeap<>();
//        h.setMin(mergeRootList(new Node<>(null, 0), h1.getMin(), h2.getMin()).sibling);
//        Node<T> x = h.getMin();
//        Node<T> prevx = null, nextx = x.sibling;
//        while (nextx != null) {
//            if (x.degree < nextx.degree ||
//                    (nextx.sibling != null && nextx.sibling.degree == x.degree)) {
//                prevx = x; x = nextx;
//            } else if (nextx.key > x.key) {
//                x.sibling = nextx.sibling;
//                nextx.link(x);
//            } else {
//                if (prevx == null) { h.setMin(nextx); }
//                else { prevx.sibling = nextx; }
//                x.link(nextx);
//                x = nextx;
//            }
//            nextx = x.sibling;
//        }
//        return h;
//--------------------------
//        h1.setMin(null);
//        h2.setMin(null);
//        if (h.getMin() == null)
//            return h;
//
//        Node<T> curH = h.getMin();
//        Node<T> curH1 = h1.getMin();
//        Node<T> curH2 = h2.getMin();
//
//        while(curH1 != null && curH2 != null){
//            if(curH1.degree < curH2.degree){
//                curH.sibling = curH1;
//                curH = curH1;
//                curH1 = curH1.sibling;
//            }
//            else {
//                curH.sibling = curH2;
//                curH = curH2;
//                curH2 = curH2.sibling;
//            }
//        }
//        if (curH1 == null) {
//            while (curH2 != null) {
//                curH.sibling = curH2;
//                curH2 = curH2.sibling;
//            }
//        }
//        else {
//            while (curH1 != null){
//                curH.sibling = curH1;
//                curH1 = curH1.sibling;
//            }
//        }
//        curH = h.min;
//        while (curH != null && curH.sibling != null){
//            if (curH.degree == curH.sibling.degree){
//                curH.parent = curH.sibling;
//                Node<T> tmp = curH.sibling;
//                curH.sibling = curH.sibling.child;
//                curH = tmp;
//            }
//            curH = curH.sibling;
//        }
//        return h;

        //---------------------------

//        while (curH2 != null) {
//            if (curH.degree != curH2.degree
//                    || (curH2.sibling != null && curH2.sibling.degree == curH.degree)) {
//                // Cases 1 and 2.
//                curH1 = curH;
//                curH = curH2;
//            } else {
//                if (curH.key < curH2.key) {
//                    // Case 3.
//                    curH.sibling = curH2.sibling;
//                    curH2.link(curH);
//                } else {
//                    // Case 4.
//                    if (curH1 == null)
//                        h.setMin(curH2);
//                    else
//                        curH1.sibling = curH2;
//
//                    curH.link(curH2);
//                    curH = curH2;
//                }
//            }
//
//            curH2 = curH.sibling;
//        }

//        return h;

//        if (h1 == null) return h2;
//        if (h2 == null) return h1;
//        BinomialHeap<T> h = new BinomialHeap<>();
//        this.head = merge(new Node(), this.head, heap.head).sibling;
//        Node x = this.head;
//        Node prevx = null, nextx = x.sibling;
//        while (nextx != null) {
//            if (x.order < nextx.order ||
//                    (nextx.sibling != null && nextx.sibling.order == x.order)) {
//                prevx = x; x = nextx;
//            } else if (greater(nextx.key, x.key)) {
//                x.sibling = nextx.sibling;
//                link(nextx, x);
//            } else {
//                if (prevx == null) { this.head = nextx; }
//                else { prevx.sibling = nextx; }
//                link(x, nextx);
//                x = nextx;
//            }
//            nextx = x.sibling;
//        }
//        return this;
        BinomialHeap<T> h = new BinomialHeap<>();
        h.setMin(mergeRootList(h1, h2));
        h1.setMin(null);
        h2.setMin(null);

        if (h.getMin() == null)
            return h;

        Node<T> prevX = null;
        Node<T> x = h.getMin();
        Node<T> nextX = x.sibling;

        while (nextX != null) {
            if (x.degree != nextX.degree || (nextX.sibling != null && nextX.sibling.degree == x.degree)) {
                prevX = x;
                x = nextX;
            }
            else {
                if (x.key < nextX.key) {
                    x.sibling = nextX.sibling;
                    nextX.link(x);
                }
                else {
                    if (prevX == null)
                        h.setMin(nextX);
                    else
                        prevX.sibling = nextX;

                    x.link(nextX); //combine trees with same degree
                    x = nextX;
                }
            }

            nextX = x.sibling;
        }

        return h;

    }



    /**
     *Make a root list with min of trees from BinomialHeaps h1 and h2
     *in sorted order of degree.
     */

//    private static <T> Node<T> mergeRootList(BinomialHeap<T> h1, BinomialHeap<T> h2)
//    {
//        // If either root list is empty, just return the other.
//        if (h1.min == null)
//            return h2.min;
//        else if (h2.min == null)
//            return h1.min;
//        else {
//            Node<T> head;        // min of merged list
//            Node<T> tail;        // last node added to merged list
//            Node<T> h1Next = h1.min,
//                    h2Next = h2.min; // next nodes to be examined in h1 and h2
//
//            //Initialize for comparision
//            //min contains smallest one
//            if (h1.min.degree <= h2.min.degree) {
//                head = h1.min;
//                h1Next = h1Next.sibling;
//            }
//            else {
//                head = h2.min;
//                h2Next = h2Next.sibling;
//            }
//
//            tail = head;
//            // Go through both root lists until one of them is
//            // exhausted. mergeRootLists them in to a sorted list based on degree
//
//            while (h1Next != null && h2Next != null) {
//                if (h1Next.degree <= h2Next.degree) {
//                    tail.sibling = h1Next;
//                    h1Next = h1Next.sibling;
//                }
//                else {
//                    tail.sibling = h2Next;
//                    h2Next = h2Next.sibling;
//                }
//
//                tail = tail.sibling;
//            }
//
//            //Merge remaining trees from other list that didn't
//            //run out first.
//            if (h1Next != null)
//                tail.sibling = h1Next;
//            else
//                tail.sibling = h2Next;
//
//            return head;
//        }
//    }

    public T extractMin() {
        // for an empty binomial heap.
        if (min == null)
            return null;

        Node<T> x = min;
        Node<T> y = x.sibling; // current node
        Node<T> pred = x;      // y prev
        Node<T> xPred = null;  // x prev

        // Determine the node x with the minimum key in the root list.
        while (y != null) {
            if (y.key < x.key) {
                x = y;
                xPred = pred;
            }
            pred = y;
            y = y.sibling;
        }
        //remove node x from root list.
        if (x == min)
            min = x.sibling;
        else
            xPred.sibling = x.sibling;

        //Add children to new Bheap and mergeRootLists with existing tree.
        BinomialHeap<T> h = new BinomialHeap<>();

        Node<T> z = x.child;
        while (z != null) {
            Node<T> next = z.sibling;
            z.sibling = h.min;
            h.min = z;
            z = next;
        }
        BinomialHeap<T> newH = merge(this, h);
        min = newH.min;
        return x.value;
    }

    public void decreaseKey(T value, int newKey) {
        decreaseKey(findNode(value), newKey);
    }

    public void decreaseKey(Node<T> node, int newKey){
        node.key = newKey;
        Node<T> y = node;
        Node<T> z = y.parent;
        while (z != null && (y.key < z.key)) {
            int v = y.key;
            y.key = z.key;
            z.key = v;
            y = z;
            z = y.parent;
        }
        //-----------------------------------
//        Node<T> temp = rootList.get(rootList.stream().map(n -> n.value).collect(Collectors.toList()).indexOf(value));
//        if (temp == null)
//            return;
//        temp.key = newKey;
//        Node<T> tempParent = temp.parent;
//
//        while ((tempParent != null) && (temp.key < tempParent.key)) {
//            int z = temp.key;
//            temp.key = tempParent.key;
//            tempParent.key = z;
//
//            temp = tempParent;
//            tempParent = tempParent.parent;
//        }
    }

    private Node<T> findNode(T value){
        List<Node<T>> rootList = min.toList();
        return rootList.get(rootList.stream().map(n -> n.value).collect(Collectors.toList()).indexOf(value));
    }

    public void delete(T value){
        Node<T> node = findNode(value);
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }

    public List<T> toList(){
        ArrayList<T> result = new ArrayList<>();
        if (min != null) {
            min.toList().stream().forEach(x -> result.add(x.value));
        }
        return result;
    }

    private static class Node<T> {
        public int key;
        public T value;
        public Node<T> parent;
        public Node<T> child;
        public Node<T> sibling;
        public int degree;

        public Node(T value, int key) {
            this.key = key;
            this.value = value;
            parent = null;
            child = null;
            sibling = null;
            degree = 0;
        }

        private void link(Node<T> parent) {
            this.parent = parent;
            this.sibling = parent.child;
            parent.child = this;
            parent.degree++;
        }

        ArrayList<Node<T>> toList() {
            ArrayList<Node<T>> list = new ArrayList<>();
            Node<T> cur = this;
            do {
                list.add(cur);
                if (cur.child != null) list.addAll(cur.child.toList());
                cur = cur.sibling;
            }
            while (cur != null);
            return list;
        }
    }
}