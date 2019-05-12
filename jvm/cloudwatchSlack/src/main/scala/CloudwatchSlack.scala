import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.softwaremill.sttp._

class CloudwatchSlack extends RequestHandler[S3Event, Unit] {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  override def handleRequest(event: S3Event, context: Context): Unit = {
    val webhookUrl = System.getenv("SLACK_WEBHOOK_URL")
    val slackChannel = System.getenv("SLACK_CHANNEL")

    val record = event.getRecords.get(0)
    val eventName = record.getEventName
    val bucketArn = record.getS3.getBucket.getArn
    val objectKey = record.getS3.getObject.getKey

    if (bucketArn == "arn:aws:s3:::example-bucket") {
      val message =
        s"""
          |{
          |  "channel": "$slackChannel",
          |  "text": "Event: $eventName\nKey:$objectKey"
          |}
        """.stripMargin

      val result = sttp.post(uri"$webhookUrl").body(message).send()
      println(result.code)
    }
  }
}
