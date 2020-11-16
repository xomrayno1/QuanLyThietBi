package com.tampro.service;

import java.util.List;

import com.tampro.dto.InvoiceDTO;
import com.tampro.utils.Paging;

public interface InvoiceService {
	void add(InvoiceDTO invoiceDTO) throws Exception;
	void update(InvoiceDTO invoiceDTO) throws Exception;
	List<InvoiceDTO> getAll(InvoiceDTO invoiceDTO , Paging paging);
	List<InvoiceDTO> getAllByProperty(String property , Object object);
	InvoiceDTO findById(int id);
}
