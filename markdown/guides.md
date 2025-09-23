---
title: Guides
...

# Guide to Office Hours

Office hours are often a key part towards success in a difficult course like this one. All instructors and TAs will hold weekly office hours. Please come if you have questions about anything. We have some tips so that we all use office hours efficiently and effectively.

1. **Try problems for at least 15 minutes before asking for help.** Learning requires struggling. We are here to prevent you from becoming overly demotivated from struggling, but we can't help you if you haven't put in any effort yet.
1. **Explain your progress.** Once we know where you are, we can best tailor help to your situation. We won't provide general hints.
1. **One person, one question.** When office hours are crowded, limit yourself to one question. Add yourself back to the queue to ask another question.
1. **Don't wait until the last day.** Office hours get crowded when assignments are due. Save yourself time with a shorter queue by coming early.
1. **Don't ask staff to read.** If you've written something relevant to your question, you should explain it out loud on a whiteboard, maybe with an example. It will be much faster for everyone. (Exception: questions about the act of writing.)
1. **Prefer Ed for clarifications and most lecture-related questions.** Your question might already be answered, and if not, it might be good for everyone to hear the answer. (Of course, feel free to ask these questions in OH too if you want support from a specific staff or prefer OH for any reason.)

# Guide to Homework

## Planning

A typical week's homework will include:

- 3 concept checks with possible reading
- 2 standard homework problems
- 1 homework extension

We hope that the concept checks take you no more than 10 minutes each (provided that you paid attention in lecture), and that each reading (when required) takes no more than 15 minutes. If you find yourself taking more than this amount of time, tell us using the [Anonymous Feedback Tool](feedback.cs.washington.edu) and we'll start making them shorter.

Do not wait until the due date (or even worse, the resubmission deadline) to start the homework problems! They are meant to be difficult, even if you really understand the course material, because they require creativity to solve. Be sure to start them early enough so that if you find yourself stuck for a while, you still have time to come to office hours and get help.

## How to Write Mathematics

"Good" mathematical writing depends on your intended audience. In this course, your audience is a student at another university who is taking a class similar to CSE 417, unless otherwise stated. Remember&mdash;the reader has not thought about the problem as much as you have, so take care to avoid skipping steps, but assume knowledge of basic definitions and theorems.

We will typically give a range of expected word count for each problem to help you adjust your level of detail, but we will never actually count the words in your solution. Our official solutions will always fit in the lower end of the expected word count. (Word counts don't include math: we use [this website](https://app.uio.no/ifi/texcount/online.php) to count.)

## How to Write Pseudocode

Pseudocode is a way to communicate code to humans. It uses programming blocks like `while` and `if`, but is meant to communicate ideas to another human, not a machine. 

Just like mathematical writing, good pseudocode depends on your audience. Again, imagine your audience as a student at a similar level in another university, unless otherwise stated. Thus, avoid Java-specific syntax, and use context to determine the appropriate level of detail. For most algorithm design problems, except the at the beginning of the course when we are training you on the basics, you should feel free to use phrases like "swap the two array entries," "let `i` be the first index in array `arr` such that __," etc. to shorten your pseudocode, since these are easy things that we will assume you know how to implement correctly. 

## Five Steps of an Algorithmic Solution

In general, if we ask you to "describe" or "design" an algorithm (or use any similar phrase), then you should do all five of these things, unless otherwise noted.

1. **Summarize the core idea.** Use 1&ndash;2 sentences to explain to the grader as a human what your algorithm is doing at a high level.

1. **Write pseudocode for your algorithm.** Restate the inputs and desired outputs of the problem, then write the steps of pseudocode to transform those inputs to the desired outputs. Follow the advice above.

1. **Show a representative example(s).** Pick a generic example and walk though your algorithm on this example. If there are multiple major cases, show an example for each case.

1. **Prove your algorithm correct.** This means for all inputs, your algorithm's output matches the desired output. If you have unbounded loops, also justify why your algorithm terminates, and if any operations can raise errors (like popping an empty array, or generally considering something that might not exist), also justify why those errors do not occur. 

1. **Analyze the running time.** Give the worst-case running time in big-O terms, as tightly as possible. This is typically a short part, but if your pseudocode left subroutines underdefined, be sure sketch how to make them explicit here so that you can analyze the running time.


## LaTeX

LaTeX is like a programming language for writing mathematics. It produces pretty documents and makes writing complicated symbols, fractions, equations, etc. much easier compared to Word or Google Docs.

You are *not* required to use LaTeX, but we recommend it! Each standard homework will come with a LaTeX template. The easiest way to get started is to upload the template to [Overleaf](https://www.overleaf.com/). We'd be happy to help you with syntax and formatting at office hours, too. (Or, we hate to admit it, but LaTeX formatting is not a bad use of ChatGPT...)

## Filming

In this class, for all standard homework assignments and theoretical extensions, you have the option of recording a short video to explain your solution, instead of writing the solution on paper. The intention is that if you are taking this course to prepare for technical interviews, you may find that speaking your solution is more what you want to practice over writing solutions. It is optional and will be scored the same as if you wrote down the content of the video.

The video should include yourself talking and drawing/writing key points, and no one else. You may choose any of the following formats to film:
* Film a video of yourself drawing at a physical whiteboard.
* Film a video of your hand writing on paper. 
* Screen record on an iPad with an Apple Pencil, or Android/Windows equivalents.
* Other ideas that acheive the same intent.

You **should not** prepare slides, write a script, or edit/clip/cut the video, but feel free to have some things (such as the problem statement, your pseudocode for a solution, etc.) already written down and annotate on top of them during your video. Feel free to also have some small amount of notes that you reference (like talking points), but again, do not read off a script. We will provide an example of what a video should look like.

You *may* ask someone to hold the camera. (You can also use a tripod, use a stand, macgyver a stand, etc.) We prefer that you find someone not enrolled in this class to hold the camera. Contact us if you need to request an exception. 