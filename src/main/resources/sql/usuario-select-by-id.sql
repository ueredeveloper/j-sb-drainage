SELECT CONCAT(
    '{',
    '"', 'anexo', '"', ':',
    '{','"', 'id', '"',':', _a.id, ',',
    '"', 'numero', '"',':', '"', _a.numero,'"', ',',
    '"', 'processos', '"',':', 
    CASE 
        WHEN EXISTS (SELECT 1 FROM processo _p WHERE _p.anexo = _a.id) 
        THEN CONCAT('[', (
            SELECT STRING_AGG(
                CONCAT('{', 
                    '"', 'id', '"', ':', _p.id, ',', 
                    '"', 'numero', '"', ':', '"', _p.numero, '"', ',',
                    '"', 'usuario', '"', ':', 
                    CASE 
                        WHEN _u.id IS NOT NULL 
                        THEN CONCAT('{', 
                            '"', 'id',  '"', ':', _u.id, ',', 
                            '"', 'nome', '"', ':', '"', _u.nome, '"', ',',
                            '"', 'cpfCnpj', '"', ':', '"', _u.cpf_cnpj, '"',
                        '}')
                        ELSE 'null'
                    END,
                '}'), 
                ','
            ) 
            FROM processo _p 
            LEFT JOIN usuario _u ON _u.id = _p.usuario
            WHERE _p.anexo = _a.id
        ), ']') 
        ELSE '[]' 
    END, 
    '}',
    '}'
) 
FROM anexo _a 
WHERE _a.id = 64;
