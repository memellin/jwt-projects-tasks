# jwt-projects-tasks
Diagrama do projeto:
![image](https://github.com/memellin/jwt-projects-tasks/assets/60275233/c65cee58-10fe-4497-a637-0209cbfd5acd)

# Tecnologias utilizadas

## Back end

- Java - 17
- Spring Boot - 2.6.6
- PostGreSQL - pgadmin15
- JPA / Hibernate
- Maven
- OAuth/JWT

# Como executar o projeto

## Back end

Pré-requisitos: Java 11, PostGreSQL

```
# clonar repositório
git clone https://github.com/memellin/spring-project-management.git

# Inserir dados no PostGreSQL:
# A senha só é criptografada a partir do uso do metodo post no código para o banco de dados, inserindo a senha diretamente pelo banco ela pode receber valores nao criptografados, aqui eu ja estou mandando assim mas voce consegue converter na internet.
INSERT INTO tb_user (name, surname, email, password) VALUES ('Enock', 'Brown', 'enock@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG'); 
INSERT INTO tb_user (name, surname, email, password) VALUES ('Milena', 'Brown', 'milena@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_projects (name, description, start_date, end_date, status) VALUES
  ('Project 1', 'This is the first project.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 MONTH, 1),
  ('Project 2', 'This is the second project.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 MONTHS, 2),
  ('Project 3', 'This is the third project.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 MONTHS, 3),
  ('Project 4', 'This is the fourth project.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 4 MONTHS, 4),
  ('Project 5', 'This is the fifth project.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 5 MONTHS, 5);

INSERT INTO tb_task (description, status, project_id) VALUES
  ('Task 1', 1, 1),
  ('Task 2', 2, 1),
  ('Task 3', 3, 1),
  ('Task 4', 1, 2),
  ('Task 5', 2, 2),
  ('Task 6', 3, 2),
  ('Task 7', 1, 3),
  ('Task 8', 2, 3),
  ('Task 9', 3, 3),
  ('Task 10', 1, 4),
  ('Task 11', 2, 4),
  ('Task 12', 3, 4),
  ('Task 13', 1, 5),
  ('Task 14', 2, 5),
  ('Task 15', 3, 5);

# entrar na pasta do projeto 
cd pasta
# executar o projeto
./mvnw spring-boot:run
