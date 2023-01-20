CREATE TABLE HC_CAMP_2 (
   cno       NUMBER          CONSTRAINT hc_camp_cno_pk PRIMARY KEY,
   image       VARCHAR2(3000),
   name      VARCHAR2(300),
   tel       VARCHAR2(300),
   address    VARCHAR2(300),
   camp_env    VARCHAR2(300),
   camp_type    VARCHAR2(300),
   homepage    VARCHAR2(300),
   period       VARCHAR2(300),
   day       VARCHAR2(100),
   hit       NUMBER          DEFAULT 0
);

-- 생성 X CREATE SEQUENCE hc_camp_like_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;

CREATE TABLE HC_CAMP_LIKE_2 (
   crno    NUMBER          CONSTRAINT hc_camp_like_crno_pk    PRIMARY KEY,
   image    VARCHAR2(3000)    CONSTRAINT hc_camp_like_image_nn    NOT NULL,
   name    VARCHAR2(300)    CONSTRAINT hc_camp_like_name_nn    NOT NULL,
   mid    VARCHAR2(100),
   cno    NUMBER
);

CREATE SEQUENCE hc_camp_like_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_CAMP_LIKE_2 ADD CONSTRAINT hc_camp_like_cno_fk FOREIGN KEY (cno) REFERENCES HC_CAMP_2 (cno);

CREATE TABLE HC_CAMP_REVIEW_2 (
   clno    NUMBER          CONSTRAINT hc_camp_review_clno_pk       PRIMARY KEY,
   writer    VARCHAR2(100)    CONSTRAINT hc_camp_review_wirter_nn    NOT NULL,
   content CLOB          CONSTRAINT hc_camp_review_content_nn    NOT NULL,
   regdate DATE          DEFAULT SYSDATE,
   mid    VARCHAR2(100),
   cno    NUMBER
);

CREATE SEQUENCE hc_camp_review_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_CAMP_REVIEW_2 ADD CONSTRAINT hc_camp_review_cno_fk FOREIGN KEY (cno) REFERENCES HC_CAMP_2 (cno);
ALTER TABLE HC_CAMP_REVIEW_2 ADD CONSTRAINT hc_camp_review_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_MEMBER_2 (
   mid       VARCHAR2(100)    CONSTRAINT hc_mem_mid_pk       PRIMARY KEY,
   password   VARCHAR2(100)   CONSTRAINT hc_mem_pwd_nn      NOT NULL,
   name       VARCHAR2(100)    CONSTRAINT hc_mem_name_nn       NOT NULL,
   email       VARCHAR2(100)    CONSTRAINT hc_mem_email_uk       UNIQUE,
   tel         VARCHAR2(100)   CONSTRAINT hc_mem_tel_nn      NOT NULL,
   birth      VARCHAR2(100)   CONSTRAINT hc_mem_birth_nn      NOT NULL,
   postcode    VARCHAR2(200)     CONSTRAINT hc_mem_postcode_nn   NOT NULL,
   home_addr    VARCHAR2(200)    CONSTRAINT hc_mem_homeAddr_nn   NOT NULL,
   detail_addr VARCHAR2(200)   CONSTRAINT hc_mem_detailAddr_nn   NOT NULL,
   sex         VARCHAR2(10)   CONSTRAINT hc_mem_sex_ck      CHECK (sex IN('남자', '여자')),
    ROLE       VARCHAR2(10)    CONSTRAINT hc_mem_role_ck      CHECK (role IN('관리자', '일반회원'))
);

CREATE SEQUENCE hc_member_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;

CREATE TABLE HC_ORDER_2 (
   ono       NUMBER          CONSTRAINT hc_order_ono_pk       PRIMARY KEY,
   code       VARCHAR2(100)    CONSTRAINT hc_order_code_nn    NOT NULL,
   status       VARCHAR2(100)    CONSTRAINT hc_order_status_nn    NOT NULL,
   price       NUMBER,
   order_date    DATE          DEFAULT SYSDATE,
   mid       VARCHAR2(100)
);

CREATE SEQUENCE hc_order_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ORDER_2 ADD CONSTRAINT hc_order_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_DELIVERY_2 (
   dno       NUMBER          CONSTRAINT hc_delivery_dno_pk          PRIMARY KEY,
   name       VARCHAR2(100)    CONSTRAINT hc_delivery_name_nn          NOT NULL,
   tel       VARCHAR2(100)    CONSTRAINT hc_delivery_tel_nn          NOT NULL,
   postcode    VARCHAR2(200)     CONSTRAINT hc_delivery_postcode_nn      NOT NULL,
   home_addr    VARCHAR2(200)    CONSTRAINT hc_delivery_homeAddr_nn      NOT NULL,
   detail_addr VARCHAR2(200)   CONSTRAINT hc_delivery_detailAddr_nn   NOT NULL
);

