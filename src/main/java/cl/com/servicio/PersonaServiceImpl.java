/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.servicio;

import cl.com.dao.IPersonaDAO;
import cl.com.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando Davila
 */
@Service
public class PersonaServiceImpl implements IPersonaService{

        //inyeccion dependencias de interfaz IPersanaDAO
        @Autowired
        private IPersonaDAO personaDao;
        
        @Override
        @Transactional(readOnly = true) //Solo lee datos de la BD
        public List<Persona> listarPersona() {
                return (List<Persona>) personaDao.findAll();
        }

        @Override
        @Transactional
        public void guardar(Persona persona) {
                personaDao.save(persona);
        }

        @Override
        @Transactional
        public void eliminar(Persona persona) {
                personaDao.delete(persona);
        }

        @Override
        @Transactional(readOnly = true)
        public Persona encontrarPersona(Persona persona) {
                return personaDao.findById(persona.getIdClientes()).orElse(null);
        }
 
        
}
