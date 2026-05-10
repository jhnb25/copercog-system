/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package copercog.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Entity;

/**
 * Entidade usuario que representa um registro de usuário no banco.
 */
@Entity
@Table(name = "usuarios")
public class usuarios {
   
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_usuario")
    private int id;
                   
                        private String login;
                        private String senha;
                        private String tipo;
                    
                        public int getId() {
                            return id;
                        }
                    
                       
                    
                        public String getLogin() {
                            return login;
                        }
                    
                        public void setLogin(String login) {
                            this.login = login;
                        }
                    
                        public String getSenha() {
                            return senha;
                        }
                    
                        public void setSenha(String senha) {
                            this.senha = senha;
                        }
                    
                        public String getTipo() {
                            return tipo;
                        }
                    
                        public void setTipo(String tipo) {
                            this.tipo = tipo;
                        }    
    
    public usuarios() {
}
    public usuarios(String login,String senha ){
    this.login=login;
    this.senha=senha;
    
    }
    //preciso pegar o tipo 
    //o tipo vem do nome 
    
    
    
}
   
