package warikan.domain.model.支払

case class 割合(value: Double) {
  require(value > 0)
}

case class 支払割合(多め: 割合 = 割合(1.2), 普通: 割合 = 割合(1.0), 少なめ: 割合 = 割合(0.8)) {
  require(多め.value > 普通.value)
  require(普通.value > 少なめ.value)

  def 支払区分から支払割合に変換(paymentDivision: 支払区分): 割合 = paymentDivision match {
    case 支払区分.多め  => this.多め
    case 支払区分.普通  => this.普通
    case 支払区分.少なめ => this.少なめ
  }
}
