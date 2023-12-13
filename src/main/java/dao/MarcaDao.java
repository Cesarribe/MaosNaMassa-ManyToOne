package dao;

import domain.Marca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MarcaDao implements IMarcaDao{

    @Override
    public void salvarMarca(Marca marca) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(marca);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void atualizarMarca(Marca marca) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            // Verifica se a marca já está no contexto persistido antes de atualizá-la
            if (!entityManager.contains(marca)) {
                marca = entityManager.merge(marca); // Se não estiver, faz merge para torná-la gerenciada
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void deletarMarca(Long id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Marca marca = entityManager.find(Marca.class, id);
            entityManager.remove(marca);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public Marca encontrarMarcaPorId(Long id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Marca marca;

        try {
            marca = entityManager.find(Marca.class, id);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return marca;
    }


    @Override
    public List<Marca> listarMarcas() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Marca> marcas;

        try {
            TypedQuery<Marca> query = entityManager.createQuery("SELECT m FROM Marca m", Marca.class);
            marcas = query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return marcas;
    }

}
