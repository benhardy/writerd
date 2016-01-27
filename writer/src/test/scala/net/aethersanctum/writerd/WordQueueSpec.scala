package net.aethersanctum.writerd

class WordQueueSpec extends BaseSpec {
  describe("a WordQueue") {
    it("should make suggestions") {
      implicit val randomizer = TestUtil.mockRandom(0.8, 0.1, 0.5)

      val q = WordQueue("a", "b", "c")
      q.suggest should be === Suggestion("c")
      q.suggest should be === Suggestion("a")
      q.suggest should be === Suggestion("b")
    }
  }
}
