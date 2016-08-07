package trees;

/**
 * Created by 777 on 07.08.16.
 */
public abstract class Tree<T extends Comparable<T>> {



    Node<T> root;

    public Tree(T elem) {
        this.root = new Node<>(elem);
    }

    public boolean search(T elem, Tree<T> tree) {

        if(tree == null) return false;

        if(elem.compareTo(tree.root.item) < 0){
            return search(elem, tree.root.l);
        }

        return elem.compareTo(tree.root.item) >= 0 || search(elem, tree.root.r);



    }

    public void insert(T elem, Tree tree) {

    }

    public void delete(T elem, Tree tree) {
    }


    public class Node<T extends Comparable<T>> {

        T item;
        Tree<T> l;
        Tree<T> r;

        public Node(T elem) {

            this.item = elem;
            l = null;
            r = null;
        }


        public T getItem() {
            return item;
        }

        public Tree<T> getL() {
            return l;
        }

        public Tree<T> getR() {
            return r;
        }
    }
}