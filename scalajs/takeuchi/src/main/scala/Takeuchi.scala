import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

object Takeuchi {

  type Callback = js.Function2[Error, js.Any, Unit]

  @JSExportTopLevel("handleRequest")
  def handleRequest(n: Int, context: js.Object, callback: Callback): Unit = {
    val result = takeuchi(n * 2, n, 0)
    val response = Response(s"Yay! Result is $result!")
    callback(null, response.toJsObj)
  }

  private def takeuchi(x: Int, y: Int, z: Int): Int =
    if (x <= y) y else
      takeuchi(
        takeuchi(x - 1, y, z),
        takeuchi(y - 1, z, x),
        takeuchi(z - 1, x, y))
}

@JSExportAll
case class Response(message: String) {
  def toJsObj: js.Object = js.Dynamic.literal(
    "message" -> message
  )
}