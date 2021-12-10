# JetBrains-SortingTool

Stage 1/6: Numbers only
Description
With this project, you will learn how to process numeric and text input, sort it, and output useful information about the data. Your final program will work with numbers, words, and lines. In the first stage, we will stick to integer numbers.

The program should read user input consisting of several lines, each containing integers separated by an arbitrary number of spaces. Then it should count the number of integers in the input, find the greatest one, and identify the number of times this integer appears. Finally, it should print this information to the console.

If you run your program and try to type in the numbers manually, you'll see that this process will go on infinitely. To end the input, the user should type the end-of-file symbol, informing the operating system that no more input will be provided. On Linux and Mac, the shortcut for this symbol is Ctrl+D or Cmd+D, and on Windows the combination is Ctrl+Z. You don't need to check specifically for the end-of-file symbols in your program, use scanner.hasNext... instead. This will return false if the end of the input is reached.

Objectives
Read integers from the console until the end of the input is reached.

Compute the following information:

The number of integers in the input (X)

The greatest number in the input (Y)

How many times the greatest number occurs in the input (Z)

Output it using this template:

Total numbers: X.
The greatest number: Y (Z time(s)).
Example
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
The greatest number: 42 (1 time(s)).



Stage 2/6: Not just numbers
Description
Remember how we wanted the program to work not only with numbers but also with lines and words? In this stage, you will add behavior for text data types to your program. You will also implement parsing for command-line arguments that will allow the user to define the input data type

After parsing the arguments and reading the input, the program should treat the input according to its data type and output an information message similar to the one from the previous stage.

Objectives
Parse arguments that define the input data type:
if the optional -dataType argument is provided, it should be followed by long, line, or word, which means that the input consists of numbers, lines, or words, respectively.
if the argument is not provided, you should assume that the -dataType argument is word.
Read the input depending on the type:
long — numbers with an arbitrary number of spaces between them, just like in the previous stage.
line — each line treated as a whole string.
word — continuous sequences of characters separated by an arbitrary number of spaces.
Compute the following information about the data:
The number of lines, numbers, or words in the input.
The greatest number or the longest line or word in the input.
How many times this greatest or longest element occurs in the input (compare words and lines by length; if two elements are the same length, arrange them alphabetically).
The greatest/longest element's occurrence percentage.
Print this information as shown in the examples. Note that you should print the longest line on a separate line, so you will end up printing 4 lines instead of 2.
Examples
Run configuration examples:

java SortingTool -dataType long
java SortingTool -dataType line
java SortingTool -dataType word
Run examples

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1, for integers:

> 1 -2   333 4
> 42
> 1                 1
Total numbers: 7.
The greatest number: 333 (1 time(s), 14%).
Example 2, for lines:

> 1 -2   333 4
> 42
> 1                 1
Total lines: 3.
The longest line:
1                 1
(1 time(s), 33%).
Example 3, for words:

> 1 -2   333 4
> 42
> 1                 1
Total words: 7.
The longest word: 333 (1 time(s), 14%).



Stage 3/6: Sorting it out
Description
This project is called Sorting Tool, but, so far, you still haven’t really sorted the elements of the user input. Let's add a number-sorting mechanism to the program and provide an appropriate command-line argument to use this function.

The new optional -sortIntegers argument indicates that the input numbers should be sorted.

Objectives
Update the parsing of command-line arguments to support the number sorting option.

If the -sortIntegers argument is provided, ignore the other arguments and output two lines: the first containing the total number of numbers in the input, and the second containing all of the input numbers in ascending order.

If the -sortIntegers argument is not provided, the behavior of the program should be the same as in the previous stage.

Example
Run configuration example:

java SortingTool -sortIntegers
Run example

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
Sorted data: -2 1 1 1 4 33 42



Stage 4/6: Everything counts
Description
Now that it’s possible to sort numbers, it's time to implement the same functionality for words and lines. But that's not all for this stage! Let's also add a new sorting type that is often rather useful: sorting by count, a type of sorting that arranges the elements by number of occurrences.

Instead of -sortIntegers, we will use the universal -sortingType argument.

The result of sorting by count should be pairs (count, dataEntry) sorted by the count value.

