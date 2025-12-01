---
title: Hardness
...

# Overview

When you learned about applications of network flows, you learned the concept of a reduction.

**Reducing from problem A to problem B** means solving problem A by encoding it as an instance of problem B, then running an algorithm that you already know for problem B. In this class, we've seen several ways to use reductions:

* Where problem B is a graph algorithm (like BFS/DFS/topological sort/etc.)
* Where problem B is network flows
* Where problem B is SAT

In today's final lecture, we'll learn about one more way to use reductions, for a very different purpose than what we've done before. 

# Proving "hardness"

In class on Wednesday, we'll briefly sketch how to prove from scratch that certain problems are "hard", such as SAT and the Halting problem (analyzing whether or not a given piece of code terminates). But as you'll see, proofs of hardness from scratch are often very complicated and difficult, and would be extremely confusing to do for the vast majority of problems. 

Right now, for this reading, assume that we've already proved that something is hard, for example, that SAT is NP-hard. We'll use the technique of reduction not to solve a problem, but to prove that some other problem is also NP-hard!

In particular, **if you can solve problem A by encoding it as an instance of problem B, then problem B must be "more general" or "harder" than problem A**. We write this as: $$A \le_P B$$

The $P$ refers to fact that we also require the **encoding be constructible in polynomial time**. 

# Example of Hardness

Recall that SAT is the general problem of taking a CNF formula (equivalently, a set of clauses) and deciding if it can be satisfied by some assignment, and the 3SAT is the specific case where you are guaranteed that every clause in the input CNF formula has at most 3 literals. 

The fact that $\text{3SAT} \le_P \text{SAT}$ is obvious. All inputs to 3SAT are valid inputs to SAT, so if we could solve SAT, we could also easily solve 3SAT. 3SAT is easier.

It turns out that also, $\text{SAT} \le_P \text{3SAT}$! This is not obvious at all. This says that if you could solve 3SAT, you could also solve the more general problem of SAT. Here is the method:

Suppose you have a long clause $\ell_1 \lor \ell_2 \lor \dots \lor \ell_n$ as part of your input to SAT, where $\ell_1, \ell_2, \dots, \ell_n$ are some literals. Our goal is to write an equivalent input to 3SAT, where you can only have 3 literals per clause.

Create new variables $z_2, \dots, z_{n-2}$ and write the clauses:

$$
\begin{gather*}
\ell_1 \lor \ell_2 \lor z_2 \\
\lnot z_2 \lor \ell_3 \lor z_3 \\
\lnot z_3 \lor \ell_4 \lor z_4 \\
\vdots \\
\lnot z_{n-2} \lor \ell_{n-1} \lor \ell_{n}
\end{gather*}
$$

To check that these clauses really do mean the same thing as our original clause, let's think carefully about what these new clauses mean. Take the first two and suppose they are both true. 

* The variable $z_2$ must be either be true or false. 
* If it is false, because $\ell_1 \lor \ell_2 \lor z_2$ is true, we know $\ell_1 \lor \ell_2$. 
* If it is true, because $\lnot z_2 \lor \ell_3 \lor z_3$ is true, we know $\ell_3 \lor z_3$. 
* Thus, no matter what, we know that $\ell_1 \lor \ell_2 \lor \ell_3 \lor z_3$ is true. 
* Continuing this logic with the rest of the clauses, we conclude that if all the clauses are true, then $\ell_1 \lor \ell_2 \lor \dots \lor \ell_n$ must also be true.

Conversely, suppose $\ell_1 \lor \ell_2 \lor \dots \lor \ell_n$ is true. 

* At least one of the individual literals must then be true, say it is $\ell_i$. 
* Then, assuming $3 \le i \le n-2$, the clause $\lnot z_{i-1} \lor \ell_i \lor z_i$ is already true. 
* Pick $z_2, \dots, z_{i-1}$ to all be true and $z_i, \dots, z_{n-2}$ to all be false.
* Check now that every single clause now is satisfied!

The argument is very similar when $i = 1$, $2$, $n-1$, or $n$ &mdash; think about how it would work by yourself!

What we've proven is that whenever an assignment satisfies the original CNF formula, we can convert it into an assignment that satisfies the new CNF formula with 3 literals per clause, and vice versa! Therefore, this is a valid way to solve SAT using 3SAT! 

Lastly, we note that this reduction is polynomial time. For a clause with $n$ literals, we write down $O(n)$ new clauses of 3 literals each. So in fact, this was a linear-time reduction. This proves that $\text{SAT} \le_P \text{3SAT}$.

In conclusion, because $\text{SAT} \le_P \text{3SAT}$ and SAT is already NP-hard, it must be the case that 3SAT is also NP-hard! Since $\text{3SAT} \le_P \text{SAT}$ as well, the problems $\text{SAT}$ and $\text{3SAT}$ are actually equally difficult problems. By reducing problems to each other, we can show that many problems are difficult to solve!

# What will happen in class

The method of reductions is the easiest and most common method to prove that a problem is hard. But it only works if you already know some problems to be hard to begin with! In class, we'll roughly sketch how to prove a problem to be hard, from scratch, as well as give more examples of hardness reductions.

