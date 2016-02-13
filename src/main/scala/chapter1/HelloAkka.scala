package chapter1

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by lukaszkaniowski on 13/02/2016.
  */


// actor message
case class WhoToGreat(who: String)

//actor
class GreaterActor extends Actor {
  override def receive = {
    case WhoToGreat(who) => println(s"Hello $who")
  }
}


object HelloAkka extends App {
  val system = ActorSystem("Hello-Akka")

  val greater = system.actorOf(Props[GreaterActor], "greater")

  greater ! WhoToGreat("Akka")
}