Note that from this stage on, your program focuses on sorting user data, so it will no longer output information about the greatest number or the longest string.

Objectives
Update the parsing of command-line arguments to support the sorting type definition:

if the -sortingType argument is provided, it should be followed by natural or byCount, which defines the sorting type.
if the argument is not provided, then consider natural to be the default sorting type.
Implement natural sorting for words and lines, and sorting by count for all data types. Within the group, elements with equal count values should be sorted naturally.

Note: for strings (words and lines), natural order is lexicographic order, for numbers it is numeric order.

Print the line containing the total number m of elements in the input: Total ELEMENTS: m., where ELEMENTS can be numbers, words, or lines, depending on the input. Then output the sorting results:

for natural sorting of numbers or words, print elements on one line in ascending order:
Sorted data: el_1 el_2 ... el_m 
for natural sorting of lines, print lexicographically sorted elements each on a new line:
Sorted data:
line_1
line_2
…
line_m
for sorting by count, print elements sorted by the number of occurrences, each on a new line, using the following format:
element: count time(s), percentage%
Here, element is a number, word, or line, count is the number of times it appears in the input, and percentage is the proportion of the count to the total number of elements in the input, given as a percentage.
Examples
Run configuration examples:

java SortingTool -sortingType natural -dataType long
java SortingTool -dataType word -sortingType byCount
Run examples:

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1, for sorting longs by count:

> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
-2: 1 time(s), 14%
4: 1 time(s), 14%
33: 1 time(s), 14%
42: 1 time(s), 14%
1: 3 time(s), 43%
Example 2, for sorting numbers naturally:

> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
Sorted data: -2 1 1 1 4 33 42 
Example 3, for sorting words naturally:

> 1 -2   33 4
> 42
> 1                 1
Total words: 7.
Sorted data: -2 1 1 1 33 4 42 
Example 4, for sorting lines naturally:

> 1 -2   33 4
> 42
> 1                 1
Total lines: 3
Sorted data:
1                 1
1 -2   33 4
42



Stage 5/6: Error_
Description
There is always a possibility that someone will run your program the wrong way. It shouldn't just silently crash, but instead, it should print a message that informs the user of the mistake they made.

In this stage, let's implement error handling for various exceptional situations the user might encounter.

Objectives
Add exception handling for possible errors and output error messages to the console:

if the -sortingType argument is provided but the type is not, print a message No sorting type defined!
if the -dataType argument is provided but the type is not, print No data type defined!
if unknown command-line arguments are provided, print "-arg" is not a valid parameter. It will be skipped. for each unknown argument -arg;
if there are strings in the input, but the data type is defined as long, print "abc" is not a long. It will be skipped. for each string abc from the input.
Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1: sorting numbers naturally without errors

$> java SortingTool -sortingType natural -dataType long
> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
Sorted data: -2 1 1 1 4 42 333 
Example 2: sorting numbers by count without errors

$> java SortingTool -sortingType byCount -dataType long
> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
-2: 1 time(s), 14%
4: 1 time(s), 14%
33: 1 time(s), 14%
42: 1 time(s), 14%
1: 3 time(s), 43%
Example 3: missing sorting type

$> java SortingTool -sortingType
No sorting type defined!
Example 4: missing data type

$> java SortingTool -dataType
No data type defined!
Example 5: invalid arguments and input value

$> java SortingTool -dataType long -sortingType natural -abc -def
"-abc" is not a valid parameter. It will be skipped.
"-def" isn not a valid parameter. It will be skipped.
> a 2 -42
"a" is not a long. It will be skipped.
Total numbers: 2.
Sorted data: -42 2



Stage 6/6: X-files
Description
Sometimes it's useful to read data that is from a file, rather than from the standard input, and write the result to another file instead of printing it to the console. Add this functionality to your program along with the appropriate command-line argument support.

Objectives
Update command-line arguments parsing to support the -inputFile and -outputFile arguments.

If -inputFile is provided followed by the file name, read the input data from the file.

If -outputFile is provided followed by the file name, output only the error messages to the console and print the results to the file.

Examples
Example 1: input file is defined

java SortingTool -sortingType byCount -inputFile input.txt
Example 2: input and output files are defined

java SortingTool -sortingType byCount -inputFile data.dat -outputFile out.txt
