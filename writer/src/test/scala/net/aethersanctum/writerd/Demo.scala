package net.aethersanctum.writerd

import net.aethersanctum.writerd.WordKind.NOUN
import net.aethersanctum.writerd.objectplan.ObjectPlan

import scala.StringBuilder

object Demo {
  def main(args:Array[String]): Unit = {
    implicit val corpus = Corpus.default
    implicit val randomizer:Unit=>Double = (Unit) => math.random
    println("suggest loaded word: " + corpus.suggest(NOUN))
    (0 until 5).foreach { n =>
      val thing = ObjectPlan().write(new StringBuilder).toString
      println(s"suggest object ${n}: ${thing}")
    }
  }
}
