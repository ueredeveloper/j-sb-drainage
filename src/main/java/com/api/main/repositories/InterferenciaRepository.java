package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaModel;

@Repository
public interface InterferenciaRepository extends JpaRepository<InterferenciaModel, Long> {

	@Query("SELECT "
			+ "CONCAT('{\"interferencia\": {\"id\": ', i.id, ', \"latitude\": \"', i.latitude, '\", \"longitude\": \"', i.longitude, '\"}}'), "
			+ "CONCAT('{\"tipoInterferencia\": {\"id\": ', ti.id, ', \"descricao\": \"', ti.descricao,'\"}}'), "
			+ "CONCAT('{\"tipoOutorga\": {\"id\": ', to.id, ', \"descricao\": \"', to.descricao,'\"}}'), "
			+ "CONCAT('{\"subtipoOutorga\": {\"id\": ', so.id, ', \"descricao\": \"', so.descricao,'\"}}'), "
			+ "CONCAT('{\"situacaoProcesso\": {\"id\": ', sp.id, ', \"descricao\": \"', sp.descricao,'\"}}'), "
			+ "CONCAT('{\"tipoAto\": {\"id\": ', ta.id, ', \"descricao\": \"', ta.descricao,'\"}}'), "
			+ "CONCAT('{\"endereco\": {\"id\": ', e.id, ', \"logradouro\": \"', e.logradouro,'\"}}') "
			+ "FROM InterferenciaModel i " + "LEFT JOIN i.tipoInterferencia ti " + "LEFT JOIN i.tipoOutorga to "
			+ "LEFT JOIN i.subtipoOutorga so " + "LEFT JOIN i.situacaoProcesso sp " + "LEFT JOIN i.tipoAto ta "
			+ "LEFT JOIN i.endereco e "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<Object[]> listByLogradouro(@Param("keyword") String keyword);

}
