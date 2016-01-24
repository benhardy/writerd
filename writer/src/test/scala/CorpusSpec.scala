package net.aethersanctum.writerd

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class CorpusSpec extends BaseSpec {
  describe("A Corpus") {
    describe("can be created") {
      val c = new Corpus
      c should not be null
    }
  }
}
