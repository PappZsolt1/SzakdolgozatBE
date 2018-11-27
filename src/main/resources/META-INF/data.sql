INSERT INTO Gender VALUES (1, "Férfi")
INSERT INTO Gender VALUES (2, "Nő")

INSERT INTO Genre VALUES (1, "Akció")
INSERT INTO Genre VALUES (2, "Vígjáték")
INSERT INTO Genre VALUES (3, "Horror")
INSERT INTO Genre VALUES (4, "Dráma")
INSERT INTO Genre VALUES (5, "Sci-fi")
INSERT INTO Genre VALUES (6, "Thriller")
INSERT INTO Genre VALUES (7, "Krimi")
INSERT INTO Genre VALUES (8, "Dokumentumfilm")
INSERT INTO Genre VALUES (9, "Kalandfilm")
INSERT INTO Genre VALUES (10, "Rajzfilm")
INSERT INTO Genre VALUES (11, "Történelmi film")
INSERT INTO Genre VALUES (12, "Romantikus film")

INSERT INTO AgeClassification VALUES (1, "Bárki megtekintheti")
INSERT INTO AgeClassification VALUES (2, "6+")
INSERT INTO AgeClassification VALUES (3, "12+")
INSERT INTO AgeClassification VALUES (4, "16+")
INSERT INTO AgeClassification VALUES (5, "18+")

INSERT INTO Rules VALUES (1, "Hozzászólások írása során tilos trágár, rasszista, gyűlöletkeltő szavakat illetve kifejezéseket használni. Amennyiben ez megtörténik, a hozzászólás tartalma moderátorok által törölve lesz!\r\n\r\nÚj témák létrehozásakor a hozzászólásokra vonatkozó szabályokat be kell tartani, valamint a témának az oldal tartalmához kapcsolódnia kell, illetve olyan témát nem szabad létrehozni, amilyen már létezik. Ha ezen szabályok nincsenek betartva, a téma moderátorok által törölve lesz!\r\n\r\nA szabályok nem ismerete nem mentesít azok betartása alól!")

INSERT INTO ErrorReport VALUES (1, "Hiba.", false, "2018. 05. 02. 22:08:32")

--INSERT INTO Actor VALUES (10, "bio", "2001. 01. 01.", "New York", "John Doe", LOAD_FILE("D:\images\circle.png"), 1)
--INSERT INTO Movie VALUES (10, "1", null, null, null, null, null, null, null, null, null)
--INSERT INTO Movie_Actor VALUES (10, 10)

INSERT INTO ErrorReport VALUES (2, "Árvíztűrő tükörfúrórgép. Árvíztűrő tükörfúrórgép. Árvíztűrő tükörfúrórgép.", false, "2018. 05. 02. 22:08:32")

--INSERT INTO Series VALUES (10, LOAD_FILE("D:\\images\\circle.png"), 0, 2000, "title", null, null) --works
