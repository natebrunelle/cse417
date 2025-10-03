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
