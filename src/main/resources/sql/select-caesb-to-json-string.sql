/* Selecionar o atributo caesb, boolean, e criar uma string em formato json utilizando postgres */

select concat('{','"caesb"', ':',
CASE WHEN _sub.caesb THEN 'true' ELSE 'false' END
, '}') 
from subterranea _sub where _sub.id = 10