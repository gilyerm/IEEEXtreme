
let bob;
function main() {
    // write your code here.
    // call functions `nextString`, `nextFloat` and `nextInt`
    // to read the next token of input (ignoring whitespace)
    // Alternatively, you can create your own input parser functions
    // use console.log() to write to stdout

    bob = new Array(9);
    for (let b=0; b<9; b++) {
        let r = nextInt()-1;
        let c = nextInt()-1;
        let move = r*3+c;
        console.log(r,c);
        bob[b] = move;
    }
    console.log(bob);

    let shortestGame = tryAllGames(new Array(10), 5);
    console.log("short",shortestGame);
    let myMoves = getOddEvenArr(shortestGame, 1);
    console.log("my",myMoves);
    for (move of myMoves) {
        let r,c;
        r = floor(move/3);
        c = move % 3;
        console.log(r+","+c)
    }
}

let shortestChoiceSeq = new Array(10)
function tryAllGames(cur_board, loops) {
    if (loops <= 0)
        return;
    console.log(cur_board, loops);

    let bobChoice;
    do {
        bobChoice = bob.unshift();
    } while (!legalMove(cur_board, bobChoice));
    board = cur_board.splice();
    board.push(bobChoice);

    for (let p=0; p<9; p++) {
        board = cur_board.splice();
        while (!legalMove(board,p)) p++;
        let myChoice = p;
        board.push(myChoice);

        if (checkWinner(board)) {   // bob wins before i play
            let winner = board.splice();    // copy cur board
            if (winner.length < shortestChoiceSeq.length)
                shortestChoiceSeq = winner;
            console.log("bob win!", board);
            break;
        }

        board = tryAllGames(board, loops-1);

        if (checkWinner(board)) {   // i win after my play
            // winner = undefined;  // mark as bad game
            break;
        }
    }

    return shortestChoiceSeq;
}

function legalMove(board, move) {
    return board && !board.includes(move);
}

/// 0,1,2
/// 3,4,5
/// 6,7,8

winningStates = [
    [0,1,2], // top row
    [3,4,5], // mid row
    [6,7,8], // bot row
    [0,3,6], // left col
    [1,4,7], // mid col
    [2,5,8], // right col
    [0,4,8], // main diag
    [2,4,6], // sec diag
];
function checkWinner(board) {
    let bobsMoves, myMoves;
    if (board === undefined)
        return false;
    bobsMoves = getOddEvenArr(board,0);
    myMoves = getOddEvenArr(board,1);

    let bobWins = false, meWins = false;
    for (winState of winningStates) {
        bobWins = bobWins || winState.every(elm => bobsMoves.includes(elm));
        meWins = meWins || winState.every(elm => myMoves.includes(elm));
        if (bobWins || meWins) break;
    }

    return bobWins || meWins;
}

function getOddEvenArr(arr, i) {
    if (arr) return arr.filter(
        (element, index, array) => (index % 2 === i) );
    return undefined;
}


data = [
  1, 2,
  3, 3,
  3, 1,
  1, 1,
  2, 2,
  2, 3,
  3, 2,
  2, 1,
  1, 3,
];

function nextInt() {
    return parseInt(data.unshift());
}


// // default parsers for JS.
// function nextInt() {
//     return parseInt(nextString());
// }

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

/*
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
*/

//          ,17
// 4  1  9   14
// 8  5  6   19
// 3  7  2   12

// 15 13 17 '11
