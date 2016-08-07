package heap;

import trees.Tree;

import java.util.Collection;

/**
 * Created by 777 on 07.08.16.
 */
public class BinomialHeap<T> extends Heap<T> implements Mergeable{


    Collection<BinomialTree<T>> heap;

    @Override
    public Heap<T> meld() {
        return null;
    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public boolean insert() {
        return false;
    }

    @Override
    public T extractMin() {
        return null;
    }

    @Override
    public T deleteMin() {
        return null;
    }

    @Override
    public Object merge(Object t1, Object t2) {
        return null;
    }


    class BinomialTree<T extends Comparable<T>> extends Tree<T>{

        private int order;
        private int size;
        private int numOfChildren;
        private Node<T> root;


        public BinomialTree(T elem){

            super(elem);
        }

        public BinomialTree<T> merge(BinomialTree<T> bt1, BinomialTree<T> bt2){

            if(bt1.root.){


            }

        }


    }

}
