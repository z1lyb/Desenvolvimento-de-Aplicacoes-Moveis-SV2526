package annotations

@Target(AnnotationTarget.FUNCTION) // can only be applied to functions
@Retention(AnnotationRetention.SOURCE) // only used during compilation, not at runtime
annotation class Greeting(val message: String)