ALTER TABLE HC_DELIVERY_2 ADD CONSTRAINT hc_delivery_dno_fk FOREIGN KEY (dno) REFERENCES HC_ORDER_2 (ono);

CREATE TABLE HC_ORDER_ITEM_2 (
   oino       NUMBER          CONSTRAINT hc_order_itme_oino_pk    PRIMARY KEY,
   image       VARCHAR2(3000)    CONSTRAINT hc_order_item_image_nn    NOT NULL,
   name       VARCHAR2(100)    CONSTRAINT hc_order_item_name_nn    NOT NULL,
   price       NUMBER          CONSTRAINT hc_order_item_price       NOT NULL,
   quantity    NUMBER          CONSTRAINT hc_order_item_quantity    NOT NULL,
   ino       NUMBER,
   ono       NUMBER
);

CREATE SEQUENCE hc_order_item_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER table HC_ORDER_ITEM_2 ADD CONSTRAINT hc_order_item_ino_fk FOREIGN KEY (ino) REFERENCES HC_ITEM_2 (ino);
ALTER table HC_ORDER_ITEM_2 ADD CONSTRAINT hc_order_item_ino_fk FOREIGN KEY (ono) REFERENCES HC_ORDER_2 (ono);

CREATE TABLE HC_ITEM_CATEGORY_2 (
   icno    NUMBER          CONSTRAINT hc_item_cat_icno_pk    PRIMARY KEY,
   name    VARCHAR2(100)    CONSTRAINT hc_item_cat_name_nn    NOT NULL,
   code    VARCHAR2(100)    CONSTRAINT hc_item_cat_code_nn    NOT NULL,
   pcode    VARCHAR2(100)    CONSTRAINT hc_item_cat_pcode_nn NOT NULL
);

-- 생성 X CREATE SEQUENCE hc_item_category_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;

CREATE TABLE HC_ITEM_2 (
   ino       NUMBER          CONSTRAINT hc_item_ino_pk       PRIMARY KEY,
   image       VARCHAR2(3500)    CONSTRAINT hc_item_image_nn    NOT NULL,
   name        VARCHAR2(300)    CONSTRAINT hc_item_name_nn       NOT NULL,
   description VARCHAR2(3500)    CONSTRAINT hc_item_desc_nn       NOT NULL,
   status      VARCHAR2(100)   CONSTRAINT hc_item_status_nn    NOT NULL,
   brand      VARCHAR2(100)   CONSTRAINT hc_item_brand_nn      NOT NULL,
   price      NUMBER         DEFAULT 0,
   sale      NUMBER         DEFAULT 0,
   stock      NUMBER         DEFAULT 0,
   like_cnt   NUMBER         DEFAULT 0,
   jjim_cnt   NUMBER         DEFAULT 0,
   icno       NUMBER
);

-- 생성 X CREATE SEQUENCE hc_item_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ITEM_2 ADD CONSTRAINT hc_item_icno_fk FOREIGN KEY (icno) REFERENCES HC_ITEM_CATEGORY_2 (icno);

CREATE TABLE HC_ITEM_QA_2 (
   qano       NUMBER          CONSTRAINT hc_item_qa_qano_pk       PRIMARY KEY,
   title       VARCHAR2(100)    CONSTRAINT hc_item_qa_title_nn       NOT NULL,
   content    CLOB          CONSTRAINT hc_item_qa_content_nn    NOT NULL,
   TYPE       VARCHAR2(100)   CONSTRAINT hc_item_qa_type_ck       CHECK (TYPE IN('상품', '배송')),
   LOCK       VARCHAR2(10)    CONSTRAINT hc_item_qa_lock_ck       CHECK (LOCK IN('Y', 'N')),
   password    VARCHAR2(100),
   hit       NUMBER          DEFAULT 0,
   regdate    DATE          DEFAULT SYSDATE,
   ino       NUMBER,
   mid       VARCHAR2(100)
);

CREATE SEQUENCE hc_item_qa_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ITEM_QA_2 ADD CONSTRAINT hc_item_qa_ino_fk FOREIGN KEY (ino) REFERENCES HC_ITEM_2 (ino);
ALTER TABLE HC_ITEM_QA_2 ADD CONSTRAINT hc_item_qa_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_ITEM_QA_REPLY_2 (
   qarno    NUMBER         CONSTRAINT hc_item_qa_reply_qarno_pk       PRIMARY KEY,
   writer   VARCHAR2(100) CONSTRAINT hc_item_qa_reply_qarno_nn       NOT NULL,
   content CLOB         CONSTRAINT hc_item_qa_reply_content_nn    NOT NULL,
   regdate DATE         DEFAULT SYSDATE,
   qano    NUMBER
);
CREATE SEQUENCE hc_item_qa_reply_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ITEM_QA_REPLY_2 ADD CONSTRAINT hc_item_qa_reply_qano_fk FOREIGN KEY (qano) REFERENCES HC_ITEM_QA_2 (qano);

