/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.servicio;

import cl.com.dao.IUsuarioDAO;
import cl.com.domain.Rol;
import cl.com.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando Davila
 */
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

        @Autowired
        private IUsuarioDAO usuarioDao;
        
        @Override
        @Transactional(readOnly = true)
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                
                Usuario usuario = usuarioDao.findByUsername(username);
                
                if(usuario == null){
                        throw new UsernameNotFoundException(username);
                }
                
                
                var roles = new ArrayList<GrantedAuthority>();
                for(Rol rol: usuario.getRoles()){
                        roles.add( new SimpleGrantedAuthority(rol.getNombre()));
                        log.info(roles.toString());
                }
                
                return new User(usuario.getUsername(), usuario.getPassword(), roles );
                
        }
        
}
