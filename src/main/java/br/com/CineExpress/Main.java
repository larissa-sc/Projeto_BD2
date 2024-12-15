package br.com.CineExpress;

import br.com.CineExpress.FilmeDAO;
import br.com.CineExpress.SerieDAO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criação das instâncias dos DAOs
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("filmesSeriesPU");
        FilmeDAO filmeDAO = new FilmeDAO();
        SerieDAO serieDAO = new SerieDAO();

        // Criar e salvar um novo Filme
        Filme filme = new Filme();
        filme.setTitulo("Inception");
        filme.setAnoLancamento(2010);
        filme.setGenero("Sci-Fi");
        filme.setDiretor("Christopher Nolan");
        filmeDAO.salvar(filme);

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
        filmeBuscado.setGenero("Science Fiction");
        filmeDAO.atualizar(filmeBuscado);

        // Remover um Filme
        filmeDAO.remover(filmeBuscado.getId());

        // Criar e salvar uma nova Serie
        Serie serie = new Serie();
        serie.setTitulo("Breaking Bad");
        serie.setAnoLancamento(2008);
        serie.setGenero("Drama");
        serie.setCriador("Vince Gilligan");
        serieDAO.salvar(serie);

        // Criar e salvar uma nova Temporada
        Temporada temporada = new Temporada();
        temporada.setNumero(1);
        temporada.setSerie(serie);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(temporada);
        em.getTransaction().commit();
        em.close();

        // Criar e salvar um novo Episodio
        Episodio episodio = new Episodio();
        episodio.setTitulo("Pilot");
        episodio.setNumero(1);
        episodio.setTemporada(temporada);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(episodio);
        em.getTransaction().commit();
        em.close();

        // Criar e salvar um novo Usuario
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setSenha("password123");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();

        // Criar e salvar uma nova Avaliacao
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);
        avaliacao.setComentario("Incrível!");
        avaliacao.setUsuario(usuario);
        avaliacao.setFilme(filme); // Supondo que o filme ainda exista no banco de dados
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(avaliacao);
        em.getTransaction().commit();
        em.close();

        // Fechar o EntityManagerFactory
        emf.close();
    }
}