package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{ADVERB, ADJECTIVE, NOUN, VERB}

case class Word(word:String, attributes:Map[String,String])

object Word {
  implicit def toWord(str: String): Word = Word(str, Map())
}

sealed trait WordKind

object WordKind {
  case object VERB extends WordKind
  case object NOUN extends WordKind
  case object ADVERB extends WordKind
  case object ADJECTIVE extends WordKind

  val reverse = Map(
    "VERB" -> VERB,
    "NOUN" -> NOUN,
    "ADVERB" -> ADVERB,
    "ADJECTIVE" -> ADJECTIVE
  )

  def fromString(s:String) = reverse(s)
}

trait Corpus {
  def suggest(kind:WordKind): Suggestion
}

object Corpus {
  def apply(items: (WordKind, WordQueue)*): Corpus = apply(items.toMap)

  def apply(map:Map[WordKind, WordQueue]): Corpus = {
    new Corpus {
      override def suggest(kind: WordKind): Suggestion = {
        map(kind).suggest
      }
    }
  }
  implicit val randomSource: (Unit) => Double = (Unit) => math.random

  def example: Corpus = apply(
    VERB -> WordQueue("eat", "lick", "enjoy"),
    NOUN -> WordQueue("dog", "cat", "mouse"),
    ADVERB -> WordQueue("greedibly", "quickly", "lazily"),
    ADJECTIVE -> WordQueue("gluttonous", "fast", "big")
  )

  def default = Corpus(
    NOUN -> WordQueue.loadResource("nouns-negative.json")
  )
}
