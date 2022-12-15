// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )	   --> Insert x
// void remove( x )	   --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )	 --> Return true if empty; else false
// void makeEmpty( )	  --> Remove all items
// void printTree( )	  --> Print tree in sorted order

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTreeWeiss
{
    /**
     * Construct the tree.
     */
    public BinarySearchTreeWeiss( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( Comparable x )
    {
        root = insert( x, root );
    }

    public void insertBad( Comparable x )
    {
        root = insertBad( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( Comparable x )
    {
        root = remove( x, root );
    }


    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin( )
    {
        return elementAt( findMin( root ) );
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public Comparable findMax( )
    {
        return elementAt( findMax( root ) );
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find( Comparable x )
    {
        return elementAt( find( x, root ) );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }


    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private Comparable elementAt( BinaryNode t )
    {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BinaryNode insert( Comparable x, BinaryNode t )
    {
        /* 1*/	  if( t == null )
        /* 2*/		  t = new BinaryNode( x, null, null );
        /* 3*/	  else if( x.compareTo( t.element ) < 0 )
        /* 4*/		  t.left = insert( x, t.left );
        /* 5*/	  else if( x.compareTo( t.element ) > 0 )
        /* 6*/		  t.right = insert( x, t.right );
        /* 7*/	  else
        /* 8*/		  ;  // Duplicate; do nothing
        /* 9*/	  return t;
    }

    private BinaryNode insertBad ( Comparable x, BinaryNode t )
    {
        if( t == null )
            t = new BinaryNode( x, null, null );
        else
            t.left = insert( x, t.left );
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BinaryNode remove( Comparable x, BinaryNode t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BinaryNode findMin( BinaryNode t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax( BinaryNode t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode find( Comparable x, BinaryNode t )
    {
        if( t == null )
            return null;
        if( x.compareTo( t.element ) < 0 )
            return find( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            return find( x, t.right );
        else
            return t;	// Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( BinaryNode t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /** The tree root. */
    private BinaryNode root;

    public boolean equals (Object o)
    {
        if (!(o instanceof BinarySearchTreeWeiss))
            return false;

        BinarySearchTreeWeiss other = (BinarySearchTreeWeiss) o;

        return equals (this.root, other.root);
    }

    private static boolean equals (BinaryNode th, BinaryNode other)
    {
        if ((th == null) && (other == null))
            return true;
        else if ((th == null) || (other == null))
            return false;
        else if (!(th.element.equals (other.element)))
            return false;
        else
            return (equals (th.left, other.left)  &&
                    equals (th.right, other.right));

    }

    public boolean testTreeProperty ()
    {
        if (root == null)
            return true;
        return (testTreeProperty (root.left, null, root.element)
                && testTreeProperty (root.right, root.element, null));
    }

    private boolean testTreeProperty (BinaryNode t, Comparable lowLimit, Comparable highLimit)
    {
        if (t == null)
            return true;
        boolean current;
        if (lowLimit == null)
            current = (t.element.compareTo (highLimit) < 0);
        else if (highLimit == null)
            current = (t.element.compareTo (lowLimit) > 0);
        else
            current = (t.element.compareTo (lowLimit) > 0) &&
                    (t.element.compareTo (highLimit) < 0);

        return	(current &&
                testTreeProperty (t.left, lowLimit, t.element) &&
                testTreeProperty (t.right, t.element, highLimit));
    }

    public BinarySearchTreeWeiss copyTree ()
    {
        BinarySearchTreeWeiss result = new BinarySearchTreeWeiss();
        result.root = copyTree (this.root);
        return result;
    }

    private BinaryNode copyTree (BinaryNode t)
    {
        if (t == null)
            return null;
        BinaryNode left = copyTree (t.left);
        BinaryNode right = copyTree (t.right);
        return new BinaryNode (t.element, left, right);
    }

    public boolean isSimilar (BinarySearchTreeWeiss o)
    {
        if (o == null)
            return false;
        return isSimilar (this.root, o.root);
    }

    private static boolean isSimilar (BinaryNode th, BinaryNode other)
    {
        if ((th == null) && (other == null))
            return true;
        else if ((th == null) || (other == null))
            return false;
        else
            return (isSimilar (th.left, other.left)  &&
                    isSimilar (th.right, other.right));

    }

    // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTreeWeiss t1 = new BinarySearchTreeWeiss( );
        BinarySearchTreeWeiss t2;

        for( int i = 1; i <= 100; i++ )
        {
            int element = (int) (Math.random () * 1000);
            t1.insert (new Integer( element ));
        }
        t2 = t1.copyTree();
        System.out.println("before bad insertion");
        System.out.println(t1.equals(t2));
        System.out.println(t1.isSimilar(t2));
        System.out.println(t1.testTreeProperty());

        System.out.println("------------------------------------------------");
        System.out.println("after bad insertion");
        t1.insertBad(new Integer(10000));
        System.out.println(t1.equals(t2));
        System.out.println(t1.isSimilar(t2));
        System.out.println(t1.testTreeProperty());
    }
}

class BinaryNode
{
    // Constructors
    BinaryNode( Comparable theElement )
    {
        this( theElement, null, null );
    }

    BinaryNode( Comparable theElement, BinaryNode lt, BinaryNode rt )
    {
        element  = theElement;
        left	 = lt;
        right	= rt;
    }

    // Friendly data; accessible by other package routines
    Comparable element;	  // The data in the node
    BinaryNode left;		 // Left child
    BinaryNode right;		// Right child
}
