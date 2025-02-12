package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;


@Repository
public interface AnexoRepository extends JpaRepository<AnexoModel, Long> {
	

	
	/*@Query("SELECT DISTINCT a FROM AnexoModel a LEFT JOIN _a.processos p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_a.numero) LIKE %:keyword%)")
	List<AnexoModel> listByKeyword(String keyword);*/
	
	@Query(value = 
		    "SELECT CONCAT(\r\n"
		    + "'{',\r\n"
		    + "'\"', 'anexo', '\"', ':',\r\n"
		    + "'{','\"', 'id', '\"',':', _a.id, ','\r\n"
		    + "'\"', 'numero', '\"',':', '\"', _a.numero,'\"', ','\r\n"
		    + "'\"', 'processos', '\"',':', \r\n"
		    + "CASE WHEN EXISTS (SELECT 1 FROM processo _p WHERE _p.anexo = _a.id) \r\n"
		    + "THEN CONCAT('[', (\r\n"
		    + "SELECT STRING_AGG(CONCAT('{', \r\n"
		    + "'\"', 'id', '\"', ':', _p.id, ',', \r\n"
		    + "'\"', 'numero', '\"', ':', '\"', _p.numero, '\"', ','\r\n"
		    + "'\"', 'usuario', '\"', ':', CONCAT('{', '\"', 'id',  '\"', ':', _u.id, ',', '\"', 'nome', '\"', ':','\"', _u.nome, '\"',',', '\"','cpfCnpj', '\"', ':','\"', _u.cpf_cnpj, '\"','}'),\r\n"
		    + "'}'), ',') \r\n"
		    + "FROM processo _p \r\n"
		    + "LEFT JOIN usuario _u ON _u.id = _p.usuario\r\n"
		    + "WHERE _p.anexo = _a.id), ']') \r\n"
		    + "ELSE '[]' END, \r\n"
		    + "'}', '}') \r\n"
		    + "FROM anexo _a \r\n"
		    + "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_a.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))",
		    nativeQuery = true)
		List<Object> listByKeyword(@Param("keyword") String keyword);

}
