import collection.JavaConverters._
import scala.annotation.tailrec

import com.amazonaws.services.lambda.runtime.Context

class Fibonacci {

  def handleRequest(n: Int, context: Context): java.util.Map[String, String] = {
    val result = fibonacci(n)
    Map("message" -> s"Yay! Result is $result!").asJava
  }

  private def fibonacci(n: Int): BigInt = {
    @tailrec
    def _loop(n: Int, a: BigInt, b: BigInt): BigInt =
      if (n == 0) a else _loop(n - 1, b, a + b)

    _loop(n, 0, 1)
  }
}
