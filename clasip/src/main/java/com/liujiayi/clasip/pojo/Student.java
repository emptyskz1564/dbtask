package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 创建时间: 2020-10-12 17:04
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("Student")
public class Student implements Serializable {
    Integer sid;
    String pwd;
    String name;
    String major;
    String className;
    String grade;
}
