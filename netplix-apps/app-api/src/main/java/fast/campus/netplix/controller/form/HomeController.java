package fast.campus.netplix.controller.form;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mav.addObject("username", authentication.getName());

        return mav;
    }
}
