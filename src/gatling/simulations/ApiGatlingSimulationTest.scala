// package gatling.simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit

class ApiGatlingSimulationTest extends Simulation {

  val userCount = 5

  val url = "http://localhost:8080/users"

  val scn = scenario("ServletStackApiTest").repeat(userCount, "n") {
    exec(
      http("AddUser-API")
        .post(url)
        .header("Content-Type", "application/json")
        .body(StringBody("""{"id":"user${n}","name":"wang${n}", "age":10}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(userCount, "n") {
    exec(
      http("GetUser-API")
        .get(url + "/user${n}")
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(1, "n") {
    exec(
      http("ListtUsers-API")
        .get(url)
        .check(status.is(200))
    )
  }.repeat(userCount, "n") {
    exec(
      http("UpdateUser-API")
        .put(url + "/user${n}")
        .header("Content-Type", "application/json")
        .body(StringBody("""{"id":"user${n}","name":"wang${n}", "age":20}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(userCount, "n") {
    exec(
      http("RemoveUser-API")
        .delete(url + "/user${n}")
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }

  // setUp(scn.inject(atOnceUsers(100))).maxDuration(FiniteDuration.apply(10, "minutes"))

  setUp(
    scn.inject(
        constantUsersPerSec(30) during (60 seconds)
    )
    ).maxDuration(FiniteDuration.apply(5, "minutes"))

}