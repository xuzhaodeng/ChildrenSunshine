package com.pas.edu.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.pas.edu.entity.NameSheet;
import com.pas.edu.entity.PoiChildRoster;
import com.pas.edu.entity.Summary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Description:poi excel export
 * @author:zht
 * @time:2017年5月22日 下午2:12:19
 */

public class ExcelUtil {
	
    /**
     * 导出汇总表格
     * @throws Exception
     */
    public static void exportTotalExcel(FileOutputStream fos, String organization, List<Summary> summaries) throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();//缓存
//        workbook.setCompressTempFiles(true);
        //表头样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //设置垂直居中
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 12);  //字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  //字体状态正常
        titleStyle.setFont(titleFont);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        // 生成一个(带标题)表格
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow titleRow = (HSSFRow) sheet.createRow(0);//表头 rowIndex=0

        HSSFCell titleCell = titleRow.createCell(0);
        titleRow.getCell(0).setCellStyle(titleStyle);
        titleCell.setCellValue("填报单位：" + organization + "                                                                                                                 填报日期：" + TimeUtils.timestamp2DateString(System.currentTimeMillis()));
        titleRow.setHeight((short) 600);  //行高
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));  //合并


        // 列头样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//前景色样式
        headerStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());//前景色颜色 灰色X17
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //设置垂直居中
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        Font headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short) 10);  //字体大小
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //字体状态正常
        headerStyle.setFont(headFont);


        HSSFRow headRow = sheet.createRow(1);
        headRow.setHeight((short) 400);
        //第一列
        setCellContent(sheet, headRow, "县（市、区）", 0, headerStyle);
        //第二列
        setCellContent(sheet, headRow, "孤儿", 1, headerStyle);
        //第三列
        setCellContent(sheet, headRow, "特困儿童", 2, headerStyle);
        //第四列
        setCellContent(sheet, headRow, "重病重残儿童", 3, headerStyle);
        //第四列
        setCellContent(sheet, headRow, "其他困境儿童", 4, headerStyle);
        setCellContent(sheet, headRow, "合计", 5, headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
        headRow.createCell(6).setCellValue("基本生活保障");
        headRow.getCell(6).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
        headRow.createCell(8).setCellValue("教育保障");
        headRow.getCell(8).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
        headRow.createCell(10).setCellValue("基本医疗保障");
        headRow.getCell(10).setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 13));
        headRow.createCell(12).setCellValue("落实监护责任");
        headRow.getCell(12).setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 15));
        headRow.createCell(14).setCellValue("残疾儿童福利服务");
        headRow.getCell(14).setCellStyle(headerStyle);

        HSSFRow thirdRow = sheet.createRow(2);
        setNextCellConten(thirdRow, 6, true, headerStyle);
        setNextCellConten(thirdRow, 7, false, headerStyle);
        setNextCellConten(thirdRow, 8, true, headerStyle);
        setNextCellConten(thirdRow, 9, false, headerStyle);
        setNextCellConten(thirdRow, 10, true, headerStyle);
        setNextCellConten(thirdRow, 11, false, headerStyle);
        setNextCellConten(thirdRow, 12, true, headerStyle);
        setNextCellConten(thirdRow, 13, false, headerStyle);
        setNextCellConten(thirdRow, 14, true, headerStyle);
        setNextCellConten(thirdRow, 15, false, headerStyle);
        /**以上全是表头信息*/


        //根据有多少个市区县，建立多少行
        for (int i = 0; i < summaries.size(); i++) {
            HSSFRow contentRow = sheet.createRow(i + 3);
            Summary summary = summaries.get(i);
            CellStyle contentStyle = workbook.createCellStyle();
            contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

            contentRow.createCell(0).setCellValue(summary.getOrgName());
            contentRow.createCell(1).setCellValue(summary.getOrphanCount());
            contentRow.createCell(2).setCellValue(summary.getProvertyCount());
            contentRow.createCell(3).setCellValue(summary.getDisabilityCount());
            contentRow.createCell(4).setCellValue(summary.getOtherDifficultCount());
            contentRow.createCell(5).setCellValue(summary.getTotal());
            contentRow.createCell(6).setCellValue(summary.getBaseProtectCount());
            contentRow.createCell(7).setCellValue(summary.getBaseNotProtectCount());
            contentRow.createCell(8).setCellValue(summary.getEduProtectCount());
            contentRow.createCell(9).setCellValue(summary.getEduNotProtectCount());
            contentRow.createCell(10).setCellValue(summary.getMedicalProtectCount());
            contentRow.createCell(11).setCellValue(summary.getMedicalNotProtectCount());
            contentRow.createCell(12).setCellValue(summary.getCustodyCount());
            contentRow.createCell(13).setCellValue(summary.getCustodyNotCount());
            contentRow.createCell(14).setCellValue(summary.getDisabilityWelfareCount());
            contentRow.createCell(15).setCellValue(summary.getDisabilityNotWelfareCount());


        }

        /**下面全是备注信息*/
        HSSFRow attentionRow = sheet.createRow(summaries.size() + 3);
        CellStyle attentionRowStyle = workbook.createCellStyle();
        HSSFFont attentionRowFont = workbook.createFont();
        //注意事项的单元格格式
        attentionRowFont.setFontHeightInPoints((short) 10);  //字体大小
        attentionRowFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //字体加粗
        attentionRowStyle.setFont(attentionRowFont);
        attentionRowStyle.setWrapText(true);  //自动换行
        attentionRowStyle.setAlignment(CellStyle.ALIGN_LEFT);
        attentionRowStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        attentionRowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        attentionRowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        attentionRowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        attentionRowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        sheet.addMergedRegion(new CellRangeAddress(summaries.size() + 3, summaries.size() + 3, 0, 15));
        attentionRow.setHeight((short) 500);
        attentionRow.createCell(0).setCellValue("注：统计单位：人，已保人数指在某一保障方面享受一种或多种保障措施的困境儿童人数，未保数指某一保障方面未享受任何保障措施的人数，即未保障、未服务、未监护的人数。");
        attentionRow.getCell(0).setCellStyle(attentionRowStyle);

        HSSFRow lastRow = sheet.createRow(summaries.size() + 4);
        sheet.addMergedRegion(new CellRangeAddress(summaries.size() + 4, summaries.size() + 4, 0, 15));
        lastRow.setHeight((short) 400);
        lastRow.createCell(0).setCellValue("填报人：" + organization + "                               联系方式：" + organization + "                           民政部负责人：" + organization);
        lastRow.getCell(0).setCellStyle(attentionRowStyle);


