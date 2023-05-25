/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.dao;

import cl.com.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fernando Davila
 */
public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {
        Usuario findByUsername(String username);
}
