
import config.Environment
import org.scalatest._

class Tests extends FlatSpec with Matchers {

  it should "return non-null String of source Json folder value" in {

    Environment.getJsonSourceFolder() shouldNot be(null)
  }

  it should "catching an Load Data Exception" in {
    a[Exception] should be thrownBy {
      throw new Exception("It's just only a test..")
    }
  }
}