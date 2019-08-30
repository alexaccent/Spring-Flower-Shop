--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.CUSTOMER
   (
	    LOGIN VARCHAR(30) PRIMARY KEY NOT NULL,
	    PASSWORD VARCHAR(30) NOT NULL,
	    PHONE VARCHAR(30) NOT NULL,
	    ADDRESS VARCHAR(30) NOT NULL,
	    BALANCE DECIMAL NOT NULL,
	    DISCOUNT INT
   );

--------------------------------------------------------
--  DDL for Table ADMINISTRATOR
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.ADMINISTRATOR
   (
        LOGIN VARCHAR(30) PRIMARY KEY NOT NULL,
        PASSWORD VARCHAR(30) NOT NULL,
        BALANCE DECIMAL NOT NULL,
        ACCESS_LEVEL VARCHAR(30)
   );
--------------------------------------------------------
--  Insert data
--------------------------------------------------------
INSERT INTO FLOWERSHOP.ADMINISTRATOR (LOGIN, PASSWORD, BALANCE, ACCESS_LEVEL) VALUES ('admin', 'admin123', 0.0, 'root');
INSERT INTO FLOWERSHOP.CUSTOMER (LOGIN, PASSWORD, PHONE, ADDRESS, BALANCE) VALUES ('Alex', '1234', '8-903-408-93-80', 'Tver',2000.0);

COMMIT;