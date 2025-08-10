package com.alura.yuri.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer numeroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {}

    public Livro(Long id, String titulo, String idioma, Integer numeroDownloads, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
        this.autor = autor;
    }

    public Livro(LivroDto livro) {
        this.titulo = livro.titulo();
        this.idioma = livro.idiomas().get(0);
        this.numeroDownloads = livro.numeroDownloads();
        if (!livro.autores().isEmpty()) {
            this.autor = new Autor(livro.autores().get(0));
        }
    }
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getNumeroDownloads() {
		return numeroDownloads;
	}

	public void setNumeroDownloads(Integer numeroDownloads) {
		this.numeroDownloads = numeroDownloads;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
    public String toString() {
        return "Título: " + this.titulo + "\n" +
               "Autor: " + (this.autor != null ? this.autor.getNome() : "Desconhecido") + "\n" +
               "Idioma: " + this.idioma + "\n" +
               "Número de downloads: " + this.numeroDownloads + "\n";
    }
}
