package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.{ADVERB, ADJECTIVE, NOUN, VERB}

case class Word(appearance:String)

sealed trait WordKind

object WordKind {
  case object VERB extends WordKind
  case object NOUN extends WordKind
  case object ADVERB extends WordKind
  case object ADJECTIVE extends WordKind
}

trait Corpus {
  def suggest(kind:WordKind): String
}

object Corpus {
  def apply = {
    new Corpus {
      override def suggest(kind: WordKind): String = {
        kind match {
          case VERB => "run"
          case NOUN => "dog"
          case ADJECTIVE => "hungry"
          case ADVERB => "quickly"
        }
      }
    }
  }
}
