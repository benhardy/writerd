package net.aethersanctum.writerd

import java.lang.Math.random

object WordUtils {
  def randomSample(items: Array[String]): String = {
    items((random * items.length).toInt)
  }

  def randomSample[T](items: IndexedSeq[T]): T = {
    items((random * items.size).toInt)
  }

  def pluralizedNoun(noun: String): String = {
    if (noun.endsWith("man")) {
      noun.substring(0, noun.length - 3) + "men"
    } else
    if (noun.endsWith("s") || noun.endsWith("z") || noun.endsWith("x") || noun.endsWith("ch") || noun.endsWith("sh")) {
      noun + "es"
    }
    else if (noun.endsWith("y") && !isVowel(secondLast(noun))) {
      noun.substring(0, noun.length - 1) + "ies"
    } else {
      noun
    }
  }

  def startsWithVowel(word: String): Boolean = {
    isVowel(word.charAt(0))
  }

  def isVowel(ch: Char): Boolean = {
    "aeiou".indexOf(ch) >= 0
  }
/*
  def appropriateArticle(plan: Plan): String = {
    return appropriateArticle(plan.getFirstWord)
  }

  def appropriateArticle(firstWord: Suggestion[Word]): String = {
    val vowely: Boolean = firstWord.filter(w -> startsWithVowel(w.getWord())).isAvailable
    return if (vowely) "an" else "a"
  }

  def tenseModifiedVerb(verbWord: Word, tenseInUse: VerbPlan.Tense, subject: SubjectPlan): String = {
    val buf: StringBuilder = new StringBuilder(verbWord.toString)
    if (tenseInUse eq PRESENT && !subject.resemblesPluralForVerbs) {
      alterForPresentTense(verbWord, buf)
    }
    else if (tenseInUse eq PAST) {
      alterForPastTense(verbWord, buf)
    }
    return buf.toString
  }

  private def alterForPresentTense(verbWord: Word, buf: StringBuilder) {
    val fixedY: Boolean = tenseTransformVerbEndingInY(verbWord, buf)
    if (!fixedY && verbWord.getWord.endsWith("ss")) {
      buf.append("e")
    }
    buf.append('s')
  }

  private def alterForPastTense(verbWord: Word, buf: StringBuilder) {
    val otherForm: Optional[String] = verbWord.getAttribute("pt")
    if (otherForm.isPresent) {
      buf.setLength(0)
      buf.append(otherForm.get)
      return
    }
    val fixedY: Boolean = tenseTransformVerbEndingInY(verbWord, buf)
    val needsE: Boolean = !verbWord.getWord.endsWith("e") || verbWord.getWord.endsWith("ss")
    if (needsE && !fixedY) {
      buf.append("e")
    }
    buf.append("d")
  }

  def tenseTransformVerbEndingInY(verbWord: Word, buf: StringBuilder): Boolean = {
    if (verbWord.getWord.endsWith("y") && !isVowel(secondLast(verbWord.getWord))) {
      buf.setLength(buf.length - 1)
      buf.append("ie")
      return true
    }
    return false
  }
*/
  private def secondLast(word: String): Char = {
    return word.charAt(word.length - 2)
  }
/*
  def writeWithTrailingSpace(plan: Plan, insult: StringBuilder): StringBuilder = {
    val pos: Int = insult.length
    val afterPlan: StringBuilder = plan.write(insult)
    if (afterPlan.length <= pos) {
      return afterPlan
    }
    return afterPlan.append(" ")
  }

  def writeWithTrailingSpace(word: Suggestion[Word], insult: StringBuilder): StringBuilder = {
    val pos: Int = insult.length
    val afterPlan: StringBuilder = insult.append(word.toString)
    if (afterPlan.length <= pos) {
      return afterPlan
    }
    return afterPlan.append(" ")
  }
*/
  def ingTheVerb(word: String): String = {
    val lastIndex = word.length - 1
    val last = word.charAt(lastIndex)
    if (word.length >= 3 && last != 'y') {
      val secondLast = word.charAt(lastIndex - 1)
      val secondLastIsVowel = isVowel(secondLast)
      if (last == 'e' && !secondLastIsVowel) {
        return word.substring(0, lastIndex) + "ing"
      }
      // double up?
      if (secondLastIsVowel && !isVowel(last)) {
        val thirdLast = word.charAt(lastIndex - 2)
        if (!isVowel(thirdLast) && secondLast != 'e') {
          return word + last + "ing"
        }
      }

    } else {
      return word + "ing"
    }
    word + "ing"
  }
}
