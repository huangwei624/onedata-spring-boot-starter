package info.onedata.dynamic.datasource.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import info.onedata.dynamic.datasource.mybatisplus.entity.StudentEntity;
import info.onedata.dynamic.datasource.mybatisplus.repository.MPStudentRepository;
import info.onedata.dynamic.datasource.mybatisplus.service.StudentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEntityServiceImpl implements StudentEntityService {

    @Autowired
    private MPStudentRepository mpStudentRepository;


//    @DS("master")
    @Override
    public StudentEntity getById(Integer id) {
        return mpStudentRepository.getById(id);
    }

//    @DS("slave")
    @Override
    public StudentEntity getByName(String name) {
        StudentEntity studentEntity = mpStudentRepository.lambdaQuery().eq(StudentEntity::getName, name).one();
        return studentEntity;
    }

    @Override
    public Boolean insertStudent(StudentEntity studentEntity) {
        return mpStudentRepository.save(studentEntity);
    }
}