CREATE TABLE HC_ITEM_REVIEW_2 (
   rno    NUMBER          CONSTRAINT hc_item_review_rno_pk       PRIMARY KEY,
   writer    VARCHAR2(100)    CONSTRAINT hc_item_review_writer_nn    NOT NULL,
   content CLOB          CONSTRAINT hc_item_review_content_nn    NOT NULL,
   created   DATE          DEFAULT SYSDATE,
   updated   DATE          DEFAULT SYSDATE,
   hit    NUMBER          DEFAULT 0,
   ino    NUMBER,
   mid    VARCHAR2(100)
);

CREATE SEQUENCE hc_item_reivew_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ITEM_REVIEW_2 ADD CONSTRAINT hc_item_review_ino_fk FOREIGN KEY (ino) REFERENCES HC_ITEM_2 (ino);
ALTER TABLE HC_ITEM_REVIEW_2 ADD CONSTRAINT hc_item_review_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_ITEM_LIKE_2 (
   lno    NUMBER          CONSTRAINT hc_item_like_lno_pk       PRIMARY KEY,
   image    VARCHAR2(3500)    CONSTRAINT hc_item_like_image_nn    NOT NULL,
   name    VARCHAR2(300)    CONSTRAINT hc_item_like_name_nn    NOT NULL,
   price    NUMBER          CONSTRAINT hc_item_like_price_nn    NOT NULL,
   ino    NUMBER,
   mid    VARCHAR2(100)
);

CREATE SEQUENCE hc_item_like_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_ITEM_LIKE_2 ADD CONSTRAINT hc_item_like_ino_fk FOREIGN KEY (ino) REFERENCES HC_ITEM_2 (ino);
ALTER TABLE HC_ITEM_LIKE_2 ADD CONSTRAINT hc_item_like_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_CART_2 (
   cno     NUMBER       CONSTRAINT hc_cart_cno_pk    PRIMARY KEY,
   image     VARCHAR2(3500) CONSTRAINT hc_cart_image_nn NOT NULL,
   name     VARCHAR2(300)    CONSTRAINT hc_cart_name_nn    NOT NULL,
   price     NUMBER       CONSTRAINT hc_cart_price_nn NOT NULL,
   quantity NUMBER       DEFAULT 1,
   ino     NUMBER,
   mid     VARCHAR2(100)
);

CREATE SEQUENCE hc_cart_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_CART_2 ADD CONSTRAINT hc_cart_ino_fk FOREIGN KEY (ino) REFERENCES HC_ITEM_2 (ino);
ALTER TABLE HC_CART_2 ADD CONSTRAINT hc_cart_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_BOARD_2 (
   bno    NUMBER          CONSTRAINT hc_board_bno_pk       PRIMARY KEY,
   writer    VARCHAR2(100)    CONSTRAINT hc_board_writer_nn    NOT NULL,
   title   VARCHAR2(100)    CONSTRAINT hc_board_title_nn   NOT NULL,
   content CLOB          CONSTRAINT hc_board_content_nn    NOT NULL,
   regdate DATE         DEFAULT SYSDATE,
   hit      NUMBER         DEFAULT 0,
   mid    VARCHAR2(100)
);

CREATE SEQUENCE hc_board_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_BOARD_2 ADD CONSTRAINT hc_board_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_BOARD_REPLY_2 (
   rno    NUMBER          CONSTRAINT hc_board_reply_rno_pk   PRIMARY KEY,
   writer    VARCHAR2(100)    CONSTRAINT hc_board_reply_writer_nn NOT NULL,
   content CLOB          CONSTRAINT hc_board_reply_content_nn NOT NULL,
   regdate DATE          DEFAULT SYSDATE,
   bno    NUMBER,
   mid    VARCHAR2(100)
);

CREATE SEQUENCE hc_board_reply_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;
ALTER TABLE HC_BOARD_REPLY_2 ADD CONSTRAINT hc_board_reply_mid_fk FOREIGN KEY (bno) REFERENCES HC_BOARD_2 (bno);
ALTER TABLE HC_BOARD_REPLY_2 ADD CONSTRAINT hc_board_reply_mid_fk FOREIGN KEY (mid) REFERENCES HC_MEMBER_2 (mid);

CREATE TABLE HC_NOTICE_2 (
   nno    NUMBER         CONSTRAINT hc_notice_nno_pk PRIMARY KEY,
   title    VARCHAR2(100) CONSTRAINT hc_notice_title_nn NOT NULL,
   content CLOB         CONSTRAINT hc_notice_content_nn NOT NULL,
   regdate DATE         DEFAULT SYSDATE,
   hit    NUMBER         DEFAULT 0
);

CREATE SEQUENCE hc_notice_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 NOCACHE;

@@C:\Users\song\Documents\카카오톡 받은 파일\HC_ITEM_2.sql
