package com.mlh.sprayswaggersample.routing

import akka.actor.{Props, Actor}
import com.wordnik.swagger.annotations._
import javax.ws.rs.Path
import spray.routing.{Route, HttpService}
import com.mlh.sprayswaggersample.client._
import com.mlh.sprayswaggersample._

@Api(value = "/pet", description = "Operations about pets.", position = 0)
trait PetHttpService extends HttpService with PerRequestCreator{

  val routes = readRoute

  @ApiOperation(value = "Find a pet by ID", notes = "Returns a pet based on ID", httpMethod = "GET", response = classOf[Pet])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "ID of pet that needs to be fetched", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Pet not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def readRoute = get {
    path("pet" / IntNumber) { id =>
      //complete(Pet(id, "Sparky", new java.util.Date()))
      rejectEmptyResponse {
        handlePerRequest {
          Get(id)
        }
      }
    }
  }

  def handlePerRequest(message: RestMessage): Route =
    ctx => perRequest(actorRefFactory, ctx, Props[PetClientActor], message)
}

