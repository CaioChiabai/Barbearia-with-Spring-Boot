CREATE TABLE IF NOT EXISTS `procedimento` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `nome` varchar(100) NOT NULL,
    `preco` double NOT NULL,
    `duracao` time(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
);