import java.nio.charset.Charset

import scala.scalajs.js.annotation.JSExportTopLevel

object Crawler {

  @JSExportTopLevel("handleRequest")
  def handleRequest(url: String): Unit = {
    val charset = Charset.forName("Shift_JIS")
    println(charset)
  }
}

