package info.onedata.dynamic.datasource.mybatisplus.easyexcel.handler;

import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author: huangwei
 * @create: 2021-11-02 20:43
 */
public class MyCellContentStyleStrategy extends AbstractCellStyleStrategy {
    /**
     * Sets the cell style of header
     *
     * @param context
     */
    @Override
    protected void setHeadCellStyle(CellWriteHandlerContext context) {
    }

    /**
     * Sets the cell style of content
     *
     * @param context
     */
    @Override
    protected void setContentCellStyle(CellWriteHandlerContext context) {
        try {
            Head headData = context.getHeadData();
            if (headData.getFieldName().equals("teacherId")) {
                Cell cell = context.getCell();
                if (cell.getNumericCellValue() > 50) {
                    WriteCellData<?> firstCellData = context.getFirstCellData();
                    WriteCellStyle writeCellStyle = new WriteCellStyle();

                    WriteFont writeFont = new WriteFont();
                    writeFont.setFontName("宋体");
                    writeFont.setColor(Font.COLOR_RED);
                    writeFont.setFontHeightInPoints((short)14);
                    writeCellStyle.setWriteFont(writeFont);
                    writeCellStyle.setFillBackgroundColor(IndexedColors.YELLOW1.index);
                    writeCellStyle.setFillForegroundColor(IndexedColors.YELLOW1.index);
                    writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
                    WriteCellStyle.merge(writeCellStyle, firstCellData.getOrCreateStyle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
