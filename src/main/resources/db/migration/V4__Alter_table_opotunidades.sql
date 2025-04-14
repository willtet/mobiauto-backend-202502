ALTER TABLE tb_oportunidades
    ALTER COLUMN status SET NOT NULL;

ALTER TABLE tb_oportunidades
    ADD COLUMN data_atribuicao TIMESTAMP,
    ADD COLUMN data_conclusao TIMESTAMP;

ALTER TABLE tb_oportunidades
    ADD COLUMN usuario_id BIGINT,
    ADD CONSTRAINT fk_usuario_responsavel FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id);