package net.aethersanctum.writerd

import org.mockito.Mockito.when
import org.scalatest.mock.MockitoSugar._

object TestUtil {
  /**
    * Create a fake random number generator that returns the numbers in order
    */
  def mockRandom(probs:Double*): (Unit) => Double = {
    val randomator = mock[Unit => Double]
    probs.foldLeft(when(randomator())) { (r, num) => r.thenReturn(num) }
    randomator
  }
}
