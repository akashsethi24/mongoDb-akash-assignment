package models


import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument
import services.ConnectToDB
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by akash on 9/4/16.
  */
case class User (id : Int , name:String , password: String , email : String)

class UserOperation {

  val collection = ConnectToDB.db.collection[BSONCollection]("user_details")

  def register(user: User):Boolean = {

    val query = BSONDocument("email"->user.email)
    val isUser = Await.result(collection.find(query).one[BSONDocument],10.seconds)
    if(isUser.isDefined){
      false
    }
    else{
      try {
        val userBson = BSONDocument("id" -> user.id, "name" -> user.name, "password" -> user.password, "email" -> user.email)
        collection.insert(userBson)
        true
      }
      catch {
        case exception:Exception => false
      }
    }
  }

  def login(email:String,password:String) : Boolean = {

    val query = BSONDocument("email"->email,"password"->password)
    val userDetails = Await.result(collection.find(query).one[BSONDocument],10.seconds)
    if(userDetails.isDefined) {
      true
    }
    else {
      false
    }
  }

}