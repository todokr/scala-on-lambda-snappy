import com.amazonaws.services.lambda.runtime.Context
import collection.JavaConverters._

class Hello {

  def handleRequest(rawRequest: java.util.Map[String, String], context: Context): java.util.Map[String, String] = {
    val map = rawRequest.asScala
    val name = map("name")
    val age = map("age")
    Map("message" -> s"Hello, $name. You're $age years old!").asJava
  }
}