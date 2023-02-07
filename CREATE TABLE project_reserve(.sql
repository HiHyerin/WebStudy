-- project_reserve_time
insert into project_reserve_time 
values(1, '10:00');

insert into project_reserve_time 
values(2, '11:00');

insert into project_reserve_time 
values(3, '12:00');

insert into project_reserve_time 
values(4, '13:00');

insert into project_reserve_time 
values(5, '14:00');
insert into project_reserve_time 
values(6, '15:00');
insert into project_reserve_time 
values(7, '16:00');
insert into project_reserve_time 
values(8, '17:00');
insert into project_reserve_time 
values(9, '18:00');
insert into project_reserve_time 
values(10, '19:00');
insert into project_reserve_time 
values(11, '20:00');
insert into project_reserve_time 
values(12, '20:30');
insert into project_reserve_time 
values(13, '21:00');
insert into project_reserve_time 
values(14, '21:30');
insert into project_reserve_time 
values(15, '22:00');
insert into project_reserve_time 
values(16, '22:30');

commit;

desc food_location;
alter table food_location add reserve_day varchar2(100);
select * from food_location;

alter table project_reserve_date MODIFY time varchar2(100);
truncate table project_reserve_date;
select * from project_reserve_date;

desc project_reserve_date;
desc project_reserve_time;
desc project_reserve;
commit;

alter table project_reserve MODIFY rdate varchar2(100);
drop table project_reserve;

commit;


CREATE TABLE project_reserve(
   rno NUMBER,
   fno NUMBER,
   id VARCHAR2(20),
   rdate VARCHAR2(100) CONSTRAINT pro_res_rdate_nn NOT NULL,
   rtime VARCHAR2(20) CONSTRAINT pro_res_rtime_nn NOT NULL,
   inwon NUMBER,
   reserve_no VARCHAR2(20) CONSTRAINT pro_res_ro_nn NOT NULL,
   ok CHAR(1),
   regdate DATE DEFAULT SYSDATE,
   CONSTRAINT pro_res_rno_pk PRIMARY KEY(rno),
   CONSTRAINT pro_res_id_fk FOREIGN KEY(id)
   REFERENCES project_member(id),
   CONSTRAINT pro_res_inwon_ck CHECK(inwon>0)
);
delete from project_reserve;
commit;