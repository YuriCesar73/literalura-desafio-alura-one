package com.alura.yuri.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.yuri.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Optional<Autor> findByNome(String nome);

	
	@Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
	List<Autor> autoresVivosNoAno(int ano);

}
