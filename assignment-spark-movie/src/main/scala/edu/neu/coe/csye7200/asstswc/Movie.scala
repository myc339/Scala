package edu.neu.coe.csye7200.asstswc

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
/***
  * This is an example of spark word count.
  * Replace the ??? with appropriate implementation and run this file
  * with the argument "input/ScalaWiki.txt"
  * Write down the count of word "Scala" in your submission
  * You can find the implementation from https://spark.apache.org/examples.html
  */

object Movie extends App {

//  def wordCount(lines: RDD[String], separator: String) = {
//    lines.flatMap(_.split(separator))
//      .map((_, 1))
//      .reduceByKey(_ + _)
//      .sortBy(-_._2)
//  }
  def MovieAvg(df: DataFrame,spark:SparkSession) = {
    df.createOrReplaceTempView("movie")
    spark.sql("select movieId,avg(rating) from movie group by movieId  order by cast(movieId as Int) asc").rdd
  }
  def MovieStddev(df: DataFrame,spark:SparkSession) ={
    df.createOrReplaceTempView("movie")
    spark.sql("select movieId,stddev(rating) from movie group by movieId order by cast(movieId as Int) asc").rdd
  }
//  override def main(args: Array[String]) = {
//
//    val spark = SparkSession
//      .builder()
//      .appName("WordCount")
//      .master("local[*]")
//      .getOrCreate()
//
//    if (args.length>0) {
//      wordCount(spark.read.textFile(args.head).rdd," ").take(10).foreach(println(_))
//    }


}
