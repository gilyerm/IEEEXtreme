
function main() {

    let n = nextInt();
    let heights = [], crewByHeight = [];
    for (let i=0; i<n; i++) {
        let c = nextString();
        let h = nextInt();
        if (!crewByHeight[h]) crewByHeight[h] = [];
        crewByHeight[h].push(c);
        if (!heights.includes(h)) heights.push(h);
    }
    let fstIdx = 1, lstIdx;
    for (let h of heights.sort()) {
        let hBracket = crewByHeight[h];
        if (!hBracket) continue;
        lstIdx = fstIdx + hBracket.length-1;
        console.log(hBracket.sort().join(" "), fstIdx, lstIdx);
        fstIdx = lstIdx + 1;
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
