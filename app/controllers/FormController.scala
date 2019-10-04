package controllers

import javax.inject._
import play.api._
import play.api.mvc._

class FormController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getFormPracticePage: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.formPractice())
  }

}
