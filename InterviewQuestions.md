# Interview Questions (optional)

1. 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case. You may assume that you can sort the n integers in time proportional to n2 or better.

**Note:** these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.

**Answer:** Let's consider that we have a sorted array:
For each element C of the array we will try to find two numbers: A and B, such that A + B = C.
For this, we will use two pointers, one at the beginning and the other at the end, like the binary search. With this we can achieve O(n^2).

2. Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.

Standard version: Use ∼3lgn compares in the worst case.
Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).

What do you think?

Let's assume the array is first in ascending order and then in descending order:

1. We take the middle of the array and compare the middle element with one of its neighbor to see if the max is on the right or on the left
2. Compare the middle element with the desired value
3. If the middle element is smaller than the desired value AND the max is on the left side, then do bitonic search on the left subarray (we are sure that the value is not in the right subarray)
4. If the middle element is smaller than the desired value AND the max is on the right side, then do bitonic search on the right subarray
5. If the middle element is bigger than the desired value, then do descending binary search on the right subarray and ascending binary search on the left subarray.

In the last case, it might be surprising to do a binary search on a subarray that may be bitonic but it actually works because we know that the elements that are not in the good order are all bigger than the desired value. For instance, doing an ascending binary search for the value 5 in the array [2, 4, 5, 6, 9, 8, 7] will work because 7 and 8 are bigger than the desired value 5.

Here is a fully working implementation (in Java) of the bitonic search in time ~2logN:


3. Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:

* Version 0: 1 egg, ≤T tosses.
* Version 1: ∼1lgn eggs and ∼1lgn tosses.
* Version 2: ∼lgT eggs and ∼2lgT tosses.
* Version 3: 2 eggs and ∼2√n tosses.
* Version 4: 2 eggs and ≤c√T tosses for some fixed constant c.
