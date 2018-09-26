package com.springboot.girl.util;

/**
 * @Description 解析导入excel
 * @Author GuanHuizhen
 * @Date 2018/9/14
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.springboot.girl.bean.vo.CustomerVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;

public class ReadExcel<T> {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    /**
     * 读EXCEL文件，获取客户信息集合
     * @return
     */
    public <T> List<T> getExcelInfo(String fileName, HttpServletRequest request,MultipartFile Mfile){
       /* MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("protocolFilePath");

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径*/
        File file = new  File("D:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("D:\\fileupload\\" + fileName );
        //将上传的文件写入新建的文件中
       /* try {
            multipartFile.transferTo(file1);
           //cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //初始化客户信息的集合
        List<T> customerList=new ArrayList<>();
        //初始化输入流
        InputStream is = null;
        try{
            //验证文件名是否合格
            if(!validateExcel(fileName)){
                return null;
            }
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息
            customerList = getExcelInfo(is, isExcel2003);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }
    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public <T> List<T> getExcelInfo(InputStream is,boolean isExcel2003){
        List<T> customerList=null;
        try{
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            customerList=readExcelValue(wb);
        }
        catch (IOException e)  {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private Class<T> returnClass;
    private String columns[] = {"Name","SimpleName","Trade","Source","Address","Remark"};
    private String typeStr[] = {"string","string","string","string","string","string"};

    public ReadExcel(Class<T> returnClass,String columns[],String typeStr[]) {
        this.returnClass = returnClass;
        this.columns = columns;
        this.typeStr = typeStr;
    }

    public ReadExcel(Class<T> returnClass) {
        this.returnClass = returnClass;
    }

    private <T> List<T> readExcelValue(Workbook wb) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //得到第一个sheet
        Sheet sheet=wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows=sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if(totalRows>=1 && sheet.getRow(0) != null){
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<T> customerList=new ArrayList<>();
        for(int i=1;i<totalRows;i++) {
            T customerVo = (T) this.returnClass.newInstance();
            String cellValues[] = readRow(sheet,i);
            int index = 0;
            for(String column : columns) {
                Method method = CustomerVo.class.getMethod("set"+column,types.get(typeStr[index]));
                method.invoke(customerVo,cellValues[index]);
                index ++;
            }
            //添加客户
            customerList.add(customerVo);
        }

        //方法二：循环Excel行数,从第二行开始。标题不入库
/*        for(int r=1;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            CustomerVo customerVo = new CustomerVo();
            //循环Excel的列
            for(int c = 0; c <this.totalCells; c++){
                Cell cell = row.getCell(c);
                if(c==0){//第一列不读
                }
                else if(c==1){
                    customerVo.setName(cell.getStringCellValue());//客户名称
                }else if(c==2){
                    customerVo.setSimpleName(cell.getStringCellValue());//客户简称
                }else if(c==3){
                    customerVo.setTrade(cell.getStringCellValue());//行业
                }else if(c==4){
                    customerVo.setSource(cell.getStringCellValue());//客户来源
                }else if(c==5){
                    customerVo.setAddress(cell.getStringCellValue());//地址
                }else if(c==6){
                    customerVo.setRemark(cell.getStringCellValue());//备注信息
                }

                }
            }*/
            //添加客户
            //customerList.add(customerVo);
        return customerList;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    private static Map<String,Class<?>> types = new HashMap<>();
    static {
        types.put("string",String.class);
        types.put("double",Double.class);
        types.put("int",Integer.class);
    }

    public static String[] readRow(Sheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return null;
        } else {
            int columns = row.getPhysicalNumberOfCells();
            if (columns == 0) {
                return null;
            } else {
                String[] values = new String[columns];

                for(int i = 0; i < columns; ++i) {
                    values[i] = getStringCellValue(row.getCell(i));
                }

                return values;
            }
        }
    }

    public static String getStringCellValue(Cell cell) {
        String strCell = "";
        if (cell == null) {
            return "";
        } else {
            switch(cell.getCellType()) {
                case 0:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        try {
                            Date d = cell.getDateCellValue();
                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            strCell = fmt.format(d);
                        } catch (Exception var4) {
                            ;
                        }
                    } else {
                        strCell = trimZero(String.format("%f", cell.getNumericCellValue()));
                    }
                    break;
                case 1:
                    strCell = cell.getStringCellValue().trim();
                    break;
                case 2:
                    try {
                        strCell = cell.getStringCellValue().trim();
                    } catch (IllegalStateException var5) {
                        strCell = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case 3:
                    strCell = "";
                    break;
                case 4:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    strCell = "";
            }

            return !strCell.equals("") && strCell != null ? strCell : "";
        }
    }

    public static String trimZero(String number) {
        if (number.indexOf(".") > 0) {
            while(true) {
                if (!number.endsWith("0")) {
                    if (number.endsWith(".")) {
                        number = number.replace(".", "");
                    }
                    break;
                }

                number = number.substring(0, number.length() - 1);
            }
        }

        return number;
    }


    public static void main(String args[]) throws Exception {
        /*String columns[] = {"Name","SimpleName","Trade","Source","Address","Remark"};

        String typeStr[] = {"string","string","string","string","string","string"};
        CustomerVo customerVo = new CustomerVo();
        int index = 0;
        for(String column : columns) {

            Method method = CustomerVo.class.getMethod("set"+column,types.get(typeStr[index]));
            index ++;
            method.invoke(customerVo,method.getName()+index);
        }
        System.out.println(customerVo.toString());
*/
        //readExcelValue(new XSSFWorkbook(is));
        ReadExcel<CustomerVo> readExcel = new ReadExcel<>(CustomerVo.class);
        readExcel.getExcelInfo("扣款导入文件.xls",null,null);
    }

}
