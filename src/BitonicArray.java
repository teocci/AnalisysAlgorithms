import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by teocci on 10/20/16.
 */
public class BitonicArray {

    public static final int INDEX_NOT_FOUND = -1;
    private int count = 10;
    private int inflectionPoint;

    private boolean fixed;

    private int minLimit;
    private int maxLimit;
    private int fraction;
    private int minimum, maximum;
    private int[] array;
    private int[] increasing;
    private int[] decreasing;

    // Create a Bitonic array from with values greater than 1.
    public BitonicArray(int n) {
        if (!(n > 3))
            throw new IllegalArgumentException();

        count = n;
        array = new int[count];

        if (count == 4) {
            inflectionPoint = 2;
        } else {
            // Minimum sequence is defined by two integer
            inflectionPoint = StdRandom.uniform(2, count - 1);
        }

        generateIncreasingSequence();
        generateDecreasingSequence();

        array = concatenate();
    }

    private void generateIncreasingSequence() {
        int max = inflectionPoint;
        int factor = count - inflectionPoint;
        fixed = false;
        if (max < factor) {
            fraction = -1 * factor / max;
        } else {
            fraction = max / factor;
        }

        factor = 1;
        if (fraction < -1) {
            factor = -1 * fraction;
        }

        minLimit = (3 * factor) * (max);
        maxLimit = (3 * factor) * (count + max);

        increasing = new int[max];

        int base  = StdRandom.uniform(1, minLimit);
        for (int i = 0; i < max; ++i) {
            increasing[i] = base;
            base = base + StdRandom.uniform(1, (3 * factor));
        }

        minimum = increasing[0];
        maximum = increasing[max - 1];

        if (factor > 1 && max < minLimit) {
            increasing[max - 1] = maxLimit - StdRandom.uniform(3, (minLimit / 2) + (3 * factor));
            StdOut.println("Maximum value fixed from: " + maximum + " to: " + increasing[max - 1]);
            fixed = true;
            maximum = increasing[max - 1];
        }
    }

    private void generateDecreasingSequence() {
        int max = count - inflectionPoint;
        decreasing = new int[max];

        int factor = 1;
        if (fraction > 1) {
            factor = fraction;
        }

        int base  = increasing[inflectionPoint - 1] - StdRandom.uniform(1, (3 * factor));

        for (int i = 0; i < max; ++i) {
            while (contains(base))
                base--;
            decreasing[i] = base;
            base = base - StdRandom.uniform(1, (3 * factor));
        }

        if (decreasing[max - 1] < minimum)
            minimum = decreasing[max - 1];
    }

    public boolean descendingBinarySearch(int left, int right, int value)
    {
        StdOut.println("descendingBinarySearch: " + left + " " + right);

        // empty interval
        if (left == right) {
            return false;
        }

        // look at the middle of the interval
        int mid = (right + left)/2;

        if (array[mid] == value) {
            StdOut.println("Value found.");
            return true;
        }

        // interval is not splittable
        if (left + 1 == right) {
            return false;
        }

        if (value < array[mid]) {
            return descendingBinarySearch(mid+1, right, value);
        }
        else {
            return descendingBinarySearch(left, mid, value);
        }
    }

    public boolean ascendingBinarySearch(int left, int right, int value)
    {
        StdOut.println("ascendingBinarySearch: " + left + " " + right);

        // empty interval
        if (left == right) {
            //throw new IllegalArgumentException("empty interval");
            return false;
        }

        // look at the middle of the interval
        int mid = (right+left)/2;
        if (array[mid] == value) {
            StdOut.println("Value found.");
            return true;
        }

        // interval is not splittable
        if (left+1 == right) {
            return false;
        }

        if (value > array[mid]) {
            return ascendingBinarySearch(mid+1, right, value);
        }
        else {
            return ascendingBinarySearch(left, mid, value);
        }
    }

    public boolean bitonicSearch(int left, int right, int value)
    {
        // StdOut.println("bitonicSearch: " + left + " " + right);

        // empty interval
        if (left == right) {
            return false;
        }

        int mid = (right+left)/2;
        if (array[mid] == value) {
            StdOut.println("Value found.");
            return true;
        }

        // not splittable interval
        if (left+1 == right) {
            return false;
        }

        if(array[mid] > array[mid-1]) {
            if (value > array[mid]) {
                return bitonicSearch(mid + 1, right, value);
            } else {
                return ascendingBinarySearch(left, mid, value) || descendingBinarySearch(mid + 1, right, value);
            }
        }

        else {
            if (value > array[mid]) {
                return bitonicSearch(left, mid, value);
            }
            else {
                return ascendingBinarySearch(left, mid, value) || descendingBinarySearch(mid + 1, right, value);
            }
        }
    }

    public int[] concatenate() {
        int aLen = increasing.length;
        int bLen = decreasing.length;
        int[] c = new int[aLen + bLen];
        System.arraycopy(increasing, 0, c, 0, aLen);
        System.arraycopy(decreasing, 0, c, aLen, bLen);
        return c;
    }

    public boolean contains(final int key) {
        return contains(increasing, key);
    }

    public boolean contains(final int[] array, final int valueToFind) {
        return indexOf(valueToFind) != INDEX_NOT_FOUND;
    }

    public int indexOf(final int valueToFind) {
        return indexOf(valueToFind, 0);
    }

    public int indexOf(final int valueToFind, int startIndex) {
        if (increasing == null) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < increasing.length; i++) {
            if (valueToFind == increasing[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    private int getMinimun() {
        return minimum;
    }

    private int getMaximun() {
        return maximum;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    private void printArray() {
        StdOut.println("Inflection Point: " + inflectionPoint);
        StdOut.println("Fraction: " + fraction);
        StdOut.println("Minimum Value: " + getMinimun());
        StdOut.println("Maximum Value: " + getMaximun());
        StdOut.println("Minimum Limit: " + getMinLimit());
        StdOut.println("Maximum Limit: " + getMaxLimit());
        StdOut.println("-----------------------------------");
        StdOut.println("Increasing Array: " + Arrays.toString(increasing));
        StdOut.println("Decreasing Array: " + Arrays.toString(decreasing));
        StdOut.println("Biotonic Array: " + Arrays.toString(array));
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        //int[] bitonicArray = {2, 3, 5, 7, 9, 11, 13, 4, 1, 0};

        int left = 0;
        int right = n;

        BitonicArray bitonicArray = new BitonicArray(n);
        bitonicArray.printArray();

        int value = StdRandom.uniform(bitonicArray.getMinimun(), bitonicArray.getMaximun());

        StdOut.println("-----------------------------------");
        StdOut.println("Searching Value: " + value);
        StdOut.println("-----------------------------------");
        bitonicArray.bitonicSearch(left, right, value);

        // print "Value found." is the desired value is in the bitonic array
    }

}
