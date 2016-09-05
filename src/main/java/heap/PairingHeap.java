//package heap;
//
//import java.util.Scanner;
//
//
//class PairHeap<T> {
//    private PairNode root;
//    public PairHeap( )
//    {
//        root = null;
//    }
//    public boolean isEmpty()
//    {
//        return root == null;
//    }
//    public void makeEmpty( )
//    {
//        root = null;
//    }
//    public PairNode insert(int x)
//    {
//        PairNode newNode = new PairNode( x );
//        if (root == null)
//            root = newNode;
//        else
////            root = compareAndLink(root, newNode);
//        return newNode;
//    }
//
//    private PairNode combineSiblings(PairNode firstSibling)
//    {
//        if( firstSibling.nextSibling == null )
//            return firstSibling;
//        int numSiblings = 0;
//        for ( ; firstSibling != null; numSiblings++)
//        {
////            treeArray = doubleIfFull( treeArray, numSiblings );
////            treeArray[ numSiblings ] = firstSibling;
//            /* break links */
//            firstSibling.prev.nextSibling = null;
//            firstSibling = firstSibling.nextSibling;
//        }
////        treeArray = doubleIfFull( treeArray, numSiblings );
////        treeArray[ numSiblings ] = null;
//        int i = 0;
//        for ( ; i + 1 < numSiblings; i += 2);
////            treeArray[ i ] = compareAndLink(treeArray[i], treeArray[i + 1]);
////        int j = i - 2;
////        if (j == numSiblings - 3)
////            treeArray[ j ] = compareAndLink( treeArray[ j ], treeArray[ j + 2 ] );
////        for ( ; j >= 2; j -= 2)
////            treeArray[j - 2] = compareAndLink(treeArray[j-2], treeArray[j]);
////        return treeArray[0];
//    }
//    private PairNode[] doubleIfFull(PairNode [ ] array, int index)
//    {
//        if (index == array.length)
//        {
//            PairNode [ ] oldArray = array;
////            array = new PairNode[index * 2];
//            for( int i = 0; i < index; i++ )
//                array[i] = oldArray[i];
//        }
//        return array;
//    }
//    public int deleteMin( )
//    {
//        if (isEmpty( ) )
//            return -1;
//        int x = root.element;
//        if (root.leftChild == null)
//            root = null;
//        else
//            root = combineSiblings( root.leftChild );
//        return x;
//    }
//    /* inorder traversal */
//    public void inorder()
//    {
//        inorder(root);
//    }
//    private void inorder(PairNode r)
//    {
//        if (r != null)
//        {
//            inorder(r.leftChild);
//            System.out.print(r.element +" ");
//            inorder(r.nextSibling);
//        }
//    }
//
//    class PairNode
//    {
//        int element;
//        PairNode leftChild;
//        PairNode nextSibling;
//        PairNode prev;
//
//        /* Constructor */
//        public PairNode(int x)
//        {
//            element = x;
//            leftChild = null;
//            nextSibling = null;
//            prev = null;
//        }
//    }
//}