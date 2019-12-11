0:
    X -> NONE  RIGHT  0
    0 -> X  RIGHT  01
    1 -> NONE  RIGHT  reject
    END -> NONE  RIGHT  accept

comment
. This is a single line comment.
. This is a multiline comment.
. Hello  world.
endcomment
01:
    X -> NONE  RIGHT  01
    0 -> NONE  RIGHT  01
    1 -> X  RIGHT  010
    END -> NONE  RIGHT  reject
010:
    X -> NONE  RIGHT  010
    0 -> X  RIGHT  0101
    1 -> NONE  RIGHT  010
    END -> NONE RIGHT reject
0101:
    X -> NONE RIGHT 0101
    0 -> NONE RIGHT 0101
    1 -> X LEFT reverse
    END -> NONE RIGHT reject
reverse:
    X -> NONE  LEFT  reverse
    0 -> NONE  LEFT  reverse
    1 -> NONE  LEFT  reverse
    END -> NONE  RIGHT  0
    

comment
# Moves the tape head forward 3 characters
#
# accept: Successfully moved
# reject: Reached end of tape
endcomment


    
