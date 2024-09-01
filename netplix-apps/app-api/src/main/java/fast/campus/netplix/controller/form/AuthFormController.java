package fast.campus.netplix.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthFormController {
    @GetMapping("/login")
    public String loign() {
        return "login.html";
    }
}
