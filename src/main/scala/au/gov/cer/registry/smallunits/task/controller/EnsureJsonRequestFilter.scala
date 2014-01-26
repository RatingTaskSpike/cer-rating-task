package au.gov.cer.registry.smallunits.task.controller

import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finagle.http.{Request => FinagleRequest, Response => FinagleResponse, MediaType}

class EnsureJsonRequestFilter extends SimpleFilter[FinagleRequest, FinagleResponse] with App {

  def apply(request: FinagleRequest,
             service: Service[FinagleRequest, FinagleResponse]) = {

    val contentType = request.contentType.getOrElse("")
    service(request) map { response =>
//      require(MediaType.Json == contentType)
      response
    }
  }
}