//        workbook.write(new FileOutputStream(new File("C:/Users/tyson/Desktop/困境儿童统计表.xls")));
        workbook.write(fos);

    }

    /**
     * @param sheet     列表
     * @param row       行
     * @param content   单元格内容
     * @param column    列
     * @param cellStyle 格式
     */
    public static void setCellContent(HSSFSheet sheet, HSSFRow row, String content, int column, CellStyle cellStyle) {
        sheet.addMergedRegion(new CellRangeAddress(1, 2, column, column));
        row.createCell(column).setCellValue(content);
        sheet.setColumnWidth(column, 15 * 256);
        row.getCell(column).setCellStyle(cellStyle);
    }

    /**
     * @param row           行
     * @param column        列
     * @param haveSafeguard 是否已经保障
     * @param cellStyle     单元格格式
     */
    public static void setNextCellConten(HSSFRow row, int column, boolean haveSafeguard, CellStyle cellStyle) {
        if (!haveSafeguard) {
            row.createCell(column).setCellValue("未保");
        } else {
            row.createCell(column).setCellValue("已保");
        }
        row.getCell(column).setCellStyle(cellStyle);
    }


    /**
     * 导出具体儿童信息
     * @param childDetail
     * @param fos
     * @throws Exception
     */
    public static void exportDetailExcel(PoiChildRoster childDetail, FileOutputStream fos) throws Exception {
    	HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet();//创建一个表格
        sheet.setColumnWidth(0, 30 * 256);//设置第一列的宽度
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);


        /**第一列的样式*/
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_LEFT);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleStyle.setFont(titleFont);


        /**左边的样式*/
        HSSFCellStyle leftStyle = workbook.createCellStyle();
        leftStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//前景色样式
        leftStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//前景色颜色 灰色X17
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        leftStyle.setFont(titleFont);
        leftStyle.setAlignment(CellStyle.ALIGN_CENTER);
        leftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        leftStyle.setWrapText(true);  //设置自动换行

        /**所有单元格样式*/
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        HSSFRow firstRow = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        firstRow.setHeight((short) 700);
        HSSFCell firstRowCell = firstRow.createCell(0);

        firstRowCell.setCellValue("填报人：" + childDetail.getWriteBy() + "填报日期：" + TimeUtils.timestamp2DateString(System.currentTimeMillis()));
        firstRowCell.setCellStyle(titleStyle);


        sheet.addMergedRegion(new CellRangeAddress(1, 7, 0, 0));
        //第二行，第一列
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) 700);
        HSSFCell row1Cell0 = row1.createCell(0);
        setCellData(row1Cell0, "困境儿童情况", leftStyle);

        sheet.addMergedRegion(new CellRangeAddress(1, 4, 6, 6));

        //第二行，第二列
        HSSFCell row1Cell1 = row1.createCell(1);

        setCellData(row1Cell1, "姓名", cellStyle);

        HSSFCell row1Cell2 = row1.createCell(2);
        setCellData(row1Cell2, childDetail.getChildName(), cellStyle);
        HSSFCell row1Cell3 = row1.createCell(3);
        setCellData(row1Cell3, "性别", cellStyle);
        HSSFCell row1Cell4 = row1.createCell(4);
        setCellData(row1Cell4, childDetail.getChildSex(), cellStyle);
        HSSFCell row1Cell5 = row1.createCell(5);
        setCellData(row1Cell5, "", cellStyle);

        //第三行内容
        HSSFRow row2 = sheet.createRow(2);
        row2.setHeight((short) 700);
        HSSFCell row2Cell1 = row2.createCell(1);
        HSSFCell row2Cell2 = row2.createCell(2);
        HSSFCell row2Cell3 = row2.createCell(3);
        HSSFCell row2Cell4 = row2.createCell(4);
        HSSFCell row2Cell5 = row2.createCell(5);
        setCellData(row2Cell1, "出生年月", cellStyle);
        setCellData(row2Cell2, childDetail.getChildBornTime(), cellStyle);
        setCellData(row2Cell3, "民族", cellStyle);
        setCellData(row2Cell4, childDetail.getNation(), cellStyle);
        setCellData(row2Cell5, "", cellStyle);


        //第四行
        HSSFRow row3 = sheet.createRow(3);
        row3.setHeight((short) 700);
        HSSFCell row3Cell1 = row3.createCell(1);
        HSSFCell row3Cell2 = row3.createCell(2);
        setCellData(row3Cell1, "户籍地", cellStyle);
        setCellData(row3Cell2, childDetail.getAddress(), cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 5));

        //第五行
        HSSFRow row4 = sheet.createRow(4);
        row4.setHeight((short) 700);
        HSSFCell row4Cell1 = row4.createCell(1);
        HSSFCell row4Cell2 = row4.createCell(2);
        HSSFCell row4Cell4 = row4.createCell(4);
        HSSFCell row4Cell5 = row4.createCell(5);
        setCellData(row4Cell1, "身份证号", cellStyle);
        setCellData(row4Cell2, childDetail.getChildIdCard(), cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));
        setCellData(row4Cell4, "家庭人口", cellStyle);
        setCellData(row4Cell5, childDetail.getFamilyPopulation() + "", cellStyle);


        //第六行
        HSSFRow row5 = sheet.createRow(5);
        row5.setHeight((short) 700);
        HSSFCell row5Cell1 = row5.createCell(1);
        HSSFCell row5Cell2 = row5.createCell(2);
        HSSFCell row5Cell3 = row5.createCell(3);
        setCellData(row5Cell1, "致困原因", cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(5, 7, 1, 1));
        setCellData(row5Cell2, "儿童本身原因,", cellStyle);
        setCellData(row5Cell3, childDetail.getItselfReason(), cellStyle);

        //第七行
        HSSFRow row6 = sheet.createRow(6);
        row6.setHeight((short) 700);
        HSSFCell row6Cell2 = row6.createCell(2);
        HSSFCell row6Cell3 = row6.createCell(3);
        setCellData(row6Cell2, "家庭原因", cellStyle);
        setCellData(row6Cell3, childDetail.getFamilyReason(), cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 6));
        //第八行
        HSSFRow row8 = sheet.createRow(7);
        row8.setHeight((short) 700);
        HSSFCell row8Cell2 = row8.createCell(2);
        HSSFCell row8Cell3 = row8.createCell(3);
        setCellData(row8Cell2, "监护缺少", cellStyle);
        setCellData(row8Cell3, childDetail.getGuaReason(), cellStyle);

        //第九行
        HSSFRow row9 = sheet.createRow(8);
        row9.setHeight((short) 700);
        HSSFCell row9Cell0 = row9.createCell(0);
        HSSFCell row9Cell1 = row9.createCell(1);
        HSSFCell row9Cell2 = row9.createCell(2);
        HSSFCell row9Cell3 = row9.createCell(3);
        HSSFCell row9Cell4 = row9.createCell(4);
        HSSFCell row9Cell5 = row9.createCell(5);
        HSSFCell row9Cell6 = row9.createCell(6);
        setCellData(row9Cell0, "户主（监护人）情况", leftStyle);
        sheet.addMergedRegion(new CellRangeAddress(8, 10, 0, 0));
        setCellData(row9Cell1, "姓名", cellStyle);
        setCellData(row9Cell2, childDetail.getGuaName(), cellStyle);
        setCellData(row9Cell3, "性别", cellStyle);
        setCellData(row9Cell4, childDetail.getGuaSex(), cellStyle);
        setCellData(row9Cell5, "出生年月", cellStyle);
        setCellData(row9Cell6, childDetail.getGuaBornTime(), cellStyle);

        //第10行
        HSSFRow row10 = sheet.createRow(9);
        row10.setHeight((short) 700);
        HSSFCell row10Cell1 = row10.createCell(1);
        HSSFCell row10Cell2 = row10.createCell(2);
        setCellData(row10Cell1, "监护人联系方式", cellStyle);
        setCellData(row10Cell2, childDetail.getGuaTelNum(), cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 2, 6));

        //地十一行
        HSSFRow row11 = sheet.createRow(10);
        row11.setHeight((short) 700);
        HSSFCell row11Cell1 = row11.createCell(1);
        HSSFCell row11Cell2 = row11.createCell(2);
        HSSFCell row11Cell5 = row11.createCell(5);
        HSSFCell row11Cell6 = row11.createCell(6);
        setCellData(row11Cell1, "身份证号", cellStyle);
        setCellData(row11Cell2, childDetail.getGuaIdCard(), cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 2, 4));
        setCellData(row11Cell5, "与儿童关系", cellStyle);
        setCellData(row11Cell6, childDetail.getGuaChildRela(), cellStyle);

        HSSFRow row12 = sheet.createRow(11);
        row12.setHeight((short) 700);
        HSSFCell row12Cell0 = row12.createCell(0);
        HSSFCell row12Cell1 = row12.createCell(1);
        setCellData(row12Cell0, "监护情况", leftStyle);
        String guaHappening = childDetail.getGuaHappening();
        if (guaHappening == null)
            return;
        String guaHappeningContent = "";
        if (guaHappening.equals("A")) {
            guaHappeningContent = "监护良好（有监护人且较好履行监护义务）";
        } else if (guaHappening.equals("B")) {
            guaHappeningContent = "监护一般（有监护人但监护能力不足）";
        } else if (guaHappening.equals("C")) {
            guaHappeningContent = "未监护（无监护人或者有监护人但不履行监护义务）";
        }
        setCellData(row12Cell1, guaHappeningContent, cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 6));


        HSSFRow row13 = sheet.createRow(12);
        row13.setHeight((short) 700);
        HSSFCell row13Cell0 = row13.createCell(0);
        HSSFCell row13Cell1 = row13.createCell(1);
        setCellData(row13Cell0, "困境类别", leftStyle);
        String dilemmaCategory = childDetail.getDilemmaCategory();
        String dilemmaContent = "";
        if (dilemmaCategory == null)
            return;
        if (dilemmaCategory.equals("A")) {
            dilemmaContent = "孤儿";
        } else if (dilemmaCategory.equals("B")) {
            dilemmaContent = "特困儿童";
        } else if (dilemmaCategory.equals("C")) {
            dilemmaContent = "重病重残儿童";
        } else if (dilemmaCategory.equals("D")) {
            dilemmaContent = "贫困家庭儿童";
        } else if (dilemmaCategory.equals("E-1")) {
            dilemmaContent = "其他-打拐解救儿童";
        } else if (dilemmaCategory.equals("E-2")) {
            dilemmaContent = "其他-服刑人员子女";
        } else if (dilemmaCategory.equals("E-3")) {
            dilemmaContent = "其他-强制隔离戒毒人员子女";
        } else if (dilemmaCategory.equals("E-4")) {
            dilemmaContent = "受虐儿童";
        } else if (dilemmaCategory.equals("E-5")) {
            dilemmaContent = "被恶意弃养儿童";
        } else if (dilemmaCategory.equals("E-6")) {
            dilemmaContent = "其他情况";
        }
        setCellData(row13Cell1, dilemmaContent, cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 6));
        String[] welfares = childDetail.getWelfareHappening().split(",");
        for (int i = 0; i < welfares.length; i++) {
            String welfare = welfares[i];
            if (welfare == null)
                return;
            int currentRow = i + 13;
            HSSFRow rowI1 = sheet.createRow(currentRow);
            rowI1.setHeight((short) 700);
            HSSFCell rowI1Cell = rowI1.createCell(1);
            String welfareContent = "";

            if (welfare.equals("A")) {
                welfareContent = "纳入明天计划";
            } else if (welfare.equals("B")) {
                welfareContent = "免费配置康复器具";
            } else if (welfare.equals("C")) {
                welfareContent = "免费接受康复训练";
            } else if (welfare.equals("D")) {
                welfareContent = "接受福利机构替代照料、养育辅导、康复训练等服务";
            } else if (welfare.equals("0")) {
                welfareContent = "未服务";
            }
            setCellData(rowI1Cell, welfareContent, cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 6));//合并每一行后面的内容
        }
        /**
         * 这里要用get来获取，因为在上面的循环里面，已经创建了13行，如果再用create来操作，会导致第一次创建的呗覆盖
         */
        HSSFRow row14 = sheet.getRow(13);
        row14.setHeight((short) 700);
        HSSFCell row14Cell0 = row14.createCell(0);
        setCellData(row14Cell0, "残疾儿童福利情况（当困境类别为C时，此项必填）", leftStyle);
        if (welfares.length + 12 > 13) {
            //只有当后者大于前者时，才能进行合并
            sheet.addMergedRegion(new CellRangeAddress(13, welfares.length + 12, 0, 0));
        }


        int rangRow = 13 + welfares.length;

        String[] basicLifes = childDetail.getBasicLifeHappening().split(",");

        for (int i = 0; i < basicLifes.length; i++) {
            String basicLife = basicLifes[i];
            String basicContent = "";
            if (basicLife == null)
                return;
            if (basicLife.equals("A")) {
                basicContent = "孤儿保障";
            } else if (basicLife.equals("B")) {
                basicContent = "特困救助";
            } else if (basicLife.equals("C")) {
                basicContent = "低保救助";
            } else if (basicLife.equals("D")) {
                basicContent = "临时救助";
            } else if (basicLife.equals("0")) {
                basicContent = "未保障";

            } else if (basicLife.equals("E")) {
                basicContent = "残疾人两项补贴";
            } else if (basicLife.equals("F")) {
                basicContent = "其他形式生活保障";

            }
            int currentRow = rangRow + i;
            HSSFRow rowI3 = sheet.createRow(currentRow);
            rowI3.setHeight((short) 700);
            HSSFCell rowI1Cell = rowI3.createCell(1);
            setCellData(rowI1Cell, basicContent, cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 3));
            if (basicLife.equals("0")) {
                HSSFRow rangRow1 = sheet.getRow(rangRow);
                HSSFCell rangRow1Cell4 = rangRow1.createCell(4);
                setCellData(rangRow1Cell4, childDetail.getOtherBasicLife(), cellStyle);
            }
        }
        HSSFRow rangRow1 = sheet.getRow(rangRow);
        rangRow1.setHeight((short) 700);
        HSSFCell rangRow1Cell0 = rangRow1.createCell(0);
        if (basicLifes.length - 1 + rangRow > rangRow) {
            sheet.addMergedRegion(new CellRangeAddress(rangRow, basicLifes.length - 1 + rangRow, 0, 0));
        }
        setCellData(rangRow1Cell0, "基本生活情况", leftStyle);
        //合并最后的备注部分
        sheet.addMergedRegion(new CellRangeAddress(rangRow, rangRow + basicLifes.length - 1, 4, 6));
        rangRow = rangRow + basicLifes.length;
        /*==================*/
        HSSFRow rangRow2 = sheet.createRow(rangRow);
        rangRow2.setHeight((short) 700);
        HSSFCell rangRow2Cell0 = rangRow2.createCell(0);
        HSSFCell rangRow2Cell1 = rangRow2.createCell(1);
        HSSFCell rangRow2Cell2 = rangRow2.createCell(2);
        setCellData(rangRow2Cell0, "教育情况（单选）", leftStyle);
        String educationHappening = childDetail.getEducationHappening();
        String educationContent = "";
        if (educationHappening == null)
            return;
        if (educationHappening.equals("A")) {
            educationContent = "学前阶段保障";
        } else if (educationHappening.equals("B")) {
            educationContent = "义务教育两免一补";
        } else if (educationHappening.equals("C")) {
            educationContent = "高中、中职教育资助";
        } else if (educationHappening.equals("D")) {
            educationContent = "辍学";
        } else if (educationHappening.equals("E")) {
            educationContent = "未入学";
        } else if (educationHappening.equals("0")) {
            educationContent = "未保障";
        }

        setCellData(rangRow2Cell1, educationContent, cellStyle);
        if (educationHappening.equals("0")) {
            setCellData(rangRow2Cell2, childDetail.getOtherEducation(), cellStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(rangRow, rangRow, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(rangRow, rangRow, 4, 6));

        rangRow = rangRow + 1;

        String[] medicals = childDetail.getMedicalHappening().split(",");

        for (int i = 0; i < medicals.length; i++) {
            String medical = medicals[i];
            String medicalContent = "";
            if (medical == null)
                return;
            if (medical.equals("A")) {
                medicalContent = "居民基本医疗保险";
            } else if (medical.equals("B")) {
                medicalContent = "大病保险";
            } else if (medical.equals("C")) {
                medicalContent = "大病补充医疗保险";
            } else if (medical.equals("D")) {
                medicalContent = "大病医疗救助";
            } else if (medical.equals("0")) {
                medicalContent = "未保障";

            } else if (medical.equals("E")) {
                medicalContent = "慈善救助";
            }
            int currentRow = rangRow + i;
            HSSFRow rowI2 = sheet.createRow(currentRow);
            rowI2.setHeight((short) 700);
            HSSFCell rowI2Cell = rowI2.createCell(1);

            setCellData(rowI2Cell, medicalContent, cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 3));

            if (medical.equals("0")) {
                HSSFRow rangRow3 = sheet.getRow(rangRow);
                HSSFCell rangRow3Cell4 = rangRow3.createCell(4);
                setCellData(rangRow3Cell4, childDetail.getOtherMedical(), cellStyle);

            }
        }
        HSSFRow rangRow3 = sheet.getRow(rangRow);
        rangRow3.setHeight((short) 700);
        HSSFCell rangRow3Cell0 = rangRow3.createCell(0);
        if (medicals.length - 1 + rangRow > rangRow) {
            sheet.addMergedRegion(new CellRangeAddress(rangRow, rangRow + medicals.length - 1, 0, 0));
        }
        setCellData(rangRow3Cell0, "医疗情况", leftStyle);
        sheet.addMergedRegion(new CellRangeAddress(rangRow, rangRow + medicals.length - 1, 4, 6));


//        workbook.write(new FileOutputStream(new File("/Users/wutaisong/Downloads/困境儿童统计表.xls")));
        workbook.write(fos);

    }

    /**
     * 导出花名册列表
     * @param fos
     * @param nameList
     * @param writeBy
     * @throws ParseException
     * @throws IOException
     */
    public static void exportRoster(FileOutputStream fos, List<NameSheet> nameList, String writeBy) throws ParseException, IOException {
    	 HSSFWorkbook workbook = new HSSFWorkbook();
         HSSFSheet sheet = workbook.createSheet();

         sheet.setColumnWidth(0, 8 * 256);
         sheet.setColumnWidth(1, 16 * 256);
         sheet.setColumnWidth(2, 8 * 256);
         sheet.setColumnWidth(3, 18 * 256);
         sheet.setColumnWidth(4, 18 * 256);
         sheet.setColumnWidth(5, 18 * 256);
         sheet.setColumnWidth(6, 16 * 256);
         sheet.setColumnWidth(7, 16 * 256);
         sheet.setColumnWidth(8, 16 * 256);
         sheet.setColumnWidth(9, 16 * 256);
         sheet.setColumnWidth(10, 16 * 256);
         sheet.setColumnWidth(11, 16 * 256);

         HSSFCellStyle titleStyle = workbook.createCellStyle();
         titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
         HSSFFont titleFont = workbook.createFont();
         titleFont.setFontHeightInPoints((short) 12);
         titleStyle.setFont(titleFont);

         HSSFCellStyle headStyle = workbook.createCellStyle();
         HSSFFont headFont = workbook.createFont();
         headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         headFont.setFontHeightInPoints((short) 10);
         headStyle.setFont(headFont);
         headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
         headStyle.setAlignment(CellStyle.ALIGN_CENTER);
         headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//前景色样式
         headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//前景色颜色 灰色X17


         HSSFCellStyle bottomStyle = workbook.createCellStyle();
         bottomStyle.setAlignment(CellStyle.ALIGN_LEFT);
         bottomStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);


         HSSFRow row0 = sheet.createRow(0);
         sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
         HSSFCell row0Cell0 = row0.createCell(0);
         row0Cell0.setCellValue("填报单位：" + writeBy + "填报日期：" + TimeUtils.timestamp2DateString(System.currentTimeMillis()));
         row0.setHeight((short) 600);

         HSSFRow row1 = sheet.createRow(1);
         HSSFCell row1Cell0 = row1.createCell(0);
         HSSFCell row1Cell1 = row1.createCell(1);
         HSSFCell row1Cell2 = row1.createCell(2);
         HSSFCell row1Cell3 = row1.createCell(3);
         HSSFCell row1Cell4 = row1.createCell(4);
         HSSFCell row1Cell5 = row1.createCell(5);
         HSSFCell row1Cell6 = row1.createCell(6);
         HSSFCell row1Cell7 = row1.createCell(7);
         HSSFCell row1Cell8 = row1.createCell(8);
         HSSFCell row1Cell9 = row1.createCell(9);
         HSSFCell row1Cell10 = row1.createCell(10);
         HSSFCell row1Cell11 = row1.createCell(11);

         setCellData(row1Cell0, "序号", headStyle);
         setCellData(row1Cell1, "姓名", headStyle);
         setCellData(row1Cell2, "性别", headStyle);
         setCellData(row1Cell3, "出生年月", headStyle);
         setCellData(row1Cell4, "联系方式", headStyle);
         setCellData(row1Cell5, "住所", headStyle);
         setCellData(row1Cell6, "困境类型", headStyle);
         setCellData(row1Cell7, "生活保障", headStyle);
         setCellData(row1Cell8, "教育保障", headStyle);
         setCellData(row1Cell9, "医疗保障", headStyle);
         setCellData(row1Cell10, "监护情况", headStyle);
         setCellData(row1Cell11, "残疾儿童服务情况", headStyle);

         row1.setHeight((short) 600);

         for (int i = 0; i < nameList.size(); i++) {
             NameSheet nameSheet = nameList.get(i);
             HSSFRow dataRow = sheet.createRow(2 + i);
             HSSFCell dataRowCell0 = dataRow.createCell(0);
             HSSFCell dataRowCell1 = dataRow.createCell(1);
             HSSFCell dataRowCell2 = dataRow.createCell(2);
             HSSFCell dataRowCell3 = dataRow.createCell(3);
             HSSFCell dataRowCell4 = dataRow.createCell(4);
             HSSFCell dataRowCell5 = dataRow.createCell(5);
             HSSFCell dataRowCell6 = dataRow.createCell(6);
             HSSFCell dataRowCell7 = dataRow.createCell(7);
             HSSFCell dataRowCell8 = dataRow.createCell(8);
             HSSFCell dataRowCell9 = dataRow.createCell(9);
             HSSFCell dataRowCell10 = dataRow.createCell(10);
             HSSFCell dataRowCell11 = dataRow.createCell(11);

             dataRowCell0.setCellValue(nameSheet.getChild_id());
             dataRowCell1.setCellValue(nameSheet.getChild_name());
             dataRowCell2.setCellValue(nameSheet.getChild_sex());
             dataRowCell3.setCellValue(nameSheet.getChild_birthday());
             dataRowCell4.setCellValue(nameSheet.getGuardian_phone());
             dataRowCell5.setCellValue(nameSheet.getChild_address());
             String plight_category = nameSheet.getPlight_category();
             String dilemmaContent = "";
             if (plight_category == null)
                 return;
             if (plight_category.equals("A")) {
                 dilemmaContent = "A:孤儿";
             } else if (plight_category.equals("B")) {
                 dilemmaContent = "B:特困儿童";
             } else if (plight_category.equals("C")) {
                 dilemmaContent = "C:重病重残儿童";
             } else if (plight_category.equals("D")) {
                 dilemmaContent = "D:贫困家庭儿童";
             } else if (plight_category.equals("E-1")) {
                 dilemmaContent = "E-1:其他-打拐解救儿童";
             } else if (plight_category.equals("E-2")) {
                 dilemmaContent = "E-2:其他-服刑人员子女";
             } else if (plight_category.equals("E-3")) {
                 dilemmaContent = "E-3:其他-强制隔离戒毒人员子女";
             } else if (plight_category.equals("E-4")) {
                 dilemmaContent = "E-4:受虐儿童";
             } else if (plight_category.equals("E-5")) {
                 dilemmaContent = "E-5:被恶意弃养儿童";
             } else if (plight_category.equals("E-6")) {
                 dilemmaContent = "E-6:其他情况";
             }
             dataRowCell6.setCellValue(dilemmaContent);
             String[] baseLifes = nameSheet.getBasic_life().split(",");
             StringBuilder sb = new StringBuilder();
             for (int j = 0;j<baseLifes.length;j++){
                 String basicLife = baseLifes[j];
                 String basicContent = "";
                 if (basicLife == null)
                     return;
                 if (basicLife.equals("A")) {
                     basicContent = "A:孤儿保障";
                 } else if (basicLife.equals("B")) {
                     basicContent = "B:特困救助";
                 } else if (basicLife.equals("C")) {
                     basicContent = "C:低保救助";
                 } else if (basicLife.equals("D")) {
                     basicContent = "D:临时救助";
                 } else if (basicLife.equals("0")) {
                     basicContent = nameSheet.getOtherBasicLife();

                 } else if (basicLife.equals("E")) {
                     basicContent = "E:残疾人两项补贴";
                 } else if (basicLife.equals("F")) {
                     basicContent = "F:其他形式生活保障";

                 }
                 sb.append(basicContent).append("\n");
             }
             dataRowCell7.setCellValue(sb.toString());
             sb.delete(0,sb.length());
             String education_happening = nameSheet.getEducation_happening();
             String educationContent = "";
             if (education_happening == null)
                 return;
             if (education_happening.equals("A")) {
                 educationContent = "A:学前阶段保障";
             } else if (education_happening.equals("B")) {
                 educationContent = "B:义务教育两免一补";
             } else if (education_happening.equals("C")) {
                 educationContent = "C:高中、中职教育资助";
             } else if (education_happening.equals("D")) {
                 educationContent = "D:辍学";
             } else if (education_happening.equals("E")) {
                 educationContent = "E:未入学";
             } else if (education_happening.equals("0")) {
                 educationContent = nameSheet.getOtherEducation();
             }
             dataRowCell8.setCellValue(educationContent);
             String[] medicals = nameSheet.getMedical_happening().split(",");
             for (int n = 0;n< medicals.length;n++){
                 String medical = medicals[n];
                 String medicalContent = "";
                 if (medical == null)
                     return;
                 if (medical.equals("A")) {
                     medicalContent = "A:居民基本医疗保险";
                 } else if (medical.equals("B")) {
                     medicalContent = "B:大病保险";
                 } else if (medical.equals("C")) {
                     medicalContent = "C:大病补充医疗保险";
                 } else if (medical.equals("D")) {
                     medicalContent = "D:大病医疗救助";
                 } else if (medical.equals("0")) {
                     medicalContent = nameSheet.getOtherMedical();

                 } else if (medical.equals("E")) {
                     medicalContent = "E:慈善救助";
                 }
                 sb.append(medicalContent).append("\n");
             }
             dataRowCell9.setCellValue(sb.toString());
             sb.delete(0,sb.length());
             String guaHappening = nameSheet.getGuardian_hapening();
             if (guaHappening == null)
                 return;
             String guaHappeningContent = "";
             if (guaHappening.equals("A")) {
                 guaHappeningContent = "A:监护良好（有监护人且较好履行监护义务）";
             } else if (guaHappening.equals("B")) {
                 guaHappeningContent = "B:监护一般（有监护人但监护能力不足）";
             } else if (guaHappening.equals("C")) {
                 guaHappeningContent = "C:未监护（无监护人或者有监护人但不履行监护义务）";
             }
             dataRowCell10.setCellValue(guaHappeningContent);
             
             if(nameSheet.getWelfare() == null){
            	 return;
             }
             
             String[] welfares = nameSheet.getWelfare().split(",");
             for (int k = 0; k < welfares.length; k++) {
                 String welfare = welfares[k];
                 String welfareContent = "";

                 if (welfare.equals("A")) {
                     welfareContent = "A:纳入明天计划";
                 } else if (welfare.equals("B")) {
                     welfareContent = "B:免费配置康复器具";
                 } else if (welfare.equals("C")) {
                     welfareContent = "C:免费接受康复训练";
                 } else if (welfare.equals("D")) {
                     welfareContent = "D:接受福利机构替代照料、养育辅导、康复训练等服务";
                 } else if (welfare.equals("0")) {
                     welfareContent = nameSheet.getOtherWelfare();
                 }
                 sb.append(welfareContent).append("\n");
             }
             dataRowCell11.setCellValue(sb.toString());
             sb.delete(0,sb.length());


             dataRow.setHeight((short) 600);
         }


         HSSFRow attentionRow = sheet.createRow(2 + nameList.size());
         HSSFCell attentionRowCell = attentionRow.createCell(0);
         attentionRowCell.setCellValue("注：各项保障服务监护方面的统计均填写代码；");
         attentionRowCell.setCellStyle(bottomStyle);
         attentionRow.setHeight((short) 600);

         sheet.addMergedRegion(new CellRangeAddress(nameList.size() + 2, nameList.size() + 2, 0, 11));

         HSSFRow bottomRow = sheet.createRow(3 + nameList.size());
         HSSFCell bottomRowCell = bottomRow.createCell(0);
         bottomRowCell.setCellValue("填报人：在系统中调用选择                                                                                                         部门负责人审核：根据系统审批流程自动生成     ");
         bottomRowCell.setCellStyle(bottomStyle);
         bottomRow.setHeight((short) 600);
         sheet.addMergedRegion(new CellRangeAddress(nameList.size() + 3, nameList.size() + 3, 0, 11));

         //workbook.write(new FileOutputStream(new File("C:/Users/tyson/Desktop/困境儿童统计表.xls")));
         workbook.write(fos);
    }

    private static void setCellData(HSSFCell cell, String content, CellStyle cellStyle) {
        cell.setCellValue(content);
        cell.setCellStyle(cellStyle);
    }

}