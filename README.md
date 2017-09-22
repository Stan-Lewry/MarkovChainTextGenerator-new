# Markov Chain Text Generator

This is an application that utilizes a Markov Chain to generate a psuedo-random string of words based on a given corpus.
A Markov Chain is an example of a Markov Process, which is defined as a process that satisfies the Markov Property.
The Markov Property refers to the memoryless property of a process, meaning that each transition is only dependant on the current state and does not require information from previous states.

We can use this principle of a Markov Chain to generate psuedo random strings of text. The approach used here can be described in the following steps:
        1) Select two consecutive words from a corpus of text, insert these two words as the first two words of our output string.
        2) Search the corpus and find every word that appears immidietly after the last two words of our output string.
        3) Select one of these words at random and append to our output string.
        4) Repeat from step 2 until we have a string of the desired length.

Strings generated in this manner will by no means make any sense, however selecting the next word on the basis of the previous two words does give the illusion of coherence, certainly more so than a purely random approach and does so without the need for any NLP techniques.

## This Project

The objective of this project is to implement the described technique in a small Java application with a GUI. That will allow the user to generate strings of any desired length using the Open American National Corpus.