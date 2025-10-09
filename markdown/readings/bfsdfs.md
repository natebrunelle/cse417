---
title: Graph Search
...

# Review of graphs

You learned about graphs and algorithms for traversing graphs in CSE 373. This reading is mostly a review of those algorithms. This reading covers what you need to know for CSE 417, but if it's been a while, it might feel a bit fast and condensed. [Review this reading from CSE 373 about graphs and graph traversal if you need a second explanation.](https://courses.cs.washington.edu/courses/cse373/25su/lessons/midterm-and-graphs/)

Recall that an graph is a data structure with vertices connected by edges. Some people call vertices "nodes" and edges "arcs"&mdash;it doesn't matter. In a **directed graph**, edges go from one vertex to another, but you may not necessarily be able to go back. In an **undirected graph**, edges go in both directions. 

We assume that the graph is represented in a bidirectional adjacency list, which means that the following two basic operations can be performed in $O(1)$ time from a vertex $v$:

* Getting the list of vertices that $v$ can go to via a single edge, called out-neighbors.
* Getting the list of vertices that can go to $v$ via a single edge, called in-neighbors. (Identical to out-neighbors for undirected graphs.)

# Breadth-first search (BFS)

## Pseudocode 

Given a starting point, BFS traverses the graph by visiting all vertices close to the start before moving farther away. Recall that a queue is a data structure that processes the oldest entries first. It is well suited for BFS.

```
1. Initialize a queue Q with the starting point.
2. Mark all vertices "unseen", except the start is "seen".
3. while Q is not empty:
4.     Get/remove the next vertex x from Q.
5.     Process x somehow.
6.     for all unseen outneighbors y of x:
7.         Mark y "seen" and add it to Q.
```

## Example

Suppose you have the following undirected graph:

```
 / 2 - 3
1  |
 \ 4
```

Suppose we start BFS at 1 and default to lower number first when multiple outneighbors are placed in the queue at the same time. We write queues so that the **left side goes first**. BFS evolves as follows:

| Queue | Get/remove | Queue is then | Final queue |
| :---- | :--------- | :------------ | :---------- |
| 1     | 1          | [empty]       | 2 4         |
| 2 4   | 2          | 4             | 4 3         |
| 4 3   | 4          | 3             | 3 (no new neighbors)          |
| 3     | 3          | [empty]       | [empty] (no new neighbors)    |

## Running time

For a graph with $n$ vertices and $m$ edges, BFS runs in time $O(n +m)$. Although the queue will never have duplicate vertices (since we have "seen"/"unseen" marks), each time we process a vertex, we do work for every outneighbor. Thus, overall, we do a constant amount of work for every vertex and for every edge, which is $O(n + m)$.

# Depth-first search (DFS)

## Pseudocode 

Given a starting point, DFS traverses the graph by following a path of edges as far as possible, then backtracking when unable to go further. Recall that a stack is a data structure that processes the newest entries first. It is well-suited for DFS.

```
1. Initialize a stack S with the starting point.
2. Mark all vertices "unprocessed".
3. while S is not empty:
4.     Get/remove the next vertex x from S.
5.     if x is "unprocessed":
6.         Process x somehow.
7.         Add all outneighbors of x to S.
8.         Mark x "processed".
```

Some authors prefer to write DFS as a recursive function. When doing so, the system's default call stack acts as the stack that we keep in the above pseudocode. (I personally find the iterative code easier to understand. You are free to disagree with the instructor's opinion.)

Note that for DFS, we mark vertices when they are actually processed, and for BFS, we mark vertices when they are first seen and added to the queue. This means that the BFS queue will never have duplicates, but the DFS stack may have duplicate vertices. Note that some authors use the word "visited" for both "seen" and "processed" depending on context, but for clarity, we will use different words for these different concepts.

## Example

Suppose you have the following undirected graph:

```
 / 2 - 3
1  |
 \ 4
```

Suppose we start DFS at 1 and default to **higher** number first when multiple outneighbors are placed in the stack at the same time. Note that because it is stack (last in, first out), this means we **remove lower number first** still. We write stacks so that the **right side goes first**. DFS evolves as follows:

| Stack | Get/remove | Stack is then | Final stack |
| :---- | :--------- | :------------ | :---------- |
| 1     | 1          | [empty]       | 4 2         |
| 4 2   | 2          | 4             | 4 4 3 1     |
| 4 4 3 1 | 1        | 4 4 3         | 4 4 3 (already processed) |
| 4 4 3 | 3          | 4 4           | 4 4 2     |
| 4 4 2 | 2          | 4 4           | 4 4 (already processed) |
| 4 4   | 4          | 4             | 4 2 1  |
| 4 2 1 | 1          | 4 2           | 4 2 (already processed) |
| 4 2   | 2          | 4             | 4 (already processed) |
| 4     | 4          | [empty]       | [empty] (already processed) |


## Running time

For a graph with $n$ vertices and $m$ edges, DFS also runs in time $O(n +m)$. Although the stack may take more iterations to clear than the BFS queue, many iterations only involve a constant amount of work (when the vertex is already processed). Already-processed vertices can only be seen as many times as there are incoming edges, so these add only $O(m)$ time. Every vertex is processed exactly once, and adds their outneighbors to the queue then. Thus, overall, we still do a constant amount of work for every vertex and for every edge, which is $O(n + m)$.