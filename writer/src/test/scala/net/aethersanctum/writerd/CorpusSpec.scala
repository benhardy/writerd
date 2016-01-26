package net.aethersanctum.writerd

import org.scalatest.ShouldMatchers
import org.scalatest.mock.MockitoSugar._
import org.mockito.Mockito.when

import net.aethersanctum.writerd.WordKind.VERB

class CorpusSpec extends BaseSpec with ShouldMatchers {
  describe("A Corpus") {
    def makeVerbQueue: WordQueue = {
      // make randomness not-random for test
      implicit val randomizer: (Unit) => Double = mock[Unit => Double]
      when(randomizer.apply())
        .thenReturn(0.8)
        .thenReturn(0.1)
        .thenReturn(0.5)

      WordQueue("eat", "drink", "smell")
    }
    it("uses WordQueue to make suggestions") {
      val corpus = Corpus(
        VERB -> makeVerbQueue
      )
      corpus.suggest(VERB) should be === Suggestion("smell")
      corpus.suggest(VERB) should be === Suggestion("eat")
      corpus.suggest(VERB) should be === Suggestion("drink")
    }
  }
}
