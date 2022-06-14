package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.UserInfo;
import com.yj.dao.master.mapper.UserInfoMapper;
import com.yj.model.dto.LoginDto;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@DS("master")
@Service
public class UserInfoService extends BaseDaoServiceImpl<UserInfoMapper, UserInfo> {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return {@link UserInfo}
     */
    public UserInfo findByUsername(String username) {
        // 构建mybatisPlus查询条件，等同于 select top 1 * from user_info where username = username
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("username", username);
        return getOne(queryWrapper);
    }

    /**
     * bean转换dto
     *
     * @param userInfo 用户信息
     * @return {@link LoginDto}
     */
    public LoginDto beanToDto(UserInfo userInfo) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(userInfo.getUsername());
        loginDto.setPassWord(userInfo.getPassword());
        loginDto.setUserType(userInfo.getUserType());
        return loginDto;
    }
}
