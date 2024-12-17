CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `telefone` varchar(30) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt1a18li06719gqn3piuksrndr` (`user_id`),
  CONSTRAINT `FK5928j6vwtyns9yr2cof9658hg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);