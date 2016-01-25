package net.aethersanctum.writerd

case class Suggestion(word: Word)

class WordQueue(val words: List[Word]) {
  def suggest = Suggestion(words((words.size * math.random).toInt))
}
