package com.yj.dao.master.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.dao.master.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {}
