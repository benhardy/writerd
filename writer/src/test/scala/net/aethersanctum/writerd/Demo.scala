package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.NOUN

object Demo {
  def main(args:Array[String]): Unit = {
    val corpus = Corpus.default
    println("suggest loaded word: " + corpus.suggest(NOUN))
    0
  }
}
