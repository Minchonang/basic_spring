package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
// import lombok.ToString;

@Entity
@Data
// @ToString(exclude = { "emps" })
public class Dept {
   @Id
   int deptno;
   String dname;
   String ioc;

   // 양뱡향을 쓸 경우:
   // 외래키 x -> lazy
   @JsonIgnore
   @OneToMany(mappedBy = "dept")
   // @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
   List<Emp> emps = new ArrayList<>();
}
