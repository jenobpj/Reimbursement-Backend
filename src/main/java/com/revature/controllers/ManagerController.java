package com.revature.controllers;

import com.revature.dto.AddReimbursement;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.service.JWTService;
import com.revature.service.ManagerService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.List;

public class ManagerController implements  Controller{

    private JWTService jwtService;
    private ManagerService managerService;

    public  ManagerController(){
        this.jwtService=new JWTService();
        this.managerService=new ManagerService();
    }

    private  Handler test =(ctx) -> {
        ctx.json("test");
    };

    private Handler getAllReimbursements = (ctx) ->{
        String jwt=ctx.header("Authorization").split(" ")[1];
         Jws<Claims> token= this.jwtService.validateJwt(jwt);

         if(!token.getBody().get("userRole").equals("manager")){
             throw new UnauthorizedResponse("You must be manager to access this endpoint");
         }
        List<ResponseReimbursementDTO> reimbursements=this.managerService.getAllReimbursements();
         ctx.json(reimbursements);
    };

    private Handler getAllReimbursementsById = (ctx) -> {
        String jwt=ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token=this.jwtService.validateJwt(jwt);

        if(!token.getBody().get("userRole").equals("employee")){
            throw new UnauthorizedResponse("You must be a employee to access this endpoint");
        }

        String  userId= ctx.pathParam("employee_id");
        int id=Integer.parseInt(userId);
        if(!token.getBody().get("userId").equals(id)){
            throw new UnauthorizedResponse("You cannot obtain reimbursements that don't belong to yourself");
        }

        List<ResponseReimbursementDTO> dtos=this.managerService.getAllReimbursementsById(id);
        ctx.json(dtos);

    };

    private Handler addReimbursements = (ctx) -> {
        String jwt=ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token=this.jwtService.validateJwt(jwt);

        if(!token.getBody().get("userRole").equals("employee")){
            throw new UnauthorizedResponse("You must be a employee to access this endpoint");
        }

        String  userId= ctx.pathParam("employee_id");
        int id=Integer.parseInt(userId);
        if(!token.getBody().get("userId").equals(id)){
            throw new UnauthorizedResponse("You cannot obtain reimbursements that don't belong to yourself");
        }

        String  amount=ctx.formParam("amount");
        int intAmount=Integer.parseInt(amount);
        String description=ctx.formParam("description");
        UploadedFile file=ctx.uploadedFile("image");
        InputStream is=file.getContent();
        String typeId=ctx.formParam("typeId");
        int intTypeId=Integer.parseInt(typeId);


        AddReimbursement dto= new AddReimbursement();
        dto.setAmount(intAmount);
        dto.setDescription(description);
        dto.setImage(is);
        dto.setTypeId(intTypeId);

        ResponseReimbursementDTO getDTO=this.managerService.addReimbursement(id,dto);
        ctx.status(201);
        ctx.json(getDTO);


    };

    private Handler makeDecision = (ctx) ->{
        String jwt=ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token=this.jwtService.validateJwt(jwt);

        if(!token.getBody().get("userRole").equals("manager")){
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        String reimbursementId=ctx.pathParam("reimbursements_id");
        String status=ctx.queryParam("status");
        int managerId=token.getBody().get("userId",Integer.class);

        if(status == null){
            throw new IllegalArgumentException("You need to provide the status in order to update");
        }
        ResponseReimbursementDTO responseReimbursementDTO=this.managerService.makeDecision(reimbursementId,status,managerId);
        ctx.json(responseReimbursementDTO);

    };

    private Handler getReimbursementImage = (ctx) -> {
       String userId=ctx.pathParam("user_id");
       String reimbursementId=ctx.pathParam("reimbursement_id");
       InputStream image=this.managerService.getReimbursementImage(reimbursementId,userId);

        Tika tika= new Tika();
        String mimeType=tika.detect(image);

        ctx.header("Content-Type",mimeType);
        ctx.result(image);


    };
    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/test",test);
        app.get("/reimbursements",getAllReimbursements);
        app.get("/employee/{employee_id}/reimbursements",getAllReimbursementsById);
        app.post("/employee/{employee_id}/reimbursements",addReimbursements);
        app.patch("/reimbursements/{reimbursements_id}",makeDecision);
        app.get("/users/{user_id}/reimbursements/{reimbursement_id}/image",getReimbursementImage);

    }
}
