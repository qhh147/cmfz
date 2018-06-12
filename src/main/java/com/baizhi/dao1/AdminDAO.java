package com.baizhi.dao1;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface AdminDAO {
    Admin queryByName(String name);
    void updatePass(@Param("aname") String name, @Param("password") String password);
}
