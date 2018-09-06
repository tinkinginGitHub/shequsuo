package cn.anyoufang.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 2017/5/25.
 */
public class ExportExcel<T> {


    private static final  Pattern P = Pattern.compile("^//d+(//.//d+)?$");

    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
                            OutputStream out) {
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
                            OutputStream out, String pattern) {
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
    }



    /**
     * @param
     * @author chenyi
     * @Description 导出excel
     * @date 2017-6-12
     */
    public void responseExcel(HttpServletResponse response, ExportExcel<T> ex, String[] headers, List<T> data, String fileName) {
        try {
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            ex.exportExcel(fileName, headers, data, outputStream, "yyyy-MM-dd");
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings("unchecked")
    public void exportExcel(String title, String[] headers,
                            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        // HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        // HSSFFont font = workbook.createFont();
        // font.setColor(HSSFColor.VIOLET.index);
        //font.setFontHeightInPoints((short) 12);
        //  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        //style.setFont(font);
        // 生成并设置另一个样式
        //  HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        //  HSSFFont font2 = workbook.createFont();
        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        //style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        //  HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        //  comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        // comment.setAuthor("leno");

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            //cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                // cell.setCellStyle(style2);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "男";
                        if (!bValue) {
                            textValue = "女";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (int)(35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value == null) {
                            value = "";
                        }
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Matcher matcher = P.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            //font3.setColor(HSSFColor.BLUE.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 查询指定目录中电子表格中所有的数据
//     *
//     * @param file 文件完整路径
//     * @return
//     */
//    public static List<Member> getAllByExcel(InputStream is) {
//        List<Member> list = new ArrayList<Member>();
//        try {
//            Workbook rwb = Workbook.getWorkbook(is);
//            Sheet rs = rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
//            int clos = rs.getColumns();//得到所有的列
//            int rows = rs.getRows();//得到所有的行
//
//            System.out.println(clos + " rows:" + rows);
//            for (int i = 1; i < rows; i++) {
//                for (int j = 0; j < clos; j++) {
//                    //第一个是列数，第二个是行数
//                    String id = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
//                    String name = rs.getCell(j++, i).getContents();
//                    String sex = rs.getCell(j++, i).getContents();
//                    String num = rs.getCell(j++, i).getContents();
//
//                    System.out.println("id:" + id + " name:" + name + " sex:" + sex + " num:" + num);
//                    // list.add(new Member(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return list;
//
//    }


    public static void main(String[] args) {
        // 测试学生
//        ExportExcel<PayRecord> ex = new ExportExcel<PayRecord>();
//        String[] headers =
//                {"云支付账号", "姓名", "电话", "缴费时间", "推荐码"};
//        List<PayRecord> dataset = new ArrayList<PayRecord>();
//        dataset.add(new PayRecord());
//
//        // 测试图书
//        try {
//            OutputStream out = new FileOutputStream("E://a.xls");
//            ex.exportExcel(headers, dataset, out);
//            out.close();
//            JOptionPane.showMessageDialog(null, "导出成功!");
//            System.out.println("excel导出成功！");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}