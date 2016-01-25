package net.aethersanctum.writerd

class WordQueueSpec extends BaseSpec {
  describe("a WordQueue") {
    it("should make suggestions") {
      val q = new WordQueue(List("a", "b", "c"))
      val s = q.suggest
      q.words should contain (s.word)
    }
  }
}
