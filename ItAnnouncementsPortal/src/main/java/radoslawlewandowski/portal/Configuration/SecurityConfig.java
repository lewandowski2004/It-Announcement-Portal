package radoslawlewandowski.portal.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import radoslawlewandowski.portal.Service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bcp;

    @Autowired
    private DataSource ds;

    @Value("select email, password, active from user where email=?")
    private String userQuery;

    @Value("select email, password, active from company where email=?")
    private String companyQuery;

    @Value("select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?")
    private String rolesQueryUser;

    @Value("select c.email, r.role from company c inner join company_role cr on(c.company_id=cr.company_id) inner join role r on(cr.role_id=r.role_id) where c.email=?")
    private String rolesQueryCompany;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(rolesQueryUser)
                .dataSource(ds).passwordEncoder(bcp);
        auth.jdbcAuthentication().usersByUsernameQuery(companyQuery)
                .authoritiesByUsernameQuery(rolesQueryCompany)
                .dataSource(ds).passwordEncoder(bcp);
    }



    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/registerUser").permitAll()
                .antMatchers("/registerCompany").permitAll()
               // .antMatchers("/addUser1").permitAll()
               // .antMatchers("/add").permitAll()
               // .antMatchers("/users").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        http.csrf().disable();
    }
    public void configure(WebSecurity webSec) throws Exception {
        webSec.ignoring()
                .antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**");
    }

}