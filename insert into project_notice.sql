desc project_member;
select * from zipcode;

update project_member set
admin='y'
where id='hong';

commit;

select * from project_member;

desc project_notice;

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),1,'������','������ʷչ� ��ȭ�����帶�� �������!',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',10);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),2,'������','8����(����) ������ �Ա� �簳 �ȳ� (2022�� 11�� 1�� ����)',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',13);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),3,'���߱�','���ѹα� ���ڿ����㰡�� ��� ����(����) �ѽ��� Ȯ�� ���� �ȳ�',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',12);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),4,'Ȳ����','�������� ��Ŀ�������н� ���� ��� ���� ���� ����',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',15);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),5,'������','2021 ����� �λ�������å �ȳ�',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',27);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),6,'������','�������� SNS ��Ī���� �ȳ�',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',32);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),7,'���84','�� 8ȸ �����¡ �������ǰ ������ ���ְ���',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',25);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),8,'������','2023 ���� ������ ���α׷� �ű� �߱� ������',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',24);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),9,'�¿�','2023 ���л� �������� 'TripMate' ���� ����',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',21);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),10,'�赿��','NCT ���� ���� �̺�Ʈ ��÷�ڹ�ǥ',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',16);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),11,'������','�߱��� �����뿪�ȳ��� ������ȭ ��������� ��������',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',65);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),12,'�ֿ���','�����뿪�ȳ��� ������ȭ���� ����� ��������',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',34);

insert into project_notice(no, type, name, subject, content, hit)
values((select NVL(MAX(no)+1,1) from project_notice),13,'�ڳ���','Visit Seoul ����� ������ ���� ��÷�� ��ǥ',
'������ʷչ� ��ȭ�����帶�� �������! �߿������ �ұ��ϰ�..',16);

commit;

select * from project_notice;

select ename, sal, rownum
from emp
where rownum <= 5
order by sal desc;
-- �������� �������� ��� (TOP-N)
select ename, sal rownum
from (select ename, sal from emp order by sal desc)
where rownum<=5;