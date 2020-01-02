// Title: Happier
// Alphabet: {🙂,🙁}
// Language: {w in {🙂,🙁}* | w contains more 🙂 than 🙁}

Start:
    🙁 -> ❌ right Find🙂
    🙂 -> ❌ right Find🙁
    ❌ -> none right Start
    
Find🙂:
    🙁 -> none right Find🙂
    🙂 -> ❌ left Reverse
    ❌ -> none right Find🙂

Find🙁:
    🙁 -> ❌ left Reverse
    🙂 -> none right Find🙁
    ❌ -> none right Find🙁
    end -> none right accept

Reverse:
    🙁 -> none left Reverse
    🙂 -> none left Reverse
    ❌ -> none left Reverse
    end -> none right Start
