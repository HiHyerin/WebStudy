drop table project_goods_buy;

CREATE TABLE project_goods_buy(
   bno NUMBER,
   gno NUMBER,
   id VARCHAR2(20),
   account NUMBER,
   total_price NUMBER,
   buy_ok CHAR(1),
   CONSTRAINT pgb_bno_pk PRIMARY KEY(bno),
   CONSTRAINT pgb_gno_fk FOREIGN KEY(gno)
   REFERENCES goods_all(no),
   CONSTRAINT pgb_account_ck CHECK(account>0),
   CONSTRAINT pgb_tp_ck CHECK(total_price>0),
   CONSTRAINT pgb_bo_ck CHECK(buy_ok IN('y','n'))
);
ALTER TABLE project_goods_buy ADD CONSTRAINT pgb_id_fk FOREIGN KEY(id) 
REFERENCES project_member(id);

ALTER TABLE project_goods_buy add regdate Date default sysdate;
create sequence pgb_bno_seq
    start with 1
    INCREMENT by 1
    NOCYCLE
    NOCACHE;
    
    
desc project_goods_buy;