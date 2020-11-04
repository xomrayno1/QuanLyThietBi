package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.MaintenanceDTO;
import com.tampro.service.MaintenanceService;

@Component
public class MaintenanceValidator implements Validator{
	@Autowired
	MaintenanceService maintenanceService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(MaintenanceDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MaintenanceDTO maintenanceDTO = (MaintenanceDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		if(!StringUtils.isEmpty(maintenanceDTO.getName())) {
			List<MaintenanceDTO> list = maintenanceService.getAllByProperty("name", maintenanceDTO.getName());		
			if(maintenanceDTO.getId() != 0) {
				if(!list.isEmpty()) {
					MaintenanceDTO current = maintenanceService.findById(maintenanceDTO.getId());
					if(!maintenanceDTO.getName().equals(current.getName())) {
						errors.rejectValue("name", "error.exists");
					}
				}
			}else {
				if(!list.isEmpty()) {
					errors.rejectValue("name", "error.exists");
				}
			}
		}
		if(maintenanceDTO.getIdProduct() == 0) {
			errors.rejectValue("idProduct", "error.required");
		}
	}
	
	
}
