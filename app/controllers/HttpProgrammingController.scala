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

}
