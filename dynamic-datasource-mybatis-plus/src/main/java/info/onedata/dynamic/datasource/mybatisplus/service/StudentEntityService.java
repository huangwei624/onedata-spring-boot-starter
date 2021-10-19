package info.onedata.dynamic.datasource.mybatisplus.service;

import info.onedata.dynamic.datasource.mybatisplus.entity.StudentEntity;

public interface StudentEntityService {

    StudentEntity getById(Integer id);

    StudentEntity getByName(String name);

    Boolean insertStudent(StudentEntity studentEntity);
}
