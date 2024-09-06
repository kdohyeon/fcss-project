package fast.campus.netplix.validation;

import fast.campus.netplix.controller.form.NetplixFormUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class NetplixFormUserValidator implements Validator {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(NetplixFormUser.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NetplixFormUser user = (NetplixFormUser) target;
        if (!passwordEncoder.matches(user.getPassword(), user.getConfirmedPassword())) {
            errors.rejectValue("password", "비밀번호가 동일하지 않습니다.");
        }
    }
}
