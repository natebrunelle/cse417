---
title: Using SAT solvers
...

# Overview

The focus of today's lecture will be on converting various problems into instances of SAT. In this reading, we'll start with a simple example of a logic puzzle.

# A logic puzzle

Here is the puzzle: A group of people accuse each other of being witches and make statements, some of which may be lies. A truthteller is someone who always tells the truth, and a liar is someone who makes *at least one* lie.

* Alice: Bob is a witch. I am not a witch.
* Bob: Carol is as honest as I am.
* Carol: The number of witches is even. Alice and I are not both truthtellers. I am not a
witch.
* David: At least one of the witches is a truthteller.

Who are the witches?

If you'd like, take a moment to think about how you would solve this puzzle by hand.

# The solution

We'll use 8 boolean variables, for each of the 4 people being a truthteller and being a witch. First, we'll convert each of the statements into a logical expression using logical symbols. A reminder of a few of the common symbols we use:

| Symbol | Meaning |
| :-: | :-: |
| $\land$ | and |
| $\lor$ | or |
| $\lnot$ | not |
| $\implies$ | implies (if, then) |
| $\iff$ | if and only if |
| $\oplus$ | exclusive or |

With these symbols, we rewrite each of the four people's statements as follows:

Alice's statement is:

$$\text{ATrue} \iff (\text{BWitch} \land \lnot \text{AWitch})$$

In other words, if Alice is a truthteller, then we know both of her claims are true, and conversely, if both of her claims are true, then she was a truthteller.

Bob's statement is:

$$\text{BTrue} \iff (\text{CTrue} \iff \text{BTrue})$$

For Carol's statement, a new concept to translate is that the number of witches is even. For this, we can use $\oplus$ (exclusive or). This symbol means one or the other, but not both. To summarize what it means a different way, see the table below.

| $x$ | $y$ | $x \oplus y$ |
| :-: | :-: | :-: |
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

If we stare at this for a little, you'll notice that $x \oplus y$ is basically $x + y$ (mod 2), meaning we compute $x + by$ and take the remainder when divided by 2. Thus, this can be used to detect even or odd! False would mean 0 (mod 2), i.e. even, and true would mean 1 (mod 2), i.e. odd. We get that:

$$\text{EvenWitches} = \lnot (\text{AWitch} \oplus \text{BWitch} \oplus \text{CWitch} \oplus \text{DWitch})$$

Plugging this in, Carol's full statement is then:

$$\text{CTrue} \iff (\text{EvenWitches} \land {} \lnot (\text{ATrue} \land \text{CTrue}) \land \lnot \text{CWitch})$$

David's statement is:

$$\begin{align*}
\text{DTrue} \iff (&(\text{ATrue} \land \text{AWitch}) \lor (\text{BTrue} \land \text{BWitch}) \lor {} \\ & (\text{CTrue} \land \text{CWitch}) \lor (\text{DTrue} \land \text{DWitch}) )
\end{align*}$$

# Conversion to CNF

Recall that to plug these queries into a SAT solver, we need to write them not as logical statements in general, but a particular form of logical statement called a CNF. A CNF is an AND of clauses, a clause is an OR of literals, and a literal is a variable or its negation. One example is something like

$$(x_1 \lor \lnot x_2) \land (x_3 \lor x_4 \lor \lnot x_5) \land (\lnot x_3 \lor x_2)$$

How do we translate logical statements into CNF? 

Take Alice's statement, for example. It uses the symbol $\iff$, which we don't want. But we know that $x \iff y$ is equivalent to:

$$(x \land y) \lor (\lnot x \land \lnot y)$$

This says that both are true, or both are false. This only uses $\land$ and $\lor$ as we wanted, but the order is wrong: it is an OR of ANDs, and we wanted an AND of ORs. In general, you can express any logical symbol or sentence as an OR of ANDs by simply listing all the possible ways it could be true, then taking the OR of all these ways. But it is not the form that we need.

We can apply standard rules like the distributive property to transform this sentence into a CNF. Just like how $a \times (b + c) = (a \times b) + (a \times c)$, you can take a moment to quickly convince yourself that the same property is true for $\land$ and $\lor$. In particular:

* $a \land (b \lor c) = (a \land b) \lor (a \land c)$
* $a \lor (b \land c) = (a \lor b) \land (a \lor c)$

These help us flip from an OR of ANDs to CNF, which is an AND of ORs! In particular, if we apply the second distributive rule with $a = (x \land y)$, $b = \lnot x$ and $c = \lnot y$, the expression for $x \iff y$ stated above can be transformed into

$$\begin{align*}
(x \land y) \lor (\lnot x \land \lnot y) & = ((x \land y) \lor \lnot x) \land ((x \land y) \lor \lnot y) \\
& = ((x \lor \lnot x) \land (y \lor \lnot x)) \land ((x \lor \lnot y) \land (y \lor \lnot y)) \\
& = (T \land (y \lor \lnot x)) \land ((x \lor \lnot y) \land T) \\
& = (y \lor \lnot x) \land (x \lor \lnot y)
\end{align*}$$

This is now a CNF! Think about the result for a second - it says that for $x \iff y$ to be true, we need both $y \lor \lnot x$ and $x \lor \lnot y$. The first one, $y \lor \lnot x$, can be read as "if $x$ is true, then to satisfy this, $y$ must be true", in other words, $x \implies y$. The second one is likewise $y \implies x$. So it makes sense! Maybe you could've actually derived this without all the messy distributive property work, but what I showed you was a general way to transform *any* statement in CNF, not just this particular case.

Applied to Alice's statement, we now have:

$$((\text{BWitch} \land \lnot \text{AWitch}) \lor \lnot \text{ATrue}) \land (\text{ATrue} \lor \lnot (\text{BWitch} \land \lnot \text{AWitch}))$$

The distributive rule simplifies the left part into 2 clauses: $\text{BWitch} \lor \lnot \text{ATrue}$ and $\lnot \text{AWitch} \lor \lnot \text{ATrue}$. For the right part, we apply what's known as de Morgan's rules:

* $\lnot (a \lor b) = \lnot a \land \lnot b$
* $\lnot (a \land b) = \lnot a \lor \lnot b$

In particular, the second one can be used to turn $\lnot (\text{BWitch} \land \lnot \text{AWitch})$ into $\lnot \text{BWitch} \lor \text{AWitch}$. Then we're done! We get the following 3 clauses from Alice's statement in total:

$$\text{BWitch} \lor \lnot \text{ATrue}$$

$$\lnot \text{AWitch} \lor \lnot \text{ATrue}$$

$$\text{ATrue} \lor \lnot \text{BWitch} \lor \text{AWitch}$$

It might have been a bit of work, but notice how it was all just very mechanical work - no creativity needed! Just apply the algebraic rules and transform the words into CNF form.

# Summary

Here are the steps we took to convert a problem into an instance of SAT:

1. Define your variables and model the problem using logical formulas. At this step, use whatever logical symbols and functions you like.
1. For all logical symbols that are not $\land$, $\lor$, and $\lnot$, get rid of them by substituting in an equivalent expression using just $\land$, $\lor$, and $\lnot$. You can always find one possible expression by listing all ways the expression can be true, then ORing them together.
1. Use the distributive rule and De Morgan's laws to rewrite each statement in CNF form, as an AND or ORs. 

