import java.io.File

import com.lcj.gradle.plugin.upload.scp.ScpTo
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lichangjiang on 18-2-11.
  */
class ScpToTest extends FlatSpec with Matchers {

  "ScpTo" should "send file to remote successfully" in {
    val scp = new ScpTo("lichangjiang","67.218.148.213",27448,"lcj890712",new File("/home/lichangjiang/Desktop/pg35.txt"),"/home/lichangjiang")
    scp.execute()
  }
}
