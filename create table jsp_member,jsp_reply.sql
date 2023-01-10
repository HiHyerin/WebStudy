create table jsp_member(
    id varchar2(20),
    pwd varchar2(10) CONSTRAINT jm_pwd_nn NOT null,
    name varchar2(34) CONSTRAINT jm_name_nn NOT null,
    sex varchar2(10),
    constraint jm_id_pk primary key(id),
    constraint jm_sex_ck check(sex IN('����','����'))
);

insert into jsp_member values('hong','1234','ȫ�浿','����');
insert into jsp_member values('shim','1234','��û��','����');
insert into jsp_member values('park','1234','�ڹ���','����');
insert into jsp_member values('kang','1234','������','����');
insert into jsp_member values('kim','1234','������','����');
commit;

create table jsp_reply(
    no number,
    fno number,
    id varchar2(20),
    name varchar2(34) constraint jr_name_nn not null,
    msg CLOB constraint jr_msg_nn not null,
    regdate DATE default sysdate,
    star number default 0,
    constraint jr_no_pk primary key(no),
    constraint jr_fno_fk foreign key(fno)
    references project_food(fno),
    constraint jr_id_fk foreign key(id)
    references jsp_member(id)
);

create sequence jr_no_seq
    start with 1
    increment by 1
    nocycle
    nocache;