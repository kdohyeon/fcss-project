package fast.campus.netplix.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultFormController {
    @GetMapping("/")
    public String main() {
        return "main.html";
    }
}
