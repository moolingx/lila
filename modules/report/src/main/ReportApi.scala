package lila.report

import play.api.libs.json._

import lila.db.api._
import lila.db.Implicits._
import lila.user.User
import tube.reportTube

final class ReportApi {

  def create(setup: ReportSetup, by: User): Funit =
    Reason(setup.reason).fold[Funit](fufail("Invalid report reason " + setup.reason)) { reason ⇒
      val report = Report.make(
        user = setup.user,
        reason = reason,
        text = setup.text,
        createdBy = by)
      $insert(report)
    }

  def unprocessed = $find($query(Json.obj("processed" -> false)) sort $sort.createdDesc, 50)
  def recent = $find($query.all sort $sort.createdDesc, 50)
}
