/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Fernando Davila
 */
@Entity
@Data
@Table(name= "usuario")
public class Usuario implements Serializable{
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idUsuario;
        
        @NotEmpty
        private String username;
        
        @NotEmpty
        private String password;
        
        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_usuario")
        private List<Rol> roles;
        
        
}
