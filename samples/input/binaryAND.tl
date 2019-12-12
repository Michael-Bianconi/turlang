//  Title: Bitwise AND
//  Created: December 12, 2019
//  Purpose: Performs AND operation on two binary numbers.
//  Input: <binary> <binary>
//  Output: true if successful, false otherwise.
//
//  Binary numbers must be the same length and separated
//  by at least one space.
A:
    0 -> x right B
    1 -> x right C
    space -> none right I
B:
    0 -> none right B
    1 -> none right B
    space -> none right D
C:
    0 -> none right C
    1 -> none right C
    space -> none right E
D:
    0 -> space right F
    1 -> space right F
    space -> none right D
E:
    0 -> space left G
    1 -> space left H
    space -> none right E
F:
    0 -> none left F
    1 -> none left F
    space -> none left F
    x -> 0 right A
G:
    0 -> none left G
    1 -> none left G
    space -> none left G
    x -> 0 right A
H:
    0 -> none left H
    1 -> none left H
    space -> none left H
    x -> 1 right A
I:
    space -> none right I
    end -> none right accept
