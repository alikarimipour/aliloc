/**
 * 3/6/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService mMyUserDetailService;
    @Autowired
    private BCryptPasswordEncoder mBCryptPasswordEncoder;
    @Autowired
    private MyAuthenticationManager mMyAuthenticationManager;

    public SecurityConfig() {

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mMyUserDetailService).passwordEncoder(mBCryptPasswordEncoder);
    }

    /*@Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(super.authenticationManagerBean());
        return authenticationTokenFilter;
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/initial/**").permitAll()
                .antMatchers("/service/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .and()
                .addFilter(new JWTAuthenticationFilter(mMyAuthenticationManager))
                .addFilter(new JWTAuthorizationFilter(mMyAuthenticationManager))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler()
    {
        return new MyAccessDeniedHandler();
    }

}
