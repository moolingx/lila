package lila.user

import scala._

case class Profile(
    country: Option[String] = None,
    location: Option[String] = None,
    bio: Option[String] = None,
    firstName: Option[String] = None,
    lastName: Option[String] = None) {

  def nonEmptyRealName = List(ne(firstName), ne(lastName)).flatten match {
    case Nil   ⇒ none
    case names ⇒ (names mkString " ").some
  }

  def countryInfo = country flatMap Countries.info

  def nonEmptyLocation = ne(location)

  def nonEmptyBio = ne(bio)

  private def ne(str: Option[String]) = str filter (_.nonEmpty)
}

object Profile {

  val default = Profile()

  import lila.db.Tube
  import play.api.libs.json._

  private[user] lazy val tube = Tube[Profile](
    Json.reads[Profile],
    Json.writes[Profile])
}
