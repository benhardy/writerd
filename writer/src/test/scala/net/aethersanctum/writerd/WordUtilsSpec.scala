package net.aethersanctum.writerd

class WordUtilsSpec extends BaseSpec {
  describe("ingTheVerb") {
    import WordUtils.ingTheVerb
    it("should be able to do simple appends") {
      ingTheVerb("jump") should be === "jumping"
    }

    it("should be able to handle words ending in E") {
      ingTheVerb("strafe") should be === "strafing"
    }

    it("should know when to double up on consonants") {
      ingTheVerb("jam") should be === "jamming"
    }

    it("should handle words ending in two vowels followed by a consonant") {
      ingTheVerb("beam") should be === "beaming"
    }

    it("should just append ing to words ending in 'ee'") {
      ingTheVerb("free") should be === "freeing"
    }

    it("should also append ing to words ending in two matching vowels other than 'ee'") {
      ingTheVerb("shoo") should be === "shooing"
    }

    it("should just append ing to really short verbs without chopping anything off") {
      ingTheVerb("be") should be === "being"
      ingTheVerb("do") should be === "doing"
    }

    it("should just append ing to verbs ending in Y") {
      ingTheVerb("display") should be === "displaying"
    }

    it("shouldn't double up trailing consonants when preceded by an E") {
      ingTheVerb("interpret") should be === "interpreting"
      ingTheVerb("fillet") should be === "filleting"
    }
  }
}
