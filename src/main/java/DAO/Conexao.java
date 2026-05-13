package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {

    private static final String PERSISTENCE_UNIT = "Copercog";

    // Factory: uma só instância durante toda a vida da aplicação
    private static EntityManagerFactory fabrica;

    static {
        fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    // Sempre retorna um EntityManager NOVO — nunca reutiliza o mesmo
    public static EntityManager getEntityManager() {
        if (fabrica == null || !fabrica.isOpen()) {
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return fabrica.createEntityManager();
    }

    // Fechar apenas no encerramento da aplicação
    public static void closeFactory() {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
    }
}