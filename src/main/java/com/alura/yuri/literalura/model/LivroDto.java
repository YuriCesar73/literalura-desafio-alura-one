package com.alura.yuri.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDto(  @JsonAlias("title") String titulo,
	    @JsonAlias("authors") List<AutorDto> autores,
	    @JsonAlias("languages") List<String> idiomas,
	    @JsonAlias("download_count") int numeroDownloads) {

}
