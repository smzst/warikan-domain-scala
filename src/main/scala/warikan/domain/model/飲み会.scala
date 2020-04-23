package warikan.domain.model

import java.time.LocalDateTime

import warikan.domain.model.money.Money
import warikan.domain.model.参加者._
import warikan.domain.model.支払.支払割合

case class 飲み会名(value: String) {
  require(value.length > 0)
}
case class 開催日時(value: LocalDateTime) {
  require(value.isAfter(LocalDateTime.now()))
}
case class 合計金額(value: Money)
case class 請求金額(value: Money)

case class 飲み会(name: 飲み会名, dateTime: 開催日時, members: 全参加者, totalAmounts: 合計金額, allPaymentRatio: 支払割合) {
  private val sumPaymentRatio: Double =
    members.value.map(m => allPaymentRatio.支払区分から支払割合に変換(m.paymentDivision).value).sum
  private val rateOfIncrease: Double = calculateRateOfIncrease(sumPaymentRatio, members.value.size)

  def 参加者追加(member: 参加者): 飲み会 = copy(members = members.追加(member))
  def 参加者削除(id: 参加者ID): 飲み会   = copy(members = members.削除(id))

  def 一人当たりの請求金額(id: 参加者ID): Option[請求金額] = {
    for {
      member <- members.value.find(_.id == id)
      paymentRatio          = allPaymentRatio.支払区分から支払割合に変換(member.paymentDivision).value
      increasedPaymentRatio = paymentRatio + rateOfIncrease
      avgAmounts            = totalAmounts.value / members.value.size
    } yield 請求金額(avgAmounts * increasedPaymentRatio)
  }

// 全員分の割り勘結果を計算して、参加者と請求金額の Map を返すのがあってもよさそう
//  def 参加者全員の請求金額(): Map[参加者ID, 請求金額] = {
//    members.value.map(m => 一人当たりの請求金額(m.id))
//  }

  private def calculateRateOfIncrease(sumPaymentRatio: Double, numberOfMembers: Int): Double =
    1 - (sumPaymentRatio / numberOfMembers)
}
