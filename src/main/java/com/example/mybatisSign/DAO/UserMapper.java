package com.example.mybatisSign.DAO;

import com.example.mybatisSign.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper     //加上这个防止 userMapper 报 null
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from sign_table where username=#{username}")
    User GetUserByUsername(@Param("username")String username);

    @Select("select * from sign_table where username=#{username} and password=#{password}")
    User GetUserByUsernameAndPassowrd(@Param("username")String username, @Param("password")String password);

    @Insert("insert into sign_table(username, password) values(#{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR})")
    void Save(String username, String password);
}