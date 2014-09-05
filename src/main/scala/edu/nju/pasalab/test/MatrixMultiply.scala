package edu.nju.pasalab.test

import com.esotericsoftware.kryo.Kryo
import edu.nju.pasalab.sparkmatrix.{BlockID, IndexRow, IndexMatrix, MTUtils}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.serializer.KryoRegistrator
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Two large matrices multiply
 */
object MatrixMultiply {

  /**
   * Function :Two large matrices multiply
   *
   * @param args args(0):<input file path A> args(1)<input file path B> args(2)<output file path> args(3)<block num>
   */
   def main (args: Array[String]) {
    val conf = new SparkConf().setAppName("FileMatrixMultiply")
     val sc = new SparkContext(conf)
     val ma = MTUtils.loadMatrixFile(sc,args(0))
     val mb = MTUtils.loadMatrixFile(sc,args(1))
     val result =  ma.multiply(mb, args(3).toInt )
     result.saveToFileSystem(args(2))
     sc.stop()

  }

}
