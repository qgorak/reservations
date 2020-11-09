package s4.spring.reservations.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyErrorController implements ErrorController  {

	@RequestMapping("/error")
	public RedirectView handleError(HttpServletRequest request,ModelMap model) {
		return new RedirectView("/");
	}
@Override
public String getErrorPath() {
    return null;
}
}
