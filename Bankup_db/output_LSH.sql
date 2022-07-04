Drop table if exists `planets`;
create TABLE `planets` (planet_id INTEGER,name VARCHAR(15) not null,popvalue INTEGER, primary key(planet_id));
Insert into `planets` values(1,'Antares',17);
Insert into `planets` values(2,'Bgztl',8);
Insert into `planets` values(3,'Bismoll',27);
Insert into `planets` values(4,'Braal',8);
Insert into `planets` values(5,'Cargg',20);
Insert into `planets` values(6,'Colu',30);
Insert into `planets` values(7,'Daxam',30);
Insert into `planets` values(8,'Dryad','\N');
Insert into `planets` values(9,'Durla',10);
Insert into `planets` values(10,'Earth','\N');
Insert into `planets` values(11,'Hyrakius','\N');
Insert into `planets` values(12,'Imsk',27);
Insert into `planets` values(13,'Krypton',0);
Insert into `planets` values(14,'Naltor',27);
Insert into `planets` values(15,'Orando',8);
Insert into `planets` values(16,'Phlon','\N');
Insert into `planets` values(17,'Rimbor',30);
Insert into `planets` values(18,'Starhaven',27);
Insert into `planets` values(19,'Talok VIII',27);
Insert into `planets` values(20,'Teall','\N');
Insert into `planets` values(21,'Tharr',27);
Insert into `planets` values(22,'Titan','\N');
Insert into `planets` values(23,'Trom',27);
Insert into `planets` values(24,'Winath',8);
Insert into `planets` values(25,'Xanthu','\N');
Insert into `planets` values(26,'Zoon',34);
Drop table if exists `heroes`;
create TABLE `heroes` (hero_id INTEGER,codename VARCHAR(30),secretIdentity VARCHAR(30),homeWorld_id INTEGER, primary key(hero_id),foreign key(homeWorld_id) references `planets`(planet_id));
Insert into `heroes` values(1,'Cosmic Boy','Rokk Krinn ',4);
Insert into `heroes` values(2,'Lightning Lad ','Garth Ranzz ',24);
Insert into `heroes` values(3,'Saturn Girl ','Imra Ardeen ',22);
Insert into `heroes` values(4,'Triplicate Girl','Luornu Durgo ',5);
Insert into `heroes` values(5,'Phantom Girl','Tinya Wazzo ',2);
Insert into `heroes` values(6,'Superboy','Kal-El (a.k.a. Clark Kent) ',13);
Insert into `heroes` values(7,'Chameleon Boy','Reep Daggle ',9);
Insert into `heroes` values(8,'Colossal Boy ','Gim Allon ',10);
Insert into `heroes` values(9,'Invisible Kid ','Lyle Norg ',10);
Insert into `heroes` values(10,'Star Boy ','Thom Kallor ',25);
Insert into `heroes` values(11,'Kid Quantum ','James Cullen ',1);
Insert into `heroes` values(12,'Brainiac 5','Querl Dox ',6);
Insert into `heroes` values(13,'Supergirl ','Kara Zor-El',13);
Insert into `heroes` values(14,'Laurel Gand ','',7);
Insert into `heroes` values(15,'Sun Boy ','Dirk Morgna ',10);
Insert into `heroes` values(16,'Shrinking Violet','Salu Digby ',12);
Insert into `heroes` values(17,'Bouncing Boy ','Chuck Taine ',10);
Insert into `heroes` values(18,'Ultra Boy','Jo Nah ',17);
Insert into `heroes` values(19,'Mon-El','Lar Gand ',7);
Insert into `heroes` values(20,'Matter-Eater Lad ','Tenzil Kem ',3);
Insert into `heroes` values(21,'Element Lad','Jan Arrah ',21);
Insert into `heroes` values(22,'Lightning Lass','Ayla Ranzz ',24);
Insert into `heroes` values(23,'Dream Girl ','Nura Nal ',14);
Insert into `heroes` values(24,'Ferro Lad ','Andrew Nolan ',10);
Insert into `heroes` values(25,'Karate Kid ','Val Armorr ',10);
Insert into `heroes` values(26,'Princess Projectra','Projectra Wind''zzor ',15);
Insert into `heroes` values(27,'Shadow Lass ','Tasmia Mallor ',19);
Insert into `heroes` values(28,'Chemical King ','Condo Arlik ',16);
Insert into `heroes` values(29,'Timber Wolf','Brin Londo ',26);
Insert into `heroes` values(30,'Wildfire','Drake Burroughs ',10);
Insert into `heroes` values(31,'Tyroc ','Troy Stewart ',10);
Insert into `heroes` values(32,'Dawnstar','',18);
Insert into `heroes` values(33,'Blok ','',8);
Insert into `heroes` values(34,'Invisible Kid II ','Jacques Foccart ',10);
Insert into `heroes` values(35,'White Witch','Mysa Nal ',14);
Insert into `heroes` values(36,'Magnetic Kid ','Pol Krinn ',4);
Insert into `heroes` values(37,'Polar Boy ','Brek Bannin ',21);
Insert into `heroes` values(38,'Quislet ','(an unpronounceable glyph) ',20);
Insert into `heroes` values(39,'Tellus ','Ganglios ',11);
Insert into `heroes` values(40,'Hero on non-existant planet','Nobody',473);
Drop table if exists `powers`;
create TABLE `powers` (hero_id INTEGER,description VARCHAR (100), primary key(hero_id,description),foreign key(hero_id) references `heroes`(hero_id));
Insert into `powers` values(1,'Magnetism manipulation');
Insert into `powers` values(1,'control and generation of magnetic fields');
Insert into `powers` values(2,'Electrical manipulation');
Insert into `powers` values(2,'control and generation of electrical fields');
Insert into `powers` values(3,'Telepathy');
Insert into `powers` values(3,'ability to read and control minds');
Insert into `powers` values(4,'Ability to split into three bodies');
Insert into `powers` values(5,'Intangibility');
Insert into `powers` values(6,'Flight');
Insert into `powers` values(6,'Invulnerability');
Insert into `powers` values(6,'Superhuman Strength');
Insert into `powers` values(6,'Superhuman Speed');
Insert into `powers` values(6,'Super vision, consisting of');
Insert into `powers` values(6,'X-Ray Vision');
Insert into `powers` values(6,'Telescopic/Microscopic Vision');
Insert into `powers` values(6,'Heat Vision');
Insert into `powers` values(6,'Super-hearing');
Insert into `powers` values(6,'Super Breath (including Freeze Breath)');
Insert into `powers` values(6,'Eidetic memory');
Insert into `powers` values(6,'Regeneration');
Insert into `powers` values(6,'Longevity');
Insert into `powers` values(6,'Other enhanced physical senses (including olfaction)');
Insert into `powers` values(7,'Shapeshifting');
Insert into `powers` values(8,'Ability to grow to gigantic size');
Insert into `powers` values(9,'Invisibility to the naked eye');
Insert into `powers` values(10,'Ability to increase the mass of objects');
Insert into `powers` values(11,'Ability to cast stasis fields');
Insert into `powers` values(12,'12th level intelligence');
Insert into `powers` values(13,'Flight');
Insert into `powers` values(13,'Invulnerability');
Insert into `powers` values(13,'Superhuman Strength');
Insert into `powers` values(13,'Superhuman Speed');
Insert into `powers` values(13,'Super vision, consisting of');
Insert into `powers` values(13,'X-Ray Vision');
Insert into `powers` values(13,'Telescopic/Microscopic Vision');
Insert into `powers` values(13,'Heat Vision');
Insert into `powers` values(13,'Super-hearing');
Insert into `powers` values(13,'Super Breath (including Freeze Breath)');
Insert into `powers` values(13,'Eidetic memory');
Insert into `powers` values(13,'Regeneration');
Insert into `powers` values(13,'Longevity');
Insert into `powers` values(13,'Other enhanced physical senses (including olfaction)');
Insert into `powers` values(14,'Flight');
Insert into `powers` values(14,'Invulnerability');
Insert into `powers` values(14,'Superhuman Strength');
Insert into `powers` values(14,'Superhuman Speed');
Insert into `powers` values(14,'Super vision, consisting of');
Insert into `powers` values(14,'X-Ray Vision');
Insert into `powers` values(14,'Telescopic/Microscopic Vision');
Insert into `powers` values(14,'Heat Vision');
Insert into `powers` values(14,'Super-hearing');
Insert into `powers` values(14,'Super Breath (including Freeze Breath)');
Insert into `powers` values(14,'Eidetic memory');
Insert into `powers` values(14,'Regeneration');
Insert into `powers` values(14,'Longevity');
Insert into `powers` values(14,'Other enhanced physical senses (including olfaction)');
Insert into `powers` values(15,'light generation');
Insert into `powers` values(15,'Heat generation');
Insert into `powers` values(16,'Ability to shrink to microscopic size');
Insert into `powers` values(17,'Super-bouncing');
Insert into `powers` values(18,'Super-strength');
Insert into `powers` values(18,'super-speed');
Insert into `powers` values(18,'flight');
Insert into `powers` values(18,'invulnerability');
Insert into `powers` values(18,'flash vision');
Insert into `powers` values(18,'penetra-vision');
Insert into `powers` values(19,'Flight');
Insert into `powers` values(19,'Invulnerability');
Insert into `powers` values(19,'Superhuman Strength');
Insert into `powers` values(19,'Superhuman Speed');
Insert into `powers` values(19,'Super vision, consisting of');
Insert into `powers` values(19,'X-Ray Vision');
Insert into `powers` values(19,'Telescopic/Microscopic Vision');
Insert into `powers` values(19,'Heat Vision');
Insert into `powers` values(19,'Super-hearing');
Insert into `powers` values(19,'Super Breath (including Freeze Breath)');
Insert into `powers` values(19,'Eidetic memory');
Insert into `powers` values(19,'Regeneration');
Insert into `powers` values(19,'Longevity');
Insert into `powers` values(19,'Other enhanced physical senses (including olfaction)');
Insert into `powers` values(20,'Can eat any substance');
Insert into `powers` values(21,'Elemental transmutation');
Insert into `powers` values(22,'Electrical manipulation');
Insert into `powers` values(22,'control and generation of electrical fields');
Insert into `powers` values(23,'Precognition');
Insert into `powers` values(24,'Ability to transform into iron');
Insert into `powers` values(24,'superhuman strength');
Insert into `powers` values(24,'invulnerability');
Insert into `powers` values(25,'Mastery of all known martial arts');
Insert into `powers` values(26,'Generation of illusions');
Insert into `powers` values(27,'Shadow-casting');
Insert into `powers` values(28,'Control over the rate of chemical reactions');
Insert into `powers` values(29,'Superhuman agility');
Insert into `powers` values(29,'Superhuman strength');
Insert into `powers` values(30,'Energy blasts');
Insert into `powers` values(30,'Energy manipulation');
Insert into `powers` values(30,'flight.');
Insert into `powers` values(31,'Sonic power that creates unusual effects');
Insert into `powers` values(32,'Interstellar tracking');
Insert into `powers` values(32,'unaided space travel');
Insert into `powers` values(32,'flight');
Insert into `powers` values(33,'Superhuman strength');
Insert into `powers` values(33,'Superhuman physical resistance');
Insert into `powers` values(500,'deliberately wrong');
Insert into `powers` values(33,'energy absorption');
Insert into `powers` values(34,'Invisibility to the naked eye and to most forms of detection');
Insert into `powers` values(35,'Spellcasting');
Insert into `powers` values(36,'Magnetism manipulation');
Insert into `powers` values(36,'ability to generate and control magnetic fields');
Insert into `powers` values(37,'Cold manipulation');
Insert into `powers` values(37,'ability to absorb heat and produce cold');
Insert into `powers` values(39,'Telepathy');
Insert into `powers` values(39,'telekinesis');
Drop table if exists `missions`;
create TABLE `missions` (name VARCHAR (100),planet_name VARCHAR (15) not null, primary key(name),foreign key(planet_name) references `planets`(name));
Insert into `missions` values('Darkseid','Apocalypse');
Insert into `missions` values('Earth War','Earth');
Insert into `missions` values('Planet Kidnap','Daxam');
Insert into `missions` values('Mission on non-existant planet','Zorgorn');
