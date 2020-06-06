CREATE TABLE pessoa
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(250) NOT NULL,
    cpf             VARCHAR(11)  NOT NULL,
    data_nascimento DATETIME     NOT NULL
);

INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Jorge Pereira', '69580734011', sysdate);
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Cairo Pereira', '83139364016', sysdate);
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Joana Pereira', '82464055000', sysdate);