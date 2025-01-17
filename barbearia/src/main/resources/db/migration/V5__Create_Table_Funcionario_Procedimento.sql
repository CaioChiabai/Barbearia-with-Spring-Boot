CREATE TABLE IF NOT EXISTS `funcionario_procedimento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_funcionario` bigint NOT NULL,
  `id_procedimento` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvcwnrex7ihgqctkslj67skby` (`id_funcionario`),
  KEY `FKipfvkmh4fkl8q5ushot7vhdt1` (`id_procedimento`),
  CONSTRAINT `FKhvcwnrex7ihgqctkslj67skby` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `FKipfvkmh4fkl8q5ushot7vhdt1` FOREIGN KEY (`id_procedimento`) REFERENCES `procedimento` (`id`)
);