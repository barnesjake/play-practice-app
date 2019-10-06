package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def formPracticePage(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.formPractice())
  }

  def getHttpProgrammingPage: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.httpProgramming())
  }

  def getAdvHttpProgrammingPage: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.advHttpProgramming())
  }

  def getConfigApiPage: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.configApi())
  }

  def getTwirlTemplatesPage: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.twirlTemplates())
  }


}
