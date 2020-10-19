package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 * 签到表实体类
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("SignUp")
public class SignUp implements Serializable {
    String sid;
    String cid;
    Boolean isCome;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
