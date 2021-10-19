package info.onedata.dynamic.datasource.mybatisplus.easyexcel.simple;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.style.DefaultStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler.MyCellContentStyleStrategy;
import info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler.MyCellStyleStrategy;
import info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler.MyReaderListener;
import info.onedata.dynamic.datasource.mybatisplus.easyexcel.model.StudentExcelDTO;
import info.onedata.dynamic.datasource.mybatisplus.entity.StudentEntity;
import info.onedata.dynamic.datasource.mybatisplus.repository.MPStudentRepository;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: huangwei
 * @create: 2021-11-02 14:04
 */
@SpringBootTest
public class ReadWriteExcel {

    private static final String TEMP_FILE_PATH = "D:\\temp\\";

    @Autowired
    private MPStudentRepository mpStudentRepository;

    /**
     * 读取表格
     */
    @Test
    public void readExcel() {
        EasyExcel.read(TEMP_FILE_PATH + "ab.xlsx", StudentExcelDTO.class,
                new MyReaderListener(dataList -> {
                    ArrayList<StudentEntity> studentEntities = new ArrayList<>();
                    dataList.forEach(item -> {
                        StudentEntity studentEntity = new StudentEntity();
                        BeanUtils.copyProperties(item, studentEntity);
                        studentEntities.add(studentEntity);
                    });
                    mpStudentRepository.saveBatch(studentEntities, 1000);
                })).sheet().doRead();
    }

    /**
     * 写表格
     */
    @Test
    public void writeExcel() throws IOException {
         List<StudentExcelDTO> studentExcelDTOS = generatorData();
//        List<StudentEntity> list = mpStudentRepository.list();
//        ArrayList<StudentExcelDTO> studentExcelDTOS = new ArrayList<>();
//        list.forEach(item -> {
//            StudentExcelDTO studentExcelDTO = new StudentExcelDTO();
//            BeanUtils.copyProperties(item, studentExcelDTO);
//            studentExcelDTOS.add(studentExcelDTO);
//        });
        File file = new File(TEMP_FILE_PATH + "ab.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        EasyExcel.write(file, StudentExcelDTO.class)
                // 自适应列宽
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
//                .registerWriteHandler(new MyCellStyleStrategy())
                .registerWriteHandler(new MyCellContentStyleStrategy())
                .sheet("学生表格")
                .doWrite(studentExcelDTOS);

    }





    private List<StudentExcelDTO> generatorData() {
        ArrayList<StudentExcelDTO> studentExcelDTOS = new ArrayList<>(3000);
        for (int i = 0; i < 300000; i++) {
            StudentExcelDTO studentExcelDTO = new StudentExcelDTO();
            studentExcelDTO.setId(i + 10);
            studentExcelDTO.setAge(((int) (Math.random() * 100)));
            studentExcelDTO.setName(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
            studentExcelDTO.setTeacherId(((int) (Math.random() * 100)));
            studentExcelDTO.setNickName(nickName.get(((int) (Math.random() * nickName.size()))));
            studentExcelDTO.setAddress(address.get(((int) (Math.random() * address.size()))));
            studentExcelDTOS.add(studentExcelDTO);
        }
        return studentExcelDTOS;
    }

    private List<String> nickName = new ArrayList<String>() {{
        add("大虚斯克里托");
        add("惢き鳪戀酔せ");
        add("中毒的愛情っ");
        add("求你别分开");
        add("无极限_诺烟");
        add("闯他国做他王");
        add("丶残剑灬邪少");
    }};




    private List<String> address = new ArrayList<String>() {{
        add("河南省信阳市潢川县");
        add("河南省信阳市固始县");
        add("河南省信阳市新蔡县");
        add("湖北省黄冈市");
        add("上海浦东新区");
        add("上海嘉定区");
        add("上海虹桥区");
        add("上海普陀区");
        add("上海徐家汇");
    }};
}
