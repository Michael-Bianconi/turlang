# turlang
Interpreter for Turlang

    program → <statelist> $$$
    statelist → <state> <statelist> | eps
    state → <statehead> <statebody>
    statehead → [accept] state <string>:<nl>
    statebody → on (<char>|any), write (<char>|none), tape (left|right), go to <string> <nl>
