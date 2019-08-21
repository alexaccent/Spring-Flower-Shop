CREATE SCHEMA "FLOWERSHOP";
--------------------------------------------------------
--  DDL for Table FLOWER
--------------------------------------------------------
CREATE TABLE FLOWERSHOP.FLOWER
(
    ID LONG PRIMARY KEY,
    PRICE DECIMAL NOT NULL,
    NUMBER VARCHAR(255) NOT NULL,
    IMAGEURL VARCHAR(255) NOT NULL,
    FLOWER_ID LONG REFERENCES FLOWERSHOP.FLOWER(ID)
);
COMMIT;