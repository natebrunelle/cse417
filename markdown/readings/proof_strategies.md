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

## Indirect Proof (Proof by Contrapositive)

## Proof by Contradiction

## Proof by Induction
