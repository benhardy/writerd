package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.VERB

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    it("can make suggestions") {
      val corpus = Corpus.apply
      val suggestion: Suggestion = corpus.suggest(VERB)
    }
  }
}
