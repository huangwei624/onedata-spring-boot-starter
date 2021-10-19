package info.onedata.dynamic.datasource.mybatisplus.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: huangwei
 * @create: 2021-11-02 14:10
 */
@Data
public class StudentExcelDTO {

    @ExcelProperty("编号")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("昵称")
    private String nickName;

    @ExcelProperty("家庭地址")
    private String address;

    @ExcelProperty("教师编号")
    private Integer teacherId;
}
