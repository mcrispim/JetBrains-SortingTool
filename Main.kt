package sorting

import java.io.File
import java.util.*

enum class DataType {
    LONG, LINE, WORD
}
enum class SortType {
    NATURAL, BY_COUNT
}

object Arguments {
    var dataType = DataType.WORD
    var sortingType = SortType.NATURAL
    var hasInputFile = false
    var inputFileName = ""
    var hasOutputFile = false
    var outputFileName = ""
}
var numberOfElements = 0

fun processArgs(args: Array<String>) {
    if (args.isEmpty()) {
        return
    }
    var i = 0
    while (i <= args.lastIndex) {
        if (args[i] !in setOf<String>("-dataType", "-sortingType", "-inputFile", "-outputFile")) {
            println("\"${args[i]}\" is not a valid parameter. It will be skipped.")
            i++
            continue
        }
        val arg = if (i + 1 <= args.lastIndex && !args[i + 1].startsWith('-')) args[i + 1] else ""
        when (args[i]) {
            "-dataType" -> Arguments.dataType = when (arg) {
                "long" -> DataType.LONG
                "line" -> DataType.LINE
                "word" -> DataType.WORD
                else -> {
                    println("No data type defined!")
                    DataType.WORD
                }
            }
            "-sortingType" -> Arguments.sortingType = when (arg) {
                "natural" -> SortType.NATURAL
                "byCount" -> SortType.BY_COUNT
                else -> {
                    println("No sorting type defined!")
                    SortType.NATURAL
                }
            }
            "-inputFile" -> {
                Arguments.inputFileName = arg
                Arguments.hasInputFile = true
            }
            "-outputFile" -> {
                Arguments.outputFileName = arg
                Arguments.hasOutputFile = true
            }
            else -> {
                println("${args[i]} is not a valid parameter. It will be skipped.")
            }
        }
        i += 2
    }
}

fun readElements(): MutableList<String> {
    val elements = mutableListOf<String>()
    val scanner = if (Arguments.hasInputFile) Scanner(File(Arguments.inputFileName)) else Scanner(System.`in`)
    while (scanner.hasNext()) {
        elements.add(when (Arguments.dataType) {
            DataType.LONG -> scanner.nextInt().toString()
            DataType.WORD -> scanner.next()
            DataType.LINE -> scanner.nextLine()
        })
        numberOfElements++
    }
    scanner.close()
    return elements
}

fun sortElements(elements: MutableList<String>): List<String> {
    if (Arguments.sortingType == SortType.NATURAL) {
        return if (Arguments.dataType == DataType.LONG) {
            elements.map { it.toInt() }.sorted().map{ it.toString() }
        } else {
            elements.sorted()
        }
    }
    // sort byCount
    if (Arguments.dataType == DataType.LONG) {
        val mappedElements = mutableMapOf<Int, Int>()
        for (e in elements) {
            val n = e.toInt()
            mappedElements[n] = (mappedElements[n] ?: 0) + 1
        }
        return mappedElements
            .toList()
            .sortedBy { it.first }
            .sortedBy { it.second }
            .map { "${it.first}: ${it.second} time(s), ${it.second * 100 / numberOfElements}%" }
            .toMutableList()
    } else {
        val mappedElements = mutableMapOf<String, Int>()
        for (e in elements) {
            mappedElements[e] = (mappedElements[e] ?: 0) + 1
        }
        return mappedElements
            .toList()
            .sortedBy { it.first }
            .sortedBy { it.second }
            .map { "${it.first}: ${it.second} time(s), ${it.second * 100 / numberOfElements}%" }
            .toMutableList()
    }
}

fun output(elements: List<String>) {
    var output = ""

    output += ("Total ${when(Arguments.dataType) {
        DataType.LONG -> "numbers"
        DataType.WORD -> "words"
        DataType.LINE -> "lines"}}: $numberOfElements.") + "\n"
    if (Arguments.sortingType == SortType.NATURAL) {
        output += ("Sorted data: ")
        if (Arguments.dataType == DataType.LINE) {
            output += "\n"
            for (l in elements) {
                output += "$l\n"
            }
        } else {
            for (e in elements) {
                output += "$e "
            }
            output += "\n"
        }
    } else {
        for (e in elements) {
            output += "$e\n"
        }
    }

    if (Arguments.hasOutputFile) {
        val myFile = File(Arguments.outputFileName)
        println("Saving myFile")
        myFile.writeText(output)
    } else {
        println(output)
    }
}

fun main(args: Array<String>) {
    processArgs(args)
    val elements = readElements()
    val sortedElements = sortElements(elements)
    output(sortedElements)
}