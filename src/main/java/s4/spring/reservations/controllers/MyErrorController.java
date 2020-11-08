package s4.spring.reservations.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController  {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request,ModelMap model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    Integer statusCode = Integer.valueOf(status.toString());
	    model.addAttribute("error",statusCode);
	    return "error";
	}
@Override
public String getErrorPath() {
    return null;
}
}
