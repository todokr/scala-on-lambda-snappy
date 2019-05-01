import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

object Hello {

  type Callback = js.Function2[Error, js.Any, Unit]

  @JSExportTopLevel("handleRequest")
  def handleRequest(request: Request, context: js.Object, callback: Callback): Unit = {
    val message = s"Hello, ${request.name}. You're ${request.age} years old!"
    val response = Response(message)
    callback(null, response.toJsObj)
  }
}

// In
@js.native
trait Request extends js.Any {
  val name: String = js.native
  val age: Int = js.native
}

// Out
@JSExportAll
case class Response(message: String) {
  def toJsObj: js.Object = js.Dynamic.literal(
    "message" -> message
  )
}