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

* Version 0: 1 egg, ≤ T tosses.
* Version 1: ∼1lgn eggs and ∼1lgn tosses.
* Version 2: ∼lgT eggs and ∼2lgT tosses.
* Version 3: 2 eggs and ∼2√N tosses.
* Version 4: 2 eggs and ≤c√T tosses for some fixed constant c.

For version 0, a simple iterative search starting from the 1st floor and working up to the NNth floor in increments of 1 will work.

For version 1, a binary search across the floors 1 to N will work.

For version 2, I think you can iteratively double floors, visiting 1, then 2, then 4, then 8, etc. until the egg breaks at floor 2^k. Then you can binary search across 2^(k−1) and 2^k

For version 3, you can go iteratively go across floors with incrementing by √N: first visiting 0, then √N, then √2N, etc. Once the egg breaks at stage √kN, iterate across the range √(k−1)N and √kN one floor at a time.



https://github.com/richzw/CodeHome/blob/master/MiscellaneousQuestions/EggDrop.md

https://www.coursehero.com/file/p33auol/Score-Explanation-Answer-Total-000-000-Question-Explanation-Hints-Standard/
https://www.studyblue.com/notes/note/n/physics-12e-solutions-manual-all-chapters/file/292345
http://datagenetics.com/blog/july22012/index.html
http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
http://math.stackexchange.com/questions/835582/egg-drop-problem
http://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
http://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
http://stackoverflow.com/questions/1938101/how-to-initialize-an-array-in-java
http://stackoverflow.com/questions/8068470/java-initialize-an-int-array-in-a-constructor
http://stackoverflow.com/questions/19372930/given-a-bitonic-array-and-element-x-in-the-array-find-the-index-of-x-in-2logn
http://stackoverflow.com/questions/18081677/php-notice-undefined-index-although-using-try-catch
https://github.com/vancexu/Algs4/blob/master/1-percolation/Percolation.java
http://vancexu.github.io/2015/07/21/intro-to-union-find-data-structure-exercise.html
https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/ArrayUtils.html
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html


### how to create a bitonic array in java
http://algs4.cs.princeton.edu/14analysis/BitonicMax.java
http://groups.csail.mit.edu/cag/streamit/results/bitonic/code/streamit/BitonicSort.java
https://jznest.wordpress.com/2014/03/04/search-in-a-bitonic-array/
http://www.mit.edu/~ibaran/papers/3sum.pdf
https://github.com/eschwabe/interview-practice/blob/master/coursera/algorithms-part1/analysis-of-algorithms/ThreeSum.java
https://zhuangda.wordpress.com/2013/05/26/3-sum-in-quadratic-time/
