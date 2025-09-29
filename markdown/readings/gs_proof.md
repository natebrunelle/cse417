---
title: Gale&ndash;Shapley Proof
...

# Recap on Gale&ndash;Shapley

Take a moment to remind yourself of the key definitions regarding stable matchings and Gale&ndash;Shapley algorithm. There is nothing particularly new in this reading, but today's concept check will reinforce these basic definitions to prepare for the lecture.

* **Unstable pair**: Two people who like each other more than each likes their current partner.
* **Perfect matching**: Matching between two groups so that each person is matched with exactly one person from the other group.
* **Stable matching**: Perfect matching with no unstable pairs. 

The Gale&ndash;Shapley algorithm is the following algorithm, which we claim to produce a stable matching given two groups $P$ and $R$ with preferences. These preferences rank the other group, with no ties. "Free" means "unmatched".

```
1. while there is a free proposer p:
2.     Let r be the p's top choice that p has not yet proposed to.
3.     if r is free:
4.         Have r accept p's proposal.
5.     else if r is matched with p2 and r prefers p > p2:
6.         Have r accept p's proposal.
7.         Have r unpair themself from p2 (so p2 is now free). 
8. return all matches
```

Furthermore, recall that algorithm correctness means three things:

1. **Soundness**: There are no errors/exceptions, and when there are phrases like "let $x$ such that...", it is guaranteed for $x$ to exist. 
2. **Termination**: No infinite loops.
3. **Validity**: The output of the algorithm matches the goal of the specification.

In terms of Gale&ndash;Shapley, these mean:

1. In line 2, $p$ has not yet proposed to every receiver $r$.
2. Eventually no proposer is free.
3. The output is a stable matching.

In Wednesday's lecture, all three of these will be proven. You are encouraged to brainstorm for yourself why you think these are true!

We will also prove the proposer optimiality/receiver pessimality theorem. It says the following. (Below, "happier" and "less happy" really mean "happier or same happiness as" and "less happy or same happiness as", for the sake of brevity.)

> **Theorem.** Given two sets $P$ and $R$ with preferences, consider all possible stable matchings. Among these, there is a unique stable matching $M_{P\text{-best}}$ such that every $p \in P$ is happier in $M_{P\text{-best}}$, compared to themself in any other stable matching. 
>
> This same $M_{P\text{-best}}$ has the property that every $r \in R$ is less happy in $M_{P\text{-best}}$, compared to themself in any other stable matching. Gale&ndash;Shapley, with $P$ proposing, finds $M_{P\text{-best}}$. 

Note that line 1 of the Gale&ndash;Shapley algorithm also asked us to pick any free $p$. If multiple $p$ are free, a priori, the output of the algorithm could depend on how we pick one. This theorem implies that it does not matter, because no matter how we pick $p$, Gale&ndash;Shapley always finds $M_{P\text{-best}}$.
