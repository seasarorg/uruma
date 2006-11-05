select empno,ename,job,mgr,hiredate,
sal,comm,e.deptno,e.versionno,d.dname,m.ename as mname
from emp e left outer join dept d on e.deptno = d.deptno left outer join emp m on e.mgr = m.empno
where
empno = /*empno*/7788