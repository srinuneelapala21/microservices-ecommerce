//package com.ecommerce.productservice.security;
//
//import com.ecommerce.productservice.service.CustomUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SpringSecurityConfig {
//
//    //authentication
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        //Inmemory
////        UserDetails admin = User.withUsername("admin")
////                                .password(passwordEncoder.encode("adminpassword"))
////                                .roles("ADMIN")
////                                .build();
////
////        UserDetails user = User.withUsername("user")
////                                .password(passwordEncoder.encode("userpassword"))
////                                .roles("USER")
////                                .build();
////
////        return new InMemoryUserDetailsManager(admin,user);
//
//        //database
//        return new CustomUserDetailsService();
//    }
//
//     @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    //authorization
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       return http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                            auth.requestMatchers("/get-all-products").authenticated();
//                            auth.requestMatchers("/get-product/**").authenticated();
//                            auth.requestMatchers("/new-user").permitAll();
//                        }
//                )
//               .formLogin(withDefaults())
//                .build();
//
//    }
//}
