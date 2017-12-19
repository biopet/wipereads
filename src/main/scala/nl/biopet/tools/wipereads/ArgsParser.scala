/*
 * Copyright (c) 2014 Sequencing Analysis Support Core - Leiden University Medical Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools.wipereads

import java.io.File

import nl.biopet.utils.tool.{AbstractOptParser, ToolCommand}

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {

  head(s"""
          |${toolCommand.toolName} - Region-based reads removal from an indexed BAM file
      """.stripMargin)

  opt[File]('I', "input_file") required () valueName "<bam>" action { (x, c) =>
    c.copy(inputBam = x)
  } validate { x =>
    if (x.exists) success else failure("Input BAM file not found")
  } text "Input BAM file"

  opt[File]('r', "interval_file") required () valueName "<bed/gtf/refflat>" action {
    (x, c) =>
      c.copy(targetRegions = x)
  } validate { x =>
    if (x.exists) success else failure("Target regions file not found")
  } text "Interval BED file"

  opt[File]('o', "output_file") required () valueName "<bam>" action {
    (x, c) =>
      c.copy(outputBam = x)
  } text "Output BAM file"

  opt[File]('f', "discarded_file") optional () valueName "<bam>" action {
    (x, c) =>
      c.copy(filteredOutBam = Some(x))
  } text "Discarded reads BAM file (default: none)"

  opt[Int]('Q', "min_mapq") optional () action { (x, c) =>
    c.copy(minMapQ = x)
  } text "Minimum MAPQ of reads in target region to remove (default: 0)"

  opt[String]('G', "read_group") unbounded () optional () valueName "<rgid>" action {
    (x, c) =>
      c.copy(readGroupIds = c.readGroupIds + x)
  } text "Read group IDs to be removed (default: remove reads from all read groups)"

  opt[Unit]("limit_removal") optional () action { (_, c) =>
    c.copy(limitToRegion = true)
  } text
    "Whether to remove multiple-mapped reads outside the target regions (default: yes)"

  opt[Unit]("make_index") optional () action { (_, c) =>
    c.copy(makeIndex = false)
  } text
    "Whether to index output BAM file (default: no)"

  note("\nGTF-only options:")

  opt[String]('t', "feature_type") optional () valueName "<gtf_feature_type>" action {
    (x, c) =>
      c.copy(featureType = x)
  } text "GTF feature containing intervals (default: exon)"

  note("\nAdvanced options:")

  opt[Long]("bloom_size") optional () action { (x, c) =>
    c.copy(bloomSize = x)
  } text "Expected maximum number of reads in target regions (default: 7e7)"

  opt[Double]("false_positive") optional () action { (x, c) =>
    c.copy(bloomFp = x)
  } text "False positive rate (default: 4e-7)"

  note(
    """
         |This tool will remove BAM records that overlaps a set of given regions.
         |By default, if the removed reads are also mapped to other regions outside
         |the given ones, they will also be removed.
       """.stripMargin)

}
