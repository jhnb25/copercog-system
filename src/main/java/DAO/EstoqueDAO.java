/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import copercog.model.Cogumelos;
import copercog.model.Estoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;


/**
 *
 * @author Doom
 */
public class EstoqueDAO {
    
    
    
       
public static List<Estoque> selectestoque() {
    EntityManager em = Conexao.getEntityManager();
    try {
        return em.createQuery(
                "SELECT e FROM Estoque e",
                Estoque.class
        ).getResultList();
    } catch (Exception e) {
        throw new RuntimeException(e);
    } finally {
        if (em != null && em.isOpen()) em.close();
    }
}
    
    

//inserir qt para um objeto
//
public boolean addQuantidade(String nome, double qtd) {
    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();

        int atualizado = em.createQuery(
            "UPDATE Estoque e " +
            "SET e.pesoAtual = e.pesoAtual + :qtd " +
            "WHERE e.produto.nome = :nome"
        )
        .setParameter("nome", nome)
        .setParameter("qtd", qtd)
        .executeUpdate();

        em.getTransaction().commit();

        return atualizado > 0;

    } catch (Exception e) {
        em.getTransaction().rollback();
        throw new RuntimeException(e);
    } finally {
        if (em != null && em.isOpen()) em.close();
    }
}



public boolean diminuirQuantidade(String nome, double qtd) {

    EntityManager em = Conexao.getEntityManager();

    try {
        em.getTransaction().begin();

        Estoque e = em.createQuery(
                "SELECT e FROM Estoque e WHERE e.produto.nome = :nome",
                Estoque.class)
                .setParameter("nome", nome)
                .getSingleResult();

        if (e.getPesoAtual() < qtd) {
            em.getTransaction().rollback();
            return false;
        }

        e.setPesoAtual(e.getPesoAtual() - qtd);

        em.merge(e);

        em.getTransaction().commit();
        return true;

    } catch (Exception ex) {
        em.getTransaction().rollback();
        return false;
    } finally {
        em.close();
    }
}




//precisa retornar os nomes
//retorna os produtos
public static List<String> select_nomes() {
    List<String> listaNomes = null;
    EntityManager em = null;

    try {
        em = Conexao.getEntityManager();

        listaNomes = em.createQuery(
            "SELECT e.produto.nome FROM Estoque e", 
            String.class
        )
        .getResultList();

    } catch (Exception ex) {
        throw new RuntimeException(ex);
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    return listaNomes;
}

public static List<Cogumelos> select_produtos_em_estoque() {
    List<Cogumelos> listaProdutos = null;
    EntityManager em = null;
    try {
        em = Conexao.getEntityManager();
        listaProdutos = em.createQuery(
            "SELECT e.produto FROM Estoque e", 
            Cogumelos.class
        )
        .getResultList();
    } catch (Exception ex) {
        throw new RuntimeException(ex);
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    return listaProdutos;
}


public static String select_nome(String filtro) {

    EntityManager em = null;
    String resultado = null;

    try {
        em = Conexao.getEntityManager();

        resultado = em.createQuery(
            "SELECT e.produto.nome FROM Estoque e WHERE e.produto.nome = :nome", 
            String.class
        )
        .setParameter("nome", filtro)
        .getSingleResult();

    } catch (NoResultException nre) {
        resultado = null;
    } catch (Exception ex) {
        ex.printStackTrace();
        resultado = null;
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    return resultado;
}














public void inserirEstoque(Cogumelos produto) {
    EntityManager em = Conexao.getEntityManager();
    try {
        em.getTransaction().begin();
        Estoque estoque = new Estoque(0, produto);
        em.persist(estoque);
        em.getTransaction().commit();
    } catch (jakarta.persistence.RollbackException ex) {
        em.getTransaction().rollback();
        throw ex;
    } catch (Exception ex) {
        em.getTransaction().rollback();
        ex.printStackTrace();
          throw new RuntimeException(ex);
    } finally {
        if (em != null && em.isOpen()) em.close();
    
    
    
    }


}












public void excluirEstoque(Estoque estoque) {
    EntityManager em = Conexao.getEntityManager();
    try {
        em.getTransaction().begin();

       
        em.remove(em.merge(estoque));

        em.getTransaction().commit();
    } catch (Exception ex) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
       throw new RuntimeException("Erro ao excluir o registro: " + ex.getMessage(), ex);
    } finally {
        if (em != null && em.isOpen()) em.close();
    
    
    
    }
}






}
