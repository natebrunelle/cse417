---
title: Approximation algorithms
...

# Introduction

In Monday's lecture, we will see examples of greedy algorithms being used in contexts where the goal not to get the optimal answer, but just to do the best that we can in an efficient way. This is an extremely common use of greedy algorithms in general. 

In this reading, we'll talk about one large class of these good-but-not optimal algorithms, called *approximation algorithms*. 


# What is an approximation algorithm?

**Definition.** Suppose you are trying to solve a problem that asks you to maximize some quantity. On any particular input,

* Let $a$ be the value obtained by your algorithm.
* Let $o$ be the optimal value.

The *approximation ratio* for this particular input is $a/o$. (Since the problem is maximization, and you are suboptimal, $a/o > 1$.) The approximation ratio for your overall algorithm is the largest $a/o$ for any possible input. 

If you problem is a minimization problem, the approximation ratio is defined as $o/a$, so that it is always bigger than 1. 

# Example: Vertex cover

In this problem, the input is an undirected graph. Your goal is to **select a subset of the vertices so that every edge has at least one of its vertices selected**. 

Here is an example of a feasible solution (from Wikipedia). You can check that every edge has at least one endpoint in red.

![Sample vertex covers, with the selected vertices in red](https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Minimum-vertex-cover.svg/2560px-Minimum-vertex-cover.svg.png)

The minimum vertex cover problem is actually believed to be impossible to solve optimally in an efficient way. Of course, you can iterate through all possible subsets of the vertices, but with $n$ vertices, there are $2^n$ possible subsets! This grows incredibly fast, making such an algorithm useless for all but tiny inputs. We'll talk about "impossiblity"-type results near the end of the course, but for now, let's focus on how to solve it realistically.

We will use the following greedy algorithm: **While the selected vertices are not a vertex cover (i.e. leave some edge uncovered), pick any uncovered edge and select *both* of its endpoints.**

This may seem a little puzzling at first: why pick both endpoints when just one suffices to cover the edge that needs to be covered? In fact, this is the critical thing to do that makes this work reasonably well!

**Claim.** The greedy algorithm described above achieves an approximation ratio of 2. (In other words, it selects no more than twice the minimum possible number of vertices.)

*Proof.* When we choose both endpoints, we cover all edges that touch the chosen edge. Thus, we guarantee that the chosen edges are all *not* touching each other. *Any* vertex cover must still cover these edges, and use at least 1 vertex to cover each edge. (Since no two chosen edges were touching, it is not possible for 1 vertex to cover 2 of them.) Thus, we chose both, and any vertex cover must choose at least 1, so we chose at most twice as many as needed.

# Looking ahead

We'll discuss some more approximation algorithms and other uses of greedy algorithm in class on Monday. See you there!