ALTER TABLE tb_usuarios
  ADD COLUMN revenda_id BIGINT;

ALTER TABLE tb_usuarios
  ADD CONSTRAINT fk_tb_usuarios_revenda FOREIGN KEY (revenda_id)
  REFERENCES tb_revendas(id);