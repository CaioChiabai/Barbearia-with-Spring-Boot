CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `data_contratacao` date NOT NULL,
  `salario` decimal(38,2) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2ges1byljuyectvh5jphyam0l` (`user_id`),
  CONSTRAINT `FKcqup7miqq41c96qere1hw6c1d` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);