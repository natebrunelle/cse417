---
title: P, NP, and SAT
...

# Overview

In this last unit of the course, we'll study problems that are difficult to solve efficiently, or solve at all. Many problems that you might want to solve fall into this category, some of which we've seen already. Here are some examples:

* From Lecture 5.3, the problems of optimal graph coloring, vertex cover, and load balancing. 
* (Travelling salesperson problem, TSP) Given a weighted graph, find the shortest path that visits every vertex. For example, you could be a salesperson going door-to-door selling things.
* (Halting problem) Given a piece of code, determine if the code always terminates. You do this manually for every algorithm you write in this class, but could you automate it?

We saw one approach to solving some of these problems in Lecture 5.3: try a greedy algorithm that can approximate the solution well. To summarize what will happen in these last two weeks,

* **Monday, November 24:** We'll familiarize ourselves with some definitions and the problems we'll be trying to solve.
* **Wednesday, November 26:** There is a general purpose program called a *SAT solver*. We'll learn how to use it to solve many of these difficult problems.
* **Monday, December 1:** We'll go under the hood to talk a little bit about how SAT solvers work.
* **Wednesday, December 3:** We'll talk about how to *prove* that a problem is difficult or impossible to solve, not just because we didn't try hard enough or weren't clever enough to solve it.

# What is P?

To talk about problems that easy and hard, we need to define what these words mean. First, it will be useful to limit our view to *decision problems*

**Definition.** A decision problem is a problem whose answer to every input is either "yes" or "no".

Many other kinds of problems can be converted to and from a decision version without much effort. For example, if you had an optimization question, like "What is the distance between $s$ and $t$ in this graph?", the decision version would take an additional input $k$ and ask, "Is the distance between $s$ and $t$ at least $k$?" 

If you could solve the optimization version, you can definitely solve the decision version. If you can solve the decision version and wanted to solve the optimization version, you can binary search through different values of $k$ until you find exactly the distance between $s$ and $t$. 

When we talk about a decision problem being "easy" or "fast" to solve, we usually mean that it belongs to the class P.

**Definition.** P ("polynomial time") is the class of decision problems that can be solved with an algorithm that runs in time $O(n^c)$ for some constant $c$, where $n$ is the size of the input. 

This includes running times like $O(n \log n)$, since if a function is $O(n \log n)$, then it is also $O(n^2)$. The vast majority of decision problems that you have seen in this class (and decision versions of optimization problems) belong to P. 

There are two important things to note about P:

* P contains only decision problems. It doesn't contain optimization problems or things like that.
* Be careful that $n$ is the total input size, meaning how much space is needed to store in the input. Sometimes, like in dynamic programming with the oven allocation problem from Lecture 4.2, the running time depends on a *number* in the input. In that problem, it was the maximum time you can use. Depending on your model of computation, a number $N$ either takes $O(1)$ space to store (assuming it fits in a machine word) or $O(\log N)$ bits to write in binary. Running times like $O(N)$ would then be at least exponential in the input size, since the size is $O(\log N)$. 

# What is NP?

**Definition.** NP ("nondeterministic polynomial time") is the set of decision problems for which you can *check* whether a sample solutions works or not in $O(n^c)$ time, for some $c$. 

A common misconception is that NP stands for "not polynomial time". That's not true! NP is still a statement about the problem being somewhat easy. In particular, it's easy to check. We'll talk more about what "nondeterministic" means in this setting on Monday. For now, just think "NP = easy to check". 

Examples of NP problems include:

* Graph coloring. In this problem, we were given a graph and we had to determine if we can color the vertices with $k$ colors, such that no edge has the same color on both ends. It's very easy to check that a sample coloring works - just iterate through all the vertices, make sure that there are less than $k$ colors used overall, and iterate through all the edges, making sure that each edge has different colors on each end. This check is certainly polynomial time.
* Travelling salesperson problem, introduced earlier. The decision version asks if there is a route of length at most $k$. Given a sample route for the salesperson to take, we can just add up the distances along the route, and check that the sum is less than $k$. (And also verify that the route touches every vertex at least once.) This check is certainly polynomial time.


There are still more *complexity classes* beyond P and NP. For example, EXP is the set of decision problems that you can solve in $O(2^{n^c})$ time, for some $c$. As we said before, NP is a subset of EXP (in particular, you can solve NP problem using just $O(2^n \times n^c)$ time, for some $c$). But P and NP are the two most important complexity classes.

Most mathematicians and computer scientists believe that NP is a strictly larger set than P. Intuitively, you know from experience that checking answers is usually easier than solving problems. But we actually don't know for sure. At the same time, we have built financial systems and a myraid of other software based on the assumption that some problems are fast to check but slow to solve. Our world depends on this hypothesis. This is one of the most foundational open questions to resolve in computer science, called "P vs. NP". 