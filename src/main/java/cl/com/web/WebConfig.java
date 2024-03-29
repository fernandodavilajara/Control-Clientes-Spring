/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author Fernando Davila
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

        /**
         * *
         * determina la localidad predeterminada como español
         *
         * @return
         */
        @Bean
        public LocaleResolver localeResolver() {
                var slr = new SessionLocaleResolver();
                slr.setDefaultLocale(new Locale("es"));
                return slr;
        }

        /**
         * *
         * toma el parametro "lang" en las etiquetas HTML para modificar el
         * lenguaje que se utilizará
         *
         * @return
         */
        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
                var lci = new LocaleChangeInterceptor();
                lci.setParamName("lang");
                return lci;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registro) {
                registro.addInterceptor(localeChangeInterceptor());
        }

       

//        public void addResorucesHandlers(ResourceHandlerRegistry registry){
//                registry.addResourceHandler("/webjars/**")
//                        .addResourceLocations("resources/webjars/");
//        }
        /**
         * *
         * Permite mapear vistas que no necesariamente van a ser pasadas por la
         * clase ContreladorInicio, en este caso es la pagina de error 403, el
         * login e index
         *
         * @param registro
         */
        @Override
        public void addViewControllers(ViewControllerRegistry registro) {
                registro.addViewController("/").setViewName("index");
                registro.addViewController("/login");
                registro.addViewController("/errores/403").setViewName("/errores/403");
        }
}
