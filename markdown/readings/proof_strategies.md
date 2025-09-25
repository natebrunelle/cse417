---
title: Proof Teachniques
...

# The What and Why of Proofs

Last class we talked about algorithm correctness, and in that previous reading we discussed that we want to make sure we defend any claims we make about our algorithms carefully and precisely. This ensures that other people, and even ourselves, have confidence that our algorithms behave in exactly the way we believe. We call these careful and precise defenses of algorithms "proofs". 

This quarter in CSE 417 we will be developing some skills necessary for defending algorithm running time and correctness, which requires developing at least a small about of experience in writing proofs. Our expectation is that, by the end of each unit, you will be able to write proofs of correctness and running time for well-behaved algorithms. By well-behaved, we mean that the proofs can be written following frameworks that we provide. These frameworks will work for many algorithms you will see beyond this course as well, but certainly not all of them. Similar to creative writing, proofwriting is a skill that people spend lifetimes developing, so we're trying to keep the expectations reasonable for how much can be learned in a 10-week quarter.

## So what does a proof look like?

In general, proofs can take MANY different forms. Some proofs are meant to be super mathematically rigorous (often times called a "two-column proof"). Some proofs are meant to be algorithmically verified (often times called a "machine-checkable proof"). This quarter, though, we will focus on a proof style called a "prose proof". 

Prose proofs are the most common to appear in research publications, and are writted with a focus on readability, at least for the target audience. The format of these prose proofs will often times look like English writing interspersed with math and/or code. The tone and grammar of these proofs are meant to convey "Trust me, I know what I'm doing". The content of them is meant to be a sequence of steps that are easy for their intended audience to see why each follows logically, without being obvious. 

There are not any universally-accepted descriptions of what makes a good prose proof, but most agree that a prose proof should:

- State each claim clearly, precisely, and concisely
- Justify any conclusions or observations made
- Break steps into sufficiently small pieces so that the target audience can easily progress through each one.

Notice that none of these are formal requirements, but instead are moreso style requirements. This is because prose proofs resemble a form of language arts as much as a mathematical skill. This nature of prose proofs is why we will generally provide some structure for you to apply when writing proofs.

Just like with any language art, perhaps the most important thing to keep in mind when writing your proofs is your target audience. We need to consistently ask ourselves "who do we anticipate will be reading this proof?", and then verify that this audience would be able to easily follow along with everything we state. **For the sake of this course, your target audience should be your classmates.** When writing your proofs, I'd recommend thinking to yourself "would I have been able to follow this proof last week?" If the answer is yes, then your proof is likely at the right level of granularity. If there are steps that the you of last week would not be able to follow, then that should likely be a step that needs to be broken up further.

# Examples of prose proofs

Prose proofs are a style of proof, and we can have many different strategies for how to approach our proofs. We'll look at several of the most common proof strategies here to see some examples of prose proofs.

## Direct Proof

With any proof, you generally start with some things that you assume to be true (typically called "assumptions"), and then you follow a chain of reasoning in order to show some other statement that must also be true (typically called "conclusions"). For a direct proof, we start with the assumptions, then provide a sequence of steps that each logically follow from the previous until eventually we reach the conclusions.

Let's look at an example of a prose proof for the claim "if $n$ is even then $n^2$ is also even".

> **Claim**: if $n$ is even then $n^2$ is also even
>
> **Strategy**: Direct Proof
>
> **Assumptions**: $n$ is even
>
> **Conclusion**: $n^2$ is even
> 
> **Proof**:
>
> We begin by applying the definition of even, which means $n$ can be expressed as $2k$ for some integer $k$. Since $n=2k$ this means that $n^2 = (2k)^2$. Rewriting the right-hand-side of the equation, we have $n^2 = 4k^2$. Which is equivalent to $n^2 = 2(2k^2)$. We can then apply the definition of even to conclude that $n^2$ is even, since it is twice the integer $2k^2$.

Implicitly, the audience for this proof is someone who kows the definition of an integer, of an even integer, and of exponentiation. Provided the reader already understands all of those things, it it easy for that person to follow along from one step to the next, and so they should end up convinced that the conclusion must be true whenever the assumption is true.

We will talk a lot more about how to write these proofs, but here are some recommendations:

- Start with definitions: if you're not sure where to start, apply the definitions to the assumptions and the conclusion.
- Write down all things you know are true: If you get stuck at any step, write down all things you know to be true as a result of the previous step. You can then just try one path in you reasoning and see how that works out
- Work backwards: If you're not sure what step to take from your assumptions, you could instead look at what you need to be true in order to arrive at the conclusion. 


## Indirect Proof (Proof by Contrapositive)

Logically, a statement of the for "if P is true the Q is true" is exactly equivalent to the statement "if Q is false then P is false". To help you see that this is the case, let's look at a few intuitive examples (disclaimer: a list of intuitive examples is not a proof!)

- "If it is raining then I am wearing my rain jacket" vs. "If I am not wearing my rain jacket then it is not raining"
-  "If no one cooked food then we order carryout" vs. "If we do not order carryout then someone cooked food"
- "If everyone fits on the bus then no one needs to drive" vs. "if someone needs to drive then not everyone fits on the bus"

The idea here is if the conclusion is guaranteed to be true whenever the assumption is true, then the only way for the conclusion to be false is if the assumption is false.

For each of the examples above, we call the second statement the *contrapositive* of the first. We can find the contrapositive of a conditional statement (e.g. "if P is true then Q is true") by performing a logical negation of both the assumption ("P is true" becomes "P is false") and the conclusion ("Q is true becomes Q is false"), then reversing which is which (so the new conclusion is "P is false" and the new assumption is "Q is false"). From there, we just do a direct proof of that contrapositive ("If Q is false then P is false").

Let's see that in action for the same claim as above!

> **Claim**: if $n$ is even then $n^2$ is also even
>
> **Strategy**: Indirect Proof
>
> **Contrapositive Claim**: If $n^2$ is not even, then $n$ isn't even either.
>
> **Assumptions**: $n^2$ is not even
>
> **Conclusion**: $n$ is not even

## Proof by Contradiction

## Proof by Induction
