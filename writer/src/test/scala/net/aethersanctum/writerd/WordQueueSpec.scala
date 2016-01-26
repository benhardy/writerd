package net.aethersanctum.writerd

import org.mockito.Mockito.when
import org.scalatest.mock.MockitoSugar._

class WordQueueSpec extends BaseSpec {
  describe("a WordQueue") {
    it("should make suggestions") {
      implicit val randomizer: (Unit)=>Double = mock[Unit=>Double]
      when(randomizer.apply())
        .thenReturn(0.8)
        .thenReturn(0.1)
        .thenReturn(0.5)

      val q = WordQueue("a", "b", "c")
      q.suggest.word should be === Word("c")
      q.suggest.word should be === Word("a")
      q.suggest.word should be === Word("b")
    }
  }
}
