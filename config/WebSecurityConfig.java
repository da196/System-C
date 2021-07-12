package tz.go.tcra.hrms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import tz.go.tcra.hrms.auth.CustomAuthenticationFailureHandler;
import tz.go.tcra.hrms.auth.CustomAuthenticationLogoutSuccessHandler;
import tz.go.tcra.hrms.auth.CustomAuthenticationProvider;
import tz.go.tcra.hrms.auth.CustomAuthenticationSuccessHandler;

@Configuration
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
@ComponentScan("tz.go.tcra.hrms")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    public WebSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
    	http
    	.authorizeRequests()
    	.antMatchers("/resources/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login.htm")
        .loginProcessingUrl("/authenticate.htm") // Specifies the URL,which is used 
                                       //in action attribute on the <from> tag
        .usernameParameter("username")  // Username parameter, used in name attribute
                                      // of the <input> tag. Default is 'username'.
        .passwordParameter("password")  // Password parameter, used in name attribute
                                      // of the <input> tag. Default is 'password'.
        .successHandler(new CustomAuthenticationSuccessHandler())
//      .defaultSuccessUrl("/")   // URL, where user will go after authenticating successfully.
                                  // Skipped if successHandler() is used.
        .failureHandler(new CustomAuthenticationFailureHandler())
//      .failureUrl("/login?error")   // URL, where user will go after authentication failure.
                                      //  Skipped if failureHandler() is used.
        .permitAll() // Allow access to any URL associate to formLogin()
        .and()
        .logout()
        .logoutUrl("/logout.htm")   // Specifies the logout URL, default URL is '/logout'
        .logoutSuccessHandler(new CustomAuthenticationLogoutSuccessHandler())
//      .logoutSuccessUrl("/login") // URL, where user will be redirect after successful
                                    //  logout. Ignored if logoutSuccessHandler() is used.
        .permitAll() // Allow access to any URL associate to logout()
        .and()
        .csrf().disable(); // Disable CSRF support
    	
        /*http
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .httpBasic();*/
        // @formatter:on
    }

}