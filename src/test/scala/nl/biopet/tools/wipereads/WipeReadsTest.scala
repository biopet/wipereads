package nl.biopet.tools.wipereads

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object WipeReadsTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ToolTemplate.main(Array())
    }
  }
}
