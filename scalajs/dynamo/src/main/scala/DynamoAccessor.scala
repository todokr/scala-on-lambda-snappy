import io.scalajs.JSON

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel, JSImport}

@js.native
@JSImport("aws-sdk", JSImport.Namespace)
object AWS extends js.Object {

  @js.native
  object DynamoDB extends js.Object {

    @js.native
    class DocumentClient extends js.Object {
      def query(param: js.Any, callback:js.Function2[js.Object, js.Object, js.Any]): Unit = js.native
    }
  }
}

object DynamoAccessor {

  type Callback = js.Function2[Error, js.Any, Unit]

  val dynamoClient = new AWS.DynamoDB.DocumentClient()

  @JSExportTopLevel("handleRequest")
  def handleRequest(request: Request, context: js.Object, callback: Callback): Unit = {

    val param = js.Dictionary(
      "TableName" -> "PriceHistory",
      "KeyConditionExpression" -> "#ji = :jobid",
      "ExpressionAttributeNames" -> js.Dictionary("#ji" -> "JobId"),
      "ExpressionAttributeValues" -> js.Dictionary(":jobid" -> request.jobId)
    )

    dynamoClient.query(param, (err: js.Object, data: js.Object) => {
      if (err != null) {
        callback(null, err)
      } else {
        callback(null, data)
      }
    })
  }
}

@js.native
trait Request extends js.Any {
  val jobId: String = js.native
}