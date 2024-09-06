package fast.campus.netplix.controller.form;

import fast.campus.netplix.user.RegisterUserUseCase;
import fast.campus.netplix.user.command.UserRegistrationCommand;
import fast.campus.netplix.validation.NetplixFormUserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthFormController {

    private final NetplixFormUserValidator netplixFormUserValidator;
    private final RegisterUserUseCase registerUserUseCase;

    @GetMapping("/login")
    public String loign() {
        return "login.html";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new NetplixFormUser());
        return "register.html";
    }

    @PostMapping("/register")
    public ModelAndView register(
            NetplixFormUser user, Model model,
            BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response
    ) {
        netplixFormUserValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register");
        }

        registerUserUseCase.register(
                new UserRegistrationCommand(
                        user.getUsername(),
                        user.getConfirmedPassword(),
                        user.getEmail(),
                        user.getPhone()
                )
        );

        return new ModelAndView("/login");
    }
}
