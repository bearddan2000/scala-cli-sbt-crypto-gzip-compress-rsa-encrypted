package example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

object GZIPCompression {

  @throws(classOf[IOException])
  def compress(str: String): Array[Byte] = {
    if ((str == null) || (str.length == 0)) {
      return null;
    }
    val obj: ByteArrayOutputStream = new ByteArrayOutputStream();
    val gzip: GZIPOutputStream = new GZIPOutputStream(obj);
    gzip.write(str.getBytes());
    gzip.flush();
    gzip.close();
    return obj.toByteArray();
  }

  @throws(classOf[IOException])
  def decompress(compressed: Array[Byte]): String = {
    val outStr: StringBuilder = new StringBuilder();
    if ((compressed == null) || (compressed.size == 0)) {
      return "";
    }
    val gis: GZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(compressed));
    val bufferedReader: BufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
    return Iterator.continually(bufferedReader.readLine()).takeWhile(_ != null).mkString
  }
}
