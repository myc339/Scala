package edu.neu.coe.csye7200.asstswc

import org.apache.spark.sql.SparkSession
import org.scalatest.tagobjects.Slow
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.apache.spark.sql._
class MovieSpec extends FlatSpec with Matchers with BeforeAndAfter  {

  implicit var spark: SparkSession = _

  before {
    spark = SparkSession
      .builder()
      .appName("WordCount")
      .master("local[*]")
      .getOrCreate()
  }

  after {
    if (spark != null) {
      spark.stop()
    }
  }

  behavior of "Spark"

//  it should "work for wordCount" taggedAs Slow in {
//    Movie.wordCount(spark.read.textFile(getClass.getResource("WordCount.txt").getPath).rdd," ").collect() should matchPattern {
//      case Array(("Hello",3),("World",3),("Hi",1)) =>
//    }
//  }


  it should "size should be 134" taggedAs Slow in{
    Movie.MovieAvg(spark.read.format("csv").option("header", "true").load((getClass.getResource("rating.csv").getPath)),spark).collect().size should matchPattern{
      case 134=>
    }

  }
  it should "work for avg" taggedAs Slow in {
    Movie.MovieAvg(spark.read.format("csv").option("header", "true").load((getClass.getResource("rating.csv").getPath)),spark).collect().take(4).map{case Row(k:String,v:Double)=>k->v} should matchPattern{
      case Array(("1",3.9209302325581397),("2",3.4318181818181817), ("6",3.946078431372549), ("10",3.496212121212121))=>
    }
  }
  it should "work for movieIgestion Std" taggedAs Slow in {
    Movie.MovieStddev(spark.read.format("csv").option("header", "true").load((getClass.getResource("rating.csv").getPath)), spark).collect().take(4).map{case Row(k:String,v:Double)=>k->v} should matchPattern {
     case  Array(("1",0.8348591407114045), ("2",0.8817134921476453), ("6",0.8172244221533347), ("10",0.8593806844280277))=>
//        , (2, 0.882), (6, 0.817), (10, 0.859)
    }
  }

}

