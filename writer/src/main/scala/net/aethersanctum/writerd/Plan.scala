package net.aethersanctum.writerd

trait Plan {
  def write(buf:StringBuilder): StringBuilder
}
