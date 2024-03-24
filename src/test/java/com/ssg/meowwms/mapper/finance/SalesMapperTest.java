package com.ssg.meowwms.mapper.finance;

import com.ssg.meowwms.domain.finance.SalesVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SalesMapperTest {

    @Autowired
    private SalesMapper salesMapper;

    @Test
    public void testInsertAndSelectSales() {
        Date date = new Date();
        SalesVO newSale = SalesVO.builder()
                .type("요금정산")
                .amount(1000000)
                .warehouseId(6)
                .salesDate(date)
                .build();
        salesMapper.insertSales(newSale);

        SalesVO fetchedSale = salesMapper.selectSalesById(1);
        assertThat(fetchedSale).isNotNull();
    }

    @Test
    public void testSelectAllSales() {
        List<OptionDTO> options = new ArrayList<>();
        // Populate options as needed for your test
        List<SalesVO> salesList = salesMapper.selectAll(options);
        assertThat(salesList).isNotEmpty();
        // Additional assertions to verify the contents of salesList
    }

    @Test
    public void testUpdateSales() {
        Date date = new Date();
        SalesVO saleToUpdate = SalesVO.builder()
                .id(1)
                .type("요금정산!")
                .amount(1000000)
                .salesDate(date)
                .warehouseId(6)
                .build();
        // Modify saleToUpdate as needed
        salesMapper.updateSales(saleToUpdate);

        SalesVO updatedSale = salesMapper.selectSalesById(saleToUpdate.getId());
        // Assertions to verify that the sale has been updated
        assertThat(updatedSale.getType()).isEqualTo("요금정산!");
    }

    @Test
    public void testDeleteSales() {
        int idToDelete = 1;
        salesMapper.deleteSales(1);

        SalesVO deletedSale = salesMapper.selectSalesById(idToDelete);
        assertThat(deletedSale).isNull();
    }

    @Test
    public void testSumSalesByYear() {
        int warehouseId = 1;
        String year = "2024";
        List<SalesMonthDTO> salesSumByYear = salesMapper.sumSalesByYear(warehouseId, year);
        assertThat(salesSumByYear).isNotEmpty();
        // Additional assertions to verify the sums and months
    }
}
