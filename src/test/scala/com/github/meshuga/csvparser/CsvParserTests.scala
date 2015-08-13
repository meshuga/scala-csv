package com.github.meshuga.csvparser

import org.scalatest.FunSuite

class CsvParserTests extends FunSuite {

  test("the returned data is defined") {
    import scala.io.Source
    val input = Source.fromFile("src/test/resources/commaSimple.csv").mkString

    val sut = new CsvParser(",")

    val maybeLines: Option[List[CsvLine]] = sut.parse(input)

    assert(maybeLines.isDefined)
  }
}
