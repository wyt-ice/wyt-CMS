package com.jincheng.customer.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Download {
    public static void writeExcel(String[] title, String fileName, String[][] contents) {
        // 创建Excel文件
        File file = new File(fileName);
        try {
            file.createNewFile();
            // 创建工作薄workBook
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            // 创建工作表sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            Label label = null;

            /*
             * column：列 row：行 content:内容
             * JXL没有直接针对单元格的操作，直接对行 或者 列进行写入
             */

            // 第一行设置列名
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            // 追加数据
            for (int i = 0; i < contents.length; i++) {
                for (int j = 0; j < contents[i].length; j++) {
                    label = new Label(j, i+1, contents[i][j]);
                    sheet.addCell(label);
                }
            }
            // 写入数据
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static File hasFile;


    /**
     * 追加excel
     *
     * @param
     * @throws IOException
     * @throws BiffException
     * @throws WriteException
     * @throws RowsExceededException
     */
    public static void addExcel(File file, String[][] contents) throws BiffException,
            IOException, RowsExceededException, WriteException {
        Workbook book = Workbook.getWorkbook(file);
        Sheet sheet = book.getSheet(0);
        // 获取行
        int length = sheet.getRows();
        System.out.println(length);
        WritableWorkbook wbook = Workbook.createWorkbook(file, book); // 根据book创建一个操作对象
        WritableSheet sh = wbook.getSheet(0);// 得到一个工作对象
        // 追加数据
        for (int i = 0; i < contents.length; i++) {
            for (int j = 0; j < contents[i].length; j++) {
                Label  label = new Label(j, length+i, contents[i][j]);
                sh.addCell(label);
            }
        }
        wbook.write();
        wbook.close();
    }

    /**
     * 判断文件是否已经写入
     *
     * @param filename
     * @return
     */
    public static boolean filecheck(String filename) {
        boolean flag = false;
        File file = new File(filename);
        if (file.exists()) {
            flag = true;
        }
        setHasFile(file);
        return flag;
    }

    /**
     * 不管神马类型，都转换成string
     *
     * @param obj
     * @return
     */
    public static String converToString(Object obj) {
        return "";
    }

    public static void write(String[] title, String fileName, String[][] contents) throws RowsExceededException,
            WriteException, IOException, BiffException {

        boolean has = Download.filecheck(fileName);
        // 如果存在
        if (has)
            addExcel(getHasFile(), contents);
        else {
            writeExcel(title,fileName,contents);
        }

    }

    /**
     * @return the hasFile
     */
    public static File getHasFile() {
        return hasFile;
    }

    /**
     * @param hasFile
     *            the hasFile to set
     */
    public static void setHasFile(File hasFile) {
       Download.hasFile = hasFile;
    }
}
