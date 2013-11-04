package org.niryuu.wprandom

import org.scalatra._
import scalate.ScalateSupport
import java.net.URLEncoder
import scala.collection.JavaConversions

class WpRandomServlet extends WprandomStack {
  get("/") {
    val titles = WikipediaAccess.getRandom(10)
    val pages = titles.map(title => List(title, "http://ja.wikipedia.org/wiki/" + URLEncoder.encode(title)))
    contentType = "text/html"
    jade("/random", "pages" -> pages)
  }
}
