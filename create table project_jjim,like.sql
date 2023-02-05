
desc project_jjim;
desc project_like;

create table project_jjim(
    jno number,
    no number,
    id varchar2(20),
    constraint pj_jno_pk primary key(jno),
    constraint pj_id_fk foreign key(id)
    references project_member(id)
);

create table project_like(
    lno number,
    no number,
    id varchar2(20),
    constraint pj_lno_pk primary key(lno),
    constraint pl_id_fk foreign key(id)
    references project_member(id)
);

select * from food_location
where type like '%±‚≈∏%';

alter table goods_all ADD account NUMBER default 0;
DESC goods_all;

select * from goods_all order by no asc;