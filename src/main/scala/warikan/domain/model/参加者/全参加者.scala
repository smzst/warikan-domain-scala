package warikan.domain.model.参加者

case class 全参加者(value: Set[参加者]) {
  require(value.nonEmpty)

  def 追加(member: 参加者): 全参加者 = copy(value = value + member)
  def 削除(id: 参加者ID): 全参加者   = copy(value = value.filter(_.id != id))
}
