//     Define a function max() that takes two numbers as arguments and returns the largest of them. Use the if-then-else construct available in Javascript.
function max(a, b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

//     Define a function maxOfThree() that takes three numbers as arguments and returns the largest of them.
function maxOfThree(a, b, c) {
    return max(max(a, b), c);
}

//     Write a function isVowel() that takes a character (i.e. a string of length 1) and returns true if it is a vowel, false otherwise.
function isVowel(c) {
    if (!c || c.length !== 1) {
        return false;
    }

    let vowel = ['a', 'e', 'i', 'o', 'u']
    for (const a of vowel) {
        if (c === a) {
            return true;
        }
    }

    return false;
}

//     Define a function sum() and a function multiply() that sums and multiplies (respectively) all the numbers in an array of numbers. For example, sum([1,2,3,4]) should return 10, and multiply([1,2,3,4]) should return 24.
function sum(arr) {
    let s = 0;
    for (const a of arr) {
        s += a;
    }
    return s;
}

function multiply(arr) {
    let s = 1;
    for (const a of arr) {
        s *= a;
    }
    return s;
}

// Define a function reverse() that computes the reversal of a string. For example, reverse("jag testar") should return the string "ratset gaj".
function reverse(s) {
    let r = '';
    for (const c of s){
        r = c + r;
    }
    return r;
}

//     Write a function findLongestWord() that takes an array of words and returns the length of the longest one.
function findLongestWord(words) {
    let maxLen = 0;
    for (const w of words) {
        if (maxLen < w.length) {
            maxLen = w.length;
        }
    }
    return maxLen;
}

//     Write a function filterLongWords() that takes an array of words and an integer i and returns the array of words that are longer than i.
function filterLongWords(words, i) {
    let rArr = [];
    for (const w of words) {
        if (i < w.length) {
            rArr.push(w);
        }
    }
    return rArr;
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
        return "TEST FAILED. Expected " + expectation + " found " + func();;
    }
}

console.log("Expected output of max(20,10) is 20 and  " +
    myFunctionTest(20, function () {
        return max(20, 10);
    })
);
console.log("Expected output of maxOfThree(5,4,44) is 44  " + myFunctionTest(44, function () {
    return maxOfThree(5, 4, 44);
}));
console.log("Expected output of maxOfThree(55,4,44) is 55  " + myFunctionTest(55, function () {
    return maxOfThree(55, 4, 44);
}));
console.log("expected output of isVowel is true " + myFunctionTest(true, function() {return isVowel('i');}));
console.log("expected output of isVowel is false " + myFunctionTest(false, function() {return isVowel('x');}));

console.log("expected output of sum is 10 " + myFunctionTest(10, function() {return sum([1,2,3,4]);}));
console.log("expected output of sum is 24 " + myFunctionTest(24, function() {return multiply([1,2,3,4]);}));

console.log("expected output of reverse is gib " + myFunctionTest('gib', function() {return reverse('big');}));

console.log("expected output of findLongestWord is 6 " + myFunctionTest(6, function() {return findLongestWord(['one','two','three','sixsix']);}));
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