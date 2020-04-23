// package gatling.simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit

class RxApiGatlingSimulationTest extends Simulation {

  val userCount = 5

  val url = "http://localhost:8080/rx/users"

  val scn = scenario("ReactiveStackApiTest").repeat(userCount, "n") {
    exec(
      http("RxAddUser-API")
        .post(url)
        .header("Content-Type", "application/json")
        .body(StringBody("""{"id":"user${n}","name":"wang${n}", "age":10}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(userCount, "n") {
    exec(
      http("RxGetUser-API")
        .get(url + "/user${n}")
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(1, "n") {
    exec(
      http("RxListtUsers-API")
        .get(url)
        .check(status.is(200))
    )
  }.repeat(userCount, "n") {
    exec(
      http("RxUpdateUser-API")
        .put(url + "/user${n}")
        .header("Content-Type", "application/json")
        .body(StringBody("""{"id":"user${n}","name":"wang${n}", "age":20}"""))
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(userCount, "n") {
    exec(
      http("RxRemoveUser-API")
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