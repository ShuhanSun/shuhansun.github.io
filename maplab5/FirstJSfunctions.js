//     Define a function sum() and a function multiply() that sums and multiplies (respectively) all the numbers in an array of numbers. For example, sum([1,2,3,4]) should return 10, and multiply([1,2,3,4]) should return 24.
function sum(arr) {
    return arr.reduce((prevValue, elem) => prevValue + elem);
}

function multiply(arr) {
    return arr.reduce((prevValue, elem) => prevValue * elem);
}

// Define a function reverse() that computes the reversal of a string. For example, reverse("jag testar") should return the string "ratset gaj".
function reverse(s) {
    return [...s].reduce((prevValue, elem) => elem + prevValue);
}


//     Write a function filterLongWords() that takes an array of words and an integer i and returns the array of words that are longer than i.
function filterLongWords(words, i) {
    return words.filter((w) => w.length > i);
}

//     Modify the jsfiddle on the map/filter/reduce slide as follows:
//
// a) multiply each element by 10;
//
// b) return array with all elements equal to 3;
//
// c) return the product of all elements;
//
function stream() {
    const a = [1, 3, 5, 3, 3];
    const b = a.map(elem => elem * 10);
    console.log(b);

    const c = a.filter(elem => elem === 3);
    console.log(c);

    const d = a.reduce((prevValue, elem) => prevValue * elem);
    console.log(d);
}

function myFunctionTest(expectation, func) {
    if (expectation === func()) {
        return "TEST SUCCEEDED";
    } else {
        return "TEST FAILED. Expected " + expectation + " found " + func();
        ;
    }
}

console.log("expected output of sum is 10 " + myFunctionTest(10, function () {
    return sum([1, 2, 3, 4]);
}));
console.log("expected output of sum is 24 " + myFunctionTest(24, function () {
    return multiply([1, 2, 3, 4]);
}));

console.log("expected output of filterLongWords is 'apple' " +
    myFunctionTest('apple',
        function() {
            const longWords = filterLongWords(['one', 'apple', 'orange', 'two', 'the'], 4);
            return longWords[0];
        }));
console.log("expected output of filterLongWords is 'orange' " +
    myFunctionTest('orange',
        function() {
            const longWords = filterLongWords(['one', 'apple', 'orange', 'two', 'the'], 4);
            return longWords[1];
        }));



stream();