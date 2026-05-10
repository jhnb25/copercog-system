/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Doom
 */
public class Conexao {
    
    
    private static final String PERSISTENCE_UNIT = "Copercog";
    
    private static EntityManager em;
    private static EntityManagerFactory fabrica;
    
        public static EntityManager getEntityManager(){
        
            if(fabrica == null || !fabrica.isOpen())
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            
            
                    if(em == null || !em.isOpen()) //cria se em nulo ou se o entity manager foi fechado
            em = fabrica.createEntityManager();
            
            
            
         return em;
        }
    
    
    
    
      public static void closeEntityManager() {
    // Verificamos primeiro se é nulo, depois se está aberto
    if (em != null && em.isOpen()) {
        em.close();
    }
    
    // O Factory geralmente não deve ser fechado a cada consulta, 
    // pois ele é caro para criar. Mas se desejar fechar:
    if (fabrica != null && fabrica.isOpen()) {
        fabrica.close();
    }
}
}
