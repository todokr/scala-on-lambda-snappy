import com.softwaremill.sttp._
import argonaut._, Argonaut._

object Hello {

  def main(args: Array[String]): Unit = {
    implicit val backend: SttpBackend[Id, Nothing] = CurlBackend()
    implicit val requestCodec: CodecJson[Request] = casecodec2(Request.apply, Request.unapply)("name", "age")

    val runtimeApi = System.getenv("AWS_LAMBDA_RUNTIME_API")

    while (true) {
      val nextApi = sttp.get(uri"http://$runtimeApi/2018-06-01/runtime/invocation/next").send()
      val requestId = nextApi.headers("Lambda-Runtime-Aws-Request-Id")
      val request = Parse.decodeOption[Request](nextApi.unsafeBody).get
      val message = Json("message" -> jString(s"Hello, ${request.name}. You're ${request.age} years old!")).nospaces

      sttp.post(uri"http://$runtimeApi/2018-06-01/runtime/invocation/$requestId/response").body(message).send()
    }
  }
}

case class Request(name: String, age: Int)
