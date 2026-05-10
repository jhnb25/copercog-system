/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Conexao.getEntityManager;
import copercog.model.Clientes;

import jakarta.persistence.EntityManager;

import java.util.List;

    

/**
 *
 * @author Doom
 */
public class ClientesDAO {
    



public void insert_cliente(Clientes c) {

    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    } catch (Exception e) {

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        throw new RuntimeException(e); 

    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
     
           
 

   

     
     
     
     
     
     
     
     
   public void delete_cliente(Clientes c) {

    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();

        if (c == null) {
            throw new RuntimeException("Cliente não existe ou não foi cadastrado!");
        }

        if (!em.contains(c)) {
            c = em.merge(c);
        }

        em.remove(c);
        em.getTransaction().commit();

    } catch (Exception e) {

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        throw new RuntimeException(e); 

    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
     
    
  
public static List<Clientes> select_cliente() {

    EntityManager em = Conexao.getEntityManager();

    try {
        return em.createQuery(
                "SELECT c FROM Clientes c",
                Clientes.class
        ).getResultList();

    } catch (Exception e) {
        throw new RuntimeException(e);
    } finally {
        Conexao.closeEntityManager();
    }
}
       
      

//SELECT c.cpf FROM Clientes c WHERE c.cpf = :cpf

      
public static List<String> select_nomes_clientes() {

    EntityManager em = Conexao.getEntityManager();

    try {
        return em.createQuery(
                "SELECT c.nome FROM Clientes c",
                String.class
        ).getResultList();

    } catch (Exception e) {
        throw new RuntimeException(e); // mantém padrão da classe

    } finally {
        Conexao.closeEntityManager();
    }
}
      
      
      
  
        
//por parametro busca só 1 nome
  //filtro
public static Clientes retorna_um_cliente(String parametroNome) {

    EntityManager em = Conexao.getEntityManager();

    try {
        return em.createQuery(
                "SELECT c FROM Clientes c WHERE c.nome = :nome",
                Clientes.class
        )
        .setParameter("nome", parametroNome)
        .getSingleResult();

    } catch (jakarta.persistence.NoResultException e) {
        return null; 

    } catch (Exception e) {
        throw new RuntimeException(e); // erro real sobe pra GUI
    } finally {
        Conexao.closeEntityManager();
    }
}
     
  
  public void salvarClienteCompleto(Clientes cliente) {
    EntityManager em = getEntityManager();

    try {
        em.getTransaction().begin();

        em.persist(cliente); // CASCADE resolve o resto

        em.getTransaction().commit();

    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException(e); 
    } finally {
        em.close();
    }
}
    
    
}
