package heap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 777 on 07.08.16.
 */
public class FibonacciHeap<T extends Comparable> {
    private FibonacciNode<T> minNode;



    public FibonacciHeap (){

    }

    public FibonacciNode getMinNode() {
        return minNode;
    }

    public void setMinNode(FibonacciNode minNode) {
        this.minNode = minNode;
    }

    public T findMin() {
        return null;
    }

    public boolean add() {
        return false;
    }

    public T extractMin() {
        return null;
    }

    public T deleteMin() {
        return null;
    }

    public FibonacciHeap<T> merge(FibonacciHeap h1, FibonacciHeap h2) {
        FibonacciHeap<T> h = new FibonacciHeap<T>();

        if ((h1 != null) && (h2 != null)) {
            h.minNode = h1.minNode;

            if (h.minNode != null) {
                if (h2.minNode != null) {
                    h.minNode.getRight().setLeft(h2.minNode.getLeft());
                    h2.minNode.getLeft().setRight(h.minNode.getRight());
                    h.minNode.setRight(h2.minNode);
                    h2.minNode.setLeft(h.minNode);

                    if (h2.minNode.getValue().compareTo(h1.minNode.getValue()) < 0) {
                        h.minNode = h2.minNode;
                    }
                }
            } else {
                h.minNode = h2.minNode;
            }

            //@TODO Sum nodes count
        }

        return h;
    }


}

class FibonacciNode<T extends Comparable> {

    private int degree;
    private boolean marked = false;
    private T value;
    private FibonacciNode<T> left;
    private FibonacciNode<T> right;
    private FibonacciNode<T> parent;
    private FibonacciNode<T> child;

    public int getDegree() {
        return degree;
    }

    public boolean isMarked() {
        return marked;
    }

    public T getValue() {
        return value;
    }

    public FibonacciNode<T> getLeft() {
        return left;
    }

    public FibonacciNode<T> getRight() {
        return right;
    }

    public FibonacciNode<T> getParent() {
        return parent;
    }

    public FibonacciNode<T> getChild() {
        return child;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(FibonacciNode<T> left) {
        this.left = left;
    }

    public void setRight(FibonacciNode<T> right) {
        this.right = right;
    }

    public void setParent(FibonacciNode<T> parent) {
        this.parent = parent;
    }

    public void setChild(FibonacciNode<T> child) {
        this.child = child;
    }

    public  FibonacciNode (T elem){
        value = elem;
    }

}