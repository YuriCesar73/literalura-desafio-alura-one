package com.alura.yuri.literalura.app;

import java.util.List;
import java.util.Scanner;

import com.alura.yuri.literalura.api.ConsumoApi;
import com.alura.yuri.literalura.model.Autor;
import com.alura.yuri.literalura.model.DadosApiResposta;
import com.alura.yuri.literalura.model.Livro;
import com.alura.yuri.literalura.model.LivroDto;
import com.alura.yuri.literalura.repository.AutorRepository;
import com.alura.yuri.literalura.repository.LivroRepository;
import com.alura.yuri.literalura.service.ConverterDados;

public class Aplicacao {

	private Scanner leitura;
	private ConsumoApi consumoApi;
	private ConverterDados conversor;

	private AutorRepository autorRepository;
	private LivroRepository livroRepository;

	public Aplicacao(AutorRepository autorRepository, LivroRepository livroRepository) {
		this.autorRepository = autorRepository;
		this.livroRepository = livroRepository;
		this.leitura = new Scanner(System.in);
		this.consumoApi = new ConsumoApi();
		this.conversor = new ConverterDados();
	}

	private final String ENDERECO = "https://gutendex.com/books/?search=";

	public void exibeMenu() {
		int opcao = -1;

		while (opcao != 0) {

			String menu = """
					Escolha o número de sua opção:
					1 - Buscar livro pelo título
					2 - Listar livros registrados
					3 - Listar autores registrados
					4 - Listar autores vivos em um determinado ano
					5 - Listar livros em um determinado idioma
					0 - Sair
					""";

			System.out.println(menu);
			opcao = leitura.nextInt();
			leitura.nextLine();

			switch (opcao) {
			case 1:
				buscarLivroWeb();
				break;
			case 2:
				listarLivros();
				break;
			case 3:
				listarAutores();
				break;
			case 4:
				listarAutorVivoPorAno();
				break;
			case 5:
				listarLivrosPorIdioma();
				break;
			case 0:
				System.out.println("Saindo...");
				opcao = 0;
				break;
			default:
				System.out.println("Opção inválida");
			}

		}
	}

	private void exibirSeparadorTerminal() {
		System.out.println("--------------------------");
	}

	private void buscarLivroWeb() {
		System.out.println("Insira o título do livro: ");
		String titulo = leitura.nextLine();
		var resposta = this.consumoApi.obterDados(this.ENDERECO + titulo.replace(" ", "%20"));

		DadosApiResposta res = conversor.obterDados(resposta, DadosApiResposta.class);

		if (res.results().length != 0) {
			Livro livro = this.salvarLivro(res.results()[0]);
			this.exibirSeparadorTerminal();
			System.out.println(livro.toString());
			this.exibirSeparadorTerminal();
		}
	}

	private void listarLivros() {
		List<Livro> livros = this.livroRepository.findAll();

		for (Livro livro : livros) {
			this.exibirSeparadorTerminal();
			System.out.println(livro.toString());
			this.exibirSeparadorTerminal();

		}
	}

	private void listarAutores() {
		List<Autor> autores = this.autorRepository.findAll();

		for (Autor autor : autores) {
			this.exibirSeparadorTerminal();
			System.out.println(autor.toString());
			this.exibirSeparadorTerminal();
		}
	}

	private void listarAutorVivoPorAno() {
		System.out.println("Insira o ano em que deseja pesquisar: ");
		int ano = leitura.nextInt();

		System.out.println(ano);

		List<Autor> autores = this.autorRepository.autoresVivosNoAno(ano);

		if (autores.size() == 0) {
			System.out.println("Não há autores vivos cadastrados no ano de " + ano);
		} else {
			for (Autor autor : autores) {
				this.exibirSeparadorTerminal();
				System.out.println(autor.toString());
				this.exibirSeparadorTerminal();
			}

		}

	}

	private void listarLivrosPorIdioma() {
		System.out.println("Insira o idioma para realizar a busca");
		this.exibeOpcoesIdiomas();
		String idioma = leitura.nextLine();
		List<Livro> livros = this.livroRepository.findByIdioma(idioma);
		
		if(livros.size() == 0) {
			System.out.println("Não existem livros nesse idioma no banco de dados");
			this.exibirSeparadorTerminal();
		} else {
			for (Livro livro : livros) {
				this.exibirSeparadorTerminal();
				System.out.println(livro.toString());
				this.exibirSeparadorTerminal();
			}
		}
		
		

	}

	private Livro salvarLivro(LivroDto livroDto) {

		Autor autor = autorRepository.findByNome(livroDto.autores().get(0).nome()).orElseGet(() -> {
			Autor novoAutor = new Autor(livroDto.autores().get(0));
			return autorRepository.save(novoAutor);
		});

		Livro livro = new Livro(livroDto);
		livro.setAutor(autor);

		livroRepository.save(livro);
		return livro;
	}
	
	private void exibeOpcoesIdiomas() {
		
		System.out.println(""" 
				es - espanhol
				en - inglês
				fr - francês
				pt - português
				""");
	}
}
