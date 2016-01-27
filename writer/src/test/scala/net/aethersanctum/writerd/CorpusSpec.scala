package net.aethersanctum.writerd

import net.aethersanctum.writerd.TestUtil.mockRandom
import net.aethersanctum.writerd.WordKind.VERB
import org.scalatest.ShouldMatchers

class CorpusSpec extends BaseSpec with ShouldMatchers {
  describe("A Corpus") {
    it("uses WordQueue to make suggestions") {
      implicit val randomizer = mockRandom(0.8, 0.1, 0.5)
      val corpus = Corpus(
        VERB -> WordQueue("eat", "drink", "smell")
      )
      corpus.suggest(VERB) should be === Suggestion("smell")
      corpus.suggest(VERB) should be === Suggestion("eat")
      corpus.suggest(VERB) should be === Suggestion("drink")
    }
  }
}
