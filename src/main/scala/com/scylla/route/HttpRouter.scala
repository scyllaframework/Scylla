package com.scylla.route

import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Route
import com.scylla.commons.MessageCodes._
import com.scylla.cores.route.{API, Results}
import com.scylla.models._

case class HttpRouter() extends Results {

  def index: Route = new API("") GET complete(response)

  def signUp: Route = new API("signUp") POST {
    entity(as[User]) { user =>
      complete {
        response(user.toString, OK_200)
      }
    }
  }

  def getUser: Route = new API("getUser") GET {
    complete {
      response[User](User("email","***"))
    }
  }

  val routes: server.Route = index ~ getUser ~ signUp

}