-- Fake Data generated in: https://www.4devs.com.br/gerador_conta_bancaria
insert into account(id, agency, account_number, balance, account_limit, account_type) values(sq_account.NEXTVAL,'1611','653997',1500,500,'SPECIAL');
insert into account(id, agency, account_number, balance, account_limit, account_type) values(sq_account.NEXTVAL,'3260','572990',4000,1000,'PREMIUM');
insert into account(id, agency, account_number, balance, account_limit, account_type) values(sq_account.NEXTVAL,'2132','226520',250,100,'DEFAULT');
insert into account(id, agency, account_number, balance, account_limit, account_type) values(sq_account.NEXTVAL,'1010','123456',12509.72,1000,'PREMIUM');

-- Fake Data generated in: https://www.4devs.com.br/gerador_de_pessoas
insert into customer(id,name, surname, sex, account_id) values(sq_customer.NEXTVAL,'Heitor','Victor Drumond','M',1);
insert into customer(id,name, surname, sex, account_id) values(sq_customer.NEXTVAL,'Louise','Marli Costa','F',2);
insert into customer(id,name, surname, sex, account_id) values(sq_customer.NEXTVAL,'Mariah','Fabiana Sara Jesus','F',3);
insert into customer(id,name, surname, sex, account_id) values(sq_customer.NEXTVAL,'Larah','Alencar','F',4);

insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'D',150,100,'PGTO LUZ','2021-05-10',3);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'C',430,1930,'DEP. EM CONTA','2021-06-15',1);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'D',270,3730,'PGTO FATURA','2021-06-12',2);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'C',325,2255,'ADIANT. SAL.','2021-06-20',1);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'C',437,4167,'REND','2021-06-15',2);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'C',900,1000,'doc 237.5992elisangela batist','2021-06-01',3);
insert into bankstatement(id,transaction_type, value, balance, history, movement_date, customer_id) values(sq_bankstatement.NEXTVAL'D',1247,2920,'cel pag tit banco 237','2021-06-18',2);
