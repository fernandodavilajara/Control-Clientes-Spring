/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import lombok.Data;




/**
 *
 * @author Fernando Davila
 */

@Data //para setters y getters
@Entity
@Table(name = "persona") 
public class Persona implements Serializable{
        
        private static final long serialVersionUID  = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_clientes" ) //nombre diferente en la base de datos
        private Long idClientes;
        
        @NotEmpty
        private String nombre;
        @NotEmpty
        private String apellido;
        @NotEmpty
        @Email
        private String email;
        @NotEmpty
        private String telefono;
        
        //
        
}
