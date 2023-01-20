CREATE table project_freeboard(
    no number,
    name varchar2(34) CONSTRAINT pf1_name_nn not null,
    subject varchar2(4000) CONSTRAINT pf1_subject_nn not null,
    content clob CONSTRAINT pf1_content_nn not null,
    pwd varchar2(10) CONSTRAINT pf1_pwd_nn not null,
    regdate date default sysdate,
    hit number default 0,
    CONSTRAINT pf1_no_pk primary key(no)
);

create SEQUENCE pf_no_seq
    start with 1
    INCREMENT by 1
    NOCYCLE
    NOCACHE;

-- ��� => �α��ε� ���¿����� �� �� �ְ�

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, 'ȫ�浿', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '��û��', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '������', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '������', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '���߱�', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '������', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '�ſ���', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '�����', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, 'ȭ��', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '������', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '���84', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '��', '������Ʈ �����Խ���', '����� �Խ���','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '�¿�', '������Ʈ �����Խ���', '����� �Խ���','1234');

commit;


create table recipe(
    no number,
    title varchar2(1000) CONSTRAINT recipe_title_nn not null,
    poster varchar2(260) CONSTRAINT recipe_poster_nn not null,
    chef varchar2(200) CONSTRAINT recipe_chef_nn not null,
    link varchar2(260),
    hit number default 0,
    constraint recipe_no_pk primary key(no)
);



