-- 답변형 게시판
create table replyBoard(
    no NUMBER,
    name VARCHAR2(34)CONSTRAINT rb_name_nn NOT NULL,
    subject VARCHAR2(4000)CONSTRAINT rb_sub_nn NOT NULL,
    content CLOB CONSTRAINT rb_cont_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT rb_pwd_nn NOT NULL,
    regdate DATE DEFAULT sysdate,
    hit NUMBER,
    group_id NUMBER default 0,
    group_step NUMBER default 0,
    group_tab NUMBER default 0,
    root NUMBER default 0,
    depth NUMBER default 0,
    CONSTRAINT rb_no_pk PRIMARY KEY(no)
);

CREATE SEQUENCE rb_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    
/* P.414
    group_id : 답변 그룹             id     group_step      group_tab      root     depth
        AAAAA                       1           1             0            0        2
            => BBBBB                1           2             1            1        0
            => CCCCC                1           3             1            1        1
                => DDDDD            1           4             2            3        0
        EEEEE                       2           1             0
            => KKKKK                2           2             1
        
    group_step
    group_tab
    root
    depth
*/  


DESC replyboard;

insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'홍길동','답변형게시판','답변형게시판 = chapter 15','1234',1);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'심청이','답변형게시판','답변형게시판 = chapter 15','1234',2);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'을지문덕','답변형게시판','답변형게시판 = chapter 15','1234',3);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'유관순','답변형게시판','답변형게시판 = chapter 15','1234',4);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'박문수','답변형게시판','답변형게시판 = chapter 15','1234',5);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'이순신','답변형게시판','답변형게시판 = chapter 15','1234',6);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'인어공주','답변형게시판','답변형게시판 = chapter 15','1234',7);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'가재','답변형게시판','답변형게시판 = chapter 15','1234',8);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'코끼리','답변형게시판','답변형게시판 = chapter 15','1234',9);
insert into replyboard(no,name,subject,content,pwd,group_id)
values(rb_no_seq.nextval,'광개토대왕','답변형게시판','답변형게시판 = chapter 15','1234',10);
insert into replyboard(no,name,subject,content,pwd,group_id,group_step, group_tab)
values(rb_no_seq.nextval,'주몽','답변형게시판','답변형게시판 = chapter 15','1234',10,1,1);
insert into replyboard(no,name,subject,content,pwd,group_id,group_step, group_tab)
values(rb_no_seq.nextval,'황희','답변형게시판','답변형게시판 = chapter 15','1234',10,2,2);

commit;