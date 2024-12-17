package br.com.CineExpress.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import br.com.CineExpress.Serie;

public class SerieDAO {
	private EntityManager em;

	public SerieDAO(EntityManager em) {
	this.em = em;
	}
	

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("filmesSeriesPU");
    EntityManager em = emf.createEntityManager();
    
    public void salvar(Serie serie) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(serie);
        em.getTransaction().commit();
        em.close();
    }

    public Serie buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Serie serie = em.find(Serie.class, id);
        em.close();
        return serie;
    }

    public List<Serie> listar() {
        EntityManager em = emf.createEntityManager();
        List<Serie> series = em.createQuery("FROM Serie", Serie.class).getResultList();
        em.close();
        return series;
    }

    public void atualizar(Serie serie) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(serie);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Serie serie = em.find(Serie.class, id);
        if (serie != null) {
            em.remove(serie);
        }
        em.getTransaction().commit();
        em.close();
    }
}
