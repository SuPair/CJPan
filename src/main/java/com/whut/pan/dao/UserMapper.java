package com.whut.pan.dao;

import com.whut.pan.domain.User;
import com.whut.pan.util.StringUtil;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Sandeepin
 */
@Component
@Mapper
public interface UserMapper {

    // 增添用户
    @Insert("insert into pan_user(username, password, level, email, phone) values(#{userName},#{passWord},#{levelType},#{email},#{phone})")
    int add(User user);

    // 删除用户 By ID
    @DeleteProvider(type = UserSqlBuilder.class, method = "deleteByids")
    int deleteByIds(@Param("ids") String[] ids);

    // 删除用户 By用户名
    @DeleteProvider(type = UserSqlBuilder.class, method = "deleteByUsernames")
    int deleteByUsernames(@Param("userNames") String[] userNames);

    // 更改用户 By用户名
    @Update("update pan_user set password=#{passWord},level=#{levelType},email=#{email},phone=#{phone} where username = #{userName}")
    int update(User user);

    // 查询用户 By用户名
    @Select("select * from pan_user where username = #{userName}")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(property = "userName", column = "username", javaType = String.class),
            @Result(property = "passWord", column = "password", javaType = String.class),
            @Result(property = "levelType", column = "level", javaType = String.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "phone", column = "phone", javaType = String.class)
    })
    User queryUserByUsername(@Param("userName") String userName);

    // 查询用户 By各种参数，返回集合
    @SelectProvider(type = UserSqlBuilder.class, method = "queryUserByParams")
    List<User> queryUserList(Map<String, Object> params);

    class UserSqlBuilder {
        // 通过属性查用户
        public String queryUserByParams(final Map<String, Object> params) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from pan_user where 1=1");
            if (!StringUtil.isNull((String) params.get("userName"))) {
                sql.append(" and username like '%").append((String) params.get("userName")).append("%'");
            }
            if (!StringUtil.isNull((String) params.get("email"))) {
                sql.append(" and email like '%").append((String) params.get("email")).append("%'");
            }
            if (!StringUtil.isNull((String) params.get("phone"))) {
                sql.append(" and phone like '%").append((String) params.get("phone")).append("%'");
            }
            System.out.println("查询sql==" + sql.toString());
            return sql.toString();
        }

        //删除的方法-By ids
        public String deleteByids(@Param("ids") final String[] ids) {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM pan_user WHERE id in(");
            for (int i = 0; i < ids.length; i++) {
                if (i == ids.length - 1) {
                    sql.append(ids[i]);
                } else {
                    sql.append(ids[i]).append(",");
                }
            }
            sql.append(")");
            return sql.toString();
        }

        //删除的方法-By 用户名
        public String deleteByUsernames(@Param("userNames") final String[] userNames) {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM pan_user WHERE username in(");
            for (int i = 0; i < userNames.length; i++) {
                if (i == userNames.length - 1) {
                    sql.append("'").append(userNames[i]).append("'");
                } else {
                    sql.append("'").append(userNames[i]).append("',");
                }
            }
            sql.append(")");
            return sql.toString();
        }
    }
}
