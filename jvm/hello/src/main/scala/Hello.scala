import scala.beans.BeanProperty

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class Hello extends RequestHandler[Request, Response] {

  override def handleRequest(request: Request, context: Context): Response = {
    val message = s"Hello, ${request.name}. You're ${request.age} years old!"
    Response(message)
  }
}

case class Request(@BeanProperty var name: String, @BeanProperty var age: Int) {
  def this() = this("", 0)

}
case class Response(@BeanProperty var message: String) {
  def this() = this("")
}
