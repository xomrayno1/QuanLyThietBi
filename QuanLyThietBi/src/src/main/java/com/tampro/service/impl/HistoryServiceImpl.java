package com.tampro.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tampro.dao.HistoryDAO;
import com.tampro.dto.HistoryDTO;
import com.tampro.entity.History;
import com.tampro.entity.Maintenance;
import com.tampro.service.HistoryService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryDAO<History> historyDAO;
	@Autowired
	ServletContext context;
	
	public void delete(HistoryDTO historyDTO) {
		// TODO Auto-generated method stub
		History history = new History();
		history.setActiveFlag(0);
		history.setCreateDate(historyDTO.getCreateDate());
		history.setDescription(historyDTO.getDescription());
		history.setId(historyDTO.getId());
		history.setImgUrl(historyDTO.getImgUrl());
		history.setMaintenance(new Maintenance(historyDTO.getMaintenanceId()));
		history.setUpdateDate(new Date());
		historyDAO.delete(history);
	}

	public List<HistoryDTO> getAll(HistoryDTO historyDTO, Paging paging) {
		// TODO Auto-generated method stub
		
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(historyDTO != null) {
//			if(historyDTO.getDateTo() != null && historyDTO.getDateFrom() != null) {
//			queryStr.append(" and model.createDate  between date(:dateTo) and date(:dateFrom) ");
//			mapParams.put("dateTo", historyDTO.getDateTo());
//			mapParams.put("dateFrom", historyDTO.getDateFrom());
//			}
		}
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		for(History history : historyDAO.findAll(mapParams, queryStr.toString(), paging)) {
			HistoryDTO dto = ConvertDTO.convertHistoryToDTO(history);
			list.add(dto);
		}
		return list;
	}

	public List<HistoryDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		for(History history : historyDAO.findByProperty(property, object)) {
			HistoryDTO dto = ConvertDTO.convertHistoryToDTO(history);
			list.add(dto);
		}
		return list;
	}

	public HistoryDTO findById(int id) {
		// TODO Auto-generated method stub
		History history = historyDAO.findById(History.class, id);
		HistoryDTO dto = ConvertDTO.convertHistoryToDTO(history);
		return dto;
	}

	public void add(HistoryDTO historyDTO) throws Exception {
		// TODO Auto-generated method stub
		History history = new History();
		history.setActiveFlag(1);
		history.setCreateDate(new Date());
		history.setDescription(historyDTO.getDescription());
		if(!historyDTO.getMultipartFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+historyDTO.getMultipartFile().getOriginalFilename();
			uploadFile(historyDTO.getMultipartFile(),images);
			history.setImgUrl("/upload/"+images);			
		}
		history.setMaintenance(new Maintenance(historyDTO.getMaintenanceId()));
		history.setUpdateDate(new Date());
		historyDAO.insert(history);
	}
	public void uploadFile(MultipartFile multipartFile,String images ) throws IOException {
		File file = new File(Constant.ABSOLUTE_PATH + images);
		String realPath = context.getRealPath("/")+"upload\\"+images;
		byte[] bytes = multipartFile.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		stream.write(bytes);
		stream.close();
		multipartFile.transferTo(new File(realPath));
		
	}
}
