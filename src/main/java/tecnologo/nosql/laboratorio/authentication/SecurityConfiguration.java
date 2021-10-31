package tecnologo.nosql.laboratorio.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*    @Autowired
    private UserDetailsService customService;*/

    @Autowired
    private AuthenticationEntry authenticationEntry;

    @Autowired
    MongoUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

/*        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntry).and()
                .authorizeRequests((request) -> request.antMatchers("/api/v1/auth/login", "/v3/api-docs.yaml", "/v3/api-docs").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/api/v1/cliente/eliminarCuenta").hasRole("CLIENTE")
                        .anyRequest().authenticated())
                .addFilterBefore(new AuthenticationFilter(customService, tokenHelper),
                        UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.cors().and().headers().frameOptions().disable();*/
        http
                .csrf().disable()
                .authorizeRequests((request) -> request
                        .antMatchers("/api/v1/auth/login", "/api/v1/usuario/test").permitAll()
                        .anyRequest().authenticated())
                .httpBasic()
                .and().sessionManagement().disable();
    }
}
