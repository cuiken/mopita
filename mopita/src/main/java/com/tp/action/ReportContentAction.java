package com.tp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.tp.entity.LogContentMarket;
import com.tp.entity.LogCountContent;
import com.tp.orm.Page;
import com.tp.orm.PropertyFilter;
import com.tp.orm.PageRequest.Sort;
import com.tp.service.LogService;
import com.tp.utils.ServletUtils;
import com.tp.utils.Struts2Utils;

@Namespace("/report")
public class ReportContentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Page<LogCountContent> page = new Page<LogCountContent>();
	private LogService logService;
	private List<Integer> sliders = Lists.newArrayList();

	private Map<String, CellStyle> styles;
	private int rowIndex = 0;

	public String execute() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createTime");
			page.setOrderDir(Sort.DESC);
		}
		page = logService.searchLogCountContent(page, filters);
		sliders = page.getSlider(10);
		return SUCCESS;
	}

	public String export() throws Exception {
		List<LogCountContent> list = logService.getAllContents();
		Workbook wb = exportExcelWorkbook(list);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType(ServletUtils.EXCEL_TYPE);
		ServletUtils.setFileDownloadHeader(response, "内容统计.xls");

		wb.write(response.getOutputStream());
		response.getOutputStream().flush();
		return null;
	}

	private Workbook exportExcelWorkbook(List<LogCountContent> contents) {
		Workbook wb = new HSSFWorkbook();
		createStyles(wb);
		Sheet s = wb.createSheet("sheet");
		generateTitle(s);
		generateHeader(s);
		generateContent(s, contents);
		return wb;
	}

	private void generateTitle(Sheet s) {
		Row r = s.createRow(rowIndex++);
		Cell cl = r.createCell(0);
		cl.setCellValue("内容日报");
		cl.setCellStyle(styles.get("header"));
		s.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
	}

	private void generateHeader(Sheet s) {
		Row r = s.createRow(rowIndex++);
		CellStyle headerStyle = styles.get("header");
		String[] headers = { "序号", "内容标题", "访问总量", "广告访问量", "商店访问量", "下载总量", "市场下载量", "", "自有下载量", "日期" };
		for (int i = 0; i < headers.length; i++) {
			Cell cl = r.createCell(i);
			cl.setCellValue(headers[i]);
			cl.setCellStyle(headerStyle);
		}
		s.addMergedRegion(CellRangeAddress.valueOf("$G$2:$H$2"));
	}

	private void generateContent(Sheet s, List<LogCountContent> contents) {
		CellStyle dateCellStyle = styles.get("dateCell");
		//		CellStyle numberCellStyle = styles.get("numberCell");
		int stCell = 3;

		for (LogCountContent content : contents) {
			if (content.getDownByPerMarket().size() == 0) {
				Row r = s.createRow(rowIndex++);
				Cell c1 = r.createCell(1);
				c1.setCellValue(content.getThemeName());
				c1.setCellValue(content.getThemeName());
				Cell c2 = r.createCell(2);
				c2.setCellValue(content.getTotalVisit());
				Cell c3 = r.createCell(3);
				c3.setCellValue(content.getVisitByAd());
				Cell c4 = r.createCell(4);
				c4.setCellValue(content.getVisitByStore());
				Cell c5 = r.createCell(5);
				c5.setCellValue(content.getTotalDown());
				Cell c8 = r.createCell(8);
				c8.setCellValue(content.getDownByStore());
				Cell c9 = r.createCell(9);
				c9.setCellValue(content.getLogDate());
				c9.setCellStyle(dateCellStyle);
				stCell++;
			}

			for (LogContentMarket m : content.getDownByPerMarket()) {
				Row r = s.createRow(rowIndex++);
				Cell c1 = r.createCell(1);
				c1.setCellValue(content.getThemeName());
				Cell c2 = r.createCell(2);
				c2.setCellValue(content.getTotalVisit());
				Cell c3 = r.createCell(3);
				c3.setCellValue(content.getVisitByAd());
				Cell c4 = r.createCell(4);
				c4.setCellValue(content.getVisitByStore());
				Cell c5 = r.createCell(5);
				c5.setCellValue(content.getTotalDown());
				Cell c6 = r.createCell(6);
				c6.setCellValue(m.getMarketName());
				Cell c7 = r.createCell(7);
				c7.setCellValue(m.getTotalDown());
				Cell c8 = r.createCell(8);
				c8.setCellValue(content.getDownByStore());
				Cell c9 = r.createCell(9);
				c9.setCellValue(content.getLogDate());
				c9.setCellStyle(dateCellStyle);
			}
			int size = content.getDownByPerMarket().size();
			if (size > 0) {
				s.addMergedRegion(CellRangeAddress.valueOf("$A$" + stCell + ":$A$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$B$" + stCell + ":$B$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$C$" + stCell + ":$C$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$D$" + stCell + ":$D$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$E$" + stCell + ":$E$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$F$" + stCell + ":$F$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$I$" + stCell + ":$I$" + (stCell + size - 1)));
				s.addMergedRegion(CellRangeAddress.valueOf("$J$" + stCell + ":$J$" + (stCell + size - 1)));
				stCell += size;
			}
		}
	}

	private Map<String, CellStyle> createStyles(Workbook wb) {
		styles = Maps.newHashMap();
		DataFormat df = wb.createDataFormat();

		// --字体设定 --//

		//普通字体
		Font normalFont = wb.createFont();
		normalFont.setFontHeightInPoints((short) 10);

		//加粗字体
		Font boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		//蓝色加粗字体
		Font blueBoldFont = wb.createFont();
		blueBoldFont.setFontHeightInPoints((short) 10);
		blueBoldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		blueBoldFont.setColor(IndexedColors.BLUE.getIndex());

		// --Cell Style设定-- //

		//标题格式
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(boldFont);
		styles.put("header", headerStyle);

		//日期格式
		CellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setFont(normalFont);
		dateCellStyle.setDataFormat(df.getFormat("yyyy"));
		setBorder(dateCellStyle);
		styles.put("dateCell", dateCellStyle);

		//数字格式
		CellStyle numberCellStyle = wb.createCellStyle();
		numberCellStyle.setFont(normalFont);
		numberCellStyle.setDataFormat(df.getFormat("#,##0.00"));
		setBorder(numberCellStyle);
		styles.put("numberCell", numberCellStyle);

		//合计列格式
		CellStyle totalStyle = wb.createCellStyle();
		totalStyle.setFont(blueBoldFont);
		totalStyle.setWrapText(true);
		totalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		setBorder(totalStyle);
		styles.put("total", totalStyle);

		return styles;
	}

	private void setBorder(CellStyle style) {
		//设置边框
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Page<LogCountContent> getPage() {
		return page;
	}

	public List<Integer> getSliders() {
		return sliders;
	}
}
