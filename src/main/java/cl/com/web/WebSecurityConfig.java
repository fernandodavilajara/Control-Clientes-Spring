
package cl.com.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * la clase WebSecurityAdapter se encuentra obsoleta
 * para migracion se ha buscado informacion en:
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 * @author Fernando Davila
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        
        
        /***
         * Cadena de metodos que permite administrar politicas de acceso por 
         * niveles.Los administradores pueden acceder a las paginas /editar,  /agregar y /eliminar.
         * 
         * usuarios basicos solo pueden acceder a la lista,
        *que esta en la raiz "/" 
        * 
         tambien permite el manejo de la pagina de login y errores (en este caso
         el error 403)
         * 
         * @param http
         * @param authenticationManager
         * @return
         * @throws Exception 
         */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{

                http
                        .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/editar/**", "/agregar/**", "/eliminar")
                                .hasRole("ADMIN")
                        .requestMatchers("/").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                        )
                        .formLogin(withDefaults())
                        .exceptionHandling().accessDeniedPage("/errores/403");
                
                return http.build();

//            
              
        }
        
        
        /***
        * La lógica detrás de este Bean es configurar un objeto AuthenticationManager 
        * que se alimenta de un servicio de detalles de usuario, el cual es especificado
        * por el método userDetailsService(). La autenticación es gestionada por
        * el objeto AuthenticationManager y se construye mediante el método build().
        * 
        * ***************************************************************************
        * En resumen, este código define un Bean en Spring Security que se encarga
        * de gestionar la autenticación de usuarios en una aplicación utilizando
        * un servicio de detalles de usuario especificado.
        * ****************************************************************************
         * @param http
         * @return
         * @throws Exception 
         */
        @Bean
        protected AuthenticationManager authManager(HttpSecurity http) throws Exception{
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                        .userDetailsService(userDetailsService())
                        .passwordEncoder(passwordEncoder())
                        .and()
                        .build();
        }
        

        
        /***
         * Crea usuarios y roles con contraseñas encriptadas
        * @return 
         */
        @Bean
        protected InMemoryUserDetailsManager userDetailsService() {
                
                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
                
                manager.createUser(User.withUsername("admin")
                        .password(passwordEncoder().encode("123")) //hace referencia a metodo passwordEncoder mas abajo 
                        .roles("ADMIN", "USER")
                        .build());
                
                manager.createUser(User.withUsername("user")
                        .password(passwordEncoder().encode("123"))
                        .roles("USER")
                        .build());
                
                return manager;

        }
        
        
        @Bean
        protected PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();
        }
        

}
        

