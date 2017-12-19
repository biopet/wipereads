package nl.biopet.tools.wipereads

import java.io.File

case class Args(inputBam: File = new File(""),
                targetRegions: File = new File(""),
                outputBam: File = new File(""),
                filteredOutBam: Option[File] = None,
                readGroupIds: Set[String] = Set.empty[String],
                minMapQ: Int = 0,
                limitToRegion: Boolean = false,
                makeIndex: Boolean = false,
                featureType: String = "exon",
                bloomSize: Long = 70000000,
                bloomFp: Double = 4e-7)
