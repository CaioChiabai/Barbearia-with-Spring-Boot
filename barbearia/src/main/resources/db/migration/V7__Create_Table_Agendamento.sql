CREATE TABLE IF NOT EXISTS `agendamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `hora_inicio` time(6) NOT NULL,
  `status` enum('CANCELADO','EM_ABERTO','FINALIZADO') DEFAULT NULL,
  `id_cliente` bigint NOT NULL,
  `id_funcionario_procedimento` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5nfg6jbph7b9cj8eqq75f9f9k` (`id_cliente`),
  KEY `FKgpuerwhxqjarxtxh1txus9ej8` (`id_funcionario_procedimento`),
  CONSTRAINT `FK5nfg6jbph7b9cj8eqq75f9f9k` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKgpuerwhxqjarxtxh1txus9ej8` FOREIGN KEY (`id_funcionario_procedimento`) REFERENCES `funcionario_procedimento` (`id`)
);
