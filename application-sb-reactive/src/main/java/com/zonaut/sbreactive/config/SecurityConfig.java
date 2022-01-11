package com.zonaut.sbreactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {

//    private final DataSource dataSource;
//
//    public SecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange()
//                .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .pathMatchers("/")
//                .authenticated().and()
//                .httpBasic().and()
//                .build();
//    }

    //https://stackoverflow.com/questions/47354171/spring-webflux-custom-authentication-for-api

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
//                .withUser(User.withUsername("james")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("jennifer")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("john")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("mary")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//        ;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
