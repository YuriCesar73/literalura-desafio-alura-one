package com.alura.yuri.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(Long id, String nome, List<Livro> livros, String dataNascimento, String dataFalecimento) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = Integer.parseInt(dataNascimento);
        this.anoFalecimento = Integer.parseInt(dataFalecimento);
        this.livros = livros;
    }

    public Autor(AutorDto autor) {
        this.nome = autor.nome();
        this.anoNascimento = Integer.parseInt(autor.anoNascimento());
        this.anoFalecimento = Integer.parseInt(autor.anoMorte());
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        livro.setAutor(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public Integer getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public Integer getAnoFalecimento() {
		return anoFalecimento;
	}

	public void setAnoFalecimento(Integer anoFalecimento) {
		this.anoFalecimento = anoFalecimento;
	}

	@Override
	public String toString() {
		List<String> livros = this.getLivros().stream().map(l -> l.getTitulo()).toList();
		return "Autor: " + this.nome + "\n" +
				"Ano de nascimento: " + this.anoNascimento + "\n" +
				"Ano de falecimento: " + this.anoFalecimento+ "\n" +
				"Livros: " + livros;
	
	}
    
}
