package dao;

import domain.Acessorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AcessorioDao implements IAcessorioDao{

    @Override
    public void salvarAcessorio(Acessorio acessorio) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(acessorio);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }


    @Override
    public void deletarAcessorio(int id) {

    }

    @Override
    public Acessorio encontrarAcessorioPorId(int id) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        Acessorio acessorio = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
            entityManager = entityManagerFactory.createEntityManager();

            acessorio = entityManager.find(Acessorio.class, id);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }

        return acessorio;
    }


    @Override
    public List<Acessorio> listarAcessorios() {

        return null;
    }
}
