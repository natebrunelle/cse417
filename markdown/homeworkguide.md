---
title: Homework Guide
...

All tasks will be submitted and graded on [Gradescope]().   

# Homework Guide

## Schedule

Homework assignments will be due every week (with the exception of the first week and the week of the midterm exam), and will be made available on Wednesday evenings. They are typically due the following Wednesday at 11:59pm via Gradescope. Each problem is a separate submission so that we can implement the course late days policy, but there is no need to create separate PDFs for each problem on the assignment: You can upload the same PDF for each and just mark the appropriate pages for each submission. As a side effect, there is no need to wait until you are finished with the whole assignment to submit individual problems, so you should submit them well before the deadline if you can.

## Planning

It is very valuable to read and understand what each problem is asking soon after it has been assigned. Unlike other courses, even if you really understand the course material, if you leave things late you inevitably will find yourself stuck. One very big advantage to starting early: Once you understand what a question is asking, your brain is a very effective engine for background processing. You should take as much advantage as possible of your ability to be unconsciously working on the problem before you return to it.
As discussed on the syllabus, there are two classes of problems: Mechanical problems and long-form problems. Think about the long-form problems as you would a design for a programming task task you have never seen before. (You wouldn't start your work on that coding at the last minute and you shouldn't do that with these problems.) The mechanical problems will generally help with your understanding so they can be good to get out of the way first.

## Submission guideance

### Level of Detail

When writing pseudocode, your target audience is a competent programmer who has taken a course like CSE 332 (i.e., the other students in this course) but has only just read the problem that you are solving. Remember that you have probably thought about a problem for at least a few hours by the time you are writing up a solution. Your reader has not -- you have much more intuition than they do.

A good check is to ask "would me from last week understand this solution?" and "would me 6 months from now understand this solution?" If not, you are probably relying too much on the intuition you developed, and you should make that intuition explicit.

Our solution for a problem will always fit on one printed page (in LaTeX). It's ok if your solutions are a little longer (it's generally preferable to include too much detail than too little), but they shouldn't need to be much longer than a page.

### If you can't completely solve a problem

Usually, grades for problems go in approximately this order:
- A correct and efficient algorithm.
- An algorithm that is as efficient as the intended solution, and has the main idea of the problem, but mishandles some edge cases.
- An algorithm that is not quite as efficient as the intended solution, but incorporates some of the algorithm design principles that are relevant (e.g., is not just a brute force solution).
- An algorithm that is fundamentally incorrect or signficiantly less efficient than the intended solution or is just a brute force solution.

When designing rubrics, we generally intend it to be to your benefit to have significant, but incomplete, progress toward a correct solution, than to write an incorrect algorithm and intentionally write an incorrect proof.

It is extremely common for our problems to have more than one reasonable solution! If we tell you to use a particular technique, you must use that technique. But otherwise we accept any correct solution.

## Style

If we ask you to "describe" or "design" an algorithm (or use any similar phrase), then must do all of these four things.

1. Give us some intuition
    - Many of our problems have 1-2 core ideas in them. Telling us what you're intending to do (just in 1-2 sentences) makes it much easier for us to understand your code. You're not proving your code correct here, nor describing details. You're just giving 1-2 sentences of what you want to do.

1. Tell us what the computer should do
    - Unless otherwise noted, we want pseudocode.
    - You also should briefly explain how the pseudocode implements the intuitive ideas you have described above. This is not a line-by-line discussion, but just how the components of pseudocode actually implement the intuition.

1. Tell us why it is correct
    - We need a careful proof of correctness. This includes why the algorithm actually finishes.
    - Your target audience is someone who has come to lecture and section
    - You may need to define notation for things that do not have notation in the original problem.

1. Identify and justify its running time
    - Unless otherwise noted, you will give the running time in big-O terms. This means you should prove an upper-bound on its worst-case running time. While we only ask for a proof of the function you name as an upper bound, that function should also be a tight bound for full credit. You may need to introduce variable names if they are not given in the problem to express your running time.
    - A 2-3 sentence justification is usually sufficient (often by giving a quick justification for the running time of the various sections of code).

### Pseudocode

We have some advice and examples in [this pdf](/files/pseudocode.pdf). See also [the latex](/files/pseudocode.tex) file that generated the pdf.

### Formatting

You are not required to format your solutions using latex but we do insist that solutions be high legible and well-organized. Solutions that are not will lose points. We do provide a basic [latex template](/files/homework-template.tex) for homework which produces [this output](/files/homework-template.pdf). We highly recommend use of [Overleaf](https://www.overleaf.com/), which is available through UW, for your latex.

### Expectations for proofs

In general, proofs in 421 are not graded nearly as strictly as you were used to in 311. In 311, we needed to make sure we know that you know what you're doing. Now that you're in a 400-level course, we believe you know what the starting point of a proof should be, so you'll often be allowed to skip those pieces.

Here are some things we **really** cared about in 311 that we don't care about in 421

- When doing a direct proof of an implication, you don't need to call this out. properly!
- When doing a proof by induction, you do not need to explicitly define a predicate P()
- If the type of a variable (e.g. "integer" or "character") is clear from context, it does not need to be mentioned when introduced (though it still needs to be mentioned if it appears in the justification of a future step)
- Statements we made you prove in 311 can sometimes be taken as "obvious"
    - For example, the sum of two even numbers being even can just be asserted, it does not need a proof in this class.
    - If in doubt, ask us what we expect to be proven.

On the other hand, some things we do still care about

- Your proof has to be an English proof
- Non-direct proofs (e.g., proof by contradiction, contrapositive, or induction) need to be introduced as such at the start of the proof
- In an induction proof, you must say "by IH" (or an equivalent) when using your inductive hypothesis.
