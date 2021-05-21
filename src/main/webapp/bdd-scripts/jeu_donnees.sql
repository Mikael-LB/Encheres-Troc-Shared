-- JEU DE DONNEES TEST --

USE JEE_ENCHERES_TROC
GO

-- UTILISATEURS --

INSERT INTO UTILISATEURS VALUES ('Rico', 'COVER', 'Harry', 'harry.cover@random.com', '0602030405', '8 Rue des Flans', '44000', 'Nantes', 'kzd42ds2', 110, 0),
								('lacarotte22', 'ROTERAPE', 'Lucas', 'lucas.roterape@random.com', '0625887532', '12 Avenue du Potager', '29290', 'Saint-Renan', 'carotdel22', 80, 0),
								('Lamalade', 'NUZYTE', 'Lucie', 'lucie.nuzyte@random.com', '0782232312', '2Bis Rue des pigeons', '35131', 'Pont-Péan', 'kld1213l', 250, 0),
								('Pasfute35', 'BECILE', 'Ghislain', 'ghislain.becile@random.com', '0299421212', '17 Pl. Marechal Houpé', '01160', 'Druillat', 'motdepasse1', 10, 0),
								('Asia-Tique', 'GLADECHE', 'Aubin', 'aubin.gladeche@random.com', '0623122312', '1 Rue des Indes', '95800', 'Courdimanche', 'nke12354', 60, 0),
								('Wouf89', 'BERMANN', 'Aldo', 'aldo.bermann@random.com', '0625521278', '7 Avenue Croquette', '35000', 'Rennes', 'croquettesmiammiam', 175, 0);

INSERT INTO CATEGORIES VALUES ('Mode'),('Décoration'),('Ameubleument'),('Informatique'),('Multimedia'),('Animaux'),('Jeux & Jouets'),('Sport'),('Bricolage'), ('Autres');

INSERT INTO ARTICLES_VENDUS VALUES	('Pantalon monojambe','Pantalon en parfait état, très peu porté pour cause de possession de deux jambes',DATEADD (DAY , -12 , GETDATE()),DATEADD (DAY , -4 , GETDATE()),25, 80, 4,1),
									('Chaise en papier maché','Magnifique chaise en papier maché (mâchage artisanal à la salive ecoreponsable). Sa couleur rose saura ravir les coeurs de vos convives. Attention il n''est pas possible de s''asseoir dessus.',DATEADD (DAY , -10 , GETDATE()),DATEADD (DAY , 2 , GETDATE()),50, null, 1,3),
									('Calculatrice inversée','Calculatrice générant une opération aléatoire donnant comme résultat le nombre entré. Sûrement très pratique pour faire des chose. Je la troque car je n''en ai pas encore trouvé l''utilité',DATEADD (DAY , -8 , GETDATE()),DATEADD (DAY , 5 , GETDATE()),12, null, 2,4),
									('Vase sans fond','Superbe vase en cristal pour mettre vos fleurs. Le vase n''a en revanche pas de fond, il n''est donc pas possible de le remplir',DATEADD (DAY , -3 , GETDATE()),DATEADD (DAY , 10 , GETDATE()),20, null, 2,2),
									('Boite de raviolis','Je cède ma boîte de raviolis entamée hier soir. Je n''avais pas fait attention aux ingrédients et il y avait du basilic dans les ingrédients. Je n''ai pas réussi à finir, je déteste le basilic.',DATEADD (DAY , -2 , GETDATE()),DATEADD (DAY , 5 , GETDATE()),3, null, 5,10),
									('Cartes à jouer double-face','Cartes à jouer dont la valeur est imprimée des deux cotés. Rend les parties de poker moins intéressantes mais plus rapides',GETDATE(),DATEADD (DAY , 15 , GETDATE()),10, null, 3,7);

INSERT INTO ENCHERES VALUES (DATEADD(DAY, -8, GETDATE()), 50, 1, 3),(DATEADD(DAY, -8, GETDATE()), 80, 1, 1),
							(DATEADD(DAY, -8, GETDATE()), 55, 2, 2), (DATEADD(DAY, -3, GETDATE()), 70, 2, 4), (DATEADD(HOUR, -8, GETDATE()), 75, 2, 2),
							(DATEADD(DAY, -7, GETDATE()), 15, 3, 1), (DATEADD(DAY, -2, GETDATE()), 20, 3, 5),
							(DATEADD(HOUR, -20, GETDATE()), 110, 5, 6);

INSERT INTO RETRAITS VALUES (1, '17 Pl. Marechal Houpé', '01160', 'Druillat'),(2, '8 Rue des Flans', '44000', 'Nantes'),(3, '12 Avenue du Potager', '29290', 'Saint-Renan'),(4, '12 Avenue du Potager', '29290', 'Saint-Renan'),(5, '3 Rue Népalaise', '95800', 'Courdimanche'),(6, '2Bis Rue des pigeons', '35131', 'Pont-Péan');

