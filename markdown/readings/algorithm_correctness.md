---
title: Algorithm Correctness
...

# Reasoning, Not Intuition

Last class we introduced the stable matching problem, and the Gale-Shapley algorithm for solving it. Perhaps you found the Gale-Shapley algorithm to be an intuitive and satisfactory solution to the problem. Or, perhaps you didn't, and the intuition for how or why it works escapes you. Either way, this is totally OK! One goal we have for this course is that we should not need to rely on intuition to be confident that our algorithms behave in the ways we intend. Instead of intuition, we will rely on logic and reason to provide careful arguments about the behavior of our algorithms.

We prefer to carefully construct arguments to support our algorithms because intuition can be inconsistent. For example, even if you perhaps found the Gale-Shapley algorithm intuitive, I can guarantee many of your classmates did not. This highlights that different people may find different ideas to be intuitive vs. non-intuitive. Additionally, our intuitions can sometimes lead us astray. There will almost certainly be times this quarter where an algorithm you intuitively understand to be correct turns out to have a serious issue! 

While intuition is valuable, and we should absolutely provide intuitive explanations (we will do this in lecture as much as we can), these aspects make it insufficient for being totally convinced that our algorithms perform in the ways we hope. Because we want to be able to convince ANYONE that our algorithms are correct, including ourselves as well as others, we want to rely on something that will be more compelling that intuition alone. As such, one skill we will be learning in this course is how to construct careful arguments to support our algorithms that we can then use to accompany our intuitive explanations.

For just about every algorithm we discuss this quarter, we will want to provide two arguments to support the algorithm's behavior:

1. **Correctness**: That our algorithm always produces the correct answer 
2. **Running Time**: That our algorithm's running time is within a certain asymptotic (big-O) bound.

We will occasionally also care about other behaviors of our algorithms, but these two are ubiquitous. For this reading, we focus on arguments for the the correctness of our algorithms.

# Defining Correctness

Let's begin this discussion of correctness by identifying what exactly we *mean* by "Correctness".

When justifying the correctness of our algorithms, our objective is to provide a careful and convincing argument that our algorithm produces the correct output for every valid input it may receive. This could look very different depending on the specific tasks we are trying to accomplish, but let's start by looking at some examples.

Let's say we want to design an algorithm that sums the values in a given list of integers. For that algorithm to be correct, it will need to return the correct sum regardless of the length of the given list, the number of values given, which values are given, the order the values are given, and so forth. In this case, though, since our intention is for the algorithm to sum lists of integers, if for some reason it might return an incorrect sum if there was a fractional value in the list, then that would actually be acceptable, since our only goal is summing lists of integers. Similarly, if it gave an incorrect answer when we gave it a *set* of integers (as opposed to a list of integers) that would also be acceptable, since our algorithms seeks to sum lists.

Overall, what we're hoping to convey with this example, is that each algorithm has expectations for its input (e.g. that it is a list of integers), and to be *correct* that algorithm must *always* produce the right answer for *every* input which meets those expectations. We do not care about the algorithm's behavior for inputs which do not meet those expectations.

To argue that algorithm is correct, we typically need to justify 3 things:

1. That our algorithm is error-free. In other words, no inputs could ever cause the algorithm to display an error. This property is called **soundness**.
1. That our algorithm eventually terminates (i.e. cannot enter an infinite loop). In other words, no inputs could ever cause the algorithm to run forever. This property is called **termination**.
1. That any value returned by the algorithm is a correct answer. In other words, no inputs could ever have an incorrect answer returned by the algorithm. This property is called **validity**.

Because the **validity** property is the one where we justify that the answers given are correct, you may have found it surprising that algorithm correctness needed anything besides the **validity** step. It turns out, though, that all three parts are necessary before we can be absolutely certain that we *always* get the correct answer (side note: while all three are necessary, some of them may be much more straightforward to see than others for some algorithms). The reason all three are necessary is because we used the word *always* in our requirements for correctness. If we only showed that all the values returned are correct answers, this still leaves open the possibility that the algorithm did not return any answer at all. An algorithm which fails to produce an answer when given a valid input is not a correct algorithm!

For this reason, to accompany the argument for validity, we also need to demonstrate that the algorithm returns *something* for any valid input. To do this, we show that it none of the situations in which our algorithm might fail to return an answer can occur. There are two ways that an algorithm may fail to produce an answer. One way is if it at some point enters an infinite loop, so our **termination** argument addresses that issue. The other way is if enters into a situation where it cannot perform one of its steps, at which point there will be an error. The **soundness** argument addresses this concern. 

At the end of defending all three of these properties for our algorithm, we can now be confident that the algorithm always returns something (since it always terminates and never has an error), and also that everything it returns is the right answer. We now have confidence that our algorithm is correct!

# Concept Check

To help you to verify your own understanding of the concepts in this reading, we ask that you complete a concept check. Concept checks are instantly graded and so your feedback is provided immediately upon submission. There is no limit to the number of times you may submit a concept check.

