package com.alura.yuri.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.yuri.literalura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	
	List<Livro> findByIdioma(String idioma);

}
