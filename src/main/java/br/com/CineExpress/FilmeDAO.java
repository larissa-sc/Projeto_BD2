package br.com.CineExpress;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import br.com.CineExpress.Filme;

public class FilmeDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("filmesSeriesPU");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Filme filme) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(filme);
        em.getTransaction().commit();
        em.close();
    }

    public Filme buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Filme filme = em.find(Filme.class, id);
        em.close();
        return filme;
    }

    public List<Filme> listar() {
        EntityManager em = emf.createEntityManager();
        List<Filme> filmes = em.createQuery("FROM Filme", Filme.class).getResultList();
        em.close();
        return filmes;
    }

    public void atualizar(Filme filme) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(filme);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Filme filme = em.find(Filme.class, id);
        if (filme != null) {
            em.remove(filme);
        }
        em.getTransaction().commit();
        em.close();
    }
}
