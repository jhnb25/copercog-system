/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Conexao.getEntityManager;
import copercog.model.Endereco_Fisico;
import jakarta.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Doom
 */
public class Endereco_FisicoDAO {
    
     
   public void insert_enderecofisico(Endereco_Fisico e){

    EntityManager em = getEntityManager();

    try {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();

    } catch (Exception ex) {

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        throw new RuntimeException(ex);

    } finally {
        em.close();
    }
}
    
    
    
    
    
    
}
