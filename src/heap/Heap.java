package heap;

import trees.Tree;

import java.util.Collection;

/**
 * Created by 777 on 07.08.16.
 */
public abstract class Heap<T> implements Heapable<T> {

    public Collection<Tree> collection;

    public Heap (){}



    public abstract  Heap<T> meld();
}
