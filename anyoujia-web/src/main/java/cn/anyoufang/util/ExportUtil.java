package cn.anyoufang.util;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.TreeMap;

public class ExportUtil {
	
	public  InputStream exportProductBasicMain(OutputStream out, List<TreeMap<String, Object>> list, String exporTitle, String[] titleNames, String[] value) {
		try {
			WritableWorkbook wbook = Workbook.createWorkbook(out); // 建立excel文件
			WritableSheet wsheet =null;
			wsheet = wbook.createSheet(exporTitle, 0); // 工作表名称
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(wfont);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setLocked(true);
			titleFormat.setWrap(true);
			// 居中样式
			WritableCellFormat center = new WritableCellFormat();
			center.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置Excel表头
		
			
			for (int i = 0; i < titleNames.length; i++) {
				Label excelTitle = new Label(i, 0, titleNames[i], titleFormat);
				wsheet.setColumnView(i, titleNames[i].length() * 4);
				wsheet.addCell(excelTitle);
			}
			int row = 1; // 用于循环时Excel的行号
			if (list != null && list.size() > 0) {
				for (TreeMap<String, Object> product : list) { 
					int col = 0; 
					Label number = new Label(col++,row,row+"",center);
 					wsheet.addCell(number);
 					
 					for(int i = 0; i<value.length; i++){  
 						Label userIdName = new Label(col++,row,String.valueOf(product.get(value[i])== null?"--":product.get(value[i])),center);
	 					wsheet.addCell(userIdName);
 					} 
					row++;
				}
			}
			wbook.write(); //写入文件 
			wbook.close();
			out.flush();
			out.close(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
