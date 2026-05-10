package DAO;

import static DAO.Conexao.getEntityManager;
import copercog.model.Pedidos;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JOptionPane;

public class PedidosDAO {

  public void insert_pedidos(Pedidos p) {
    EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException(e); // sobe pra quem chamou
    } finally {
        em.close();
    }
}

    public void delete_pedidos(Pedidos p) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            if (p == null) {
                JOptionPane.showMessageDialog(null, "Pedido não existe ou não foi cadastrado!");
                return;
            }

            if (!em.contains(p)) {
                p = em.merge(p);
            }

            em.remove(p);

            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao excluir pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static List<String> select_nomescliente() {
        EntityManager em = getEntityManager();
        List<String> listaNomes = null;

        try {
            listaNomes = em.createQuery(
                    "SELECT p.nomeCliente FROM Pedidos p",
                    String.class
            ).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return listaNomes;
    }

    public static List<String> select_nomecliente(String nomeBusca) {
        EntityManager em = getEntityManager();
        List<String> nomeResultado = null;

        try {
            nomeResultado = em.createQuery(
                    "SELECT p.nomeCliente FROM Pedidos p WHERE p.nomeCliente = :nome",
                    String.class
            )
                    .setParameter("nome", nomeBusca)
                    .getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return nomeResultado;
    }

    public static List<Pedidos> selectpedidos() {
        EntityManager em = getEntityManager();
        List<Pedidos> linhas = null;

        try {
            linhas = em.createQuery(
                    "SELECT p FROM Pedidos p",
                    Pedidos.class
            ).getResultList();

        } finally {
            em.close();
        }

        return linhas;
    }
    
    
    public void updateStatus(int id, String novoStatus) {
    EntityManager em = getEntityManager();

    try {
        em.getTransaction().begin();

        em.createQuery(
            "UPDATE Pedidos p SET p.status = :status WHERE p.id = :id"
        )
        .setParameter("status", novoStatus)
        .setParameter("id", id)
        .executeUpdate();

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

    public static List<Pedidos> selectpedidosOrdenado(String ordem) {
        EntityManager em = getEntityManager();
        List<Pedidos> lista;

        try {
            String jpql = "SELECT p FROM Pedidos p";

            if ("DESC".equalsIgnoreCase(ordem)) {
                jpql += " ORDER BY p.precoTotal DESC";
            } else if ("ASC".equalsIgnoreCase(ordem)) {
                jpql += " ORDER BY p.precoTotal ASC";
            }

            lista = em.createQuery(jpql, Pedidos.class).getResultList();

        } finally {
            em.close();
        }

        return lista;
    }

    public static List<Double> select_peso() {
        EntityManager em = getEntityManager();
        List<Double> listaPeso = null;

        try {
            listaPeso = em.createQuery(
                    "SELECT p.peso FROM Pedidos p",
                    Double.class
            ).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return listaPeso;
    }
}