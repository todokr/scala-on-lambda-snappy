import collection.JavaConverters._

import argonaut._
import Argonaut._
import com.atilika.kuromoji.ipadic.Tokenizer
import com.softwaremill.sttp._

object Kuromoji {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  def main(args: Array[String]): Unit = {

    val runtime  = System.getenv("AWS_LAMBDA_RUNTIME_API")
    val tokenizer = new Tokenizer()

    try {
      println(s"runtime: $runtime")
    } catch {
      case e: Exception =>
        val message = Json("errorMessage" -> jString(e.getMessage), "errorType" -> jString(e.getClass.getName)).toString
        sttp.post(uri"http://$runtime/2018-06-01/runtime/init/error").body(message).send()
    }

    while (true) {
      val rawRequest = sttp.get(uri"http://$runtime/2018-06-01/runtime/invocation/next").send()
      val requestId = rawRequest.headers("lambda-runtime-aws-request-id").head

      try {
        val text = Parse.parseWith(rawRequest.unsafeBody, _.field("text").flatMap(_.string).get, identity)

        val tokens = tokenizer.tokenize(text)
        val results = tokens.asScala.map { token =>
          jString(s"${token.getSurface}: ${token.getAllFeatures}")
        }
        val responseJson = Json.array(results :_*)

        sttp.post(uri"http://$runtime/2018-06-01/runtime/invocation/$requestId/response").body(responseJson.nospaces).send()
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
