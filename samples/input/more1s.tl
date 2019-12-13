// Title: More 1's
// Created: December 12, 2019
// Alphabet: {0,1}
// Accepts: All strings with more 1's than 0's.
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

