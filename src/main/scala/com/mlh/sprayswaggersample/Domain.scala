package com.mlh.sprayswaggersample

trait RestMessage

case class Get(id: Int) extends RestMessage

case class Pet(id: Int, name: String) extends RestMessage

case class Error(message: String)

case class Validation(message: String)


