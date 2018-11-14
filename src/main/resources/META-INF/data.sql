INSERT INTO Gender VALUES (1, "Férfi")
INSERT INTO Gender VALUES (2, "Nő")

INSERT INTO Genre VALUES (1, "Akció")
INSERT INTO Genre VALUES (2, "Vígjáték")
INSERT INTO Genre VALUES (3, "Horror")
INSERT INTO Genre VALUES (4, "Dráma")
INSERT INTO Genre VALUES (5, "Sci-fi")

INSERT INTO AgeClassification VALUES (1, "12+")
INSERT INTO AgeClassification VALUES (2, "16+")
INSERT INTO AgeClassification VALUES (3, "18+")

INSERT INTO Rules VALUES (1, "Hozzászólások írása során tilos trágár, rasszista, gyűlöletkeltő\r\nszavakat illetve kifejezéseket használni. Amennyiben ez megtörténik,\r\na hozzászólás tartalma moderátorok által törölve lesz!\r\n\r\nÚj témák létrehozásakor a hozzászólásokra vonatkozó szabályokat\r\nbe kell tartani, valamint a témának az oldal tartalmához kapcsolódnia kell,\r\nilletve olyan témát nem szabad létrehozni, amilyen már létezik.\r\nHa ezen szabályok nincsenek betartva, a téma moderátorok által törölve lesz!\r\n\r\nA szabályok nem ismerete nem mentesít azok betartása alól!")

INSERT INTO ErrorReport VALUES (1, "Hiba.", false, "2018. 05. 02. 22:08:32", null)

INSERT INTO Article VALUES (10, "aaa\r\nbbb\r\n\r\nccc", null, false, true, "test")
