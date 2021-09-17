CREATE TABLE cotacao (
	id SERIAL NOT NULL PRIMARY KEY,
	peso NUMERIC NOT NULL,
	cep_origem VARCHAR (8) NOT NULL,
	cep_destino VARCHAR (8) NOT NULL,
	nome_destinatario VARCHAR (50) NOT NULL,
	vl_total_frete NUMERIC NOT NULL,
	data_prevista_entrega TIMESTAMP NOT NULL,
	data_consulta TIMESTAMP NOT NULL
);