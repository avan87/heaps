package trees;

/**
 * Created by 777 on 07.08.16.
 */

public abstract class Tree<V extends Comparable<V>> {



       Node<V> root;

    public Tree(V value) {
        this.root = new Node<V>(value);
    }

    public boolean search(V value,  Tree<V> tree) {

        if(tree == null) return false;

        if(tree.root.value.compareTo(tree.root.value) < 0){
            return search(value, tree.root.l);
        }

        return tree.root.value.compareTo(tree.root.value) >= 0 || search(value, tree.root.r);



    }

    public void insert(Node node, Tree tree) {

    }

    public void delete(V value, Tree tree) {
    }


    class Node<T extends Comparable<T>> {


        T value;
        Tree<T> l;
        Tree<T> r;

        public Node(T value) {

            this.value = value;
            l = null;
            r = null;
        }


        public Tree<T> getL() {
            return l;
        }

        public Tree<T> getR() {
            return r;
        }



    }
}