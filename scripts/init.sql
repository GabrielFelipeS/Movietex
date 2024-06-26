create database movietex;

\c movietex

CREATE TABLE Movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
    director VARCHAR(255),
    genre VARCHAR(255),
	duration VARCHAR(255),
    year INT,
    rating_average DECIMAL(2,1) DEFAULT 0.0,
	poster VARCHAR(255) DEFAULT './img/capas/divertida_mente.webp' NOT NULL,
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
    id_movie INT REFERENCES Movies(id) ON DELETE CASCADE,
    id_user INT REFERENCES Users(id) ON DELETE CASCADE,
    userName VARCHAR(255),
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

INSERT INTO Movies (title, description, director, genre, year, duration, poster)
VALUES ('A Origem', 'Um ladrão profissional que rouba informações ao infiltrar-se no subconsciente de suas vítimas é oferecido a chance de ter seu passado criminal apagado como pagamento por uma tarefa aparentemente impossível: "inception", a implantação de outra ideia na mente de uma pessoa.', 'Christopher Nolan', 'Ficção Científica', 2010, 148, './img/capas/origem.jpg'),
       ('O Poderoso Chefão', 'A saga da família Corleone e a ascensão de Michael Corleone como o patriarca.', 'Francis Ford Coppola', 'Crime', 1972, 175, './img/capas/poderoso_chefao.jpg'),
       ('Pulp Fiction', 'As vidas de dois assassinos de aluguel, um boxeador e um casal de bandidos se entrelaçam em quatro contos de violência e redenção.', 'Quentin Tarantino', 'Crime', 1994, 154, './img/capas/pulp_fiction.jpg'),
       ('Clube da Luta', 'Um homem insone de escritório e um saboneteiro carismático formam um clube de luta clandestino que se transforma em algo muito mais.', 'David Fincher', 'Drama', 1999, 139, './img/capas/clube_luta.jpg'),
       ('Forrest Gump', 'A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.', 'Robert Zemeckis', 'Drama', 1994, 142, './img/capas/forrest_gump.jpg'),
       ('Matrix', 'Um programador de computador é arrancado de sua vida mundana e se torna o salvador da humanidade enquanto luta contra seres de outra dimensão.', 'Lana Wachowski, Lilly Wachowski', 'Ficção Científica', 1999, 136, './img/capas/matrix.jpg'),
       ('Um Sonho de Liberdade', 'Dois homens condenados formam uma ligação ao longo de vários anos, encontrando consolo e eventual redenção através de atos de decência comum.', 'Frank Darabont', 'Drama', 1994, 142, './img/capas/sonho_liberdade.jpeg'),
       ('Batman: O Cavaleiro das Trevas', 'Quando a ameaça conhecida como Coringa causa estragos e caos nas pessoas de Gotham, Batman deve aceitar uma das maiores provações psicológicas e físicas de sua habilidade para combater a injustiça.', 'Christopher Nolan', 'Ação', 2008, 152, './img/capas/batman.jpg'),
       ('O Senhor dos Anéis: O Retorno do Rei', 'Gandalf e Aragorn lideram o Mundo dos Homens contra o exército de Sauron para atrair seu olhar do Frodo e Sam enquanto eles se aproximam do Monte Doom com o Um Anel.', 'Peter Jackson', 'Aventura', 2003, 201, './img/capas/senhor_aneis_3.jpeg'),
       ('Star Wars: Episódio V - O Império Contra-Ataca', 'Depois que o Império começa a destruir a Rebelião, Luke Skywalker começa o treinamento Jedi com Yoda. Seus amigos aceitam refúgio do predador Darth Vader e empregam um plano para resgatar Han Solo do príncipe Jabba.', 'Irvin Kershner', 'Ação', 1980, 124, './img/capas/star_wars_v.jpg'),
       ('O Senhor dos Anéis: A Sociedade do Anel', 'Um hobbit manso do Condado e oito companheiros partem em uma jornada para destruir o poderoso Um Anel e salvar a Terra-média do Senhor das Trevas.', 'Peter Jackson', 'Aventura', 2001, 178, './img/capas/senhor_aneis.jpg'),
       ('Star Wars: Episódio IV - Uma Nova Esperança', 'Luke Skywalker junta-se a Jedi, um contrabandista, um Wookiee e dois droides para salvar o universo do Império, enquanto também tenta resgatar a Princesa Leia do malvado Darth Vader.', 'George Lucas', 'Ação', 1977, 121, './img/capas/star_wars_iv.jpg'),
       ('O Senhor dos Anéis: As Duas Torres', 'Enquanto Frodo e Sam se aproximam de Mordor com a ajuda do Gollum, a divisão ainda leal da Sociedade faz uma resistência contra Sauron e seus novos aliados, Saruman e seus hordas de Isengard.', 'Peter Jackson', 'Aventura', 2002, 179, './img/capas/senhor_aneis_2.jpg'),
       ('Interestelar', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço na tentativa de garantir a sobrevivência da humanidade.', 'Christopher Nolan', 'Aventura', 2014, 169, './img/capas/interestelar.jpg'),
       ('Parasita', 'Uma família pobre e desempregada torna-se obcecada por uma família rica e insinua-se em suas vidas.', 'Bong Joon Ho', 'Thriller', 2019, 132, './img/capas/parasita.jpg');

INSERT INTO Users (email, password, name, isAdmin)
VALUES 
    ('admin@example.com', 'password2', 'Alice Smith', TRUE),
    ('admin2@example.com', 'password6', 'Bob Johnson', TRUE),
    ('user1@example.com', 'password1', 'Charlie Brown', FALSE),
    ('user2@example.com', 'password3', 'David Williams', FALSE),
    ('user3@example.com', 'password4', 'Eva Jones', FALSE),
    ('user4@example.com', 'password5', 'Frank Miller', FALSE),
    ('user5@example.com', 'password7', 'Grace Davis', FALSE),
    ('user6@example.com', 'password8', 'Hannah Wilson', FALSE),
    ('user7@example.com', 'password9', 'Isaac Moore', FALSE),
    ('user8@example.com', 'password10', 'Jack Taylor', FALSE),
    ('user9@example.com', 'password11', 'Katie Anderson', FALSE),
    ('user10@example.com', 'password12', 'Liam Thomas', FALSE),
    ('user11@example.com', 'password13', 'Mia Martinez', FALSE),
    ('user12@example.com', 'password14', 'Noah Robinson', FALSE),
    ('user13@example.com', 'password15', 'Olivia Clark', FALSE),
    ('user14@example.com', 'password16', 'Paul Rodriguez', FALSE),
    ('user15@example.com', 'password17', 'Quinn Lewis', FALSE),
    ('user16@example.com', 'password18', 'Ruby Lee', FALSE),
    ('user17@example.com', 'password19', 'Sophia Walker', FALSE),
    ('user18@example.com', 'password20', 'Thomas Hall', FALSE);

	   
INSERT INTO Assessments (id_movie, id_user, rating, userName, comment)
VALUES (1, 1, 8.5, 'User1', 'Ótimo filme com uma trama complexa e interessante.'),
       (2, 2, 9.5, 'User2', 'Um clássico absoluto. Marlon Brando está incrível.'),
       (3, 3, 8.0, 'User3', 'Divertido e único. Uma das melhores atuações de Samuel L. Jackson.'),
       (4, 4, 9.0, 'User4', 'Um dos meus filmes favoritos. Brad Pitt está excelente.'),
       (5, 5, 8.5, 'User5', 'História inspiradora com uma grande atuação de Tom Hanks.'),
       (6, 6, 9.5, 'User6', 'Filme revolucionário que mudou o gênero de ficção científica.'),
       (7, 7, 9.0, 'User7', 'Uma história de esperança e amizade.'),
       (8, 8, 9.5, 'User8', 'Christian Bale e Heath Ledger estão incríveis neste filme.'),
       (9, 9, 9.0, 'User9', 'Um final épico para a trilogia.'),
       (10, 10, 9.5, 'User10', 'O melhor filme da trilogia original de Star Wars.'),
       (11, 11, 9.0, 'User11', 'Um começo fantástico para a trilogia.'),
       (12, 12, 9.5, 'User12', 'O filme que começou tudo.'),
       (13, 13, 9.0, 'User13', 'Continua a grande história iniciada no primeiro filme.'),
       (14, 14, 9.5, 'User14', 'Uma jornada épica através do espaço e do tempo.'),
       (15, 15, 9.0, 'User15', 'Um olhar fascinante sobre a luta de classes.'),
       (1, 16, 8.0, 'User16', 'Intrigante e cheio de reviravoltas.'),
       (2, 17, 9.0, 'User17', 'Um dos melhores filmes de todos os tempos.'),
       (3, 18, 8.5, 'User18', 'Tarantino no seu melhor.'),
       (4, 19, 9.0, 'User19', 'Um filme que faz você pensar.'),
       (5, 20, 8.5, 'User20', 'Emocionante do começo ao fim.'),
       (6, 1, 9.0, 'User21', 'Um marco na ficção científica.'),
       (7, 2, 9.5, 'User22', 'Uma história poderosa e emocionante.'),
       (8, 3, 9.0, 'User23', 'O melhor filme de super-herói já feito.'),
       (9, 4, 9.5, 'User24', 'Uma conclusão satisfatória para a trilogia.'),
       (10, 5, 9.0, 'User25', 'A sequência que superou o original.'),
       (11, 6, 9.5, 'User26', 'Uma aventura épica e emocionante.'),
       (12, 7, 9.0, 'User27', 'Onde tudo começou.'),
       (13, 8, 9.5, 'User28', 'Tão bom quanto o primeiro.'),
       (14, 9, 9.0, 'User29', 'Visualmente deslumbrante e emocionalmente envolvente.'),
       (15, 10, 9.5, 'User30', 'Um retrato chocante da sociedade moderna.'),
       (1, 11, 8.5, 'User31', 'Nolan realmente sabe como contar uma história.'),
       (2, 12, 9.0, 'User32', 'Um filme que todo mundo deveria ver.'),
       (3, 13, 8.5, 'User33', 'Estiloso e cheio de ótimas atuações.'),
       (4, 14, 9.0, 'User34', 'Um filme que desafia as convenções.'),
       (5, 15, 8.5, 'User35', 'Hanks entrega uma de suas melhores atuações.'),
       (6, 16, 9.0, 'User36', 'Um filme que redefine o gênero.'),
       (7, 17, 9.5, 'User37', 'Uma história que toca o coração.'),
       (8, 18, 9.0, 'User38', 'Nolan e Bale em sua melhor forma.'),
       (9, 19, 9.5, 'User39', 'Um final digno para a trilogia.'),
       (10, 20, 9.0, 'User40', 'A melhor sequência de Star Wars.'),
       (11, 1, 9.5, 'User41', 'Uma jornada épica que vale a pena.'),
       (12, 2, 9.0, 'User42', 'O início de uma era.'),
       (13, 3, 9.5, 'User43', 'Uma sequência digna do original.'),
       (14, 4, 9.0, 'User44', 'Um filme que desafia a percepção da realidade.'),
       (15, 5, 9.5, 'User45', 'Um retrato perturbador da sociedade.');
