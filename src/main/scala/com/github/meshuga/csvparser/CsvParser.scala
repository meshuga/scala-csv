package com.github.meshuga.csvparser

import scala.io.BufferedSource

class CsvParser(columnSeparator: String, headerPresent: Boolean = true) {
  def parse(input: BufferedSource): Option[CsvFile] = input.isEmpty match {
    case false =>
      val lines: Iterator[String] = input.getLines
      Some(CsvFile(
        if (headerPresent) Some(CsvHeader(lines.next().split(columnSeparator).toList)) else None,
        lines.map(line => CsvLine(line.split(columnSeparator).toList)).toList
      ))
    case true => None
  }
}

case class CsvLine(lineValues: List[String])

case class CsvHeader(csvHeaders: List[String])

case class CsvFile(header: Option[CsvHeader], lines: List[CsvLine]) {
  def columnValues(index: Int): List[String] =
    lines.map(line => line.lineValues(index))
}