---
title: Finite cases in DP
...

# A bit of setup

Looking forward to tomorrow's release of HW5, the intended solution to Problem 9 will rely on a common idea that you haven't seen in lecture, so I wanted to give you an example. The technique is: conditioning on a finite set of cases.

You saw in lecture that sometimes, it is useful in dynamic programming problems to add another parameter. For example, in the oven problem, when deciding whether or not bake item $i$, in the case that you bake it, you don't get exactly the original problem on $i-1$. You used some time, and thus now have less time. Thus, we added an additional time parameter to the DP, to express that subproblem depend both on $i$ and the time remaining.

In the example I'm going to show you, the additional parameter is not a number like time, but a finite set of cases that could occur.

This reading is also available on Ed, where you can ask follow-up questions. 

# An example problem

There are two people who will give you money, $A$ and $B$. On day $i$, person $A$ can give you $A[i]$ dollars and person $B$ can give you $B[i]$ dollars, but there are two rules: you can only get money from one person each day, and you cannot go to the same person two days in a row. You can also wait and do nothing. Note that waiting is not a bad strategy: If $A = [10, 1, 1]$ and $B = [1, 1, 10]$, the best solution is to go to $A$ on day 1, wait on day 2, and go to $B$ on day 3. 

In the standard DP problem solving strategy you learned in class, first ask: What are the options for the last choice? We go to $A$, go to $B$, or wait. The problem is, if we go to $A$ on day $i$, what's left is not exactly the subproblem on day $i-1$. We would have to find the best profit under the additional condition that we did not go to $A$ on day $i-1$! This means that we need to track one more parameter: we track what happened on the last day! We can say:

* Let $\text{case}$ be either "go to A", "go to B", or "wait". (You would implement it as an enum, possibly.) Let $\text{best}(i, \text{case})$ be the best profit you can get up to day $i$, if you follow the instruction of $\text{case}$ in day $i$.

Alternatively, it's a little cleaner to write three separate recurrences, one for each case. 

* Let $\text{bestIfA}(i)$ be the best profit you can get up to day $i$, assuming you go to $A$ on day $i$.
* Let $\text{bestIfB}(i)$ be the best profit you can get up to day $i$, assuming you go to $B$ on day $i$.
* Let $\text{bestIfWait}(i)$ be the best profit you can get up to day $i$, assuming you waited on day $i$. 

Then we can write the recurrences in terms of each other easily:

* $\text{bestIfA}(i) = A[i] + \max(\text{bestIfB}(i-1), \text{bestIfWait}(i-1))$
* $\text{bestIfB}(i) = B[i] + \max(\text{bestIfA}(i-1), \text{bestIfWait}(i-1))$
* $\text{bestIfWait}(i) = \max(\text{bestIfA}(i-1), \text{bestIfB}(i-1), \text{bestIfWait}(i-1))$

For example, the first one says, "If we are forced to go to $A$ on day $i$, we get profit $A[i]$, plus whatever is the best profit among the acceptable cases for day $i-1$."

In this problem, your final output would be $\max(\text{bestIfA}(i), \text{bestIfB}(i), \text{bestIfWait}(i))$, i.e. the best possible profit across all three cases. 

I haven't given you the base cases for this recurrence, nor the other later parts of Four Steps of solving DPs that we did in class. Think about those on your own!

# Applying this concept to P9

That's your job! There is a thread on Ed for you to ask questions about the above example and technique, but if you want help applying this to P9, come ask a TA/Nathan/Glenn in office hours.