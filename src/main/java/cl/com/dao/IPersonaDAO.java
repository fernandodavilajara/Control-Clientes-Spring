/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.dao;

import cl.com.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Fernando Davila
 */
public interface IPersonaDAO extends CrudRepository<Persona, Long>{
        
        //Agregar aqui queries personalizadas
        
}
