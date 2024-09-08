package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.EnderecoModel;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

	@Query("SELECT "
			+ "CONCAT('{\"endereco\": {\"id\": ', e.id, ', \"logradouro\": \"', e.logradouro,'\"}}') "
			+ "FROM EnderecoModel e WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<Object> listByKeyword(@Param("keyword") String keyword);

	@Query("SELECT e.logradouro, i.latitude, i.longitude, "
			+ "CONCAT('{\"tipoAto\": {\"id\": ', ta.id, ', \"descricao\": \"', ta.descricao, '\"}}') "
			+ "FROM EnderecoModel e " + "JOIN e.interferencias i " + "JOIN i.tipoAto ta "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<Object[]> findLogradouroAndCoordinates(@Param("keyword") String keyword);

	/*
	 * Retorno do método findLogradouroAndCoordinates. Gera um string em formato
	 * json que pode ser desserealizada no frontend: [ [ "Rua Noaves, 1 2 3",
	 * -15.11, -47.123, "{\"tipoAto\": {\"id\": 1, \"descricao\": \"Despacho\"}}" ],
	 * ...
	 * 
	 * 
	 * Ver: https://replit.com/@ueredeveloper/j-map-json#src/main/java/Main.java ...
	 * Map<String, TipoAto> tipoAtoMap = gson.fromJson(tipoAtoJson, new
	 * TypeToken<Map<String, TipoAto>>() {}.getType());
	 * 
	 * // Acessar o campo 'tipoAto' TipoAto tipoAto = tipoAtoMap.get("tipoAto"); ...
	 */

}
