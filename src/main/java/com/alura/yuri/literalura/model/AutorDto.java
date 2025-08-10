package com.alura.yuri.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDto(   @JsonAlias("name") String nome,
	    @JsonAlias("birth_year") String anoNascimento,
	    @JsonAlias("death_year") String anoMorte	) {

}
