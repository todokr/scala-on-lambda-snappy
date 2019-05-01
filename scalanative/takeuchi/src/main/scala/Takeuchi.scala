import com.softwaremill.sttp._
import argonaut._, Argonaut._

object Takeuchi {

  def main(args: Array[String]): Unit = {
    implicit val backend: SttpBackend[Id, Nothing] = CurlBackend()

    val runtimeApi = System.getenv("AWS_LAMBDA_RUNTIME_API")

    while (true) {
      val nextApi = sttp.get(uri"http://$runtimeApi/2018-06-01/runtime/invocation/next").send()
      val requestId = nextApi.headers("Lambda-Runtime-Aws-Request-Id")
      val n = nextApi.unsafeBody.toInt
      val result = takeuchi(n * 2, n, 0)
      val message = Json("message" -> jString(s"Yay! Result is $result!")).nospaces

      sttp.post(uri"http://$runtimeApi/2018-06-01/runtime/invocation/$requestId/response").body(message).send()
    }
  }

  private def takeuchi(x: Int, y: Int, z: Int): Int =
    if (x <= y) y else
      takeuchi(
        takeuchi(x - 1, y, z),
        takeuchi(y - 1, z, x),
        takeuchi(z - 1, x, y))
}
