import java.util.Scanner;

public class QuickSort {
    public static final boolean TRACE = true;

    public static void quicksort( int [] array )
    {
        quicksort (array, 0, array.length - 1);
    }

    private static void quicksort( int [] array, int left, int right )
    {
        if (TRACE) System.out.println("QS( " + left + ", " + right + " )" );
        int leftPtr = left;
        int rightPtr = right;

        if( left < right )
        {
            int pivot = array[left];

            while (leftPtr < rightPtr)
            {
                while( (array[leftPtr]  <= pivot) && (leftPtr  < right))
                    leftPtr++;
                while( (array[rightPtr] >  pivot) )//&& (rightPtr > left))//&& (rightPtr > left))
                    rightPtr--;

                if( leftPtr < rightPtr )
                    swap ( array, leftPtr, rightPtr );
            }

            swap ( array, left, rightPtr );
            if (TRACE) printArrayInQuick (array, rightPtr);
            quicksort( array, left, rightPtr - 1 );
            quicksort( array, rightPtr + 1, right );
        }
    }

    public static boolean checkSort (int [] array) {
        if (array.length <= 1)
            return true;
        for (int i = 1; i <= array.length-1; i++)
            if (array[i] < array[i-1])
                return false;
        return true;
    }

    public static void swap ( int [] array, int index1, int index2 ) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static void printArray (int [] array)
    {
        if (array.length > 0)
        {
            System.out.print ("{ " + array[0]);
            //if (array[0] < 10) System.out.print(" ");
        }
        for (int i = 1; i < array.length; i++)
        {
            System.out.print (", " + array[i]);
            //if (array[0] < 10) System.out.print(" ");
        }
        if (array.length > 0)
            System.out.println (" }");
    }

    public static void printArrayInQuick (int [] array, int pivot)
    {

        printArray (array);
        if (array.length > 0)
            System.out.print ("  " );
        int i;
        for (i = 1; i < array.length; i++)
            if (array[i-1] < 10)
                if (i-1 == pivot)
                    System.out.print ("^  " );
                else
                    System.out.print ("   ");
            else
            if (i-1 == pivot)
                System.out.print ("^   ");
            else
                System.out.print ("    ");
        if (i-1 == pivot)
            System.out.print("^");
        System.out.println();
    }


    public static void main (String [] args)
    {
        final int SIZE = 10;
        int [] array = new int [SIZE];
        Scanner sc = new Scanner (System.in);
        String s;

        for (int i = 0; i < SIZE; i++)
            array [i] = (int) (Math.random() * 100);

        printArray (array);

        if (checkSort(array))
            System.out.println ("The array is sorted");
        else
            System.out.println ("The array is not sorted");

        s = sc.nextLine();

        quicksort(array);

        printArray (array);
        if (checkSort(array))
            System.out.println ("The array is sorted");
        else
            System.out.println ("The array is not sorted");

        System.exit(0);
    }
}
