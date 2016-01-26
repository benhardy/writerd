package net.aethersanctum.writerd

case class Suggestion(word: Word)

class WordQueue(val randomSource: Unit=>Double, val words: List[Word]) {
  def suggest = Suggestion(words((words.size * randomSource()).toInt))

}

object WordQueue {
  def apply(words: Word*)(implicit randomSource: Unit=>Double) = {
    new WordQueue(randomSource, words.toList)
  }
}
