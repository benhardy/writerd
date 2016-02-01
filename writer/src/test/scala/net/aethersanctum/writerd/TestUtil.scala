package net.aethersanctum.writerd

import org.mockito.Mockito.when
import org.scalatest.mock.MockitoSugar._

object TestUtil {
  /**
    * Create a fake random number generator that returns the numbers in order
    */
  def mockRandom(firstProb:Double, probs:Double*): (Unit) => Double = {
    val randomator = mock[Unit => Double]
    when(randomator.apply()).thenReturn(firstProb, probs:_*)
    randomator
  }
}
