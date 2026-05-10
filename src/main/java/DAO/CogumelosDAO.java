/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import static DAO.Conexao.getEntityManager;
import copercog.model.Cogumelos;
import copercog.model.Estoque;
import jakarta.persistence.EntityManager;
import java.util.List;



/**
 *
 * @author Doom
 */
public class CogumelosDAO {
    
    

    
    
    
    public void insert_cogumelos(Cogumelos c) {

    EntityManager em = getEntityManager();

    try {
        em.getTransaction().begin();

        em.persist(c);

        Estoque e = new Estoque();
        e.setProduto(c);
        e.setPesoAtual(0);

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
    
    
    
    
    
    
    
   
    
      public void delete_cogumelos(Cogumelos c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            if (c == null) {
                throw new RuntimeException("Cogumelo não encontrado");
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
            em.close();
        }
    
      }
    
    //seleciona um tipo
public static Cogumelos retorna_um_cogumelo(String parametroNome) {

    EntityManager em = Conexao.getEntityManager();

    try {
        return em.createQuery(
                "SELECT c FROM Cogumelos c WHERE c.nome = :nome",
                Cogumelos.class
        )
        .setParameter("nome", parametroNome)
        .getSingleResult();

    } catch (jakarta.persistence.NoResultException e) {
        return null; // regra de negócio

    } catch (Exception e) {
        throw new RuntimeException(e); // 🔥 erro real sobe pra GUI

    } finally {
        em.close();
    }
}
  
public static List<Cogumelos> retorna_todos_cogumelos() {

    EntityManager em = Conexao.getEntityManager();

    try {
        return em.createQuery(
                "SELECT c FROM Cogumelos c",
                Cogumelos.class
        )
        .getResultList();

    } catch (Exception e) {
        throw new RuntimeException(e);

    } finally {
        em.close();
    }
}
 


    
    
    public void update(Cogumelos c){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally{
          em.close();
        }
    }           

        public Cogumelos obter(int id){
      EntityManager em = getEntityManager();
      try{
          return em.find(Cogumelos.class, id);
     
       } catch(Exception e){
        throw new RuntimeException(e);
      
      }finally{
           em.close();
      }
    }
    
    
  public void updateNome(int id, String nome) {

    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();

        em.createQuery(
            "UPDATE Cogumelos c SET c.nome = :nome WHERE c.id = :id"
        )
        .setParameter("nome", nome)
        .setParameter("id", id)
        .executeUpdate();

        em.getTransaction().commit();

    } catch (Exception e) {
        em.getTransaction().rollback();
        throw new RuntimeException(e);
    } finally {
        em.close();
    }
}
  
  
  public void updatePrecoBase(int id, double precoBase) {

    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();

        em.createQuery(
            "UPDATE Cogumelos c SET c.precoBase = :preco WHERE c.id = :id"
        )
        .setParameter("preco", precoBase)
        .setParameter("id", id)
        .executeUpdate();

        em.getTransaction().commit();

    } catch (Exception e) {
        em.getTransaction().rollback();
        throw new RuntimeException(e);
    } finally {
        em.close();
    }
}
  
    
    
}
