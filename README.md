# turlang
Minimalist interpreter for Turlang.

## Syntax
#### Comments
Comments start with ```//```. Inline comments are not supported.
#### State Declarations
State declarations are in the form ```<label>:```.
#### Transition Declarations
Transition declarations are in the form ```<read> -> <write> <tape> <next>

## Grammar
    program → (<comment> | <state>) <nl> <program> | $$$
    comment → // ...
    state → <head> <nl> <body>
    head → <string>:
    body → <read> -> <write> <tape> <next>
    read → <character> | any | space | newline | tab | baz | end
    write → <character> | none | space | newline | tab | baz | end
    tape → left | right
    next → <string>
## Keywords
* any
* end
