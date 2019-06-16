function main() {
    let Ts = nextInt();
    for (let t=0; t<Ts; t++) {
        let n = nextInt();
        let unpainted = unpaintedPrecent(getElps(n));
        console.log(unpainted+"%");
    }
}
function getElps(n) {
    let elps = [];
    for (let e=0; e<n; e++) {
        let _x1 = nextInt(), _y1 = nextInt(),
            _x2 = nextInt(), _y2 = nextInt(),
            _r  = nextInt();
        elps.push({
            p1: {x: _x1, y: _y1},
            p2: {x: _x2, y: _y2},
            r : _r
        });
    }
    return elps;
}
function unpaintedPrecent(elps) {
    return Math.round(
        ( 1-estimateCoverage(elps) )*100 );
}

let res = 300;  // total res^2 points
let start = -50, end=+50;
let step = (end-start)/res;

function estimateCoverage(elps) {
    let painted = 0;
    for (let _x=start; _x<=end; _x+=step)
        for (let _y=start; _y<=end; _y+=step) {
            let p = {x: _x, y: _y};
            for (let e of elps)
                if (dist(e.p1,p) + dist(e.p2,p) <= e.r) {
                    painted++;
                    break; // count if p inside ANY elps
                }
        }
    return (painted/(res*res));
}
function dist(p1, p2) {
    return Math.hypot(p1.x-p2.x, p1.y-p2.y);
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