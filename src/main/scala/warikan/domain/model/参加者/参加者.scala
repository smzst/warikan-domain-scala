package warikan.domain.model.参加者

import java.util.UUID.randomUUID

import warikan.domain.model.支払.支払区分

case class 参加者(id: 参加者ID, name: 参加者名, paymentDivision: 支払区分)

object 参加者 {
  def apply(name: 参加者名, paymentDivision: 支払区分): 参加者 = 参加者(
    参加者ID(randomUUID().toString),
    name,
    paymentDivision
  )
}
