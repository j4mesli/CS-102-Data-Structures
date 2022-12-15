// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

import javax.swing.JOptionPane;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public BinaryHeap( AnyType [ ] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }


    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }



    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */

    // proof here: https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/

    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }


    public void increaseKey (int p, int delta)
    {
        if (p >= array.length)
            return;
        if (!(array[p] instanceof Integer))
            return;
        array[p] =  (AnyType) (new Integer((Integer)array[p] + delta));
        percolateDown(p);
    }

    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public void decreaseKey (int p, int delta)
    {
        if (p >= array.length)
            return;
        if (!(array[p] instanceof Integer))
            return;
        array[p] = (AnyType) (new Integer((Integer)array[p] - delta));
        percolateUp(p);
    }


    private void percolateUp( int hole )
    {
        int parent;
        AnyType tmp = array[ hole ];

        for( ; hole / 2 >= 1; hole = parent )
        {
            parent = hole / 2;
            if( parent != 0 && array[ parent ].compareTo( tmp ) > 0 )
                array[ hole ] = array[ parent ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public void printHeap ()
    {
        int newline = 2;
        int leadSpaces = 30;
        int inbetweenSpaces = (leadSpaces - 2) / 2;
        for (int sp = 1; sp <= leadSpaces; sp++)
            System.out.print (" ");
        for (int i = 1; i <= currentSize; i++)
        {
            if (i == newline)
            {
                System.out.println ();
                inbetweenSpaces = leadSpaces;
                leadSpaces = (leadSpaces - 2) / 2;
                for (int sp = 1; sp <= leadSpaces; sp++)
                    System.out.print (" ");
                newline *= 2;
            }
            if ((Integer)array[i] < 10)
                System.out.print(array[i] + " ");
            else
                System.out.print(array[i] );
            for (int sp = 1; sp <= inbetweenSpaces; sp++)
                System.out.print (" ");

        }
        System.out.println ();
    }





    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array


    // Test program
    public static void main( String [ ] args )
    {
        int i,j;
        int array [] = { 0, 8, 10, 12, 20, 11, 18, 20, 29, 31, 2, 16, 15}; //{10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
        // Integer myIntArray [] = { 8, 10, 12, 20, 11, 18, 20, 29, 31}; //{10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};

/*
        BinaryHeap heapByHeapify = new BinaryHeap (myIntArray);
        System.out.println("After buildHeap operartion:");
        heapByHeapify.printHeap();
*/
        i = JOptionPane.showConfirmDialog (null,"Continue?");

        //////////////////////////////////////////////////////////////
        BinaryHeap<Integer> h = new BinaryHeap<Integer>( array.length );

        for (i = 0; i <= array.length-1; i++)
        {
            h.insert (array[i]);
            System.out.println("------------------------------------------------------------------");
            h.printHeap();
            int jj = JOptionPane.showConfirmDialog (null, "Remove an item?",
                    "continue", JOptionPane.YES_NO_OPTION );
        }


        i = JOptionPane.showConfirmDialog (null, "Remove an item?",
                "continue", JOptionPane.YES_NO_OPTION );
        while (i == 0)
        {
            System.out.println("------------------------------------------------------------------");
            h.deleteMin();
            h.printHeap();
            i = JOptionPane.showConfirmDialog (null, "Remove an item?",
                    "continue", JOptionPane.YES_NO_OPTION );
        }

        //////////////////////////////////////////////////////////////
        h = new BinaryHeap<Integer>( array.length );

        for (i = 0; i <= array.length-1; i++)
            h.insert (array[i]);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Start from scratch");
        h.printHeap();

        String s = JOptionPane.showInputDialog
                ("Enter an element to change (q to quit)");
        while (s.charAt(0) != 'q')
        {
            int element = Integer.parseInt(s);
            s = JOptionPane.showInputDialog ("Enter the amount you want to chage it by");
            int delta = Integer.parseInt(s);
            if (delta < 0)
                h.decreaseKey (element, 0-delta);
            else
                h.increaseKey (element, delta);

            System.out.println("------------------------------------------------------------------");
            h.printHeap();
            s = JOptionPane.showInputDialog
                    ("Enter an element to change (q to quit)");
        }
        System.exit(0);
    }
}