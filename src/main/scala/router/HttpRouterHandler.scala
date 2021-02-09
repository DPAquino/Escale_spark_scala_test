package router

import colossus.core.ServerContext
import colossus.protocols.http.Http
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.UrlParsing._
import colossus.protocols.http.server.RequestHandler
import colossus.service.Callback
import colossus.service.GenRequestHandler.PartialHandler
import service.EscaleServiceImpl
import service.enums.TypeFilter

class HttpRouterHandler(context: ServerContext) extends RequestHandler(context) {

  override def handle: PartialHandler[Http] = {
    case request@Get on Root / "test" => {
      Callback.successful(request.ok("Welcome to Scala Colossus!!!!"))
    }
    case request@Get on Root / "get" / "mapping" / "by-device" => {
      Callback.successful(request.ok(EscaleServiceImpl.searchSessionizationData(TypeFilter.Device)))
    }
    case request@Get on Root / "get" / "mapping" / "by-browser" => {
      Callback.successful(request.ok(EscaleServiceImpl.searchSessionizationData(TypeFilter.Browser)))
    }
    case request@Get on Root / "get" / "mapping" / "by-os" => {
      Callback.successful(request.ok(EscaleServiceImpl.searchSessionizationData(TypeFilter.OS)))
    }
  }
}