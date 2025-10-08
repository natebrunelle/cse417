---
title: Sorting
...

Thus far we have seen several examples of divide and conquer algorithms (Merge Sort, Quick Sort, Trominos Tiling, Schoolbook Integer Multiplication, Karatsuba's Algorithm, Maximum Sum Subarray), and showed different ways to use the "base case, divide, conquer, combine" anatomy to solve this diversity of problems, and use the Master Theorem to help use reason about running time. We additionally showed some examples where providing additional return values can help us to write our algorithms by improving running time (Improved Maximum Sum Subarray) or enabling correctness (Binary Tree Diameter).

Next, for this reading (our final one on divide and conquer) we will continue on this theme of "more tasks per stackframe saves time overall" by looking at algorithms where sorting will be helpful. More precisely, these next algorithms we discuss will have similar behaviors in that they are not themselves sorting tasks (i.e. our target output is not a sorted list), but sorting "along the way" will be very useful.

# List Inversions

We'll look at one example in this reading, then a second example in class (teaser, the one we'll discuss in class is Nathan's FAVORITE divide and conquer algorithm).

For the reading, we'll be writing an algorithm to determine the number of inversions in a list. We define a list inversion as the number of out of order items in a given list. Suppose we wanted a list to be sorted in ascending order (i.e. smallest to largest). In that case a list inversion is a pair of indices $i<j$ where $arr[i]>arr[j]$, in other words a pair of values where the larger one appears before the smaller one. The number of inversions in a list is the count of all such pairs. 

For example, the list [1,3,2] has 1 inversion, that being the pair of indices (1,2), because the values 3 and 2 are out of order.

The list [2,3,1] has 2 inversions, those being the pairs of indices (0,2) and (1,2), because the value 1 is out of order with the values 2 and 3.

The list [3,2,1] has 3 inversions, those being the pairs of indices (0,1), (0,2), and (1,2), because all pairs of values are out of order.

The list [1,2,3] has 0 inversions, because all items are in order.

To help us gather an intuition, all of the following are true of list inversions:

1. A sorted list has 0 inversions
1. A reversed sorted list has the maximum number of inversions (which will specifically be $\frac{n(n-1)}{2}$, i.e. "$n$ choose $2$")
1. The number of inversions is equal to the number of times a smaller element appears after a larger one.

We will leverage this last statement to produce an algorithm. To find the number of list inversions, we will do the following:

- For each element of the list, count the number of times we see a smaller value after it.
- Sum together those counts across all elements.

To put this in psuedocode, we'll get:

>    public int inversions(List arr){
>        inversions = 0
>        for(int i = 0; i < arr.size(); i++){
>            for(int j = i+1; j < arr.size(); j++){
>                if(arr[i] > arr[j]){
>                    inversions += 1;
>                }
>            }
>        }
>        return inversions;
>    }

Given the two nested for loops, this algorithm will run in time $O(n^2)$. Now let's use divide and conquer to bring this down to $O(n \log n)$.
