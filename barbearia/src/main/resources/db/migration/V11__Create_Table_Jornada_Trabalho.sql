CREATE TABLE IF NOT EXISTS `jornada_trabalho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fim_intervalo` time(6) NOT NULL,
  `fim_jornada` time(6) NOT NULL,
  `inicio_intervalo` time(6) NOT NULL,
  `inicio_jornada` time(6) NOT NULL,
  `id_funcionario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK13n0ts4512dp85p482et3wo52` (`id_funcionario`),
  CONSTRAINT `FK13n0ts4512dp85p482et3wo52` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`)
);