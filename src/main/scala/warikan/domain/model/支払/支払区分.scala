package warikan.domain.model.支払

sealed trait 支払区分

object 支払区分 {
  case object 多め  extends 支払区分
  case object 普通  extends 支払区分
  case object 少なめ extends 支払区分
}
