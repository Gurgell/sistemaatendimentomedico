# sistema de atendimento medico

Projeto desenvolvido na disciplina de Programação de Computadores III, utilizando o Maven como gerenciador de dependências, JavaFX para a criação das interfaces gráficas, JSON como formato de persistência de dados e JUnit Jupiter para a realização de testes unitários. O projeto segue a arquitetura MVC.

A ideia central era criar uma solução que permitisse o cadastro de médicos, especialidades, pacientes, planos de saúde e a gestão de uma agenda de consultas. Para cadastrar um médico, é necessário primeiro registrar sua especialidade. Um médico pode possuir múltiplas especialidades, e uma especialidade pode ser compartilhada por vários médicos.

Os pacientes podem ou não possuir um plano de saúde. Ao marcar uma consulta na agenda, é necessário especificar se a consulta é particular ou coberta pelo plano de saúde. Além disso, é necessário indicar o médico, a especialidade, a data, a hora e o paciente envolvido na consulta.
