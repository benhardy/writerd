package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.VERB

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    it("can provide verbs") {
      val corpus = Corpus.apply
      corpus.suggest(VERB) should be === "run"
    }
  }
}
