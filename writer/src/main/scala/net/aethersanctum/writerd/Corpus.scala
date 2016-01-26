package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{ADVERB, ADJECTIVE, NOUN, VERB}

case class Word(appearance:String)

object Word {
  implicit def toWord(str: String): Word = Word(str)
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

  def example: Corpus = apply(
    VERB -> new WordQueue(List("eat", "lick", "enjoy")),
    NOUN -> new WordQueue(List("dog", "cat", "mouse")),
    ADVERB -> new WordQueue(List("greedibly", "quickly", "lazily")),
    ADJECTIVE -> new WordQueue(List("gluttonous", "fast", "big"))
  )
}
