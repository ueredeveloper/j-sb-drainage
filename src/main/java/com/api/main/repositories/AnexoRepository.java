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
		    + "    '{',\r\n"
		    + "    '\"', 'anexo', '\"', ':',\r\n"
		    + "    '{','\"', 'id', '\"',':', _a.id, ',',\r\n"
		    + "    '\"', 'numero', '\"',':', '\"', _a.numero,'\"', ',',\r\n"
		    + "    '\"', 'processos', '\"',':', \r\n"
		    + "    CASE \r\n"
		    + "        WHEN EXISTS (SELECT 1 FROM processo _p WHERE _p.anexo = _a.id) \r\n"
		    + "        THEN CONCAT('[', (\r\n"
		    + "            SELECT STRING_AGG(\r\n"
		    + "                CONCAT('{', \r\n"
		    + "                    '\"', 'id', '\"', ':', _p.id, ',', \r\n"
		    + "                    '\"', 'numero', '\"', ':', '\"', _p.numero, '\"', ',',\r\n"
		    + "                    '\"', 'usuario', '\"', ':', \r\n"
		    + "                    CASE \r\n"
		    + "                        WHEN _u.id IS NOT NULL \r\n"
		    + "                        THEN CONCAT('{', \r\n"
		    + "                            '\"', 'id',  '\"', ':', _u.id, ',', \r\n"
		    + "                            '\"', 'nome', '\"', ':', '\"', _u.nome, '\"', ',',\r\n"
		    + "                            '\"', 'cpfCnpj', '\"', ':', '\"', _u.cpf_cnpj, '\"',\r\n"
		    + "                        '}')\r\n"
		    + "                        ELSE 'null'\r\n"
		    + "                    END,\r\n"
		    + "                '}'), \r\n"
		    + "                ','\r\n"
		    + "            ) \r\n"
		    + "            FROM processo _p \r\n"
		    + "            LEFT JOIN usuario _u ON _u.id = _p.usuario\r\n"
		    + "            WHERE _p.anexo = _a.id\r\n"
		    + "        ), ']') \r\n"
		    + "        ELSE '[]' \r\n"
		    + "    END, \r\n"
		    + "    '}',\r\n"
		    + "    '}'\r\n"
		    + ") \r\n"
		    + "FROM anexo _a "
		    + "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_a.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))",
		    nativeQuery = true)
		List<Object> listByKeyword(@Param("keyword") String keyword);

}
