import collection.JavaConverters._

import com.amazonaws.services.lambda.runtime.Context
import com.atilika.kuromoji.ipadic.Tokenizer

class Kuromoji {

  val tokenizer = new Tokenizer()

  def handleRequest(rawRequest: java.util.Map[String, String], context: Context): java.util.List[String] = {
    val rawString = rawRequest.asScala("text")
    val tokens = tokenizer.tokenize(rawString)

    tokens.asScala.map { token =>
      s"${token.getSurface}: ${token.getAllFeatures}"
    }.asJava
  }
}
