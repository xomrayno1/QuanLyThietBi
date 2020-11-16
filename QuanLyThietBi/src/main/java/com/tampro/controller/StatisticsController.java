package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.MaintenanceDTO;
import com.tampro.report.ReportGoodsIssue;
import com.tampro.report.ReportGoodsReceipt;
import com.tampro.report.ReportMaintenance;
import com.tampro.service.InvoiceService;
import com.tampro.service.MaintenanceService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	MaintenanceService maintenanceService;
	@Autowired
	InvoiceService invoiceService;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		 
	}
	@GetMapping(value = {"/maintenance/","/maintenance"})
	public String redirectMaintenance() {
		return "redirect:/statistics/maintenance/1";
	}
	@RequestMapping(value = {"/maintenance/{page}"})
	public String statisticsMaintenance(Model model,@PathVariable("page") int page,@ModelAttribute("searchForm") MaintenanceDTO  maintenanceDTO) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		return "statistics-maintenance";
	}
	@PostMapping(value = {"/maintenance/export-excel"})
	public ModelAndView exportExcelMaintenance(@ModelAttribute("searchForm") MaintenanceDTO maintenanceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, null);
		if(maintenanceDTO.getDateTo() != null && maintenanceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(maintenanceDTO.getDateTo())+"_"+format.format(maintenanceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportMaintenance());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@GetMapping(value = {"/goods-receipt/","/goods-receipt"})
	public String redirectGoodsReceipt() {
		return "redirect:/statistics/goods-receipt/1";
	}
	@RequestMapping("/goods-receipt/{page}")
	public String listGoodsReceipt(Model model,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		return "statistics-goodsreceipt";
	}
	@PostMapping(value = {"/goods-receipt/export-excel"})
	public ModelAndView exportExcelGoodsReceipt(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null);
		if(invoiceDTO.getDateTo() != null && invoiceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(invoiceDTO.getDateTo())+"_"+format.format(invoiceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportGoodsReceipt());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@GetMapping(value = {"/goods-issue/","/goods-issue"})
	public String redirectGoodsIssue() {
		return "redirect:/statistics/goods-issue/1";
	}
	@RequestMapping("/goods-issue/{page}")
	public String listredirectGoodsIssue(Model model,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
	 
		return "statistics-goodsissue";
	}
	@PostMapping(value = {"/goods-issue/export-excel"})
	public ModelAndView exportExcelGoodIssue(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null);
		if(invoiceDTO.getDateTo() != null && invoiceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(invoiceDTO.getDateTo())+"_"+format.format(invoiceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportGoodsIssue());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
}
