package com.alura.yuri.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConverterDados {
	
	private ObjectMapper mapper = new ObjectMapper();

	public <T> T obterDados(String json, Class<T> classe) {
		
		  try {
		        return mapper.readValue(json, classe);
		    } catch (JsonProcessingException e) {
		        throw new RuntimeException("Erro ao converter JSON: " + e.getMessage(), e);
		    }	
	}
	
//	public <T> T obterDados(String json, Class<T> classe) {
//        Map<String, Object> data;
//		try {
//			data = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
//	        System.out.println(data.get("results"));
//	        
//			var teste = mapper.readValue(json, classe);
//			System.out.println(teste);
//
//	        
//
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//		
//	}


}
