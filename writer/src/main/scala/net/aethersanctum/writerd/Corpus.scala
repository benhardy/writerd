package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{ADVERB, ADJECTIVE, NOUN, VERB}
import net.aethersanctum.writerd.WordQueue.loadResource

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
  def suggest(kind:WordKind, filter:Word=>Boolean): Suggestion
}

object Corpus {
  def apply(items: (WordKind, WordQueue)*): Corpus = apply(items.toMap)

  def apply(map:Map[WordKind, WordQueue]): Corpus = {
    new Corpus {
      override def suggest(kind: WordKind): Suggestion = {
        map(kind).suggest
      }
      override def suggest(kind: WordKind, filter: Word=>Boolean): Suggestion = {
        map(kind).suggest(filter)
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
    NOUN -> loadResource("nouns-negative.json", "nouns-neutral-positive.json"),
    ADJECTIVE -> loadResource("adjectives-negative.json"),
    ADVERB -> loadResource("adverbs.json")
  )
}
