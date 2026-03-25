package dam.exer_2

import kotlin.system.exitProcess

fun main() {
    // Ex. 2 - Console-based calculator
    var choice: String? // String or null
    print("----Calculator-----")
    do {
        // The user selects the intended operation by typint its prompt
        println("\nSelect the intended operation by typing the corresponding prompt:" +
                "\n  Addition: add" +
                "\n  Subtraction: sub" +
                "\n  Multiplication: mul" +
                "\n  Division: div" +
                "\n  Boolean AND: and" +
                "\n  Boolean OR: or" +
                "\n  Boolean NOT: not" +
                "\n  Bitwise shift (left): shl" +
                "\n  Bitwise shift (right): shr" +
                "\n  End the application: end")

        print("Select a choice: ")
        choice = readlnOrNull()

        when (choice) {
            // Basic arithmetic operations
            "add" -> addition()
            "sub" -> subtraction()
            "mul" -> multiplication();
            "div" -> division();
            // Boolean operations
            "and" -> boolAnd();
            "or" -> boolOr();
            "not" -> boolNot();
            // Bitwise shift operators
            "shl" -> shiftLeft();
            "shr" -> shiftRight();
            // Closing the app
            "end" -> end()
            // Invalid choices
            else -> println("Invalid choice. Please type an existing prompt.")
        }
    } while (choice != "end")
}


/**
 * Represents an addition between two numbers.
 */
fun addition(){
    print("Insert the first number: ")
    val num1 = readln().toFloat()
    print("Insert the second number: ")
    val num2 = readln().toFloat()

    val result = num1 + num2
    println("$num1 + $num2 = $result.")
    continueCalc()
}

/**
 * Represents a subtraction between two numbers.
 */
fun subtraction(){
    print("Insert the first number: ")
    val num1 = readln().toFloat()
    print("Insert the second number: ")
    val num2 = readln().toFloat()

    val result = num1 - num2
    println("$num1 - $num2 = $result")
    continueCalc()
}

/**
 * Represents a multiplication between two numbers.
 */
fun multiplication(){
    print("Insert the first number: ")
    val num1 = readln().toFloat()
    print("Insert the second number: ")
    val num2 = readln().toFloat()

    val result = num1 * num2
    println("$num1 * $num2 = $result")
    continueCalc()
}

/**
 * Represents a division between two numbers.
 */
fun division() {
    print("Insert the first number: ")
    val num1 = readln().toFloat()
    var num2 : Float
    do {
        print("Insert the second number: ")
        num2 = readln().toFloat()

        if (num2 == 0f) println("You cannot divide by zero. Please insert a new number.") // Doesn't permit division by zero
    } while (num2 == 0f)

    val result = num1 / num2
    println("$num1 / $num2 = $result")
    continueCalc()
}

/**
 * Represents a boolean AND operation
 */
fun boolAnd() {

    print("Insert the first boolean value (0: False|1: True): ")
    val bool1 = readln().toBoolean()
    print("Insert the second boolean value (0: False|1: True): ")
    val bool2 = readln().toBoolean()

    val result = bool1 && bool2
    println("$bool1 && $bool2 = $result")
    continueCalc()
}

/**
 * Represents a boolean OR operation
 */
fun boolOr() {
    print("Insert the first boolean (0: False|1: True): ")
    val bool1 = readln().toBoolean()
    print("Insert the second boolean (0: False|1: True): ")
    val bool2 = readln().toBoolean()

    val result = bool1 || bool2
    println("$bool1 || $bool2 = $result")
    continueCalc()
}

/**
 * Represents a boolean NOT operation
 */
fun boolNot() {
    print("Insert the boolean you want to negate (0: False|1: True): ")
    val bool = readln().toBoolean()
    val neg = !bool
    print("~$bool = $neg") // ~ como simbolo lógico de negação
    continueCalc()
}

/**
 * Represents a bitwise left shift operation
 */
fun shiftLeft(){
    var bin : String
    do {
        print("Insert a binary number: ")
        var valid = true
        bin = readln()

        for (char in bin) {
            if (char != '0' && char != '1') { // spaces with characters other than 0/1 aren't bits
                valid = false
                break
            }
        }

        if(!valid){
            println("The inserted binary number can only consist of the characters 1 and 0.")
        }
    } while (!valid)

    var shift : Int
    do {
        print("How many bits to shift ?")
        shift = readln().toInt()

        if (shift < 0) println("Please insert a positive number.")
    } while (shift < 0)

    val number = bin.toInt(2)
    val result = number.shl(shift)
    println("${number.toString(2)} >> $shift = ${result.toString(2)}(2) = ${result.toString(10)}(10) = ${result.toString(16)}(16)")
    continueCalc()
}

/**
 * Represents a bitwise right shift operation
 */
fun shiftRight() {
    var bin : String
    do {
        print("Insert a binary number: ")
        var valid = true
        bin = readln()

        for (char in bin) {
            if (char != '0' && char != '1') {
                valid = false
                break
            }
        }

        if(!valid){
            println("The inserted binary number can only consist of the characters 1 and 0.")
        }
    } while (!valid)

    var shift : Int
    do {
        print("How many bits to shift ?")
        shift = readln().toInt()

        if (shift < 0) println("Please insert a positive number.")
    } while (shift < 0)

    val number = bin.toInt(2)
    val result = number.shr(shift)
    println("${number.toString(2)} << $shift = ${result.toString(2)}(2) = ${result.toString(10)}(10) = ${result.toString(16)}(16)")
    continueCalc()
}

fun continueCalc() {
    // Asks the user if they's like to continue using the calculator and acts accordingly
    print("Continue using calculator? (Y/N): ")
    do {
        val opc = readlnOrNull()
        when (opc) {
            "Y", "y"-> return
            "N", "n" -> {
                end()
                return
            }
            else -> println("Invalid option. Please type Y for \"yes\" and N for \"no\".")
        }
    } while (opc != "N" && opc != "Y") // while invalid
}

/**
 * Closes the application.
 */
fun end() {
    println("Closing the application...")
    exitProcess(0)
}




