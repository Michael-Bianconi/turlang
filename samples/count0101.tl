0:
    X -> NONE, RIGHT, 0
    0 -> X, RIGHT, 01
    1 -> NONE, RIGHT, reject
    $$ -> NONE, RIGHT, accept
01:
    X -> NONE, RIGHT, 01
    0 -> NONE, RIGHT, 01
    1 -> X, RIGHT, 010
    $$ -> NONE, RIGHT, reject
010:
    X -> NONE, RIGHT, 010
    0 -> X, RIGHT, 0101
    1 -> NONE, RIGHT, 010
    $$ -> NONE, RIGHT, reject
0101:
    X -> NONE, RIGHT, 0101
    0 -> NONE, RIGHT, 0101
    1 -> X, LEFT, reverse
    END -> NONE, RIGHT, reject
reverse:
    X -> NONE, LEFT, reverse
    0 -> NONE, LEFT, reverse
    1 -> NONE, LEFT, reverse
    END -> NONE, RIGHT, 0

#
# Moves the tape head forward 3 characters
#
# accept: Successfully moved
# reject: Reached end of tape
#
module jump3:
    j0:
        any -> none, right, j1
        end -> none, left, reject
    j1:
        any -> none, right, j2
        end -> none, left, reject
    j2:
        any -> none, right, accept
        end -> none, left, reject
endmodule
    









    
