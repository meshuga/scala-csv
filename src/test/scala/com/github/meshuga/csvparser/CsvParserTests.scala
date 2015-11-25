package com.github.meshuga.csvparser

import org.scalatest.FunSuite

import scala.io.Source

class CsvParserTests extends FunSuite {

  test("the returned data is defined") {
    val input = Source.fromFile("src/test/resources/commaSimple.csv")
    val sut = new CsvParser(",")

    val maybeLines = sut.parse(input)

    assert(maybeLines.isDefined)
    assert(maybeLines.get.header.isDefined)
    assert(maybeLines.get.header.get.csvHeaders == List("a", "b"))
    assert(maybeLines.get.lines.head.lineValues == List("1", "asd"))
  }

  test("the returned data have no headers") {
    val input = Source.fromFile("src/test/resources/commaSimple.csv")
    val sut = new CsvParser(",", headerPresent = false)

    val maybeLines = sut.parse(input)

    assert(maybeLines.isDefined)
    assert(maybeLines.get.header.isEmpty)
    assert(maybeLines.get.lines.size == 2)
    assert(maybeLines.get.lines.head.lineValues == List("a", "b"))
  }

  test("values for a column are returned") {
    val sut = CsvFile(None, List(CsvLine(List("1", "2"))))

    val values = sut.columnValues(1)

    assert(values == List("2"))
  }
}
