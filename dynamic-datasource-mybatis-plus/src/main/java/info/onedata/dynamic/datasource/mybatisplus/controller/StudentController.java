package info.onedata.dynamic.datasource.mybatisplus.controller;


import info.onedata.dynamic.datasource.mybatisplus.entity.StudentEntity;
import info.onedata.dynamic.datasource.mybatisplus.service.StudentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentEntity")
public class StudentController {

    @Autowired
    private StudentEntityService studentEntityService;

    @GetMapping("/getById")
    public Object getById(Integer id) {
        return studentEntityService.getById(id);
    }

    @GetMapping("/getByName")
    public Object getByName(String name) {
        return studentEntityService.getByName(name);
    }

    @GetMapping
    public Object insertStudent(String name, Integer age, Integer teacherId) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setAge(age);
        studentEntity.setTeacherId(teacherId);
        return studentEntityService.insertStudent(studentEntity);
    }
}

