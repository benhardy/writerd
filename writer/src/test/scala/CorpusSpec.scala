package net.aethersanctum.writerd

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    it("can be created") {
      val c = new Corpus
      c should not be null
    }
  }
}
