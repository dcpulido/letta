DROP DATABASE IF EXISTS `lettaG4`; 

CREATE DATABASE `lettaG4`;

CREATE TABLE `lettaG4`.`events` (
        `id` int NOT NULL AUTO_INCREMENT,
        `name` varchar(140) NOT NULL,
        `description` varchar(500) NOT NULL,
        `place` varchar(140) DEFAULT NULL,
        `date` timestamp NULL DEFAULT NULL,
		`idCreador` int DEFAULT -1,
		`categoria` varchar(20) DEFAULT 'Otros',
	    `foto` varchar(500) DEFAULT NULL,
		
        PRIMARY KEY (`id`)
);
CREATE TABLE `lettaG4`.`users` (
        `id` int NOT NULL AUTO_INCREMENT ,
        `name` varchar(50) NOT NULL,
        `email` varchar(100) NOT NULL,
        `pass` varchar(80) DEFAULT NULL,
        PRIMARY KEY (`id`)
);
CREATE TABLE `lettaG4`.`users_on_events` (
        `userId` int NOT NULL,
        `eventId` int NOT NULL,
        PRIMARY KEY (`userId`,`eventId`),
        FOREIGN KEY (`userId`) REFERENCES `lettaG4`.`users`(`id`),
        FOREIGN KEY (`eventId`) REFERENCES `lettaG4`.`events`(`id`)
);



GRANT ALL ON `lettaG4`.* TO 'lettaG4'@'localhost' IDENTIFIED BY 'lettaG4';

INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('evento que no deberia aparecer','Evento de deportes','Ourense, Bar Amancio','2016-01-17 18:30:00',-1,'https://s-media-cache-ak0.pinimg.com/736x/20/f6/32/20f63216e3f130e9643d82f990582eba.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Estreno Star Wars','Evento de cine','Coruña, Bar Digital','2016-04-17 20:30:00',2,'http://img.lum.dolimg.com/v1/images/starwars_551c43f4.jpeg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Concierto Linkin Park','Evento de música','Leon, Bar Musical','2016-05-31 22:00:00',3,'http://img.prntscr.com/img?url=http://i.imgur.com/0trRtLG.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Obra Teatral Las bicicletas son para el verano','Evento de teatro','Ourense, Bar Teatro','2016-07-18 18:30:00',4,'https://s-media-cache-ak0.pinimg.com/236x/d0/3c/89/d03c8943b8e1431c818a6f73f4701b82.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Quinto Libro de Juego de tronos','Evento de literatura','Santiago, Cafeteria Abrente','2016-05-18 20:30:00',5,'http://img.prntscr.com/img?url=http://i.imgur.com/5aXK3Uy.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Programa Ahora Caigo 20/05/2016','Evento de television','Bar Television','2016-05-20 22:30:00',6,'http://img.prntscr.com/img?url=http://i.imgur.com/rBRBey4.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Prueba BTT Vigo','Evento de otros','Vigo, Bar Cuvi','2016-08-19 20:30:00',7,'http://img.prntscr.com/img?url=http://i.imgur.com/LINKXXO.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Final Copa del Rey','Evento de deportes','Ourense, Bar Graduado','2016-06-17 18:30:00',-1,'http://img.prntscr.com/img?url=http://i.imgur.com/w8Cm7wh.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Pelicula El libro de la Selva','Evento de cine','Lugo, Bar Cacique','2016-05-22 20:30:00',2,'http://img.prntscr.com/img?url=http://i.imgur.com/kTlbHg8.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Disco Colplay','Evento de música','Verin, Bar Alegre','2016-07-25 14:00:00',3,'http://img.prntscr.com/img?url=http://i.imgur.com/Cy1Ehma.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Carrera MotoGP','Evento de Deportes','Bertamirans, Bar Berta','2016-08-18 18:30:00',4,'http://img.prntscr.com/img?url=http://i.imgur.com/nwSob4L.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Libro Inferno','Evento de literatura','La guardia, Bar Pizarra','2016-10-01 20:30:00',5,'http://img.prntscr.com/img?url=http://i.imgur.com/62AY7qx.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Programa Gran Prix','Evento de television','Melide, Bar Internacional','2016-09-03 22:30:00',6,'http://img.prntscr.com/img?url=http://i.imgur.com/hjw2grZ.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Dorna','Evento de otros','Ribeira, Bar Dorna','2016-07-19 20:30:00',7,'http://img.prntscr.com/img?url=http://i.imgur.com/saEWC5c.png');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Final Roland Garros','Evento de deportes','Madrid, Bar Deportes','2016-05-23 18:30:00',-1,'http://img.prntscr.com/img?url=http://i.imgur.com/C15yQQi.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Estreno Capitan America','Evento de cine','Ourense, Bar Bekas','2016-05-31 20:30:00',2,'http://img.prntscr.com/img?url=http://i.imgur.com/TcZAErj.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Programa Gran Prix','Evento de serie','Chantada, Internacional','2017-09-03 22:30:00',6,'http://img.prntscr.com/img?url=http://i.imgur.com/LRgdJMm.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Dorna','Evento de otros','Paris, Bar Dorna','2016-07-19 20:30:00',7,'http://img.prntscr.com/img?url=http://i.imgur.com/H2E23Uy.png');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Final Roland Garros','Evento de deportes','Madrid, Bar PP','2016-06-23 18:30:00',-1,'http://img.prntscr.com/img?url=http://i.imgur.com/0pt37On.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Programa Gran Prix','Evento de serie','Valladolid, Internacional','2017-09-03 22:30:00',6,'http://img.prntscr.com/img?url=http://i.imgur.com/LRgdJMm.jpg');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Dorna','Evento de otros','Tui, Bar Dorna','2016-07-19 20:30:00',7,'http://img.prntscr.com/img?url=http://i.imgur.com/H2E23Uy.png');
INSERT INTO `lettaG4`.`events` (`name`,`description`,`place`,`date`,`idCreador`,`foto`) 
	VALUES ('Final Roland Garros','Evento de deportes','Madrid, Bar Podemos','2016-06-23 18:30:00',-1,'http://img.prntscr.com/img?url=http://i.imgur.com/0pt37On.jpg');


INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Diego','diego@esei.uvigo.es','diegopass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Juan','juan@esei.uvigo.es','juanpass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Pedro','pedro@esei.uvigo.es','pedropass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Maria','maria@esei.uvigo.es','mariapass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Lucia','lucia@esei.uvigo.es','luciapass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Monica','monica@esei.uvigo.es','monicapass');
INSERT INTO `lettaG4`.`users` (`name`,`email`,`pass`) 
	VALUES ('Silvia','silvia@esei.uvigo.es','silviapass');
	

INSERT INTO `lettaG4`.`users` (`id`,`name`,`email`,`pass`) 
	VALUES (-1,'Anonimo','anonimo@anonimo.anonimo','anonimo');
	
	
	
INSERT INTO `lettaG4`.`users_on_events` (`userId`,`eventId`) VALUES (1,1);
INSERT INTO `lettaG4`.`users_on_events` (`userId`,`eventId`) VALUES (1,2);
INSERT INTO `lettaG4`.`users_on_events` (`userId`,`eventId`) VALUES (2,1);
