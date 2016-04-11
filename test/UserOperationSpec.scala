import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
/**
  * Created by akash on 11/4/16.
  */
@RunWith(classOf[JUnitRunner])
class UserOperationSpec extends Specification{

  "Application" should {

    "Return 200 on Submit Valid Data" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitLogin").withFormUrlEncodedBody("username" -> "akash@gmail.com", "password" -> "akash")).get
      status(input) must equalTo(200)
      contentAsString(input) must contain("Login")
    }

    "Return 200 on Submit with Invalid  password" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitLogin").withFormUrlEncodedBody("username" -> "akash@gmail.com", "password" -> "Random")).get
      status(input) must equalTo(200)
    }


    "Return 400 on Invalid Form" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitLogin").withFormUrlEncodedBody("username" -> "akash@gmail.com", "password" -> "")).get
      status(input) must  equalTo(BAD_REQUEST)
    }

    "Return 400 on Invalid Form with No Email" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitLogin").withFormUrlEncodedBody("username" -> "", "password" -> "akash")).get
      status(input) must  equalTo(BAD_REQUEST)
    }

    "Return 200 On Valid Registration" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitRegistration").withFormUrlEncodedBody("id" -> "3","name" -> "Akash Sethi", "password" -> "akash" , "email" -> "AkashSethi@hotmail.com")).get
      status(input) must equalTo(200)
    }

    "Email Already Exits" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitRegistration").withFormUrlEncodedBody("id" -> "4","name" -> "Akash Sethi", "password" -> "akash" , "email" -> "AkashSethi@hotmail.com")).get
      status(input) must equalTo(200)
    }

    "Bad Form With no email" in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitRegistration").withFormUrlEncodedBody("id" -> "3","name" -> "Akash Sethi", "password" -> "akash" , "email" -> "")).get
      status(input) must equalTo(400)
    }

    "Form With No Password " in new WithApplication() {
      val input = route(FakeRequest(POST, "/submitRegistration").withFormUrlEncodedBody("id" -> "3","name" -> "Akash Sethi", "password" -> "" , "email" -> "AkashSethi@hotmail.com")).get
      status(input) must equalTo(400)
    }

  }
}
