// Title: MM/DD/YYYY
// Alphabet: {0-9,/}
// Language: {w | w is a valid date in the format MM/DD/YYYY
//                (Leap years not accounted for)             }



Month-:
    0   -> none right Month-0
    1   -> none right Month-1
    
Month-0:
    1   -> none right MonthToDay31
    2   -> none right MonthToDay28
    3   -> none right MonthToDay31
    4   -> none right MonthToDay30
    5   -> none right MonthToDay31
    6   -> none right MonthToDay30
    7   -> none right MonthToDay31
    8   -> none right MonthToDay31
    9   -> none right MonthToDay30

Month-1:
    0   -> none right MonthToDay31
    1   -> none right MonthToDay30
    2   -> none right MonthToDay31
    
MonthToDay28:
    /   -> none right Day28-
    
MonthToDay30:
    /   -> none right Day30-
    
MonthToDay31:
    /   -> none right Day31-
    
Day28-:
    0   -> none right Day28-0
    1   -> none right Day28-1
    2   -> none right Day28-2

Day28-0:
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day28-1:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day28-2:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    
Day30-:
    0   -> none right Day30-0
    1   -> none right Day30-1
    2   -> none right Day30-2
    3   -> none right Day30-3
    
Day30-0:
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day30-1:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day30-2:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear

Day30-3:
    0   -> none right DayToYear

Day31-:
    0   -> none right Day31-0
    1   -> none right Day31-1
    2   -> none right Day31-2
    3   -> none right Day31-3
    
Day31-0:
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day31-1:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear
    
Day31-2:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear
    3   -> none right DayToYear
    4   -> none right DayToYear
    5   -> none right DayToYear
    6   -> none right DayToYear
    7   -> none right DayToYear
    8   -> none right DayToYear
    9   -> none right DayToYear

Day31-3:
    0   -> none right DayToYear
    1   -> none right DayToYear
    2   -> none right DayToYear

DayToYear:
    /   -> none right Year-

Year-:
    0   -> none right Year--
    1   -> none right Year--
    2   -> none right Year--
    3   -> none right Year--
    4   -> none right Year--
    5   -> none right Year--
    6   -> none right Year--
    7   -> none right Year--
    8   -> none right Year--
    9   -> none right Year--

Year--:
    0   -> none right Year---
    1   -> none right Year---
    2   -> none right Year---
    3   -> none right Year---
    4   -> none right Year---
    5   -> none right Year---
    6   -> none right Year---
    7   -> none right Year---
    8   -> none right Year---
    9   -> none right Year---
    
Year---:
    0   -> none right Year----
    1   -> none right Year----
    2   -> none right Year----
    3   -> none right Year----
    4   -> none right Year----
    5   -> none right Year----
    6   -> none right Year----
    7   -> none right Year----
    8   -> none right Year----
    9   -> none right Year----
    
Year----:
    0   -> none right accept
    1   -> none right accept
    2   -> none right accept
    3   -> none right accept
    4   -> none right accept
    5   -> none right accept
    6   -> none right accept
    7   -> none right accept
    8   -> none right accept
    9   -> none right accept    
    
