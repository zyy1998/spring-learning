package com.github.zyy1998.springlearning.springbase.cache;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private Long userId;
    private String userName;
}
