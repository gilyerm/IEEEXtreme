let graph={}
function main() {
    let n=nextInt(),m=nextInt();
    let minId=Number.POSITIVE_INFINITY,
        maxId=Number.NEGATIVE_INFINITY;
    for(let i=0;i<n;i++){
        let id=nextInt();
        graph[id] = [];
        minId = Math.min(minId, id);
        maxId = Math.max(maxId, id);
    }
    for(let i=0;i<m;i++){
        let x = nextFloat();
        let y = nextFloat();
        let d = nextFloat();
        //if (!graph[x]) graph[x]=[];
        graph[x].push({nbr:y, cost:d});
        //if (!graph[y]) graph[y]=[];
        graph[y].push({nbr:x, cost:d});
    }
    let pars=djikstraAlgorithm(minId,maxId);
    let sum=0, tar=maxId;
    do {
        sum+=getKilos(tar);
        tar= pars[tar];
    } while(tar!=null);
    console.log(sum);
}

const primes = [
    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
    71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
    151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199];
const len=primes.length;

function getKilos(num){
    let mul=1;
    let i;
    for(i=0;i<len;i++) {
        let tmp=mul;
        tmp *= primes[i];
        if(tmp<=num)  mul=tmp;
        else  break;
    }
    return i;
}

function djikstraAlgorithm(fromP, toPts) {
    let distances = {};
    let prev = {};  // reference to previous nodes
    let pq = new Heap( (a,b) => b.d - a.d );

    let nodes=Object.keys(graph);
    for (let rw=0; rw<nodes.length; rw++)
    {
        let cell = nodes[rw];
        distances[cell] = Infinity;
        prev[cell] = null;
    }
    distances[fromP] = 0;
    pq.push({p: fromP, d: 0});

    while (!pq.isEmpty()) {
        let cur = pq.pop();
        let curId = cur.p;
        for (let nbr of graph[cur.p]) { // FE nbr
            let alt = distances[curId] + nbr.cost;
            let nbrId = nbr.nbr;
            if (alt < distances[nbrId]) {
                distances[nbrId] = alt;
                prev[nbrId] = curId;
                pq.push({ p: nbrId, d: alt });
            }
        }
        if (cur.p === toPts) break;
    }

    return prev;
}
class Heap {
    constructor(sort) {
        this._array = [];
        if (!(sort instanceof Function))
            sort = (a, b) => b - a;
        this._sort = sort;
        Object.defineProperty(this, 'length', {
            enumerable: true,
            get: function() { return this._array.length },
        });
    }

    push(node) {
        node = node || {};
        this._array.push(node);
        this._bubble();
    }
    pop() {
        if (this.isEmpty()) return null;
        let top = this.peek();
        let last = this._array.pop();
        if (this.length > 0) {
            this._array[0] = last;
            this._sink();
        }
        return top;
    }
    peek() {
        return this._array[0];
    }
    isEmpty() {
        return this.length === 0;
    }

    _compare(i, j) {
        return this._sort(this._array[i], this._array[j]) > 0;
    }
    _bubble() {
        let i = this.length - 1;
        let j = this._parent(i);
        while (j !== null && this._compare(i, j)) {
            this._swap(i, j);
            i = j;
            j = this._parent(i);
        }
    }
    _sink() {
        let i = 0;
        let lc = this._left(i);
        let rc = this._right(i);
        let next;

        while (lc !== null) {
            next = lc;
            if (rc !== null && this._compare(rc, lc))
                next = rc;
            if (this._compare(next, i)) {
                this._swap(i, next);
                i = next;
                lc = this._left(i);
                rc = this._right(i);
            }
            else return;
        }
    }
    print() {
        var s = '';
        var nodes = 1;
        var values = 0;
        for (var i = 0; i < this.length; i++) {
            s += ' ' + this._array[i].toString();
            values++;
            if (values === nodes) {
                nodes = nodes << 1;
                values = 0;
                s += '\n';
            }
        }
        console.log('\n' + s + '\n');
    };
    _parent(i) {
        let pi = (i - 1)/2 >> 0;
        return pi >= 0 ? pi : null;
    }
    _left(i) {
        let li = i*2 + 1;
        return li < this.length ? li : null;
    }
    _right(i) {
        let ri = i*2 + 2;
        return ri < this.length ? ri : null;
    }
    _swap(i, j) {
        var a = this._array;
        var v = a[i];
        a[i] = a[j];
        a[j] = v;
    }
}

// default parsers for JS.
function nextInt() {
    return parseInt(nextString());
}
function nextFloat() {
    return parseFloat(nextString());
}
function nextString() {
    var next_string = "";
    clearWhitespaces();
    while (input_cursor < input_stdin.length && !isWhitespace(input_stdin[input_cursor])) {
        next_string += input_stdin[input_cursor];
        input_cursor += 1;
    }
    return next_string;
}
function nextChar() {
    clearWhitespaces();
    if (input_cursor < input_stdin.length) {
        return input_stdin[input_cursor++];
    } else {
        return '\0';
    }
}
process.stdin.resume();
process.stdin.setEncoding('ascii');

var input_stdin = "";
var input_cursor = 0;
process.stdin.on('data', function (data) { input_stdin += data; });
process.stdin.on('end', function () { main(); });
function isWhitespace(character) {
    return ' \t\n\r\v'.indexOf(character) > -1;
}
function clearWhitespaces() {
    while (input_cursor < input_stdin.length && isWhitespace(input_stdin[input_cursor])) {
        // ignore the next whitespace character
        input_cursor += 1;
    }
}
