/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wws.b.finalexam.finalexam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import model.entity.Person;
import model.entity.PersonJpaController;
import org.springframework.http.HttpEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @DanangWibisono_20200140007
 */

@RestController
@CrossOrigin
@RequestMapping("/Person")
public class controller {
    
    Person pers = new Person();
    PersonJpaController ctrl = new PersonJpaController();
    
    @GetMapping()
    public List<Person> getAll(){
        List<Person> list = new ArrayList<>();
        try{
            list = ctrl.findPersonEntities();
        }
        catch (Exception e){
            list = List.of();
        }
        return list;
    }
    
    @GetMapping("/{id}")
    public List<Person> getById(@PathVariable("id") int id){
        List<Person> list = new ArrayList<>();
        try{
            pers = ctrl.findPerson(id);
            list.add(pers);
        }
        catch(Exception e){
            list = List.of();
        }
        return list;
    }
    
    @PostMapping()
    public String createData(HttpEntity<String> paket){
        String message = "";
        try{
            String reciver = paket.getBody();
            ObjectMapper map = new ObjectMapper();
            pers = map.readValue(reciver, Person.class);
            ctrl.create(pers);
            message = pers.getNama() + " Data Saved";
        }
        catch(Exception e){
            message = "Failed";
        }
        return message;
    }
    
    @PutMapping()
    public String editData(HttpEntity<String> kirim){
        String message = "no action";
        try{
            String reciver = kirim.getBody();
            ObjectMapper map = new ObjectMapper();
            //Identitas data = new Identitas();
            pers = map.readValue(reciver, Person.class);
            ctrl.edit(pers);
            message = pers.getNama() + " has been Update";
        }
        catch(Exception e){}
        return message;
    }
    
    @DeleteMapping()
    public String deleteData(HttpEntity<String> kirim){
        String message = "no action";
        try{
            String reciver = kirim.getBody();
            ObjectMapper map = new ObjectMapper();
            pers = map.readValue(reciver, Person.class);
            ctrl.destroy(pers.getId());
            message = "Data has been Deleted";
        }
        catch(Exception e){}
        return message;
    }
    
}
