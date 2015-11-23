package demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import demo.entity.User;
import demo.entity.UserDao;
import demo.entity.UserRole;
import demo.entity.UserRoleDao;

@SpringBootApplication
@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class UiApplication {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private SecurityConfiguration securityConfig;

	// @Autowired
	// private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends
			WebSecurityConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf()
					.disable()
					/*
					 * .csrf().csrfTokenRepository(csrfTokenRepository()).and()
					 * .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
					 */
					.authorizeRequests()
					.antMatchers("/", "/register", "/updateUser",
							"/resources/**").permitAll().anyRequest()
					.authenticated().and().formLogin().loginPage("/login")
					.permitAll().defaultSuccessUrl("/myaccount");
		}

		@Bean(name = "myUserDetailsService")
		@Override
		public UserDetailsService userDetailsServiceBean() throws Exception {
			return super.userDetailsServiceBean();
		}

		// @Bean
		// public JdbcUserDetailsManager userDetailsManager() {
		// JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
		// manager.setDataSource(dataSource);
		// manager.setUsersByUsernameQuery("select username,password,enabled
		// from users where username=?");
		// manager.setAuthoritiesByUsernameQuery("select username, role from
		// user_roles where username=?");
		// manager.setRolePrefix("ROLE_");
		// return manager;
		// }

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			// auth.jdbcAuthentication()
			// .dataSource(dataSource)
			// .usersByUsernameQuery(
			// "select name,password,enabled from users where name=?");
			auth.jdbcAuthentication().dataSource(dataSource);
			// .authoritiesByUsernameQuery(
			// "select username, role from user_roles where username=?");
		}

		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request,
						HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
					CsrfToken csrf = (CsrfToken) request
							.getAttribute(CsrfToken.class.getName());
					if (csrf != null) {
						Cookie cookie = WebUtils.getCookie(request,
								"XSRF-TOKEN");
						String token = csrf.getToken();
						if (cookie == null || token != null
								&& !token.equals(cookie.getValue())) {
							cookie = new Cookie("XSRF-TOKEN", token);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
					filterChain.doFilter(request, response);
				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}

	}

	@RequestMapping("")
	public ModelAndView index(Principal principal) {
		ModelAndView mv = new ModelAndView();
		if (principal == null) {
			mv.setViewName("login");
		} else {
			mv.setView(new RedirectView("myaccount"));
		}
		return mv;
	}

	@RequestMapping("login")
	public ModelAndView login(String username, String password,
			Principal principal) {
		ModelAndView mv = new ModelAndView();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				principal, password);
		if (principal == null) {
			mv.setViewName("login");
		} else {
			mv.setView(new RedirectView("myaccount"));
		}
		return mv;
	}

	@RequestMapping("logout")
	public ModelAndView logout(Principal principal) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new RedirectView("login"));
		return mv;
	}

	@RequestMapping("register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("register");
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		mv.addObject("hostName", hostName);
                mv.addObject("dummy", "dummy");
		return mv;
	}

	@RequestMapping("editmyaccount")
	public ModelAndView editmyaccount(User user) {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("hostName", hostName);
		return mv;
	}

	@RequestMapping("myaccount")
	public ModelAndView viewMyAccount(HttpSession session, Principal principal) {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		User loginUser = null;
		Object user = ((UsernamePasswordAuthenticationToken) principal)
				.getPrincipal();
		if (user instanceof User) {
			loginUser = (User) user;
		} else {
			loginUser = userDao.findByUsername(
					((org.springframework.security.core.userdetails.User) user)
							.getUsername()).get(0);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", loginUser);
		mv.addObject("hostName", hostName);
		mv.setViewName("myaccountview");
		return mv;
	}

	@RequestMapping("updateUser")
	public ModelAndView updateMyAccount(HttpServletRequest request, User user,
			HttpSession httpSession, Principal principal) {
		if (principal == null) { // register
			userDao.save(user);
			UserRole role = new UserRole();
			role.setUsername(user.getUsername());
			role.setUserRole("ROLE_USER");
			userRoleDao.save(role);

			boolean result = true;
			try {
				// generate session if one doesn't exist
				request.getSession();
				// auto login
				UserDetails userDetail = this.securityConfig
						.userDetailsServiceBean().loadUserByUsername(
								user.getUsername());
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						user, null, userDetail.getAuthorities());
				// Authentication auth = new
				// UsernamePasswordAuthenticationToken(user, null,
				// userDetail.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(token);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result = false;
			}
		} else {
			User existUser = userDao.findByUsername(user.getUsername()).get(0);
			user.setId(existUser.getId());
			userDao.save(user);
		}
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		ModelAndView mv = new ModelAndView(new RedirectView("myaccount"));
		mv.addObject("hostName", hostName);
		mv.addObject("user", user);
		return mv;
	}
}
