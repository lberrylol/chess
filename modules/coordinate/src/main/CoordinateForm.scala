package lila.coordinate

import play.api.data.*
import play.api.data.Forms.*
import chess.Color

object CoordinateForm:

  val color = Form(
    single(
      "color" -> number(min = 1, max = 3)
    )
  )

  val score = Form(
    mapping(
      "mode" -> lila.common.Form
        .trim(text)
        .verifying(CoordMode.all.map(_.key).contains)
        .transform[CoordMode](m => CoordMode.all.find(_.key == m).get, _.key),
      "color" -> lila.common.Form.color.mapping,
      "score" -> number(min = 0, max = 100)
    )(ScoreData.apply)(unapply)
  )

  case class ScoreData(mode: CoordMode, color: Color, score: Int)
