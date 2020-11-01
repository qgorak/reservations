package s4.spring.reservations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import s4.spring.reservations.models.User;
import s4.spring.reservations.services.MyUserDetails;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //do some logic here if you want something to be done whenever
        //the user successfully logs in.
 
        HttpSession session = httpServletRequest.getSession();
        MyUserDetails authUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authUser.getUser();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        httpServletResponse.addHeader("username", user.getLogin());
        httpServletResponse.addIntHeader("userId", user.getId());
        httpServletResponse.addHeader("mail", user.getMail());
 
        //set our response to OK status

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

 
    }
}