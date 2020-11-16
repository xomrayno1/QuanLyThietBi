package com.tampro.service;

import java.util.List;

import com.tampro.dto.MaintenanceDetailDTO;
import com.tampro.utils.Paging;

public interface HistoryService {
	void delete(MaintenanceDetailDTO historyDTO)  throws Exception;
	void add(MaintenanceDetailDTO historyDTO) throws Exception;
	List<MaintenanceDetailDTO> getAll(MaintenanceDetailDTO historyDTO , Paging paging);
	List<MaintenanceDetailDTO> getAllByProperty(String property , Object object);
	MaintenanceDetailDTO findById(int id);
}
