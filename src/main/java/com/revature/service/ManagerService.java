package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursement;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import org.apache.tika.Tika;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    private ReimbursementDao reimbursementDao;

    public  ManagerService(){
        this.reimbursementDao=new ReimbursementDao();
    }

    public ManagerService(ReimbursementDao mockDao){
        this.reimbursementDao=mockDao;
    }

    public List<ResponseReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements=this.reimbursementDao.getAllReimbursements();

        List<ResponseReimbursementDTO> reimbursementDTOS= new ArrayList<>();

        for(Reimbursement reimbursement:reimbursements){
            reimbursementDTOS.add(new ResponseReimbursementDTO(reimbursement.getId(),reimbursement.getEmployee().getFirstName(),reimbursement.getDescription(),reimbursement.getEmailId(),reimbursement.getStatus(),reimbursement.getManager().getFirstName(),reimbursement.getType(),reimbursement.getAmount()));
        }
        return  reimbursementDTOS;
    }
    public List<ResponseReimbursementDTO> getAllReimbursementsById(int UserId) throws SQLException {
        List<Reimbursement> reimbursements=this.reimbursementDao.getAllReimbursementsById(UserId);

        List<ResponseReimbursementDTO> reimbursementDTOS= new ArrayList<>();

        for(Reimbursement reimbursement:reimbursements){
            reimbursementDTOS.add(new ResponseReimbursementDTO(reimbursement.getId(),reimbursement.getEmployee().getFirstName(),reimbursement.getDescription(),reimbursement.getEmailId(),reimbursement.getStatus(),reimbursement.getManager().getFirstName(),reimbursement.getType(),reimbursement.getAmount()));
        }
        return  reimbursementDTOS;
    }

    public ResponseReimbursementDTO addReimbursement(int employeeId, AddReimbursement dto) throws IOException, InvalidImageException, SQLException {
        Tika tika= new Tika();
        String mimeType=tika.detect(dto.getImage());

        if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")) {
             throw new InvalidImageException("Image must be a JPEG,PNG, OR GIF");
        }
      Reimbursement reimbursementAdded=this.reimbursementDao.addReimbursement(employeeId,dto);

        return  new ResponseReimbursementDTO(reimbursementAdded.getId(),reimbursementAdded.getEmployee().getUsername(),reimbursementAdded.getDescription(),reimbursementAdded.getEmailId(),reimbursementAdded.getStatus(),null,reimbursementAdded.getType(),reimbursementAdded.getAmount());
    }

    public ResponseReimbursementDTO makeDecision(String reimbursementId,String status,int managerId) throws SQLException {
        try{
            int intReimbursementId=Integer.parseInt(reimbursementId);
            int statusId=Integer.parseInt(status);

            Reimbursement reimbursement=this.reimbursementDao.makeDecision(intReimbursementId,statusId,managerId);

            return  new ResponseReimbursementDTO(intReimbursementId,reimbursement.getEmployee().getUsername(),reimbursement.getDescription(),reimbursement.getEmailId(),reimbursement.getStatus(),reimbursement.getManager().getUsername(),reimbursement.getType(),reimbursement.getAmount());
        }catch (NumberFormatException e){
             throw new IllegalArgumentException("Reimbursement ID and grade provided must be valid");
        }
    }

    public InputStream getReimbursementImage(String reimbursementId,String userId) throws SQLException, ImageNotFoundException {
        try{
            int rId=Integer.parseInt(reimbursementId);
            int uId=Integer.parseInt(userId);

            InputStream image=this.reimbursementDao.getReimbursementImage(rId,uId);

            if(image == null){
                throw new ImageNotFoundException("Reimbursement id" + reimbursementId + "does not have Image");
            }
            return  image;
        }catch (NumberFormatException e){
            throw new  IllegalArgumentException("Reimbursement and/or User id must be valid");
        }
    }

}
