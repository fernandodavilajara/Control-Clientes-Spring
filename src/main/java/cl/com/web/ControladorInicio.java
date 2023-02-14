/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.web;

import cl.com.domain.Persona;
import cl.com.servicio.PersonaServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Fernando Davila
 */
@Controller
@Slf4j
public class ControladorInicio {
        
        //inyeccion capa de servicios
        @Autowired
        private PersonaServiceImpl personaService;
        
        //El objeto model permite gestionar atributos
        @GetMapping("/")
        public String inicio(Model  model, @AuthenticationPrincipal User user){
                var personas = personaService.listarPersona();
                log.info("Usuario logeado: " + user);
                model.addAttribute("personas", personas);
                return "index";
        }
        
        @GetMapping("/agregar")
        public String agregar(Persona persona){
                return "modificar";
        }
        
        @PostMapping("/guardar")
        public String guardar(@Valid Persona persona, Errors errors){
                if(errors.hasErrors()){
                        return "modificar";
                }     
                personaService.guardar(persona);
                return "redirect:/";
        }
        
        @GetMapping("/editar/{idClientes}")
        public String editar(Persona persona, Model model){
                persona = personaService.encontrarPersona(persona);
                model.addAttribute("persona", persona);
                log.info("Persona " + persona.getIdClientes().toString());
                return "modificar";                
        }
        
        @GetMapping("/eliminar/{idClientes}")
        public String eliminar(Persona persona){
                personaService.eliminar(persona);
                return "redirect:/";
        }
}
