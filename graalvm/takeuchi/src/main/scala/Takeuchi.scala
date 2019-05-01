import argonaut._
import Argonaut._
import com.softwaremill.sttp._

object Takeuchi {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  def main(args: Array[String]): Unit = {

    val runtime  = System.getenv("AWS_LAMBDA_RUNTIME_API")

    while (true) {
      val rawRequest = sttp.get(uri"http://$runtime/2018-06-01/runtime/invocation/next").send()
      val requestId = rawRequest.headers("lambda-runtime-aws-request-id").head
      val n = rawRequest.unsafeBody.toInt
      val result = takeuchi(n * 2, n, 0)
      val responseJson = Json("message" -> jString(s"Yay! Result is $result!")).nospaces

      sttp.post(uri"http://$runtime/2018-06-01/runtime/invocation/$requestId/response").body(responseJson).send()
    }
  }

  private def takeuchi(x: Int, y: Int, z: Int): Int =
    if (x <= y) y else
      takeuchi(
        takeuchi(x - 1, y, z),
        takeuchi(y - 1, z, x),
        takeuchi(z - 1, x, y))
}
