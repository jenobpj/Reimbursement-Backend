package com.revature.dao;

import com.revature.dto.AddReimbursement;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            List<Reimbursement>  reimbursements=new ArrayList<>();

            String sql= "select reimbursement .id as id,reimbursement .reimb_submitted  as submitted,"+
                    "reimbursement .reimb_description as description,reimbursement .reimb_receipt  as receipt,"+
                    "reimbursement.reimb_amount as amount,users.id as employeeId,users .username as employee ,users.email as employeeEmail,users.password as emppassword,"+
                    "users.first_name as empfirstname,manager.id as managerId, manager .username  as manager ,manager .password  as manpassword,"+
                    "manager.first_name as manfirstname,reimbursement_status .status  as status,"+
                    "reimbursement_type .reimbursements as typeof  from reimbursement "+
                    "left join users on reimb_author =users .id "+
                    "left join reimbursement_type on reimb_type_id =reimbursement_type.id "+
                    "left join reimbursement_status on reimb_staus_id =reimbursement_status.id "+
                    "left join users manager on manager .id = reimb_resolver";

            PreparedStatement pstmt=con.prepareStatement(sql);

            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                //Reimbursements
                int reimbursementId=rs.getInt("id");
                String reimbursementStatus=rs.getString("status");
                String reimbursementType=rs.getString("typeof");
                int reimbursementAmount=rs.getInt("amount");
                String  description= rs.getString("description");
                String employeeEmailId=rs.getString("employeeEmail");


                //Employee
                int employeeId=rs.getInt("employeeId");
                String employeeUsername=rs.getString("employee");
                String employeePassword=rs.getString("emppassword");
                String employeeFirstname=rs.getString("empfirstname");
                String employeeRole="employee";

                User employee=new User(employeeId,employeeUsername,employeePassword,employeeFirstname,employeeRole);

                //Manager

                int managerId=rs.getInt("managerId");
                String managerUsername=rs.getString("manager");
                String managerPassword=rs.getString("manpassword");
                String managerFirstname=rs.getString("manfirstname");
                String managerRole="manager";

                User manager=new User(managerId,managerUsername,managerPassword,managerFirstname,managerRole);

                Reimbursement reimbursement=new Reimbursement(reimbursementId,reimbursementStatus,reimbursementType,reimbursementAmount,employeeEmailId,description,employee,manager);
                 reimbursements.add(reimbursement);
            }
            return  reimbursements;
        }
    }
    public List<Reimbursement> getAllReimbursementsById(int UserId) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            List<Reimbursement>  reimbursements=new ArrayList<>();

            String sql= "select reimbursement .id as id,reimbursement .reimb_submitted  as submitted,"+
                    "reimbursement .reimb_description as description,reimbursement .reimb_receipt  as receipt,"+
                    "reimbursement.reimb_amount as amount,users.id as employeeId,users .username as employee ,users.email as employeeEmail,users.password as emppassword,"+
                    "users.first_name as empfirstname,manager.id as managerId, manager .username  as manager ,manager .password  as manpassword,"+
                    "manager.first_name as manfirstname,reimbursement_status .status  as status,"+
                    "reimbursement_type .reimbursements as typeof  from reimbursement "+
                    "left join users on reimb_author =users .id "+
                    "left join reimbursement_type on reimb_type_id =reimbursement_type.id "+
                    "left join reimbursement_status on reimb_staus_id =reimbursement_status.id "+
                    "left join users manager on manager .id = reimb_resolver "+
                    "WHERE users.id=?";

            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,UserId);

            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                //Reimbursements
                int reimbursementId=rs.getInt("id");
                String reimbursementStatus=rs.getString("status");
                String reimbursementType=rs.getString("typeof");
                int reimbursementAmount=rs.getInt("amount");
                String  description= rs.getString("description");
                String employeeEmailId=rs.getString("employeeEmail");


                //Employee
                int employeeId=rs.getInt("employeeId");
                String employeeUsername=rs.getString("employee");
                String employeePassword=rs.getString("emppassword");
                String employeeFirstname=rs.getString("empfirstname");
                String employeeRole="employee";

                User employee=new User(employeeId,employeeUsername,employeePassword,employeeFirstname,employeeRole);

                //Manager

                int managerId=rs.getInt("managerId");
                String managerUsername=rs.getString("manager");
                String managerPassword=rs.getString("manpassword");
                String managerFirstname=rs.getString("manfirstname");
                String managerRole="manager";

                User manager=new User(managerId,managerUsername,managerPassword,managerFirstname,managerRole);

                Reimbursement reimbursement=new Reimbursement(reimbursementId,reimbursementStatus,reimbursementType,reimbursementAmount,employeeEmailId,description,employee,manager);
                reimbursements.add(reimbursement);
            }
            return  reimbursements;
        }
    }

    public Reimbursement addReimbursement(int employeeId, AddReimbursement dto) throws SQLException {
        try(Connection con =ConnectionUtility.getConnection()){
            con.setAutoCommit(false);

            String sql="INSERT INTO reimbursement(reimb_amount,reimb_submitted,reimb_description,reimb_receipt"+
                    ",reimb_author,reimb_staus_id,reimb_type_id) VALUES(?,now(),?,?,?,1,?)";

            PreparedStatement pstmt1=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt1.setInt(1,dto.getAmount());
            pstmt1.setString(2,dto.getDescription());
            pstmt1.setBinaryStream(3,dto.getImage());
            pstmt1.setInt(4,employeeId);
            pstmt1.setInt(5,dto.getTypeId());
            pstmt1.executeUpdate();


            ResultSet rs=pstmt1.getGeneratedKeys();
            rs.next();
            int reimbursementId=rs.getInt(1);

            String sql2="SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt2=con.prepareStatement(sql2);
            pstmt2.setInt(1,employeeId);

            ResultSet rs2 =pstmt2.executeQuery();
            rs2.next();
            int employeeID=rs2.getInt("id");
            String employeeUsername=rs2.getString("username");
            String employeePassword=rs2.getString("password");
            String employeeFirstName=rs2.getString("first_name");
            String employeeRule="employee";
            String employeeEmail=rs2.getString("email");

            String sql3="SELECT * from reimbursement_type WHERE id=?";
            PreparedStatement pstmt3=con.prepareStatement(sql3);
            pstmt3.setInt(1,dto.getTypeId());

            ResultSet rs3=pstmt3.executeQuery();
            rs3.next();
            String type=rs3.getString("reimbursements");

            User employee=new User(employeeID,employeeUsername,employeePassword,employeeFirstName,employeeRule);

            Reimbursement reimbursement=new Reimbursement(reimbursementId,"pending",type,dto.getAmount(),employeeEmail,dto.getDescription(),employee,null);

            con.commit();
            return  reimbursement;


        }

    }
    public Reimbursement makeDecision(int reimbursementId,int statusId,int ManagerId) throws SQLException {
        try(Connection con=ConnectionUtility.getConnection()){
            con.setAutoCommit(false);


            String sql="UPDATE reimbursement SET reimb_staus_id=? ,reimb_resolver=? WHERE id=?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,statusId);
            pstmt.setInt(2,ManagerId);
            pstmt.setInt(3,reimbursementId);

            pstmt.executeUpdate();

             String sql2= "select reimbursement .id as id,reimbursement .reimb_submitted  as submitted,"+
                    "reimbursement .reimb_description as description,reimbursement .reimb_receipt  as receipt,"+
                    "reimbursement.reimb_amount as amount,users.id as employeeId,users .username as employee ,users.email as employeeEmail,users.password as emppassword,"+
                    "users.first_name as empfirstname,manager.id as managerId, manager .username  as manager ,manager .password  as manpassword,"+
                    "manager.first_name as manfirstname,reimbursement_status .status  as status,"+
                    "reimbursement_type .reimbursements as typeof  from reimbursement "+
                    "left join users on reimb_author =users .id "+
                    "left join reimbursement_type on reimb_type_id =reimbursement_type.id "+
                    "left join reimbursement_status on reimb_staus_id =reimbursement_status.id "+
                    "left join users manager on manager .id = reimb_resolver "+
                    "WHERE reimbursement.id=?";

             PreparedStatement pstm2=con.prepareStatement(sql2);
             pstm2.setInt(1,reimbursementId);

             ResultSet rs2=pstm2.executeQuery();

             rs2.next();
             //Reimbursement
            String reimbursementStatus=rs2.getString("status");
            String reimbursementType=rs2.getString("typeof");
            int reimbursementAmount=rs2.getInt("amount");
            String  description= rs2.getString("description");
            String employeeEmailId=rs2.getString("employeeEmail");

            //Employee

            //Employee
            int employeeId=rs2.getInt("employeeId");
            String employeeUsername=rs2.getString("employee");
            String employeePassword=rs2.getString("emppassword");
            String employeeFirstname=rs2.getString("empfirstname");
            String employeeRole="employee";

            User employee=new User(employeeId,employeeUsername,employeePassword,employeeFirstname,employeeRole);

            //Manager

            int managerId=rs2.getInt("managerId");
            String managerUsername=rs2.getString("manager");
            String managerPassword=rs2.getString("manpassword");
            String managerFirstname=rs2.getString("manfirstname");
            String managerRole="manager";

            User manager=new User(managerId,managerUsername,managerPassword,managerFirstname,managerRole);

            Reimbursement reimbursement=new Reimbursement(reimbursementId,reimbursementStatus,reimbursementType,reimbursementAmount,employeeEmailId,description,employee,manager);
             con.commit();
             return  reimbursement;
        }
    }
    public InputStream getReimbursementImage(int rId,int uId) throws SQLException {
        try(Connection con=ConnectionUtility.getConnection()){
            String sql="SELECT reimb_receipt from reimbursement WHERE id=? AND reimb_author = ?";
            PreparedStatement pstmt =con.prepareStatement(sql);
            pstmt.setInt(1,rId);
            pstmt.setInt(2,uId);

            ResultSet rs= pstmt.executeQuery();

            if(rs.next()){
                InputStream is=rs.getBinaryStream("reimb_receipt");
                return is;
            } else {
                return null;
            }

        }
    }
}
