---
title: Divide and Conquer
...

# Algorithm Design Techniques

The time has finally come! So far this quarter we've mostly been doing set up, but now we can work towards our main goals for the quarter! Today we will be looking at our first algorithm design technique!

An algorithm design technique is sort of a design pattern for algorithms. It's an intentional way to approach building an algorithm, intended to be generalizable to many different problems. As we're looking at algorithm design techniques throughout the quarter, it's important to keep in mind that there are many of these, and none are universal. In general, there is no algorithm for writing algorithms, but there are strategies that often work.

# The Divide and Conquer Technique

The first algorithm design strategy of the quarter is called the "Divide and Conquer" technique. To very briefly summarize, divide and conquer algorithms work by breaking up a large problem into smaller versions of itself, solving each smaller version recursively, and then reassembling those solutions into a final answer. You've actually already seen some examples of this technique before! In this reading we will look at those examples, then highlight the patterns they use that we can then generalize for future algorithms.

## Merge Sort

The classic example of a divide and conquer algorithm is merge sort. As a refresher, mergesort works by:

- First check if the input list is of size 0 or 1, if so then return it.
- Otherwise split the list into two halves (we'll call these left and right), and recursively call mergesort on each half
- After the recursive calls, the left and right halves of the list will be sorted, so we merge them together in the following way:
    - Create a new empty list
    - While the left and the right are not empty:
        - Add either the first value of the left half or the first value of the right half (whichever comes first in the sorted order) to the list
    - Add any remaining elements from the left or right list (we got here because one became empty)
- Return the resulting list

The key insight that mergesort leverages is that merging two already-sorted lists is easier than sorting a list from scratch, then applying that idea recursively. Because the algorithm is recursive, we also need to have a base case, in this case it is when the list is of size 0 or 1, as those lists cannot be split in half, and they are conveniently already sorted!

## Base Case, Divide, Conquer, Combine

A divide and conquer algorithm consists of four parts:
- **Base Case**: We will express divide and conquer algorithms recursively, and so we need a base case. Most often, we will check if the input size is below some threshold. If so, then we will solve the problem iteratively (e.g. using a different algorithm or a brute force approach).
- **Divide**: If the input was too large for the base case, then we will somehow break up the problem into one or more smaller versions *of the same problem*.
- **Conquer**: We will recursively solve each smaller subproblem identified in the divide step.
- **Combine**: Once we have our solutions from the recursive calls, we use those to identify our final answer.

As we saw with mergesort, divide and conquer algorithms are helpful in the situation that combining solutions from smaller subproblems is easier than solving the larger superproblem from scratch. 

Once we have identified each of these components, our algorithm will generally behave as follows:
- **Check for base case**: if the input is smaller than some threshold then solve it directly, return the answer
- Otherwise, **Divide** the problem into one or more smaller versions of the same problem
- For each subproblem identified in the previous step:
    - **Conquer**: recursively call this algorithm on the subproblem
- **Combine**: take each subproblem's solution and use those to assemble our final answer.
- return that final answer

We can now "reverse engineer" mergesort by identifying the **Base Case**, **Divide**, and **Combine** steps of divide and conquer algorithms, then plugging them into that generic psuedocode! (We do not need to identify the conquer step, as that is always the same, just recursively solve all subproblems) In this case, those three steps for mergesort are:
- **Base Case**: check if the list is size 0 or 1, if so then just return it
- **Divide**: Split the list into a left half and a right half
- **Combine**: The merge procedure (repeatedly remove the first from the left or right, whichever should come next)

## Quicksort

Quicksort, which you saw in CSE373, is another example of a divide and conquer algorithm! As a reminder, here's the behavior of quicksort:
1. If the list is size 0 or 1, just return
1. Otherwise, select a pivot element and partition the list so that all elements smaller than the pivot are to its left, and those larger than the pivot are to its right
1. Next, recursively call quicksort on the sublist containing the elements smaller than the pivot and the sublist containing the elements larger than the pivot
1. Finally, just return.

In this case, our three steps are:
- **Base Case**: step 1
- **Divide**: step 2
- **Combine**: step 4

Notice that even though quicksort has all the same divide-and-conquer components as mergesort, the computational work is not distributed the same way between them. In mergesort the work of comparing and reodering elements occurred in the combine step. In quicksort, that occurred in the divide step. By comparison, the divide step of mergesort was really simple (just split by the middle index), and the combine step of quicksort was trivial (literally just return the answer, because it was already finished).

These two algorithms are at "extreme" ends of the workload distribution. When discussing divide and conquer, we'll see some algorithms like mergesort where the majority of the work is done when combining, we'll see some like quicksort where the majority of the work is done when dividing, and we'll see some where there is meaningful work done in both steps. All of these will still fit the divide and conquer paradigm!

# Correctness of Divide and Conquer

As a reminder, when demonstrating the correctness of any algorithm, we need to show:
- That it produces no errors (*soundness*)
- That it does not run forever (*termination*)
- That the answers it gives are correct (*validity*)


