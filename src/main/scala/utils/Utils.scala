package utils

import java.io.File

object Utils {

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def isSourceFolderEmpty(source: String): Boolean = {
    return if (getListOfFiles(source).isEmpty) true else false
  }
}