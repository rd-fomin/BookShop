package rd.fomin.bookshop.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import rd.fomin.bookshop.service.UserService;

import java.lang.reflect.Proxy;
import java.util.Set;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final UserService userService;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
//                .csrf().disable()
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers("/", "/login", "/swagger-ui/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(conf -> conf
                        .logoutSuccessUrl("/login"))
                .formLogin(conf -> conf
                        .defaultSuccessUrl("/books"))
                .oauth2Login(conf -> conf
                        .defaultSuccessUrl("/books")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService())));

        return http.build();
    }

    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            var email = userRequest.getIdToken().getClaim("email").toString();
            var user = userService.getOneByEmail(email);
            var oidcUser = new DefaultOidcUser(user.getAuthorities(), userRequest.getIdToken());

            var methods = Set.of(UserDetails.class.getMethods());

            return (OidcUser) Proxy.newProxyInstance(
                    WebSecurityConfiguration.class.getClassLoader(),
                    new Class[] {UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> methods.contains(method)
                            ? method.invoke(user, args)
                            : method.invoke(oidcUser, args));
        };
    }
}
