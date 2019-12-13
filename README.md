# Turlang
Minimalist interpreter for Turlang. Turlang is a declarative, Turing-complete programming language meant to match 1-to-1 with state diagrams. It contains only two types of statements (as well as comments). Turlang programs receive a single string as input and output ```True``` or ```False```.

## Syntax
<b>Comments</b> start with ```//```. Inline comments are not supported.
<b>State declarations</b> are in the form ```<label>:```.
<b>Transition declarations</b> are in the form <code>\<read> -> \<write> \<tape> \<next>

## Grammar
    program → (<comment> | <state>) <nl> <program> | EOF
    comment → // ...
    state → <head> <nl> <body>
    head → <string>:
    body → <read> -> <write> <tape> <next>
    read → <character> | any | space | newline | tab | baz | end
    write → <character> | none | space | newline | tab | baz | end
    tape → left | right
    next → <string> | accept | reject
## Keywords
(All keywords are case-insensitive)
* <b>any</b> Matches any value.
* <b>end</b> Matches the beginning and end of the tape.
* <b>none</b> Writes nothing to the tape (leaves it unchanged).
* <b>baz</b> A special value guaranteed to not be in any input string.
* <b>space, tab, newline</b> Matches their respective whitespaces.
* <b>left, right</b> Direction to move the tape head.
* <b>accept, reject</b> Special, program-ending states.
## Tape

The tape is a functionally infinite (but limited by available memory) list of values. Initially, it contains the input string, with ```end``` padded on either side. The tape's head starts at the first character in the input string. If the input string is empty, it starts at the rightmost ```end```.
Example: ```abc``` → ```[end, a, b, c, end]``` The tape head moves left or right depending on the transition. Non-ASCII characters (i.e. emojis) <i>are</i> supported.

Moving beyond either edge of the tape is allowed, and will not increase the size of the tape in memory. However, if the tape has anything written to it (except for ```end```), the tape will expand to accommodate the change. The maximum length of the tape is platform dependent.

## Usage
The interpreter for Turlang is written in Python3. To run the interpreter, use the following command:

    python3 turlang.py <file> <input>
    
## Tutorial
Turlang programs are nothing more than a list of states and the transitions between them. They receive a string as input and output a Boolean ```True``` or ```False```. The strings that elicit ```True``` are considered to be "in" the language of the program. The language of the program is the set of all accepted strings. Languages might be described as:

    L(Μ) = {ω ∈ {0,1}* | ω contains more 1's than 0's}
 
 The language of program (or machine) Μ is the set of all strings that contain only 0's and 1's <i>and</i> have more 1's than 0's. Turlang programs *decide* whether or not the input string is within the language (or loop infinitely).
### States
In Turlang, states have two components: the head and the body. The head is a label that identifies the state.
    
    // This creates a new state called MyState
    MyState:
States can contain any number of characters, but may not contain white-space. Notice the colon at the end. This specifies that body is about to follow.

State bodies are lists of transitions. Each transition has four components:

    <read> -> <write> <tape> <next>
```<read>``` attempts to match symbol at the tape head. If successful, then this is the transition that executes.
```<write>``` overwrites the previous symbol on the tape.
```<tape>``` May be ```left``` or ```right```, and determines which direction to move the tape head.
```<next>``` Determines the state to move to. If ```accept``` or ```reject```, the program terminates with the appropriate output.

Here's the complete Turlang program that decides the language described above:

    // Title: More 1's
    // Created: December 12, 2019
    // Alphabet: {0,1}
    // Language: {ω ∈ {0,1}* | ω contains more 1's than 0's}
    
    start:
        0 -> end right find1
        1 -> end right find0
        baz -> none right start
        end -> none right reject

	find1:
	    0 -> none right find1
	    1 -> baz left back
	    baz -> none right find1
	    end -> none right reject
 
	find0:
	    0 -> baz left back
	    1 -> none right find0
	    baz -> none right find0
	    end -> none right accept

	back:
	    0 -> none left back
	    1 -> none left back
	    baz -> none left back
	    end -> none right start


This program accepts any string the 
