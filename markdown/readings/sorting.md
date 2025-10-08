---
title: Sorting
...

Thus far we have seen several examples of divide and conquer algorithms (Merge Sort, Quick Sort, Trominos Tiling, Schoolbook Integer Multiplication, Karatsuba's Algorithm, Maximum Sum Subarray), and showed different ways to use the "base case, divide, conquer, combine" anatomy to solve this diversity of problems, and use the Master Theorem to help use reason about running time. We additionally showed some examples where providing additional return values can help us to write our algorithms by improving running time (Improved Maximum Sum Subarray) or enabling correctness (Binary Tree Diameter).

Next, for this reading (our final one on divide and conquer) we will continue on this theme of "more tasks per stackframe saves time overall" by looking at algorithms where sorting will be helpful. More precisely, these next algorithms we discuss will have similar behaviors in that they are not themselves sorting tasks (i.e. our target output is not a sorted list), but sorting "along the way" will be very useful.

# List inversions

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

>     public int inversions(List arr){
>         inversions = 0
>         for(int i = 0; i < arr.size(); i++){
>             for(int j = i+1; j < arr.size(); j++){
>                 if(arr[i] > arr[j]){
>                     inversions += 1;
>                 }
>             }
>         }
>         return inversions;
>     }

Given the two nested for loops, this algorithm will run in time $O(n^2)$. Now let's use divide and conquer to bring this down to $O(n \log n)$!

# Divide and conquer intuition

To write our divide and conquer algorithm, we will look at one more way of thinking about an inversion in a list. From our definition of an inversion, that $i<j$ but $arr[i]>arr[j]$, we can see that the number of inversions involving index $j$ paired with a smaller index matches the number of values $j$ must "overtake" to sort the list. So if $j$ is involved in $3$ inversions, then there are 3 values that $j$ should "overtake" when sorting the list!

Let's see some examples of this.

For the list [1,2,4,3] we will look at $j=3$ (so the value $3$). In this case $3$ participates in one such inversion (with index $2$), and in the sorted list [1,2,3,4] its value overtakes just one other value.

In the list [4,3,2,1] the index $3$ is the larger index in 3 inversions, $2$ is the larger index in 2 inversions, $1$ is the larger index in 1 inversion, and $0$ is the larger index in 0 inversions. Comparing to the sorted list [1,2,3,4], the value $1$ overtakes 3 other values $4$, $3$, and $2$), the value $2$ overtakes 2 other values ($3$ and $4$), and the value $3$ overtakes one value ($4$).

By way of analogy, we could consider this like a formula 1 race. If we have 4 cars racing that begin in the order [4,3,2,1], and the final race results are [1,2,3,4], then it must be that car number 1 overtook all three other drivers, car number 2 overtook numbers 3 and 4, etc.

Our divide and conquer algorithm will work by counting the total number of these "overtakings" when sorting a list. To highlight the important insight here for today, the primary structure of this algorithm will just be a sorting algorithm. On top of that sorting algorithm, we will observe how many values each element "overtakes" throughout.

# Divide and conquer algorithm

To get us started in designing this algorithm, we'll begin with our favorite divide and conquer sorting algorithm - Merge Sort!

As a refresher, here are the "base case, divide, conquer, combine" steps for merge sort:

- **Base Case**: If the list is of size 0 or 1, just return it.
- **Divide**: break the list into 2 halves by index (so we have the left half of the elements and the right half)
- **Conquer**: recursively sort each list
- **Combine**: merge the sorted sublists together by repeatedly removing the smaller front element of each.

Now, for each step, we want to recognize when we have an element overtake another:

- **Base Case**: Here we do not change the order of any elements, and so no overtakings happen
- **Divide**: Again, no elements are actually moved here
- **Conquer**: We will suppose in the conquer step, we will observe some overtakings happen, and the number of them will be returned by the recursive call (in addition to returning the sorted sublist)
- **Combine**: This is where we actually do the work of moving elements, and so this is where some values will overtake others! Whenever a value from the left sublist is chosen, it overtakes no values (because it was already the leftmost value). Whenever a value from the right sublist is chosen, it overtakes ALL values that remain in the left sublist.

With all of these insights, we can now give our divide and conquer algorithm!

- **Base Case**: if the list is of size 0 or 1, then it is already sorted and has 0 inversions, so return that list as well as the value 0
- **Divide**: break the list into 2 halves by index (so we have the left half of the elements and the right half)
- **Conquer**: recursively call the list inversions algorithm on each sublist. This will have the effect of sorting each sublist, and counting the number of overtakings that happens when doing so (call this $leftInversions$ and $rightInversions$).
- **Combine**: we start out by saying $inversions = leftInversions + rightInversions$. While doing the merge proceduce for mergesort (repeatedly extract the smaller front element from each sublist), whever we extract an element from the right sublist we will increase the inversion count by $left.size()$. In other words, each time we extract from the right sublist we do $inversions += left.size()$.

## Example

Let's look at what this algorithm does for the list [1,3,5,7,6,4,0,2]


