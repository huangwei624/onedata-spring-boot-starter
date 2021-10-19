package info.onedata.dynamic.datasource.mybatisplus.repository.impl;

import info.onedata.dynamic.datasource.mybatisplus.entity.StudentEntity;
import info.onedata.dynamic.datasource.mybatisplus.dao.IStudentDao;
import info.onedata.dynamic.datasource.mybatisplus.repository.MPStudentRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erwin Feng
 * @since 2021-10-19
 */
@Service
public class StudentRepositoryImpl extends ServiceImpl<IStudentDao, StudentEntity> implements MPStudentRepository {

}
