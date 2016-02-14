package chapter2

import akka.actor.{ActorRef, ActorSystem, Props, Actor}
import akka.actor.Actor.Receive
import chapter2.MusicController.{Stop, Play}
import chapter2.MusicPlayer.{StopMusic, PlayMusic}

object MusicController {

  sealed trait ControllerMsg

  case object Play extends ControllerMsg

  case object Stop extends ControllerMsg
  def props = Props[MusicController]
}

class MusicController extends Actor {
  override def receive = {
    case Play =>
      println("Music started")
    case Stop =>
      println("Music stopped")
  }
}


object MusicPlayer {

  sealed trait PlayerMsg

  case object PlayMusic extends PlayerMsg

  case object StopMusic extends PlayerMsg

}

class MusicPlayer extends Actor {
  override def receive = {
    case StopMusic =>
      println("I don't want to stop music")
    case PlayMusic =>
      val controller = context.actorOf(MusicController.props, "musicController")
      controller ! Play
    case _ =>
      println("Unknown message")
  }
}

object Creation extends App {
  val system = ActorSystem("creation")

  val player: ActorRef = system.actorOf(Props[MusicPlayer], "player")
  player ! PlayMusic

  system.terminate()
}
