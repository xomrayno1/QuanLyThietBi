package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.InventoryDTO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.dto.UserDTO;
import com.tampro.service.InventoryService;
import com.tampro.service.InvoiceService;
import com.tampro.service.ProductService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.InvoiceValidator;

@Controller
@RequestMapping("/goods-issue")
public class GoodsIssueController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	InvoiceValidator invoiceValidator;
	@Autowired
	ProductService productService;
	@Autowired
	InventoryService inventoryService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == InvoiceDTO.class) {
			dataBinder.setValidator(invoiceValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/goods-issue/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listInvoice(Model model,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			System.out.println(session.getAttribute(Constant.MSG_ERROR));
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		innitSelect(model);
		return "goods-issue-list";
	}
	@GetMapping(value = {"/add"})
	public String addInvoice(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new InvoiceDTO());
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "goods-issue-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewInvoice(Model model,@PathVariable("id") int id) {
		InvoiceDTO invoiceDTO = invoiceService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", invoiceDTO);
		model.addAttribute("viewOnly", true);
		return "goods-issue-action";
	}

	@GetMapping(value = {"/edit/{id}"})
	public String editInvoice(Model model,@PathVariable("id") int id) {
		InvoiceDTO invoiceDTO = invoiceService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", invoiceDTO);
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "goods-issue-action";
	}
	
	@PostMapping(value = {"/save"})
	public String saveInvoice(Model model,@ModelAttribute("submitForm") @Validated InvoiceDTO invoiceDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(invoiceDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			innitSelect(model);
			model.addAttribute("submitForm", invoiceDTO);
			return "goods-issue-action";
		}
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		if(invoiceDTO.getId() != 0) {
			try {
				invoiceService.update(invoiceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				UserDTO userDTO = (UserDTO)	session.getAttribute(Constant.USER_INFO);		
				invoiceDTO.setUserDTO(userDTO);
				invoiceService.add(invoiceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				session.setAttribute(Constant.MSG_ERROR, e.getMessage());				
				e.printStackTrace();
			}
		}
		return "redirect:/goods-issue/list/1";
	}
	public void innitSelect(Model model) {
		List<ProductDTO> list = productService.getAll(null, null);
		Collections.sort(list,new Comparator<ProductDTO>() {
			public int compare(ProductDTO o1, ProductDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listProduct", list);
		List<InventoryDTO> listInven = inventoryService.getAll(null, null);
		Collections.sort(listInven,new Comparator<InventoryDTO>() {
			public int compare(InventoryDTO o1, InventoryDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listInven", listInven);
	}
}
