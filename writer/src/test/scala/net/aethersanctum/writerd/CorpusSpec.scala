package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.VERB

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    it("can make suggestions") {
      val corpus = Corpus(
        VERB -> WordQueue("eat", "drink", "smell")
      )
      val suggestion: Suggestion = corpus.suggest(VERB)
    }
  }
}
