# sistema de atendimento medico

Projeto desenvolvido na disciplina Programação de computadores III utilizando MAVEN, JavaFX para as telas, JSON como persistência e JUnit Jupiter para testes unitários.
Arquitetura MVC.

A idéia era criar uma solução onde fosse possível Cadastrar médicos, Especialidades, Pacientes, Planos de saúde e também uma Agenda de consultas.
Para cadastrar um médico antes é necessário cadastrar uma especialidade. Um médico pode ter várias especialidades, e uma especialidade pode pertencer a vários médicos.

Os pacientes podem ou não ter um plano de saúde. Na hora de marcar uma consulta através da agenda deve informar se a consulta é particular ou pelo plano.
Deve ser especificado o médico a especialidade para essa consulta, informando data hora e o paciente.

