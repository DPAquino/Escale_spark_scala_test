import akka.actor.ActorSystem
import colossus.IOSystem
import colossus.core.InitContext
import colossus.protocols.http.server.{HttpServer, Initializer}
import router.HttpRouterHandler
import service.EscaleServiceImpl

object App extends App {

  implicit val actorSystem = ActorSystem()
  implicit val ioSystem = IOSystem()

  EscaleServiceImpl.sourceDataHandler

  HttpServer.start("Scala-Colossus", 9000) { context => new EscaleInitializer(context) }
}

class EscaleInitializer(context: InitContext) extends Initializer(context) {
  override def onConnect = context => new HttpRouterHandler(context)
}
