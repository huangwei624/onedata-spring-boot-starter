package info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.PageReadListener;
import info.onedata.dynamic.datasource.mybatisplus.easyexcel.model.StudentExcelDTO;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author: huangwei
 * @create: 2021-11-02 19:29
 */
public class MyReaderListener extends PageReadListener<StudentExcelDTO> {


    public MyReaderListener(Consumer<List<StudentExcelDTO>> consumer) {
        super(consumer);
    }

    /**
     * When analysis one head row trigger invoke function.
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        System.out.println("headMap: ");
        headMap.forEach((key, value) -> {
            System.out.println("key: " + key);
            System.out.println("value:" + value.getStringValue());
        });
        System.out.println("========");
    }
}
