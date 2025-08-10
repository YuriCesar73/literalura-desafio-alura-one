package com.alura.yuri.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.yuri.literalura.app.Aplicacao;
import com.alura.yuri.literalura.repository.AutorRepository;
import com.alura.yuri.literalura.repository.LivroRepository;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LivroRepository livroRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Aplicacao app = new Aplicacao(autorRepository, livroRepository);
		app.exibeMenu();

		
	}

}
