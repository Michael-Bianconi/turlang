import sys, re, functools, operator, argparse 

ignoreregex = re.compile("^(//.*|"")$", re.IGNORECASE)
labelregex = re.compile("^\\S+:$")
transitionregex = re.compile(
    "^(\\S|end|spc|nl|tab)\\s+->" +
    "\\s+(\\S|end|none|spc|nl|tab)" +
    "\\s+(left|right)" +
    "\\s+\\S+$", re.IGNORECASE)
states = {}
tape = []
head = 1
linecount = -1
debug = False
tapelength = None

def err(msg: str, showline=True):
    global linecount
    line = '' if linecount == -1 else '(line ' + str(linecount) + ')'
    if showline: print("Error " + line, end='')
    print(msg)
    sys.exit(1)

def create_tape(inputstring):
    global tape
    tape = ["end"] + list(inputstring) + ["end"]

def read():
    global tape, head
    if head >= 0 and head < len(tape): return tape[head]
    else: return "end"
    
def write(value):
    global tape, head, tapelength
    if len(tape) > tapelength:
        err('Tape length exceeds limit', showline=False)
    if head >= 0 and head < len(tape): tape[head] = value
    elif head >= 0:
        tape.append("end")
        write(value)
    elif head < 0:
        tape.insert(0, "end")
        head += 1
        write(value)

    
def create_transition(states, label, read, write, tape, next):

    readkeywords = ['nl','spc','tab']
    writekeywords = ['none','nl','spc','tab']
    nextkeywords = ['accept','reject']
    if read.lower() in readkeywords: read = read.lower()
    if write.lower() in writekeywords: write = write.lower()
    if next.lower() in nextkeywords: next = next.lower()
    tape = tape.lower()
    
    if read == "nl": read = '\n'
    elif read == "spc": read = ' '
    elif read == "tab": read = '\t'
    if write == "nl": write = '\n'
    elif write == "spc": write = ' '
    elif write == "tab": write = '\t'
    
    if label not in states:
        err('State body outside state definition')
    state = states[label]
    state[read] = {"write": write,"tape": tape,"next": next}


def parse(filename: str) -> tuple:

    global states, linecount
    active = None
    start = None
    try:
        with open(filename) as tlfile:
            for line in tlfile:
                line = line.strip()
                linecount += 1
                if (ignoreregex.match(line)): continue
                elif (labelregex.match(line)):
                    active = line[:-1]
                    if active.lower() in ['accept', 'reject']:
                        err('Cannot redefine halt state ' + active)
                    if active in states:
                        err('State redefinition: ' + active)
                    states[active] = {}
                    if start is None: start = active
                elif (transitionregex.match(line)):
                    line = line.split()
                    line.pop(1)  # remove arrow
                    create_transition(states, active, *line)
                else:
                    err('Syntax error')
                    sys.exit(1)
        return start
    except FileNotFoundError:
        err("Could not find file")
    except IOError:
        err("Could not open file")

def transition(statelabel, read):
    global states, head, debug
    if statelabel not in states:
        err('Jump to undefined state: ' + str(statelabel))
    state = states[statelabel]
    if not read in state: return None
    trans = state[read]
    if trans["write"] != "none": write(trans["write"])
    if trans["tape"] == "left": head -= 1
    elif trans["tape"] == "right": head += 1
    if debug:
        print('Moving to ' + trans["next"])
    return trans["next"]
        

def simulate(active):
    global states
    while True:
        current = read()
        active = transition(active, current)
        if active is None or active == "reject": return False
        elif active == "accept": return True

def main():
    global states, tape, debug, tapelength
    
    parser = argparse.ArgumentParser(description='Turlang Interpreter')
    parser.add_argument('source', type=str, help='Turlang Source')
    parser.add_argument('string', type=str, help='Input string')
    parser.add_argument('--tapelength', type=int, help='Maximum tape length')
    parser.add_argument('-d', action='store_true')
    args = parser.parse_args()
    
    debug = args.d
    filename = args.source
    start = parse(args.source)
    tapelength = args.tapelength
    create_tape(args.string)
    
    if start is None:
        err('Error: No start state')
    
    print(simulate(start))
    
if __name__ == '__main__':
    main()

    
    
    
    
    
