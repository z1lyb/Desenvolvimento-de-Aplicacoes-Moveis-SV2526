package app

import dam.DataProcessorExtractor

fun main() {
    // MyClass testing
    val myClass = MyClass()
    val wrappedMyClass = MyClassWrapper(myClass) // Use the wrapper class

    wrappedMyClass.sayHello()
    wrappedMyClass.compute()

    // Regex texting
    val input = "Name: John Address: 123 Street"

    // Using the generated DataProcessorExtractor
    val extractor = DataProcessorExtractor(input)
    println("Name: ${extractor.getName()}")
    println("Address: ${extractor.getAddress()}")
}