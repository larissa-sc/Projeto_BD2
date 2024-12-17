package br.com.CineExpress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int anoLancamento;
    private String genero;
    private String diretor;

    // Getters e Setters
    public Long getId() { 
        return id; }
    public void setId(Long id) { 
        this.id = id; }

    public String getTitulo() { 
        return titulo; }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; }

    public int getAnoLancamento() { 
        return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { 
        this.anoLancamento = anoLancamento; }

    public String getGenero() { 
        return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDiretor() { 
        return diretor; }
    public void setDiretor(String diretor) { this.diretor = diretor; }

    public String toString() {
        return this.titulo;
    }
}

