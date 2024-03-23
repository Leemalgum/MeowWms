package com.ssg.meowwms.service;

import com.ssg.meowwms.service.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;


}
