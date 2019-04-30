import argonaut._
import Argonaut._
import com.softwaremill.sttp._

object Hello {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()
  implicit def PersonCodecJson: CodecJson[Request] = casecodec2(Request.apply, Request.unapply)("name", "age")

  def main(args: Array[String]): Unit = {

    // 初期化処理
    val runtime  = System.getenv("AWS_LAMBDA_RUNTIME_API")
    try {
      println(s"runtime: $runtime")
    } catch {
      case e: Exception =>
        val message = Json("errorMessage" -> jString(e.getMessage), "errorType" -> jString(e.getClass.getName)).toString
        sttp.post(uri"http://$runtime/2018-06-01/runtime/init/error").body(message).send()
    }

    // イベントループ
    while (true) {
      // イベントデータの取得
      //val HttpResponse(body, _, headers) =
//        Http(s"http://$runtime/2018-06-01/runtime/invocation/next").asString

      val rawRequest = sttp.get(uri"http://$runtime/2018-06-01/runtime/invocation/next").send()
      val requestId = rawRequest.headers("lambda-runtime-aws-request-id").head

      try {
        val request = rawRequest.unsafeBody.decodeOption[Request].get
        val responseJson = Json("message" -> jString(s"Hello, ${request.name}. You're ${request.age} years old!")).nospaces

        // レスポンスデータの返却
        sttp.post(uri"http://$runtime/2018-06-01/runtime/invocation/$requestId/response").body(responseJson).send()
      } catch {
        case e: Exception =>
          println(e.getClass.getName)
          println(e.getMessage)
          val message = Json("errorMessage" -> jString(e.getMessage), "errorType" -> jString(e.getClass.getName)).nospaces
          sttp.post(uri"http://$runtime/2018-06-01/runtime/invocation/$requestId/error").body(message).send()
      }
    }
  }
}

case class Request(name: String, age: Int)