package services

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
/**
  * Created by akash on 9/4/16.
  */
object ConnectToDB {

  val driver = new reactivemongo.api.MongoDriver
  val connection = driver.connection(List("localhost"))
  val db = Await.result(connection.database("user"),10.seconds)

}
