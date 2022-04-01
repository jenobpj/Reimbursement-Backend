package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public UserDao(){

    }
    public User getUsernameAndPassword(String username, String password) throws SQLException {
        try(Connection con= ConnectionUtility.getConnection()){
            String sql="select users.id as id,users.username as username,users.email as email,  users.password as password ,users .first_name as firstname,user_roles .role as role  from users inner join user_roles on users.user_role_id =user_roles .id WHERE users.username = ? AND users.password = ?";
            //String sql= "select id,username,first_name,email from users where username =? AND password=?";
            PreparedStatement pstmt =con.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();

            if(rs.next()){
                int userId=rs.getInt("id");
                String un=rs.getString("username");
                String ps=rs.getString("password");
                String email=rs.getString("firstname");
                String role=rs.getString("role");

                return  new User(userId,un,ps,email,role);

            }
            return  null;

        }

    }
}
