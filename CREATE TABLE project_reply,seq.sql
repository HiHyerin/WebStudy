create sequence par_rno_seq
    start with 1
    increment by 1
    nocycle
    nocache;
    
drop table project_reply;

CREATE TABLE project_reply(
   rno NUMBER,
   bno NUMBER,
   id  VARCHAR2(20),
   name VARCHAR2(34) CONSTRAINT pr_name_nn NOT NULL,
   msg CLOB CONSTRAINT pr_msg_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   group_id NUMBER CONSTRAINT pr_gi_nn NOT NULL,
   group_step NUMBER DEFAULT 0,
   group_tab NUMBER DEFAULT 0,
   root NUMBER DEFAULT 0,
   depth NUMBER DEFAULT 0,
   CONSTRAINT pr_rno_pk PRIMARY KEY(rno),
   CONSTRAINT pr_bno_fk FOREIGN KEY(bno)
   REFERENCES project_freeboard(no),
   CONSTRAINT pr_id_fk FOREIGN KEY(id)
   REFERENCES project_member(id)
);

CREATE SEQUENCE pr_rno_seq
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;

desc project_reply;

commit;

select * from project_reply;


-- 飘罚黎记(老褒贸府) 捞秦
create table card(
    no NUMBER primary key,
    id varchar2(20) not null,
    val number
);

create table point(
    no NUMBER primary key,
    id varchar2(20) not null,
    point NUMBER
);

COMMIT;

select * from card;
select * from point;
