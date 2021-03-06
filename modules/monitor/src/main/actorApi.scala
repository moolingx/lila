package lila.monitor
package actorApi

import lila.socket.SocketMember

case class Member(channel: JsChannel) extends SocketMember {
  val userId = none
}

case object GetNbMoves

case class Join(uid: String)
case class MonitorData(data: List[String])
