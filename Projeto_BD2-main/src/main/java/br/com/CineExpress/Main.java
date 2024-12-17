package br.com.CineExpress;

import br.com.CineExpress.FilmeDAO;
import br.com.CineExpress.SerieDAO;
import br.com.CineExpress.Filme;
import br.com.CineExpress.Serie;
import br.com.CineExpress.Temporada;
import br.com.CineExpress.Episodio;
import br.com.CineExpress.Usuario;
import br.com.CineExpress.Avaliacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criação das instâncias dos DAOs
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("filmesSeriesPU");
        EntityManager em = emf.createEntityManager();
        FilmeDAO filmeDAO = new FilmeDAO(em);
        SerieDAO serieDAO = new SerieDAO(em);

        // Criar e salvar um novo Filme
        em.getTransaction().begin();
        Filme filme = new Filme();
        filme.setTitulo("Inception");
        filme.setAnoLancamento(2010);
        filme.setGenero("Sci-Fi");
        filme.setDiretor("Christopher Nolan");
        filmeDAO.salvar(filme);
        em.getTransaction().commit();

        // Buscar um Filme por ID
        Filme filmeBuscado = filmeDAO.buscarPorId(filme.getId());
        System.out.println("Filme buscado: " + filmeBuscado.getTitulo());

        // Listar todos os Filmes
        List<Filme> filmes = filmeDAO.listar();
        System.out.println("Filmes cadastrados:");
        for (Filme f : filmes) {
            System.out.println(f.getTitulo());
        }

        // Atualizar um Filme
        em.getTransaction().begin();
        filmeBuscado.setGenero("Science Fiction");
        filmeDAO.atualizar(filmeBuscado);
        em.getTransaction().commit();

        // Remover um Filme
        em.getTransaction().begin();
        filmeDAO.remover(filmeBuscado.getId());
        em.getTransaction().commit();

        // Criar e salvar uma nova Serie
        em.getTransaction().begin();
        Serie serie = new Serie();
        serie.setTitulo("Breaking Bad");
        serie.setAnoLancamento(2008);
        serie.setGenero("Drama");
        serie.setCriador("Vince Gilligan");
        serieDAO.salvar(serie);
        em.getTransaction().commit();

        // Criar e salvar uma nova Temporada
        em.getTransaction().begin();
        Temporada temporada = new Temporada();
        temporada.setNumero(1);
        temporada.setSerie(serie);
        em.persist(temporada);
        em.getTransaction().commit();

        // Criar e salvar um novo Episodio
        em.getTransaction().begin();
        Episodio episodio = new Episodio();
        episodio.setTitulo("Pilot");
        episodio.setNumero(1);
        episodio.setTemporada(temporada);
        em.persist(episodio);
        em.getTransaction().commit();

        // Criar e salvar um novo Usuario
        em.getTransaction().begin();
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setSenha("password123");
        em.persist(usuario);
        em.getTransaction().commit();

        // Criar e salvar uma nova Avaliacao
        em.getTransaction().begin();
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);
        avaliacao.setComentario("Incrível!");
        avaliacao.setUsuario(usuario);
        avaliacao.setFilme(filme); // Supondo que o filme ainda exista no banco de dados
        em.persist(avaliacao);
        em.getTransaction().commit();

        // Fechar o EntityManagerFactory
        em.close();
        emf.close();
    }
}