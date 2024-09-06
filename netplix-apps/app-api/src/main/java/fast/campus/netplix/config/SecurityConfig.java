package fast.campus.netplix.config;

import fast.campus.netplix.authentication.token.JwtAuthenticationFilter;
import fast.campus.netplix.authentication.token.JwtTokenProvider;
import fast.campus.netplix.security.NetplixLogoutSuccessHandler;
import fast.campus.netplix.security.NetplixUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final NetplixUserDetailsService netplixUserDetailsService;

    private final NetplixLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
        );
        httpSecurity.logout(logout -> logout
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll());
        httpSecurity.authorizeHttpRequests(a ->
                a.requestMatchers("/",
                                "/register",
                                "/api/v1/user/**",
                                "/api/v1/auth/**",
                                "/api/v1/sample/**"
                        ).permitAll()
                        .anyRequest().authenticated());

        httpSecurity.userDetailsService(netplixUserDetailsService);

        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
