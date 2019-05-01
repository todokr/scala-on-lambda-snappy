import collection.JavaConverters._
import com.amazonaws.services.lambda.runtime.Context

class Takeuchi {


  def handleRequest(n: Int, context: Context): java.util.Map[String, String] = {
    val result = takeuchi(n * 2, n, 0) // O(n^n)
    Map("message" -> s"Yay! Result is $result!").asJava
  }

  private def takeuchi(x: Int, y: Int, z: Int): Int =
    if (x <= y) y else
      takeuchi(
        takeuchi(x - 1, y, z),
        takeuchi(y - 1, z, x),
        takeuchi(z - 1, x, y))
}
