package lila
package ladder

import com.novus.salat._
import com.novus.salat.dao._
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.query.Imports._
import scalaz.effects._

private[ladder] final class LadderRepo(collection: MongoCollection)
    extends SalatDAO[Ladder, String](collection) {

  def byId(id: String): IO[Option[Ladder]] = io { findOneById(id) }

  def findAll = io { find(DBObject()).toList }

  def incLads(ladderId: String, by: Int): IO[Unit] = io {
    update(idQuery(ladderId), $inc("nbLads" -> by))
  }
  def idQuery(id: String) = DBObject("_id" -> id)

  def insertIO(ladder: Ladder) = io(insert(ladder)).void
}
