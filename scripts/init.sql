CREATE TABLE Movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
    director VARCHAR(255),
    genre VARCHAR(255),
    year INT,
    rating_average DECIMAL(2,1) DEFAULT 0.0,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE Movies
ADD CONSTRAINT unique_movie UNIQUE (title, director, year);

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,    
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Assessments (
    id SERIAL PRIMARY KEY,
    id_movie INT REFERENCES Movies(id),
    id_user INT REFERENCES Users(id),
    rating FLOAT,
    comment TEXT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE Assessments
ADD CONSTRAINT unique_assessments UNIQUE (id_movie, id_user);

CREATE OR REPLACE FUNCTION update_rating()
RETURNS TRIGGER AS $$
BEGIN
	WITH count_average 
	AS
	(
		SELECT (SUM(rating)/COUNT(rating)) as average
		FROM Assessments
		WHERE id_movie = NEW.id_movie
	)

	UPDATE Movies SET rating_average = (SELECT average FROM count_average) WHERE id = NEW.id_movie;
  
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_rating_average
AFTER INSERT ON Assessments
FOR EACH ROW EXECUTE PROCEDURE update_rating();

INSERT INTO Movies (title, description, director, genre, year)
VALUES ('A Origem', 'Um ladrão profissional que rouba informações ao infiltrar-se no subconsciente de suas vítimas é oferecido a chance de ter seu passado criminal apagado como pagamento por uma tarefa aparentemente impossível: "inception", a implantação de outra ideia na mente de uma pessoa.', 'Christopher Nolan', 'Ficção Científica', 2010),
       ('O Poderoso Chefão', 'A saga da família Corleone e a ascensão de Michael Corleone como o patriarca.', 'Francis Ford Coppola', 'Crime', 1972),
       ('Pulp Fiction', 'As vidas de dois assassinos de aluguel, um boxeador e um casal de bandidos se entrelaçam em quatro contos de violência e redenção.', 'Quentin Tarantino', 'Crime', 1994),
       ('Clube da Luta', 'Um homem insone de escritório e um saboneteiro carismático formam um clube de luta clandestino que se transforma em algo muito mais.', 'David Fincher', 'Drama', 1999),
       ('Forrest Gump', 'A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.', 'Robert Zemeckis', 'Drama', 1994),
       ('Matrix', 'Um programador de computador é arrancado de sua vida mundana e se torna o salvador da humanidade enquanto luta contra seres de outra dimensão.', 'Lana Wachowski, Lilly Wachowski', 'Ficção Científica', 1999),
       ('Um Sonho de Liberdade', 'Dois homens condenados formam uma ligação ao longo de vários anos, encontrando consolo e eventual redenção através de atos de decência comum.', 'Frank Darabont', 'Drama', 1994),
       ('Batman: O Cavaleiro das Trevas', 'Quando a ameaça conhecida como Coringa causa estragos e caos nas pessoas de Gotham, Batman deve aceitar uma das maiores provações psicológicas e físicas de sua habilidade para combater a injustiça.', 'Christopher Nolan', 'Ação', 2008),
       ('O Senhor dos Anéis: O Retorno do Rei', 'Gandalf e Aragorn lideram o Mundo dos Homens contra o exército de Sauron para atrair seu olhar do Frodo e Sam enquanto eles se aproximam do Monte Doom com o Um Anel.', 'Peter Jackson', 'Aventura', 2003),
       ('Star Wars: Episódio V - O Império Contra-Ataca', 'Depois que o Império começa a destruir a Rebelião, Luke Skywalker começa o treinamento Jedi com Yoda. Seus amigos aceitam refúgio do predador Darth Vader e empregam um plano para resgatar Han Solo do príncipe Jabba.', 'Irvin Kershner', 'Ação', 1980),
       ('O Senhor dos Anéis: A Sociedade do Anel', 'Um hobbit manso do Condado e oito companheiros partem em uma jornada para destruir o poderoso Um Anel e salvar a Terra-média do Senhor das Trevas.', 'Peter Jackson', 'Aventura', 2001),
       ('Star Wars: Episódio IV - Uma Nova Esperança', 'Luke Skywalker junta-se a Jedi, um contrabandista, um Wookiee e dois droides para salvar o universo do Império, enquanto também tenta resgatar a Princesa Leia do malvado Darth Vader.', 'George Lucas', 'Ação', 1977),
       ('O Senhor dos Anéis: As Duas Torres', 'Enquanto Frodo e Sam se aproximam de Mordor com a ajuda do Gollum, a divisão ainda leal da Sociedade faz uma resistência contra Sauron e seus novos aliados, Saruman e seus hordas de Isengard.', 'Peter Jackson', 'Aventura', 2002),
       ('Interestelar', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço na tentativa de garantir a sobrevivência da humanidade.', 'Christopher Nolan', 'Aventura', 2014),
       ('Parasita', 'Uma família pobre e desempregada torna-se obcecada por uma família rica e insinua-se em suas vidas.', 'Bong Joon Ho', 'Thriller', 2019);
	   
INSERT INTO Users (email, password, isAdmin)
VALUES 	('admin@example.com', 'password2', TRUE),
		('admin2@example.com', 'password6', TRUE),
		('user1@example.com', 'password1', FALSE),
		('user2@example.com', 'password3', FALSE),
		('user3@example.com', 'password4', FALSE),
		('user4@example.com', 'password5', FALSE),
		('user5@example.com', 'password7', FALSE),
		('user6@example.com', 'password8', FALSE),
		('user7@example.com', 'password9', FALSE),
		('user8@example.com', 'password10', FALSE),
		('user9@example.com', 'password11', FALSE),
		('user10@example.com', 'password12', FALSE),
		('user11@example.com', 'password13', FALSE),
		('user12@example.com', 'password14', FALSE),
		('user13@example.com', 'password15', FALSE),
		('user14@example.com', 'password16', FALSE),
		('user15@example.com', 'password17', FALSE),
		('user16@example.com', 'password18', FALSE),
		('user17@example.com', 'password19', FALSE),
		('user18@example.com', 'password20', FALSE);
	   
INSERT INTO Assessments (id_movie, id_user, rating, comment)
VALUES (1, 1, 8.5, 'Ótimo filme com uma trama complexa e interessante.'),
       (2, 2, 9.5, 'Um clássico absoluto. Marlon Brando está incrível.'),
       (3, 3, 8.0, 'Divertido e único. Uma das melhores atuações de Samuel L. Jackson.'),
       (4, 4, 9.0, 'Um dos meus filmes favoritos. Brad Pitt está excelente.'),
       (5, 5, 8.5, 'História inspiradora com uma grande atuação de Tom Hanks.'),
       (6, 6, 9.5, 'Filme revolucionário que mudou o gênero de ficção científica.'),
       (7, 7, 9.0, 'Uma história de esperança e amizade.'),
       (8, 8, 9.5, 'Christian Bale e Heath Ledger estão incríveis neste filme.'),
       (9, 9, 9.0, 'Um final épico para a trilogia.'),
       (10, 10, 9.5, 'O melhor filme da trilogia original de Star Wars.'),
       (11, 11, 9.0, 'Um começo fantástico para a trilogia.'),
       (12, 12, 9.5, 'O filme que começou tudo.'),
       (13, 13, 9.0, 'Continua a grande história iniciada no primeiro filme.'),
       (14, 14, 9.5, 'Uma jornada épica através do espaço e do tempo.'),
       (15, 15, 9.0, 'Um olhar fascinante sobre a luta de classes.'),
       (1, 16, 8.0, 'Intrigante e cheio de reviravoltas.'),
       (2, 17, 9.0, 'Um dos melhores filmes de todos os tempos.'),
       (3, 18, 8.5, 'Tarantino no seu melhor.'),
       (4, 19, 9.0, 'Um filme que faz você pensar.'),
       (5, 20, 8.5, 'Emocionante do começo ao fim.'),
       (6, 1, 9.0, 'Um marco na ficção científica.'),
       (7, 2, 9.5, 'Uma história poderosa e emocionante.'),
       (8, 3, 9.0, 'O melhor filme de super-herói já feito.'),
       (9, 4, 9.5, 'Uma conclusão satisfatória para a trilogia.'),
       (10, 5, 9.0, 'A sequência que superou o original.'),
       (11, 6, 9.5, 'Uma aventura épica e emocionante.'),
       (12, 7, 9.0, 'Onde tudo começou.'),
       (13, 8, 9.5, 'Tão bom quanto o primeiro.'),
       (14, 9, 9.0, 'Visualmente deslumbrante e emocionalmente envolvente.'),
       (15, 10, 9.5, 'Um retrato chocante da sociedade moderna.'),
       (1, 11, 8.5, 'Nolan realmente sabe como contar uma história.'),
       (2, 12, 9.0, 'Um filme que todo mundo deveria ver.'),
       (3, 13, 8.5, 'Estiloso e cheio de ótimas atuações.'),
       (4, 14, 9.0, 'Um filme que desafia as convenções.'),
       (5, 15, 8.5, 'Hanks entrega uma de suas melhores atuações.'),
       (6, 16, 9.0, 'Um filme que redefine o gênero.'),
       (7, 17, 9.5, 'Uma história que toca o coração.'),
       (8, 18, 9.0, 'Nolan e Bale em sua melhor forma.'),
       (9, 19, 9.5, 'Um final digno para a trilogia.'),
       (10, 20, 9.0, 'A melhor sequência de Star Wars.'),
       (11, 1, 9.5, 'Uma jornada épica que vale a pena.'),
       (12, 2, 9.0, 'O início de uma era.'),
       (13, 3, 9.5, 'Uma sequência digna do original.'),
       (14, 4, 9.0, 'Um filme que desafia a percepção da realidade.'),
       (15, 5, 9.5, 'Um retrato perturbador da sociedade.');
