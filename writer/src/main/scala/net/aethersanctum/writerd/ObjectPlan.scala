package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{NOUN, ADJECTIVE, ADVERB}

class ObjectPlan(chances:ObjectPlan.Chances, corpus:Corpus, randomizer:(Unit)=>Double) extends Plan {

  override def write(buf:StringBuilder) = {
    if (randomizer() < chances.adjectiveAllowed) {
      if (randomizer() < chances.adverbAllowed) {
        buf.append(corpus.suggest(ADVERB).word.appearance).append(' ')
      }
      buf.append(corpus.suggest(ADJECTIVE).word.appearance).append(' ')
    }
    buf.append(corpus.suggest(NOUN).word.appearance)
  }
}

object ObjectPlan {

  def apply(chances:ObjectPlan.Chances)(implicit randomizer:Unit=>Double, corpus:Corpus): ObjectPlan = {
    new ObjectPlan(chances, corpus, randomizer)
  }
  case class Chances(
                    adjectiveAllowed: Double,
                    adverbAllowed: Double
                    ) {

  }
}
