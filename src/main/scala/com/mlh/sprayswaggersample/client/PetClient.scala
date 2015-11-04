package com.mlh.sprayswaggersample.client

import akka.actor.Actor
import com.mlh.sprayswaggersample._

trait PetOperations {
	def getById(id: Int) = {
		println("Inside PetOperations")
		new Pet(id, "Sparky")
	}
}

class PetClientActor extends Actor with PetOperations{
  def receive = {
    //case GetPets("Lion" :: _)     => sender ! Validation("Lions are too dangerous!")
    //case GetPets("Tortoise" :: _) => () // Never send a response. Tortoises are too slow
    case Get(id)	=> sender ! getById(id)

  }
}

