package net.aethersanctum.writerd

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    describe("can be created") {
      val c = new Corpus
      c should not be null
    }
  }
}
