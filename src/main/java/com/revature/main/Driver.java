package com.revature.main;

import com.revature.controllers.AuthenticationController;
import com.revature.controllers.Controller;
import com.revature.controllers.ExceptionController;
import com.revature.controllers.ManagerController;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.service.ManagerService;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Driver {
    public static Logger logger =  LoggerFactory.getLogger(java.sql.Driver.class );
    public static void main (String[] args) throws SQLException {


        Javalin app= Javalin.create((config) ->{
            config.enableCorsForAllOrigins();
        });


        app.before((ctx) -> {
            logger.info(ctx.method() + "request received for " + ctx.path());
        });

        map(app,new AuthenticationController(),new ExceptionController(),new ManagerController());

        //ReimbursementDao r= new ReimbursementDao();
        //System.out.println(r.getAllReimbursementsById(3));

        ManagerService m=new ManagerService();
        System.out.println(m.getAllReimbursementsById(3));



        app.start(8081);



    }

    public static void map(Javalin app, Controller... controllers){
        for(Controller c:controllers){
            c.mapEndpoints(app);
        }
    }
}
