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

-- 댓글 => 로그인된 상태에서만 달 수 있게

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '홍길동', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '심청이', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '강동원', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '김유정', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '송중기', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '문동은', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '신예은', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '도경수', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '화사', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '전현무', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '기안84', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '붐', '프로젝트 자유게시판', '댓글형 게시판','1234');

insert into project_freeboard(no, name, subject, content, pwd)
values(pf_no_seq.NEXTVAL, '태연', '프로젝트 자유게시판', '댓글형 게시판','1234');

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



