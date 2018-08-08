create database projeto1;
use projeto1;

create table disciplinas(
	cod_disciplina varchar(8) not null,
    nome_disciplina varchar (30) not null,
    ch_disciplina char(1) not null,
    primary key(cod_disciplina)
);

create table turmas(
	disciplina varchar(8) not null,
    cod_turma char(3) not null,
    dh_turma varchar(16) not null,
    primary key(cod_turma),
    constraint foreign key(disciplina)
    references disciplinas(cod_disciplina)
);

create table cursos(
	cod_curso int not null,
    nome_curso varchar(30) not null,
    primary key (cod_curso)
);

create table alunos(
	matricula varchar(12) not null,
    nome_aluno varchar(50) not null,
    curso int not null,
    primary key(matricula),
    constraint foreign key(curso)
    references cursos(cod_curso)
);