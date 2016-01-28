package net.aethersanctum.writerd

import scala.annotation.tailrec

case class Suggestion(word: Word)

class WordQueue(val randomSource: Unit=>Double, val words: List[Word]) {
  def suggest = Suggestion(words((words.size * randomSource()).toInt))

  def suggest(filter: Word=>Boolean): Suggestion = {
    @tailrec
    def withFilter(count:Int): Suggestion = {
      val s = suggest
      if (filter(s.word) || count <= 0) {
        s
      } else {
        withFilter(count - 1)
      }
    }
    withFilter(50)
  }
}

object WordQueue {
  def apply(words: Word*)(implicit randomSource: Unit=>Double) = {
    new WordQueue(randomSource, words.toList)
  }
}
