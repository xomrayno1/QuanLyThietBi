package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.MaintenanceDAO;
import com.tampro.entity.Maintenance;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MaintenanceDAOImpl  extends BaseDAOImpl<Maintenance>implements MaintenanceDAO<Maintenance>{

}
