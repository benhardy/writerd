package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{NOUN, ADJECTIVE, ADVERB}

class ObjectPlan(chances:ObjectPlan.Chances, corpus:Corpus, randomizer:(Unit)=>Double) extends Plan {
  val noun = corpus.suggest(NOUN)
  val doAdjective = randomizer() >= chances.adjectiveThreshold
  val adjective = if (doAdjective) Some(corpus.suggest(ADJECTIVE)) else None
  val adverb = if (doAdjective && randomizer() >= chances.adverbThreshold) Some(corpus.suggest(ADVERB)) else None

  override def write(buf:StringBuilder) = {
    adverb.foreach(adv => buf.append(adv).append(' '))
    adjective.foreach(adj => buf.append(adj).append(' '))
    buf.append(noun)
  }
}

object ObjectPlan {

  def apply(chances:ObjectPlan.Chances)(implicit randomizer:Unit=>Double, corpus:Corpus): ObjectPlan = {
    new ObjectPlan(chances, corpus, randomizer)
  }
  def apply()(implicit randomizer:Unit=>Double, corpus:Corpus):ObjectPlan = {
    new ObjectPlan(Chances(adjectiveThreshold = 0.7, adverbThreshold = 0.5), corpus, randomizer)
  }
  case class Chances(
                    adjectiveThreshold: Double,
                    adverbThreshold: Double,
                    nice: Boolean = false
                    ) {

  }
}
