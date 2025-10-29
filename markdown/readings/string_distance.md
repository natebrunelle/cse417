---
title: String Distance Metrics
...

In our next meeting we will discuss algorithms for measuring the similarity of strings. In this reading we'll focus on introducing the problems we will solve with those algorithms.

# Motivation

String distance metrics tell us how similar two strings are. For some data types, it is obvious how to define the distance between a pair of values. For example, the distance between two numeric values (integers, floats, doubles) would be the difference between them. We've also seen definitions of distance for more complicated objects. For example, in lectures and homeworks we've defined distance between two cartesian coordinates (e.g. $x$, $y$ pairs) to be the Euclidean distance between them ($\sqrt{(x_1-x_2)^2 + (y_1-y_2)^2}$). It turns out, though, that there are MANY ways of defining the distances between cartesian coordinates. To provide just one other example, there is Manhattan distance, which says the distance between $(x_1,y_1)$ and $(x_2,y_2)$ is $|x_1-x_2| + |y_1-y_2|$ (the name is meant to highlight that you're measuring the distance you would need to travel if you were limited to only moving along grid lines, like if you were travelling in Manhattan).

There are many applications in which it would be helpful to have metrics for determining the similarity of strings. Some examples include:

- Spell checking: if you typed something that was not a real word, it's most likely the word you tried to type was a similar one.
- Searching: if a user searched for "baker", then you may want to also include results for similar words such as "bake", "bakers", "bakery", "baking".
- Gene sequence alignment: To sequence a genome, geneticists typically first chop up DNA into many small pieces, sequence each individual piece, then try to reassemble the pieces by finding where on a "reference" genome that piece most closely matches. This approach works because if you already know the species of the DNA, a there is going to be a very high similarity between that species' reference DNA and some individual's DNA.

Each of these applications has slightly different needs for measuring string distance, but we're going to do is look at one definition that might at least be helpful for all of them.

# Defining Distance

The approach we're going to use to use to define distance is going to be by counting the number of modifications you must make to transform one string to another. Each of these modifications we will call an "edit". We will specifically look at 3 types of edit:

- **Mismatch**: One character is swapped out in favor of another. For example, one mismatch would account for the difference between "cat" and "car". This is elsewhere sometimes called a substitution
- **Gap**: One character is missing from one string to another. For example, one gap would account for the difference between "cat" and "chat". This is elsewhere sometimes called either an insertion (to go from "cat" to "chat") or deletion (to go from "chat" to "cat").

So now we have a starting point for our distance metric, where the distance between strings $s_1$ and $s_2$ will be defined in terms of the number of mismatches and gaps between them. It turns out, though, that counting the mismatches and the gaps is not straightforward.

Consider the strings "ocurrance" and "occurrence". Using mismatches and gaps, I could explain the distance between the strings in many different ways:

1. 6 mismatches and 1 gap: we could transform "ocurrance" into "occurrence" by: changing the 'u' to a 'c', changing the first 'r' to a 'u', changing the 'a' to an 'r', changing the 'n' to an 'e', changing the last 'c' to an 'n', changing the 'e' to a 'c', and then adding a final e. To see this in action, it would be "ocurrance" -> "occrrance" -> "occurance" -> "occurrnce" -> "occurrece" -> "occurrene" -> "occurrenc" -> "occurence".
1. 1 mismatch and 1 gap: we could transform "ocurrance" into "occurrence" by: inserting a second 'c' into "ocurrance", then changing an "a" to an "e". This would look like "ocurrance" -> "occurrance" -> "occurrence".
1. 3 gaps: we could transform "ocurrance" into "occurrence" by: inserting a second 'c' into "ocurrance", deleting an "e", inserting an ". This would look like "ocurrance" -> "occurrance" -> "occurrnce" -> "occurrence"

Notice that we can get different numbers of edits for different strategies. The presents two challenges for us. First, it shows that it's hard to look at a two strings and tell what the "simplest" or "best" way is to transform one into another. Second, it's not entirely clear whether, for example, we should define the distance between "ocurrance" and "occurrence" to be 3 gaps or 1 mismatch plus 1 gap (clearly 6 mismatches and 1 gap is not going to be the best choice).

To address the first challenge, we will write an algorithm which will find the "best" way to transform one into the other so that we can correctly calculate the distance. This algorithm, though, depends on how we address the second challenge. The issue here is that whether gaps should be considered as better or worse than mismatches (for example, which is closest to "cat"? Is it "chat" or "hat"?) will largely be application specific. For example, in the gene alignment case, it is known that gaps are more likely to occur in nature than substitutions, so therefore we may actually favor the 3 gap distance for that application. For spell checking, though, perhaps it is more likely that a user hit the wrong key compared to missing a key or hitting an extra one.

To bring all of this together, we simply write our algorithm so that the types of edits are weighted, and then we can find the least-weight transformation.

# String Distance Problem

This now gives us all the background we need to define our string distance problem, which we will design an algorithm to solve in class.

The input to this algorithm will be two strings $s_1$ and $s_2$. In addition, we will have a value $\delta$ that represents a "gap penalty" and a collection of values $\alpha_{a,b}$ that represents the "mismatch penalty" of converting the letter $a$ into $b$. We then define the distance between $s_1$ and $s_2$ as the minimum cost collection of gap and mismatch transformations that coverts $s_1$ into $s_2$.

Brief aside: we allow different penalties per pair of letters because some mismatches may be more likely than others, for example, a typo is more likely to occur by hitting a key next to the correct one versus one far away on the keyboard.

For example, if $\alpha_{a,e}=3$ and $\delta = 1.2$ then the 3 gap transformation of "ocurrance" into "occurrence" has cost $3\delta = 3.6$ and the 1 mismatch 1 gap transformation has cost $\alpha_{a,e}+\delta = 4.2$, and so we would say that the distance between those strings is $3.6$.

# If you're curious: Simplifying assumptions that we made

The algorithm that we will design in class will be useful for the applications we mentioned, and this algorithm is used today for various applications. However, the specific ones that we mentioned all use more sophisticated definitions of distance that what our algorithm is designed for. For the most part, though, we could adapt this algorithm in various ways to handle those complexities.

Here is just a brief overview of some of the things that are done in various applications:

- For spell checking or autocorrect, in addition to errors like accidentally skipping a key or hitting the wrong key, humans are likely to make "sounds like" errors. For example, it's way more likely for someone to mix up "threw" and "through" than "threw" and "three", even though the latter pair would be closer according to our definition above.
- For genome alignment, the types of edits we present above are called point mutations because they look at just a single base. There are other types of mutations, though, that we might want to think of as one "error" based on how genes are copied. For example, there are inversion mutations where an entire portion of the genome is reversed, translocation mutations where a portion is moved from one place to another, and duplication mutations where a portion is repeated an extra time.
- For searching we also likely want to look at synonyms of words that were searched for. For example, in addition to checking "bakery" for "baker" we may want to also check "pastry shop".