package com.mayank.employwise_backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mayank.employwise_backend.entity.Employee;
import com.mayank.employwise_backend.entity.Hierarchy;
import com.mayank.employwise_backend.repository.EmployRepository;

@Service
public class EmployService {

    @Autowired
    private EmployRepository emrepo;

    @Autowired
    private JavaMailSender mailsender;

    public String addEmployee(Employee emp) {
        try {
            emrepo.save(emp);
            String text = String.join("\n",
                    "New Employee Added", "\n",
                    "EmployeeID: ",emp.getEmployeeID(),
                    "EmployeeName: " + emp.getEmployeeName(), "\n",
                    "EmployeeEmail: " + emp.getEmail(), "\n",
                    "EmployeePhoneNumber: " + emp.getPhoneNumber());
            SimpleMailMessage message = new SimpleMailMessage();
            var managerid = emp.getReportsTo();
            System.out.println(emp.getEmployeeID());
            Employee manager = emrepo.findById(managerid).orElse(null);
            if(manager != null)
            {
                message.setFrom("mayank.jonwal02@gmail.com");
                message.setTo(manager.getEmail());
                message.setSubject("New Employee Added");
                message.setText(text);
                mailsender.send(message);
            }

           

            return "success";
        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }

    public String updateEmployee(Employee emp) {
        try {
            emrepo.findById(emp.getEmployeeID())
                    .map(
                            employee -> {
                                employee.setEmail(emp.getEmail());
                                employee.setEmployeeName(emp.getEmployeeName());
                                employee.setPhoneNumber(emp.getPhoneNumber());
                                employee.setProfileImage(emp.getProfileImage());
                                employee.setReportsTo(emp.getReportsTo());

                                return emrepo.save(employee);
                            });
            return "success";
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public Object getEmployees() {
        Map<String, Object> object = new HashMap<>();
        try {
            Object employees = emrepo.findAll();

            object.put("success", true);
            object.put("message", "Employees Fetched Successfully");
            object.put("list", employees);

        } catch (Exception e) {
            object.put("success", false);
            object.put("message", e.getMessage().toString());
        }

        return object;

    }

    public String deleteEmployee(Employee emp) {
        try {
            emrepo.deleteById(emp.getEmployeeID());
            return "success";
        } catch (Exception e) {
            return e.getMessage().toString();
        }

    }

    public Employee getManager(Hierarchy h) {
        Integer num = h.getNum();
        String id = h.getId();
        Employee e = null;
        for (int i = 0; i <= num; i++) {
            e = emrepo.findById(id)
                    .map(employee -> employee)
                    .orElse(null);

            id = e.getReportsTo();

        }
        return e;
    }
}
