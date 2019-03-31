import com.softwaremill.sttp._

object Hello {

  def main(args: Array[String]): Unit = {
    implicit val backend: SttpBackend[Id, Nothing] = CurlBackend(verbose = true)

    val runtimeApi = System.getenv("AWS_LAMBDA_RUNTIME_API")
    while (true) {
      val nextApi = sttp.get(uri"http://$runtimeApi/2018-06-01/runtime/invocation/next").send()
      val requestId = nextApi.headers("Lambda-Runtime-Aws-Request-Id")
      val event = nextApi.unsafeBody
      // parse event
      // create response
      // error handling

      sttp.post(uri"http://$runtimeApi/2018-06-01/runtime/invocation/$requestId/response").body().send()
    }
  }
}
