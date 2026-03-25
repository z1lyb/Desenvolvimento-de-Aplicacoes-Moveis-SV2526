package dam.exer_1

// Ex. 1 - Event Log Processing with Extension Functions and Higher-Order Functions
sealed class Event

// The event subclasses have been declared as data classes, as they only represent information and don't have a body.
data class Login(val username : String, val timestamp : Long) : Event()
data class Purchase(val username : String, val amount : Double, val timestamp : Long) : Event()
data class Logout(val username : String, val timestamp: Long) : Event()

/**
 * Filters the events in a list by the username inserted as a parameter.
 * @param username username used to filter events
 * @return the events assigned to the corresponding username
 */
fun List<Event>.filterByUser(username : String) : List<Event>{
    return this.filter {
        when (it) {
            is Login -> username == it.username
            is Purchase -> username == it.username
            is Logout -> username == it.username
        }
    }
}

/**
 * Calculates the total amount spent by as user across purchases.
 * @param username name used to search purchases
 * @return total amount of user's purchases
 */
fun List<Event>.totalSpent(username : String) : Double{
    val userPurchases = this.filterByUser(username).filterIsInstance<Purchase>() // List of purchases made by the user
    var total = 0.0 // initializes the total purchase amount
    for (purchase in userPurchases) total += purchase.amount // calculates the total
    return total
}

/**
 * Processes events by applying a certain function to all of them.
 * After processing each event, prints a detailed description of it to the console.
 * @param events List of events to apply the function to
 * @param handler lambda (function) to be applied to all events
 */
fun processEvents(events : List<Event>, handler : (Event) -> Unit){
    for (event in events){
        handler(event)
        when(event){
            is Login -> println("[LOGIN] ${event.username} logged in at t = ${event.timestamp}")
            is Purchase -> println("[PURCHASE] ${event.username} spent $${event.amount} at t = ${event.timestamp}")
            is Logout -> println("[LOGOUT] ${event.username} logged out at t = ${event.timestamp}")
        }
    }
}


fun main(){
    val events = listOf (
        Login ("alice", 1_000),
        Purchase ("alice", 49.99, 1_100),
        Purchase ("bob", 19.99, 1_200),
        Login ("bob", 1_050),
        Purchase ("alice", 15.00, 1_300),
        Logout ("alice", 1_400),
        Logout ("bob", 1_500)
    )

    processEvents(events){}

    val totalAlice = events.totalSpent("alice")
    println("Total spent by alice: ${String.format("%.2f", totalAlice)}")

    val totalBob = events.totalSpent("bob")
    println("Total spent by bob: ${String.format("%.2f", totalBob)}")

    val aliceEvents = events.filterByUser("alice")
    println("Events for alice:")
    aliceEvents.forEach { println("\t" + it) }
}