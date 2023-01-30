desc project_member;
select * from zipcode;

update project_member set
admin='y'
where id='hong';

commit;

select * from project_member;

desc project_notice;

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),1,'강동원','서울빛초롱및 광화문광장마켓 연장공지!',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',10);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),2,'서강준','8개국(지역) 무비자 입국 재개 안내 (2022년 11월 1일 시행)',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',13);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),3,'송중기','대한민국 전자여행허가제 대상 국가(지역) 한시적 확대 시행 안내',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',12);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),4,'황정민','지방상생형 디스커버서울패스 제휴 희망 지역 모집 공고',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',15);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),5,'한혜주','2021 서울시 민생경제대책 안내',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',27);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),6,'송혜교','비짓서울 SNS 사칭주의 안내',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',32);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),7,'기안84','제 8회 서울상징 관광기념품 공모전 개최공고',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',25);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),8,'한혜진','2023 서울 팀빌딩 프로그램 신규 발굴 공모전',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',24);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),9,'태연','2023 대학생 서포터즈 'TripMate' 모집 공고',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',21);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),10,'김동현','NCT 영상 공유 이벤트 당첨자발표',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',16);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),11,'전현무','중국어 관광통역안내사 역량강화 교육대상자 모집공고',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',65);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),12,'주우재','관광통역안내사 역량강화교육 대상자 모집공고',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',34);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),13,'박나래','Visit Seoul 사용자 만족도 조사 당첨자 발표',
'서울빛초롱및 광화문광장마켓 연장공지! 추운날씨에도 불구하고..',16);

commit;

select * from project_notice;

select ename, sal, rownum
from emp
where rownum <= 5
order by sal desc;
-- 상위순위 가져오는 방법 (TOP-N)
select ename, sal rownum
from (select ename, sal from emp order by sal desc)
where rownum<=5;