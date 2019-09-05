--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.CUSTOMER
   (
	    LOGIN VARCHAR(30) PRIMARY KEY NOT NULL,
	    PASSWORD VARCHAR(30) NOT NULL,
	    PHONE VARCHAR(30) NOT NULL,
	    ADDRESS VARCHAR(30) NOT NULL,
	    BALANCE DECIMAL NOT NULL CHECK (BALANCE >= 0),
	    DISCOUNT INT CHECK (DISCOUNT >= 0)
   );

--------------------------------------------------------
--  DDL for Table ADMINISTRATOR
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.ADMINISTRATOR
   (
        LOGIN VARCHAR(30) PRIMARY KEY NOT NULL,
        PASSWORD VARCHAR(30) NOT NULL,
        ACCESS_LEVEL VARCHAR(30)
   );
--------------------------------------------------------
--  Insert data
--------------------------------------------------------
INSERT INTO FLOWERSHOP.ADMINISTRATOR (LOGIN, PASSWORD, ACCESS_LEVEL) VALUES ('admin', 'admin123', 'read and write');
INSERT INTO FLOWERSHOP.CUSTOMER (LOGIN, PASSWORD, PHONE, ADDRESS, BALANCE, DISCOUNT) VALUES ('Alex', '1234', '8-903-408-93-80', 'Tver', 2000.0, 30);

COMMIT;