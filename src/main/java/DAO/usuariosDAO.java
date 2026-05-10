/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import copercog.model.usuarios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Doom
 */
public class usuariosDAO {
    
    
    public static String getMD5(String texto) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        byte[] messageDigest = md.digest(texto.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;

    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
    
    
    
    
  
    
      public static usuarios validacao(usuarios usuario) {
                                 EntityManager em = Conexao.getEntityManager();
                                       usuarios usuario1 = null;
                       
                                               
String textoQuery = "SELECT u FROM usuarios u WHERE u.login = :login AND u.senha = :senha";
                   
      TypedQuery<usuarios> consulta = em.createQuery(textoQuery, usuarios.class);
                        consulta.setParameter("login", usuario.getLogin());
    consulta.setParameter("senha", usuario.getSenha());
                        
                   
                          
               
                          
                            try {
         usuario1 = consulta.getSingleResult();
        
        
              

        return usuario1;
    } catch (jakarta.persistence.NoResultException e) {
        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
        return null;
    }
                          
                          
                          
                          
                          
                         
                              
                              
                              
                              
                              
                          } 
      
    
    
    
    
    
}
