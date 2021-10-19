package info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.DefaultStyle;
import org.apache.poi.ss.usermodel.*;

/**
 * @author: huangwei
 * @create: 2021-11-02 20:32
 */
public class MyCellStyleStrategy extends DefaultStyle {

    public MyCellStyleStrategy() {
        super();
        WriteCellStyle headWriteCellStyle = getHeadWriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        headWriteCellStyle.setWrapped(true);
//        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        headWriteCellStyle.setLocked(true);
//        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
//        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
//        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
//        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        headWriteFont.setColor(Font.COLOR_RED);
        headWriteFont.setFontHeightInPoints((short)14);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
//
//        setHeadWriteCellStyle(headWriteCellStyle);
    }
}
