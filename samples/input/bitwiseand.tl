// Title: BitwiseAnd
// Alphabet: {0,1,&,=}
// Language: {i&j=k | i,j,k in {0,1}+ and i & j equals k}

Pre1:
    0   -> none right Pre1
    1   -> none right Pre1
    &   -> none right Pre1
    =   -> none right Pre2

Pre2:
    0   -> none left I
    1   -> none left I
    
A:
    0   -> none right A
    1   -> none right A
    X   -> none left  B
    &   -> none left  B

B:
    0   -> X    right M
    1   -> X    right C
    end -> none right S

C:
    X   -> none right C
    &   -> none right D

D:
    0   -> none right D
    1   -> none right D
    X   -> none left  E
    =   -> none left  E

E:
    &   -> none right F
    0   -> X    right F
    1   -> X    right J

F:
    X   -> none right F
    =   -> none right G

G:
    0   -> none right G
    1   -> none right G
    X   -> none left  H
    end -> none left  H

H:
    0   -> X    left  I
    =   -> none left  I

I:
    0   -> none left  I
    1   -> none left  I
    &   -> none left  I
    =   -> none left  I
    X   -> none left  I
    end -> none right A
    
J:
    X   -> none right J
    =   -> none right K

K:
    0   -> none right K
    1   -> none right K
    X   -> none left  L
    end -> none left  L

L:
    1   -> X    left I

M:
    X   -> none right M
    &   -> none right N

N:
    0   -> none right N
    1   -> none right N
    X   -> none left  O
    =   -> none left  O

O:
    0   -> X    right P
    1   -> X    right P
    &   -> none right P

P:
    X   -> none right P
    =   -> none right Q
    
Q:
    0   -> none right Q
    1   -> none right Q
    X   -> none left  R
    end -> none left  R

R:
    0   -> X    left  I
    =   -> none left  I

S:
    X   -> none right S
    &   -> none right T

T:
    0   -> none right T
    1   -> none right T
    X   -> none left  U

U:
    0   -> X    right P
    1   -> X    right P
    &   -> none right V

V:
    X   -> none right V
    =   -> none right W

W:
    0   -> X    right W
    X   -> none right W
    end -> none right accept
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
