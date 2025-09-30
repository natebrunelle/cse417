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
- The running time will be the same, regardless of how the algorithm is run (i.e. we would not accidentally conclude that an algorithm was faster just because it was run on a faster computer or implemented using a more performant programming language)
- We can use it to compare algorithms (i.e. we have a way of saying that one algorithm is faster than another)
- We do not need to select an input size in advance (some algorithms may be slower for small inputs, but faster for large inputs, and so we don't want to have to pre-decide what input size we might care about)

All of these properties will help us because they make running time a property of the algorithm itself in that it does not depend on how the algorithm is implemented or deployed. This allows us to evaluate the efficiency of algorithms before we ever write code!

# How we define running time

To achieve all of these properties, we will define algorithm running time in the following way:

We say that the running time of algorithm $A$ is a function $f: \mathbb{N} \rightarrow \mathbb{N}$ (i.e. a function mapping integers to integers) such that $f(n)$ is the worst case number of "operations" the algorithm would perform for an input of size $n$.

In other words, we express the running time of an algorithm $A$ as a function $f$. The input and outputs to that function $f$ will both be integers. The input value to $f$ will be the size of the input given to the algorithm $A$. The output value from $f$ will be the maximum (worst case) number of "operations" that $A$ will perform among all inputs of the given size. 

This definition already pretty clearly gives us some of the properties that we're after. For one, this definition does not depend on the speed of the computer that may run our algorithm. Faster computers just do each operation more quickly, they do not do fewer operations. Also, by making running time a function of the input size, it does not require us to pick a specific input in advance. It turns out this definition gives us ALL of the properties we want, but we need to do a bit more work first to see this.

## Definine "operations"

In this definition of running time, we said that we need to count the number of "operations." However, we did not really state what an "operation" is. If you talked about running time in a prior course (e.g. cse123 or cse373) you most likely used the phrase "primitive operation" or similar to describe the thing that you'd count. From there, you typically said that a primitive operation is something like arithmetic, array indexing, variable assignment, etc. This choice is not wrong, and it will pretty much always give you essentially the same result as what we'll do in this course, but we're going to select something different for our "operations" in order to make our lives easier.

Instead of counting *all primitive operations* for our algorithms, we'll instead pick just a small number of steps of our algorithm and just count those. As long as we're careful about what steps we pick, this should give us the same general answer as counting primitive operations, but with less work.

For example, when discussing sorting algorithms in CSE373 you may have said that "Selection Sort requires $n^2$ comparisons". In this case, the operation we're counting for running time is "comparisons". When doing this, we could ignore all arithmetic, array indexing, etc. when identifying our running time. 

There are generally a few ways that we could select our operation(s), but here are some things to consider when selecting which operation or operations to count:

- **Necessity**: The selected operations should be *necessary* for solving the given problem. By this we mean that any algorithm that solves the same problem should need to do these operations. For the sorting example, comparisons was a good choice because any algorithm needs to compare elements' values to determine their order.
- **Frequency**: Looking ahead, we're eventually going to be considering our running times asymptotically, meaning we'll ignore constant coefficients. If we select the *most frequent* operation, then that should give us the same asyptotic answer as if we selected several operations.
- **Magnitude**: Some operations will be more expensive to perform. Instead of looking at all operations, we could just look at the most expensive one, since that will have a greater impact on the real-world time our final implementation requires. For example, when sorting we often count comparisons because complex objects may require complex procedures to compare.

In most cases, there will be a clear choice of one or a few operations that is best according to all three considerations above. There may be times, however, when different considerations may lead you to select different operations. We'll just ignore that circumstance for now, and then bring it up again later when we see our first example. (But in case you can't wait to see that to have some idea of what we'll do... typically in this situation we would give our audience a running time for each choice of operation so they could choose for themselves based on their application.)


