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

Let's see that in action in this example below (which is not quite the same as the example above)!

> **Claim**: if $n^2$ is even then $n$ is even
>
> **Strategy**: Indirect Proof
>
> **Contrapositive Claim**: If $n$ is not even, then $n^2$ isn't even either.
>
> **Assumptions**: $n$ is not even
>
> **Conclusion**: $n^2$ is not even
>
> **Proof**:
> 
> We begin by assuming $n$ is not even, which means that $n$ is odd. Applying the definition of odd, we can write $n=2k+1$ for some integer $k$. By squaring both sides, we can conclude $n^2 = (2k+1)^2$. Applying algebra allows us to arrive at $n^2 = 4k^2+4k+1$. And so $n^2 = 2(2k^2+2k)+1$. Applying the definition of odd, we can conclude $n^2$ is odd. 

Because an indirect proof is just a direct proof applied to the contrapositive, all of the tips given for direct proofs apply here as well. There are some additional tips I can offer, though:

- If you feel like you're stuck on a direct proof, try attempting an indirect proof. Often times one or the other will be easier (to see an example you could attempt to do a direct proof of the claim in the preceding example)
- Make sure to explicitly write down your contrapositive, and make sure you got that correct before doing anything else!

## Proof by Contradiction

Proof by contradiction is perhaps the most powerful proof tool we have, but it is also perhaps the most difficult to follow. Intuitively, a proof by contradiction works by essentially stating "the claim must be true because it doesn't make sense for it to be false". To arrive at this conclusion, we begin by saying "let's suppose that the assumptions are true but the conclusion is wrong", then we provide a sequence of steps which result in a statement that is very obviously incorrect. Another way to think of this is by saying "if the claim is not correct, then here are a bunch of other things that must be correct, one of which is obviously nonsense". The name "proof by contradiction" comes from the mathematical definition of "contradiction" as a statement that cannot be true. The obviously incorrect statement plays the role of the contradiction. 


For this strategy, let's start with an example, and then dissect it from there.

> **Claim**: If $n^2$ is even then $n$ is even
>
> **Strategy**: Contradiction
>
> **Proof**:
> We procede by contradiction. Assume that $n^2$ is even but $n$ is odd. Because $n^2$ is even we can express it as $n^2=2x$ for some integer $x$. Because $n$ is odd we can express it as $n=2y+1$ for some integer $y$. Since $n=2y+1$, we also have $n^2=(2y+1)^2. This means we should have $2x=(2y+1)^2$. Applying algebra, we then have $2x=4y^2+4y+1$, thus $2x-4y^2-4y=1$, and finally $2(x-2y^2-4y)=1$. This last statement is clearly flase because for any integers $x$ and $y$ the left-hand-side of the equation is an even integer whereas $1$ is odd. This is a contradiction, and so our original claim must be true.  

In my opinion, the primary difficulty of a proof by contradiction comes from the fact that, thoughout, you're working with statements that you believe to be false. The objective here is to start with the statement that is not obviously false (the combination of the assumption and the opposite of the conclusion) and then apply transformations until you arrive at a statement that is obviously false to your target audience. 

Here are some tips for writing proofs by contradiction:

- Due to the greater challenge in following this technique over the previous two, try this one only if neither direct no indirect proof are panning out for you
- Typically you won't know in advance what the contradiction (obviously false statement) will be, instead I recommend you just keep following various chains of reasoning until you see a statement that you recognize to be false.
- Start by writing out the opposite of the conclusion, then write out all things that you know to to be true from the combination of the assumption and the opposite of the conclusion.
- When written, a proof by contradiction will appear as if the author knew exactly what the contradiction was going to be all along, but that was almost certainly not the case!

