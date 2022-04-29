import scala.annotation.tailrec

object MyClass {
  def add(x:Int, y:Int): Int = x + y

  def factorial(x: Int): Int = {
    @tailrec
    def loop(acc: Int, c: Int): Int = {
      c match {
        case 0 => acc
        case _ => loop(acc * c, c - 1)
      }
    }
    loop(1, x)
  }

  def factorialIf(x: Int): Int = {
    @tailrec
    def loop(acc: Int, c: Int): Int =
      if (c == 0) acc else loop(acc * c, c - 1)
    loop(1, x)
  }

  def measure(e: (Int) => Int, arg:Int, numIters: Int): Long = {
    @tailrec
    def loop(max: Int): Unit = {
      if (max == 0)
        return
      else {
        val x = e(arg)
        loop(max-1)
      }
    }

    val startMatch = System.nanoTime()
    loop(numIters)
    System.nanoTime() - startMatch
  }

  def main(args: Array[String]): Unit = {
    val timeIf = measure(factorialIf, 1000,1000000)
    val timeMatch = measure(factorial, 1000,1000000)
    print(s"measure if : ${timeIf}\n measure match : ${timeMatch}")
  }
}