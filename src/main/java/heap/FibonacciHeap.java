package heap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * http://neerc.ifmo.ru/wiki/index.php?title=%D0%A4%D0%B8%D0%B1%D0%BE%D0%BD%D0%B0%D1%87%D1%87%D0%B8%D0%B5%D0%B2%D0%B0_%D0%BA%D1%83%D1%87%D0%B0
 */
class FibonacciHeap<T extends Comparable>{
    private Node<T> min;
    private int n;

    public void clear() {
        min = null;
        n = 0;
    }

    private void consolidate() {
        // log(phi, Integer.MAX_VALUE) = 45!
        List<Node<T>> A = new ArrayList<>(Collections.nCopies(45, null));

        Node<T> start = min;
        Node<T> w = min;
        do {
            Node<T> x = w;
            Node<T> nextW = w.right;
            int d = x.degree;
            while (A.get(d) != null) {
                Node<T> y = A.get(d);
                if (x.key.compareTo(y.key) > 0) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    start = start.right;
                }
                if (y == nextW) {
                    nextW = nextW.right;
                }
                y.link(x);
                A.set(d, null);
                d++;
            }
            A.set(d, x);
            w = nextW;
        } while (w != start);
        min = start;
        for (Node<T> a : A) {
            if (a != null && a.key.compareTo(min.key) < 0) {
                min = a;
            }
        }
    }

    public void decreaseKey(Node<T> x, T newValue, Comparable k) {
        decreaseKey(x, newValue, k, false);
    }

    private void decreaseKey(Node<T> x, T newValue, Comparable k, boolean delete) {
        if (!delete && k.compareTo(x.key) > 0) {
            throw new IllegalArgumentException("cannot increase key value");
        }
        x.key = k;
        x.value = newValue;
        Node<T> y = x.parent;
        if (y != null && (delete || k.compareTo(y.key) < 0)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }
        if (delete || k.compareTo(min.key) < 0) {
            min = x;
        }
    }


    public void delete(Node<T> x) {
        decreaseKey(x, x.value, 0, true);
        extractMin();
    }

    public boolean isEmpty() {
        return min == null;
    }

    public Node<T> insert(T x, Comparable key) {
        Node<T> node = new Node<>(x, key);
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (key.compareTo(min.key) < 0) {
                min = node;
            }
        } else {
            min = node;
        }
        n++;
        return node;
    }

    public T min() {
        return min.getValue();
    }

    public T extractMin() {
        Node<T> z = min;
        if (z == null) {
            return null;
        }
        if (z.child != null) {
            z.child.parent = null;
            for (Node x = z.child.right; x != z.child; x = x.right) {
                x.parent = null;
            }
            Node<T> minleft = min.left;
            Node<T> zchildleft = z.child.left;
            min.left = zchildleft;
            zchildleft.right = min;
            z.child.left = minleft;
            minleft.right = z.child;
        }
        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }
        n--;
        return z.value;
    }

    public int size() {
        return n;
    }

    public static <T extends Comparable> FibonacciHeap<T> merge(FibonacciHeap<T> h1, FibonacciHeap<T> h2) {
        FibonacciHeap<T> H = new FibonacciHeap<>();
        H.min = h1.min;
        if (H.min != null) {
            if (h2.min != null) {
                H.min.right.left = h2.min.left;
                h2.min.left.right = H.min.right;
                H.min.right = h2.min;
                h2.min.left = H.min;
                if (h2.min.key.compareTo(h1.min.key) < 0) {
                    H.min = h2.min;
                }
            }
        } else {
            H.min = h2.min;
        }
        H.n = h1.n + h2.n;
        return H;
    }

    public ArrayList<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        if (min != null) {
                min.toList().stream().forEach(x -> result.add(x.getValue()));
        }
        return result;
    }

    private static class Node<T extends Comparable> {
        private T value;
        private Comparable key;
        private Node<T> parent;
        private Node<T> child;
        private Node<T> right;
        private Node<T> left;
        private int degree;
        private boolean mark;

        Node(T value, Comparable key) {
            this.value = value;
            this.key = key;
            right = this;
            left = this;
        }

        public Comparable getKey() {return key;}
        public T getValue() {return value;}

        void cascadingCut(Node<T> min) {
            Node z = parent;
            if (z != null) {
                if (mark) {
                    z.cut(this, min);
                    z.cascadingCut(min);
                } else {
                    mark = true;
                }
            }
        }

        void cut(Node<T> x, Node<T> min) {
            x.left.right = x.right;
            x.right.left = x.left;
            degree--;
            if (degree == 0) {
                child = null;
            } else if (child == x) {
                child = x.right;
            }
            x.right = min;
            x.left = min.left;
            min.left = x;
            x.left.right = x;
            x.parent = null;
            x.mark = false;
        }

        void link(Node<T> parent) {
            left.right = right;
            right.left = left;
            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }
            parent.degree++;
            mark = false;
        }

        ArrayList<Node<T>> toList() {
            ArrayList<Node<T>> list = new ArrayList<>();
            Node<T> cur = this;
            do{
                list.add(cur);
                if (cur.child != null) list.addAll(cur.child.toList());
                cur = cur.right;
            }
            while (cur != this);
            return list;
        }
    }
}