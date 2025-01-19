INSERT INTO cliente (id, nome) VALUES (1, 'Cliente 1');
INSERT INTO funcionario (id, nome) VALUES (1, 'Funcionario 1');
INSERT INTO procedimento (id, descricao, preco) VALUES (1, 'Corte de cabelo', 50.00);

INSERT INTO funcionario_procedimento (id, funcionario_id, procedimento_id) VALUES (1, 1, 1);

INSERT INTO agendamento (id, cliente_id, funcionario_procedimento_id, data, hora_inicio)
VALUES (1, 1, 1, '2025-01-20', '10:00');
