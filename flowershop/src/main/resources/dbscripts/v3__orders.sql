--------------------------------------------------------
--  DDL for Table ORDERS
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.ORDERS
   (
        ID LONG PRIMARY KEY AUTO_INCREMENT,
        OWNER_ORDERS VARCHAR(30),
	    STATUS VARCHAR(30) CHECK (STATUS IN ('CREATED', 'PAID', 'CLOSED')),
	    PRICE DECIMAL,
	    ORDERS_DATE TIMESTAMP,

	    FOREIGN KEY (OWNER_ORDERS) REFERENCES FLOWERSHOP.CUSTOMER(LOGIN)
   );

--------------------------------------------------------
--  DDL for Table FLOWER_ORDER
--------------------------------------------------------
  CREATE TABLE FLOWERSHOP.FLOWER_ORDER
   (
        ID LONG PRIMARY KEY AUTO_INCREMENT,
	    ORDERS_ID LONG,
	    FLOWER_ID LONG,
	    AMOUNT_FLOWERS LONG,
	    FOREIGN KEY (FLOWER_ID) REFERENCES FLOWERSHOP.FLOWER(ID),
	    FOREIGN KEY (ORDERS_ID) REFERENCES FLOWERSHOP.ORDERS(ID)
   );

COMMIT;