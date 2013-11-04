package org.niryuu.wprandom

import scala.slick.driver.PostgresDriver.simple._
import scala.util.Random

/**
 * Created by niryuu on 2013/11/04.
 */
object WikipediaAccess {
  def getRandom(num: Int): List[String] = {
    val db = Database.forURL(Settings.postgresURL, user=Settings.postgresUser, password=Settings.postgresPass, driver="org.postgresql.Driver")
    db.withSession { implicit session: Session =>
      val max = (for{r <- WikipediaTitles} yield r.id.count).first
      val randomList = Seq.fill(num)(Random.nextInt(max))
      val q2 = for {r <- WikipediaTitles if r.id inSet randomList} yield r.title
      q2.list
    }
  }
}

case class WikipediaTitle(id: Int, title: String)
object WikipediaTitles extends Table[WikipediaTitle]("title") {
  def id = column[Int]("id", O PrimaryKey, O AutoInc)
  def title = column[String]("title")
  def * = (id ~ title).<>[WikipediaTitle](WikipediaTitle, WikipediaTitle unapply _)
}