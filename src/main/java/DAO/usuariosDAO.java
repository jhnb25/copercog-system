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
    
    
      public static String gerarHash(String senha) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //criptografia no bd ,envia string e transforma na tabela do bd em hash, valida 
    
  
    
     public static usuarios validacao(usuarios usuario) {
    EntityManager em = Conexao.getEntityManager();
    try {
        String textoQuery = "SELECT u FROM usuarios u WHERE u.login = :login AND u.senha = :senha";
        return em.createQuery(textoQuery, usuarios.class)
                .setParameter("login", usuario.getLogin())
                .setParameter("senha", usuario.getSenha())
                .getSingleResult();

    } catch (jakarta.persistence.NoResultException e) {
        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
        return null;
    } finally {
        if (em != null && em.isOpen()) em.close(); 
    }
}
      
    
    
    
    
    
}
