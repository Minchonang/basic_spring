package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "dept" })
public class Emp {
   @Id
   int empno;
   String ename;
   String job;
   Integer mgr;
   String hiredate;
   int sal;
   Integer comm;

   // 외래키 -> Eager
   @ManyToOne
   @JoinColumn(name = "deptno") // dept_deptno
   Dept dept;

}
