package com.tampro.dto;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDTO  extends Base{
	private int id;
	private int type;
	private UserDTO userDTO;
	private ProductDTO productDTO;
	private BigDecimal price;
	private Date dateTo;
	private Date dateFrom;
	private InventoryDTO inventoryDTO;
	private int quantity;
	private int invenId;
	private int productId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getInvenId() {
		return invenId;
	}
	public void setInvenId(int invenId) {
		this.invenId = invenId;
	}
	public InventoryDTO getInventoryDTO() {
		return inventoryDTO;
	}
	public void setInventoryDTO(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}
