package com.yj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String passWord;

    private Integer userType;
}
