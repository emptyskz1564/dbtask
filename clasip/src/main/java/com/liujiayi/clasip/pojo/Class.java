package com.liujiayi.clasip.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 *  班级实体类
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("Class")
public class Class implements Serializable {
    String cid;
    String className;
<<<<<<< HEAD
=======
    String teacher;
    String time;
    String info;

//    public Class() {
//    }
>>>>>>> 4bd44049fa2855ce9f8491a3bacb924267a20dd9
}
