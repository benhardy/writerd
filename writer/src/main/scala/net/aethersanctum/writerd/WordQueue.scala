package net.aethersanctum.writerd

import java.io.{BufferedReader, InputStreamReader}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

import scala.annotation.tailrec

case class Suggestion(word: Word) {
  override def toString = word.word.toString
}

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
  val objectMapper = new ObjectMapper with ScalaObjectMapper
  objectMapper.registerModule(DefaultScalaModule)

  def apply(words: Word*)(implicit randomSource: Unit=>Double) = {
    new WordQueue(randomSource, words.toList)
  }

  def loadResource(paths:String*)(implicit randomSource: Unit=>Double): WordQueue = {
    val combined = paths.map(resourceReader)
        .map(parseWordInput)
        .reduce(_ ++ _)
    new WordQueue(randomSource, combined)
  }

  def parseWordInput(res: BufferedReader): List[Word] = {
    objectMapper.readValue[List[Word]](res)
  }

  private def resourceReader(path: String): BufferedReader = {
    val corpus = this.getClass.getClassLoader.getResourceAsStream(path)
    if (corpus == null) {
      throw new IllegalArgumentException("Can't find resource " + path)
    }
    val reader = new InputStreamReader(corpus)
    new BufferedReader(reader)
  }
}
