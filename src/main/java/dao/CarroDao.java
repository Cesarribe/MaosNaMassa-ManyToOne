package dao;

import domain.Carro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarroDao implements ICarroDao{

    @Override
    public void salvarCarro(Carro carro) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(carro);
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
    public void atualizarCarro(Carro carro) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(carro);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void deletarCarro(int id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Carro carro = entityManager.find(Carro.class, id);
            entityManager.remove(carro);
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
    public Carro encontrarCarroPorId(int id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Carro carro;

        try {
            carro = entityManager.find(Carro.class, id);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return carro;
    }

    @Override
    public List<Carro> listarCarros() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Carro> carros;

        try {
            TypedQuery<Carro> query = entityManager.createQuery("SELECT c FROM Carro c", Carro.class);
            carros = query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return carros;
    }


    @Override
    public List<Carro> listarCarrosPorMarca(int idMarca) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Carro> carros;

        try {
            TypedQuery<Carro> query = entityManager.createQuery("SELECT c FROM Carro c WHERE c.marca.id = :idMarca", Carro.class);
            query.setParameter("idMarca", idMarca);
            carros = query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return carros;
    }
}

