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

    it("can do filtering") {
      implicit val randomizer = TestUtil.mockRandom(0.01, 0.11, 0.21, 0.31, 0.41, 0.51, 0.61, 0.71, 0.81, 0.91)

      val q = WordQueue("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")

      def filter(w:Word) = w.appearance == "g"

      q.suggest(filter) should be === Suggestion("g")
    }
  }
}
