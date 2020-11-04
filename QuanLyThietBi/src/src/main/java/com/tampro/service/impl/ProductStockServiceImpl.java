package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.ProductStockDAO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.ProductStockDTO;
import com.tampro.entity.Inventory;
import com.tampro.entity.Product;
import com.tampro.entity.ProductStock;
import com.tampro.service.InvoiceService;
import com.tampro.service.ProductStockService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class ProductStockServiceImpl  implements ProductStockService{

	@Autowired
	ProductStockDAO<ProductStock> productStockDAO;
	@Autowired
	InvoiceService invoiceService;
	
	public void addOrUpdate(InvoiceDTO invoiceDTO) throws Exception  {
		// TODO Auto-generated method stub
		ProductStock productStock = productStockDAO.findStockByProductIdAndInventoryId(invoiceDTO.getProductId(), invoiceDTO.getInvenId());
		if(invoiceDTO.getId() == 0) {
			if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {			
				if(productStock == null) {
					productStock = new ProductStock();
					productStock.setActiveFlag(1);
					productStock.setCreateDate(new Date());		
					productStock.setProduct(new Product(invoiceDTO.getProductId()));
					productStock.setQuantity(invoiceDTO.getQuantity());
					productStock.setStock(new Inventory(invoiceDTO.getInvenId()));
					productStock.setPrice(invoiceDTO.getPrice());
					productStock.setUpdateDate(new Date());
					productStockDAO.insert(productStock);
				}else {
					productStock.setPrice(invoiceDTO.getPrice());
					productStock.setQuantity(productStock.getQuantity() + invoiceDTO.getQuantity());
					productStockDAO.update(productStock);
				}
			}else {
				if(productStock != null) {				
					if(invoiceDTO.getQuantity()  <= productStock.getQuantity()) {
						productStock.setQuantity(productStock.getQuantity() - invoiceDTO.getQuantity());
						productStockDAO.update(productStock);
					}else {
						throw new Exception("Số lượng trong kho không đủ để xuất");
					}
				}else {
					throw new NullPointerException("Không tìm thấy đối tượng trong kho để xuất");
				}
			}
		}else {	
			InvoiceDTO currentInvoice = invoiceService.findById(invoiceDTO.getId());
			if(invoiceDTO.getType()== Constant.GOODS_RECEIPT) {
				//-				
				if(productStock != null) {
					productStock.setPrice(invoiceDTO.getPrice());
					productStock.setQuantity(productStock.getQuantity() - currentInvoice.getQuantity() + invoiceDTO.getQuantity());
				}
			}else {
				//+
				if(productStock != null) {
					productStock.setQuantity(productStock.getQuantity() + currentInvoice.getQuantity()  -  invoiceDTO.getQuantity());
				}
			}
			productStockDAO.update(productStock);
		}
		 
	}

	


	public List<ProductStockDTO> getAll(ProductStockDTO productStockDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(productStockDTO != null) {
			if(productStockDTO.getProductId() != 0) {
				queryStr.append(" and  model.product.id = :productId ");
				mapParams.put("productId", productStockDTO.getProductId());
			}
			if(productStockDTO.getStockId() != 0) {
				queryStr.append(" and  model.stock.id = :invenId ");
				mapParams.put("invenId", productStockDTO.getStockId());
			}
		}
		List<ProductStockDTO> list = new ArrayList<ProductStockDTO>();
		for(ProductStock productStock : productStockDAO.findAll(mapParams, queryStr.toString(), paging)) {
			ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
			list.add(dto);
		}
		return list;
	}

	public List<ProductStockDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<ProductStockDTO> list = new ArrayList<ProductStockDTO>();
		for(ProductStock productStock : productStockDAO.findByProperty(property, object)) {
			ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
			list.add(dto);
		}
		return list;
	}

	public ProductStockDTO findById(int id) {
		// TODO Auto-generated method stub
		ProductStock productStock = productStockDAO.findById(ProductStock.class, id);
		ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
		return dto;
	}

	public void delete(ProductStockDTO productStockDTO) {
		// TODO Auto-generated method stub
		ProductStock productStock = new ProductStock();
		productStock.setId(productStockDTO.getId());
		productStock.setCreateDate(productStockDTO.getCreateDate());
		productStock.setActiveFlag(0);
		productStock.setPrice(productStockDTO.getPrice());
		productStock.setProduct(new Product(productStockDTO.getProductId()));
		productStock.setQuantity(productStockDTO.getQuantity());
		productStock.setStock(new Inventory(productStockDTO.getStockId()));
		productStock.setUpdateDate(new Date());
		productStockDAO.delete(productStock);
	}
	







}
