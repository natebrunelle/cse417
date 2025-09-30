---
title: Running Time
...

# Correctness is not everything

Throughout this course our primary (but not exclusive) focus will be on developing intentional processes for designing faster algorithms. Almost always, for the problems we try to solve, it will be easy to write an algorithm if we have no consideration for the speed of the algorithm. Usually we would be able to write a naive algorithm that simply tries out every possible option and then returns the correct one. While such an algorithm would likely be correct, it likely would not be efficient. Being able to tell when an algorithm is efficient and being able to define efficient algorithms both require insights that we will develop throughout the quarter.

## Application to Sorting

To help us see why we might care so much about efficiency in addition to correctness we'll compare two different sorting algorithms, both of them correct.

The first sorting algorithm we will look at is selection sort, which we discussed in class on Friday 9/26. As a reminder, selection sort will sort a list in descending order by repeatedly selecting the next largest element in the array. More precisely, for each index $i$ of the array, we will swap the value at index $i$ with the value at index $j$ where index $j$ contains the largest value between index $i$ (inclusive) and the end of the array.

The second algorithm we will look at we will call permutation sort. This algorithm will sort the array by repeatedly producing a different permutation of the array until it is in descending order.  

### Both algorithms are correct

We proved in class that selection sort is correct, so let's quickly demonstrate that permutation sort is correct.

- Soundness: the steps of this algorithm are to generate permutations, and check if the result is in descending order. Neither of those should generate an error.
- Termination: There are only a finite number of permutations, so the code will eventually terminate
- Correctness: The algorithm is designed to stop when the current permutation is in descending order, and so the result is guaranteed to be in descending order when the algorithm terminates.

(meta lesson: not all proofs of correctness need to be clever!)

### One algorithm is wildly less efficient

Even though both algorithms are correct, that does not mean that they are equally good. I implemented both algorithms in Java so that we could try out running each one. I then timed how long each algoritm took to run on arrays of various sizes. The following table shows the results:

length     Selection Sort    Permutation Sort
------     --------------    ----------------
1          2.3 ms            7.1 ms
2          2.6 ms            10.0 ms  
3          2.9 ms            14.1 ms
4          3.0 ms            20.5 ms
5          3.2 ms            104.6 ms
6          3.7 ms            581.5 ms
7          3.8 ms            1253.3 ms
8          4.1 ms            5576.2 ms
9          4.3 ms            23513.1 ms
10         4.4 ms            164403.8 ms
11         4.8 ms            854988.4 ms
12         5.4 ms            8442984.5 ms $\approx$ 8.4 s
13         6.4 ms            120.35 s
14         6.6 ms            1595.23 s $\approx$ 26 minutes
15         7.1 ms            I didn't bother trying, but I think it would be over 6 hours

Nathan (the author of this particular reading) is a pretty patient dude, so clearly there's a problem if he couldn't stand to wait for permutation sort to sort an array of length only 15. In practice we'd like to sort lists that are millions of items long in reasonable amounts of time, so selection sort is clearly better!

Perhaps it's intuitive to you why selection sort is better. After all, selection sort tries to carefully construct a sorted list piece-by-piece, whereas permutation sort exhaustively tries every reordering of elements until it happens upon one that works. What we'd like, though, is a way where we could predict which algorithm might be more efficient without going through all this effort of timing them.

# Defining Algorithm Running Time

Timing algorithms with a stop watch to see how long they run is called "Benchmarking". Benchmarking has a lot of value for many applications such as: testing to see how well a program runs on specific hardware, testing to see how well a program runs on real-world input, and testing to see how different implementations compare. In summary, benchmarking is most helpful in testing *code that has already been written*. It is not at all helpful for helping us to decide *which* algorithm to turn into a program, or in identifying how to make an algorithm faster.

When designing out algorithms, we'll need a way to talk about running time that is *predictive* rather than *measured*. Specifically, we will defince algorithm running time so that it has the following properties:

- It does not require us to implement or run our algorithm to determine its running time
- The running time will be the same, regardless of how the algorithm is run (i.e. we would not accidentally conclude that an algorithm was faster just because it was run on a faster computer)
- We can use it to compare algorithms (i.e. we have a way of saying that one algorithm is faster than another)
- We do not need to select an input size in advance (some algorithms may be slower for small inputs, but faster for large inputs, and so we don't want to have to pre-decide what input size we might care about)

All of these properties will help us because they make running time a property of the algorithm itself in that it does not depend on how the algorithm is implemented or deployed. This allows us to evaluate the efficiency of algorithms before we ever write code!


