package com.tampro.service;

import java.util.List;

import com.tampro.dto.HistoryDTO;
import com.tampro.utils.Paging;

public interface HistoryService {
	void delete(HistoryDTO historyDTO)  throws Exception;
	void add(HistoryDTO historyDTO) throws Exception;
	List<HistoryDTO> getAll(HistoryDTO historyDTO , Paging paging);
	List<HistoryDTO> getAllByProperty(String property , Object object);
	HistoryDTO findById(int id);
}
