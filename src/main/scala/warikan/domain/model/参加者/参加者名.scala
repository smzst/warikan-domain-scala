package warikan.domain.model.参加者

case class 参加者名(value: String) {
  require(value.length > 0)
}
