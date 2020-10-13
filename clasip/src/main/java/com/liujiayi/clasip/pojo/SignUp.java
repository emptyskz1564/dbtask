package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("SignUp")
public class SignUp {
    Integer sid;
    Integer cid;
    Boolean isCome;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
