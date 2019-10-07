package controllers

import javax.inject._
import play.api._
import play.api.mvc._

class HttpProgrammingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def alert: Action[AnyContent] = Action { implicit request =>
    Ok(s"Got request [ $request ]")
  }

  def notFound: Action[AnyContent] = Action { implicit request =>
    NotFound
  }

  def pageNotFound: Action[AnyContent] = Action { implicit request =>
    NotFound(<h1>Page not found example</h1>)
  }

  def oops: Action[AnyContent] = Action { implicit request =>
    InternalServerError("Example of internal server error")
  }

  def anyStatus: Action[AnyContent] = Action { implicit request =>
    Status(488)("Strange response type")
  }

  def redirect: Action[AnyContent] = Action { implicit request =>
    Redirect("/some-redirect")
  }

  def redirectWithStatusCode: Action[AnyContent] = Action { implicit request =>
    Redirect("/user/home", MOVED_PERMANENTLY)
  }

  def redirectExample: Action[AnyContent] = Action { implicit request =>
    Redirect("/form-page")
  }

  def show(id: Long): Action[AnyContent] = Action { implicit request =>
    Ok(s"id requested: [ $id ]")
  }

  def newClient(id: Long): Action[AnyContent] = Action { implicit request =>
    Redirect("")
  }

  //  val badRequest   = BadRequest(views.html.form(formWithErrors))


  /* manipulating results */
  val htmlResult2: Result = Ok(<h1>Hello World!</h1>).as(HTML)
  def manipulateResult1: Action[AnyContent] = Action { implicit request =>
    htmlResult2
  }
  def settingCookies: Action[AnyContent] = Action { implicit request =>
    val cookieToUse = Cookie("cookieTest", "testValue")
    Ok("Setting cookies :) \n" +
      s"CMD+option+i > Application > Cookies > should contain: \nName: [${cookieToUse.name}], Value: [${cookieToUse.value}]")
      .withCookies(cookieToUse)
  }

  /* storing data in session */
  def setSessionData: Action[AnyContent] = Action { implicit request =>
    Ok("Welcome!").withSession("connected" -> "user@gmail.com")
  }

  def readSession: Action[AnyContent] = Action { request =>
    request.session
      .get("connected")
      .map { user =>
        Ok(s"Hello $user")
      }
      .getOrElse {
        Unauthorized("Oops you are not authorised or connected")
      }
  }

  def clearSession: Action[AnyContent] = Action { request =>
    Ok("Clearing session data").withNewSession
  }

  def flashTest = Action { implicit request =>
    Ok {
      request.flash.get("success").getOrElse("Welcome!")
    }
  }

  def flashSave = Action {
    Redirect("/home").flashing("success" -> "The item has been created")
  }

  def flashPartial = Action { implicit request =>
    Ok(views.html.partials.flashPartial())
  }

  def jsonBodyParser1: Action[AnyContent] = Action { request: Request[AnyContent] =>
    val body = request.body
    val jsonBody = body.asJson
    jsonBody.map {
      json => Ok(s"Got: ${(json \ "name").as[String]}")
    } .getOrElse {
      BadRequest("Expected JSON in request body")
    }
  }

}
