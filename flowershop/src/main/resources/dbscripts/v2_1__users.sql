--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.CUSTOMER
   (
        ID LONG PRIMARY KEY,
	    LOGIN VARCHAR(30) NOT NULL,
	    PASSWORD VARCHAR(30) NOT NULL,
	    PHONE VARCHAR(30) NOT NULL,
	    ADDRESS VARCHAR(30) NOT NULL,
	    BALANCE DECIMAL NOT NULL,
	    DISCOUNT INT NOT NULL,
	    CUSTOMER_ID LONG REFERENCES FLOWERSHOP.CUSTOMER(ID),
   );

   ALTER TABLE FLOWERSHOP.CUSTOMER ADD FOREIGN KEY(CUSTOMER_ID) REFERENCES FLOWERSHOP.CUSTOMER(ID);
--------------------------------------------------------
--  DDL for Table ADMINISTRATOR
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.ADMINISTRATOR
   (	ID LONG  PRIMARY KEY,
        LOGIN VARCHAR(30) NOT NULL,
        PASSWORD VARCHAR(30) NOT NULL,
        BALANCE DECIMAL NOT NULL,
        ACCESSLEVEL VARCHAR(30) NOT NULL
   );
--------------------------------------------------------
--  Insert data
--------------------------------------------------------
INSERT INTO FLOWERSHOP.ADMINISTRATOR (ID, LOGIN, PASSWORD, BALANCE, ACCESSLEVEL) VALUES (1, 'admin', 'admin123', 0.0, 'root');
COMMIT;