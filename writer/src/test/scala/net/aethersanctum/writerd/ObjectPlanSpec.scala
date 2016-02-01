package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{NOUN, ADVERB, ADJECTIVE}
import net.aethersanctum.writerd.objectplan.ObjectPlan

class ObjectPlanSpec extends BaseSpec {

  import TestUtil.mockRandom

  implicit val simpleCorpus = Corpus(
    ADVERB -> WordQueue("really")(Unit=>0),
    ADJECTIVE -> WordQueue("big")(Unit=>0),
    NOUN -> WordQueue("dog")(Unit=>0)
  )

  describe("an ObjectPlan") {
    describe("when having no chance of making adverb or adjective") {
      it("makes neither adverb nor adjective") {
        val chances = ObjectPlan.Chances(adjectiveThreshold = 0.5, adverbThreshold = 0.5)
        implicit val randomizer = TestUtil.mockRandom(0.1, 0.1)
        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "dog"
      }
    }

    describe("when having a chance to make adjective") {
      val chances = ObjectPlan.Chances(adjectiveThreshold = 0.5, adverbThreshold = 0.5)

      describe("when randomness favours adjective") {
        it("will use adjective") {
          implicit val randomizer = TestUtil.mockRandom(0.7, 0.0)
          val plan = ObjectPlan(chances)
          val result = plan.write(new StringBuilder).toString()
          result should be === "big dog"
        }
      }

      describe("when randomness does not favour adjective") {
        it("will not adjective") {
          implicit val randomizer = TestUtil.mockRandom(0.1, 0.0)
          val plan = ObjectPlan(chances)
          val result = plan.write(new StringBuilder).toString()
          result should be === "dog"
        }
      }

    }
    describe("adverb handling") {

      it("will do both when allowed") {
        val chances = ObjectPlan.Chances(adjectiveThreshold = 0.5, adverbThreshold = 0.5)
        implicit val randomizer = TestUtil.mockRandom(0.9, 0.9)

        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "really big dog"
      }

      it("won't do adverb without adjective") {
        val chances = ObjectPlan.Chances(adjectiveThreshold = 0.5, adverbThreshold = 0.5)
        implicit val randomizer:Unit=>Double = TestUtil.mockRandom(0.1, 0.9, 0.0)

        val plan = ObjectPlan(chances)
        val result = plan.write(new StringBuilder).toString()
        result should be === "dog"
      }
    }

  }
}
