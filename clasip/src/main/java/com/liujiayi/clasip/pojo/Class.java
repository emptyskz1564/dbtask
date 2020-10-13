package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("Class")
public class Class {
    Integer cid;
    String classCode;
    String name;
    List<Student> holdStudents;
    Teacher holdTeacher;
}
