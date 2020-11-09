/*
* Exercise 1:
Define a filter function on the String object. The function accepts an array of strings that
specifies a list of banned words. The function returns the string after removing all the
banned words.
Example:
console.log("This house is not nice!".filter('not'));
Output: "This house is nice!"
* */

function filter(strarr, ...ban) {
    return strarr.split(" ")
        .filter((s) =>
            ban.findIndex(b => b === s) < 0)
        .reduce((a, c) => a + " " + c);
}

console.log(filter("This house is not nice!", 'not'));

/*
* Exercise 2:
Write a BubbleSort algorithm on the Array object. Bubble sort is a simple sorting algorithm
that works by repeatedly stepping through the list to be sorted,
Example:[6,4,0, 3,-2,1].bubbleSort();
Output : [-2, 0, 1, 3, 4, 6]*/
function bubbleSort(arr) {
    let swap = function (arr, i, j) {
        let t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    for (let j = 0; j < arr.length - 1; j++) {
        for (let i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
    }
    return arr;
}

console.log(bubbleSort([6, 4, 0, 3, -2, 1]))

/*
* Exercise 3:
Create an object called Teacher derived from a Person function constructor, and implement
a method called teach that receives a string called subject, and prints out: [teacher's name]
is now teaching [subject]. Create a Teacher object and call its teach method.
Also do the same thing using Object.create. When using Object.create you will need a
factory function instead of a function constructor in order to pass parameters such as
‘name’ to be set in the prototype.*/
function f3(){
    let Teacher = {}
}