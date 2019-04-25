function main()
{
    let tests = nextInt();
    for (let t=0; t<tests; t++) {
        let K_dogs = nextInt();
        let dogSizes = [];

        let N_walkers = nextInt();
        for (let i=0; i<K_dogs; i++)
            dogSizes.push(nextInt());
        dogSizes.sort((n1,n2) => n1-n2);

        let diff = [];
        for (let i=1; i<K_dogs; i++)
            diff.push( {i: i, d: dogSizes[i]-dogSizes[i-1]} );

        diff.sort((e1,e2) => e1.d-e2.d).reverse();
        let sum = 0;
        for (let i=N_walkers-1; i<diff.length; i++)
            sum += diff[i].d;

        console.log(sum);
    }
}

// default parsers for JS.
function nextInt() {
    return parseInt(nextString());
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
    while (input_cursor < input_stdin.length && isWhitespace(input_stdin[input_cursor]))
        input_cursor += 1;  // ignore the next whitespace character
}
