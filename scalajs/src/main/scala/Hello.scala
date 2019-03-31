import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

object Hello {

  type Callback = js.Function2[Error, js.Any, Unit]

  @JSExportTopLevel("sayHello")
  def sayHello(request: Request, context: js.Object, callback: Callback): Unit = {
    val message = s"Hello, ${request.name}. You're ${request.age} years old!"
    val response = Response(message, 42)
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
case class Response(message: String, secretNumber: Int) {
  def toJsObj: js.Object = js.Dynamic.literal(
    "message" -> message,
    "secretNumber" -> secretNumber
  )
}