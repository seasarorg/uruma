SELECT EMP.deptno, EMP.versionno, EMP.comm, EMP.empno, EMP.ename, EMP.hiredate, EMP.job, EMP.mgr, EMP.sal,
department.dname AS dname_0, department.deptno AS deptno_0, department.loc AS loc_0, department.versionno AS versionno_0,
manager.deptno AS deptno_1, manager.versionno AS versionno_1, manager.comm AS comm_1, manager.empno AS empno_1, manager.ename AS ename_1, manager.hiredate AS hiredate_1, manager.job AS job_1, manager.mgr AS mgr_1, manager.sal AS sal_1
FROM EMP LEFT OUTER JOIN DEPT department ON EMP.deptno = department.deptno LEFT OUTER JOIN EMP manager ON EMP.mgr = manager.empno
/*BEGIN*/
where
/*IF dto.empno != null*/and empno = /*dto.empno*/7788/*END*/
/*IF dto.ename != null*/and ename = /*dto.ename*/'SCOTT'/*END*/
/*IF dto.job != null*/and job = /*dto.job*/'ANALYST'/*END*/
/*IF dto.mgr != null*/and mgr = /*dto.mgr*/7566/*END*/
/*IF dto.mname != null*/and m.ename = /*dto.mname*/'KING'/*END*/
/*IF dto.fromHiredate != null*/and hiredate >= /*dto.fromHiredate*/'1982-12-01'/*END*/
/*IF dto.toHiredate != null*/and hiredate <= /*dto.toHiredate*/'1982-12-31'/*END*/
/*IF dto.fromSal != null*/and sal >= /*dto.fromSal*/1000/*END*/
/*IF dto.toSal != null*/and sal <= /*dto.toSal*/4000/*END*/
/*IF dto.deptno != null*/and deptno = /*dto.deptno*/20/*END*/
/*END*/
