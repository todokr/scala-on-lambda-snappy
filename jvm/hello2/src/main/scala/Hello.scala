import com.amazonaws.services.lambda.runtime.Context
import scala.beans.BeanProperty

class Hello {

  def handleRequest(request: Request, context: Context): Response = {
    Response(s"Hello, ${request.name}. You're ${request.age} years old!")
  }
}

case class Request(@BeanProperty var name: String, @BeanProperty var age: Int) {
  def this() = this("", 0)
}

case class Response(@BeanProperty var message: String) {
  def this() = this("")
}