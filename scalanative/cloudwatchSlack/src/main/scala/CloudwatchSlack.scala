import com.softwaremill.sttp._
import argonaut._, Argonaut._

object CloudwatchSlack {

  def main(args: Array[String]): Unit = {
    implicit val backend: SttpBackend[Id, Nothing] = CurlBackend()
    val runtimeApi = System.getenv("AWS_LAMBDA_RUNTIME_API")
    val webhookUrl = System.getenv("SLACK_WEBHOOK_URL")
    val slackChannel = System.getenv("SLACK_CHANNEL")

    while (true) {
      val nextApi = sttp.get(uri"http://$runtimeApi/2018-06-01/runtime/invocation/next").send()
      val requestId = nextApi.headers("Lambda-Runtime-Aws-Request-Id")
      val request = Parse.parseWith(nextApi.unsafeBody, json => {
        val record = json.fieldOrEmptyObject("Records").arrayOrEmpty.head
        val eventName = record.fieldOrEmptyString("eventName").string.get
        val s3 = record.fieldOrEmptyObject("s3")
        val bucketArn = s3.fieldOrEmptyObject("bucket").fieldOrEmptyString("arn").string.get
        val key = s3.fieldOrEmptyObject("object").fieldOrEmptyString("key").string.get
        Request(eventName ,bucketArn, key)
      }, s => throw new Exception(s"parse failed!: $s"))
      val message = Json("message" -> jString(s"Hello, ${request}")).nospaces

      if (request.bucketArn == "arn:aws:s3:::example-bucket") {
        val message =
          s"""
             |{
             |  "channel": "$slackChannel",
             |  "text": "Event: ${request.eventName}\nKey:${request.objectKey}"
             |}
        """.stripMargin

        val result = sttp.post(uri"$webhookUrl").body(message).send()
        println(result.code)
      }

      sttp.post(uri"http://$runtimeApi/2018-06-01/runtime/invocation/$requestId/response").body(message).send()
    }
  }
}

case class Request(
  eventName: String,
  bucketArn: String,
  objectKey: String
)
