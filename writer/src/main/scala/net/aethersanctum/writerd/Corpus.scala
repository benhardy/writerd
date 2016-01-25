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
}

trait Corpus {
  def suggest(kind:WordKind): Suggestion
}

object Corpus {
  def apply = {
    new Corpus {
      import Word.toWord
      val queues = Map[WordKind, WordQueue](
        VERB -> new WordQueue(List("eat", "lick", "enjoy")),
        NOUN -> new WordQueue(List("dog", "cat", "mouse")),
        ADVERB -> new WordQueue(List("greedibly", "quickly", "lazily")),
        ADJECTIVE -> new WordQueue(List("gluttonous", "fast", "big"))
      )
      override def suggest(kind: WordKind): Suggestion = {
        queues(kind).suggest
      }
    }
  }
}
