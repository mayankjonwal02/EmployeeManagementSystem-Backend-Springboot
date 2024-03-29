package com.mayank.employwise_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayank.employwise_backend.entity.Employee;
import com.mayank.employwise_backend.entity.Hierarchy;
import com.mayank.employwise_backend.service.EmployService;





@CrossOrigin(origins = "*",allowedHeaders = "*")
@Controller
public class EmployController {

    @Autowired
    private EmployService empservice;

    @Autowired
    private JavaMailSender eMailSender;



    @PostMapping("/addemployee")
    @ResponseBody
    private Object addemployee(@RequestBody Employee emp)
    {
            String message = empservice.addEmployee(emp);
            Map<String,Object> object = new HashMap<>();
            if (message == "success") 
            {
                object.put("message", "Employee Added Successfully");
                object.put("employeeid", emp.getEmployeeID());   
                object.put("success", true); 
            }
            else
            {
                object.put("message", message);
                object.put("success", false); 
            }
            return object;
    }

    @PostMapping("/updateemployee")
    @ResponseBody
    private Object updateemployee(@RequestBody Employee emp)
    {
            String message = empservice.updateEmployee(emp);
            Map<String,Object> object = new HashMap<>();
            if (message == "success") 
            {
                object.put("message", "Employee Updated Successfully");
                object.put("employeeid", emp.getEmployeeID());   
                object.put("success", true); 
            }
            else
            {
                object.put("error", message);
                object.put("success", false); 
            }
            return object;
    }

    @GetMapping("/allemployees")
    @ResponseBody
    private Object fetchemployees()
    {
        return empservice.getEmployees();
    }


    @PostMapping("/deleteemployee")
    @ResponseBody
    private Object deleteemployee(@RequestBody Employee emp)
    {
            String message = empservice.deleteEmployee(emp);
            Map<String,Object> object = new HashMap<>();
            if (message == "success") 
            {
                object.put("message", "Employee deleted Successfully");
                object.put("employeeid", emp.getEmployeeID());   
                object.put("success", true); 
            }
            else
            {
                object.put("error", message);
                object.put("success", false); 
            }
            return object;
    }


    @PostMapping("/getManager")
    @ResponseBody
    public Object postMethodName(@RequestBody Hierarchy h) {
        
        return empservice.getManager(h);
        
    }

    @GetMapping("/sendmail")
    @ResponseBody
    public Object sendmail() {
        
     Map<String,Object> object = new HashMap<>();
      
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("mayank.jonwal02@gmail.com");
            message.setTo("manoj.jonwal72@gmail.com");
            message.setSubject("Test Mail");
            message.setText("This is a dummy mail generated by Mayank's SpringBoot Application");
            eMailSender.send(message);
            object.put("message", "mail sent");
            return object;
        } catch (Exception e) {
            object.put("message", e.getMessage().toString());
            return object;
        }
    }
    

    @GetMapping("/")
    public String mainpage() {
        return "test";
    }
    
    

}
