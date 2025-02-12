-- busca documentos com número sei igual
SELECT doc.numero_sei, COUNT(*) 
FROM documento doc
WHERE numero_sei IS NOT NULL
GROUP BY numero_sei
HAVING COUNT(*) > 1;

-- seleciona um documento
select * from documento d where d.numero_sei like'%2456%'
-- delete relacionamento com usuario
delete from usuario_documento ud where ud.documento_id = 91
-- deleta documento quando não há mais relacionamento com usuário
delete from documento d where d.id = 102

-- Agora que não há documentos com numero sei iguais, fecha a coluna para apenas números únicos
ALTER TABLE documento ADD CONSTRAINT unique_numero_sei UNIQUE (numero_sei);

-- teste de inserção
--insert into documento (numero_sei) values (1)