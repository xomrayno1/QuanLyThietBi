package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.InventoryDAO;
import com.tampro.entity.Inventory;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InventoryDAOImpl extends BaseDAOImpl<Inventory>implements InventoryDAO<Inventory>{

}
