public class Merge
{
    public static void main (String [] args)
    {
        final int SIZE = 100;
        int array1 [] = new int [SIZE];
        int array2 [] = new int [SIZE];

        array1[0] = (int) (Math.random () * 10);
        array2[0] = (int) (Math.random () * 10);

        for (int i = 1; i < SIZE; i++)
        {
            array1[i] = array1[i-1] + (int) (Math.random () * 10);
            array2[i] = array2[i-1] + (int) (Math.random () * 10);
        }

        int array3 [] = merge (array1, array2);

        printArray (array1);
        printArray (array2);
        printArray (array3);


    }

    public static void printArray (int [] array)
    {
        if (array.length > 0)
            System.out.print ("{ " + array[0]);
        for (int i = 1; i < array.length; i++)
            System.out.print (", " + array[i]);
        if (array.length > 0)
            System.out.println (" }");
    }

    public static int [] merge (int [] array1, int array2[])
    {
        int [] array3 = new int [array1.length + array2.length];
        int array1Pos = 0;
        int array2Pos = 0;
        int array3Pos = 0;

        while ((array1Pos < array1.length) && (array2Pos < array2.length))
        {
            if (array1[array1Pos] < array2[array2Pos])
                array3[array3Pos++] = array1[array1Pos++];
            else
                array3[array3Pos++] = array2[array2Pos++];
        }

        while( array1Pos < array1.length )	// Copy rest of array1
            array3[ array3Pos++ ] = array1[ array1Pos++ ];

        while( array2Pos < array2.length )	// Copy rest of array2
            array3[ array3Pos++ ] = array2[ array2Pos++ ];

        return array3;

    }
}
