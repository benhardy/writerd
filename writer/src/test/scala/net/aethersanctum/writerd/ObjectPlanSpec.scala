package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{NOUN, ADVERB, ADJECTIVE}

class ObjectPlanSpec extends BaseSpec {

  import TestUtil.mockRandom

  describe("an ObjectPlan") {
    describe("when having no chance of making adverb or adjective") {
      it("makes neither adverb nor adjective") {
        val chances = ObjectPlan.Chances(adjectiveAllowed = 0, adverbAllowed = 0)
        implicit val randomizer = TestUtil.mockRandom(0.999, 0.999)

        implicit val simpleCorpus = Corpus(
          ADVERB -> WordQueue("really"),
          ADJECTIVE -> WordQueue("big"),
          NOUN -> WordQueue("dog")
        )
        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "dog"
      }
    }
    describe("when having a chance to make adjective") {
      val chances = ObjectPlan.Chances(adjectiveAllowed = 0.5, adverbAllowed = 0)
      describe("when randomness favours adjective") {
        it("will use adjective") {
          implicit val randomizer = TestUtil.mockRandom(0.3, 0.999)

          implicit val simpleCorpus = Corpus(
            ADVERB -> WordQueue("really"),
            ADJECTIVE -> WordQueue("big"),
            NOUN -> WordQueue("dog")
          )
          val plan = ObjectPlan(chances)
          val result = plan.write(new StringBuilder).toString()
          result should be === "big dog"
        }
      }
      describe("when randomness does not favour adjective") {
        it("will not adjective") {
          implicit val randomizer = TestUtil.mockRandom(0.7, 0.999)

          implicit val simpleCorpus = Corpus(
            ADVERB -> WordQueue("really"),
            ADJECTIVE -> WordQueue("big"),
            NOUN -> WordQueue("dog")
          )
          val plan = ObjectPlan(chances)
          val result = plan.write(new StringBuilder).toString()
          result should be === "dog"
        }
      }
    }
    describe("adverb handling") {
      it("will do both when allowed") {
        val chances = ObjectPlan.Chances(adjectiveAllowed = 0.5, adverbAllowed = 0.5)
        implicit val randomizer = TestUtil.mockRandom(0.1, 0.1)

        implicit val simpleCorpus = Corpus(
          ADVERB -> WordQueue("really"),
          ADJECTIVE -> WordQueue("big"),
          NOUN -> WordQueue("dog")
        )
        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "really big dog"
      }
      it("won't do adverb without adjective") {
        val chances = ObjectPlan.Chances(adjectiveAllowed = 0.5, adverbAllowed = 0.5)
        implicit val randomizer = TestUtil.mockRandom(0.9, 0.1)

        implicit val simpleCorpus = Corpus(
          ADVERB -> WordQueue("really"),
          ADJECTIVE -> WordQueue("big"),
          NOUN -> WordQueue("dog")
        )
        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "dog"
      }
    }

  }
}
