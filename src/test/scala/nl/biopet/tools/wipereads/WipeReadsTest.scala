package nl.biopet.tools.wipereads

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class WipeReadsTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      WipeReads.main(Array())
    }
  }
}
