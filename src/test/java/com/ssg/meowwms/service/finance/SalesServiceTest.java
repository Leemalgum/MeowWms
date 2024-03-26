package com.ssg.meowwms.service.finance;

import com.ssg.meowwms.domain.finance.SalesVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.finance.SalesDTO;
import com.ssg.meowwms.dto.finance.SalesMonthDTO;
import com.ssg.meowwms.mapper.finance.ExpenseMapper;
import com.ssg.meowwms.mapper.finance.SalesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SalesServiceTest {

    @Mock
    private SalesMapper salesMapper;

    @Mock
    private ExpenseMapper expenseMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SalesServiceImpl salesService;

    private SalesDTO salesDTO;
    private SalesVO salesVO;
    private OptionDTO optionDTO;

    @BeforeEach
    void setUp() {
        salesDTO = SalesDTO.builder()
                .type("요금정산")
                .amount(1000000)
                .warehouseId(6)
                .salesDate(new Date())
                .build();
        salesVO = SalesVO.builder()
                .type("요금정산")
                .amount(1000000)
                .warehouseId(6)
                .salesDate(new Date())
                .build();;
        optionDTO = new OptionDTO();
        optionDTO.setType("type");
        optionDTO.setValue("온라인");
        when(modelMapper.map(any(SalesDTO.class), eq(SalesVO.class))).thenReturn(salesVO);
        when(modelMapper.map(any(SalesVO.class), eq(SalesDTO.class))).thenReturn(salesDTO);
    }

    @Test
    void insertSales() {
        salesService.insertSales(salesDTO);
        verify(salesMapper, times(1)).insertSales(salesVO);
    }

    @Test
    void selectSalesById() {
        when(salesMapper.selectSalesById(anyInt())).thenReturn(salesVO);
        SalesDTO result = salesService.selectSalesById(1);
        assertNotNull(result);
        verify(salesMapper, times(1)).selectSalesById(1);
    }

    @Test
    void selectAll() {
        when(salesMapper.selectAll(anyList())).thenReturn(Arrays.asList(salesVO));
        List<SalesDTO> results = salesService.selectAll(Arrays.asList(optionDTO));
        assertFalse(results.isEmpty());
        verify(salesMapper, times(1)).selectAll(anyList());
    }

    @Test
    void deleteSales() {
        salesService.deleteSales(1);
        verify(salesMapper, times(1)).deleteSales(1);
    }

    @Test
    void updateSales() {
        salesService.updateSales(salesDTO);
        verify(salesMapper, times(1)).updateSales(salesVO);
    }

    @Test
    void sumSales() {
        when(salesMapper.sumSales(anyList())).thenReturn(100);
        int sum = salesService.sumSales(Arrays.asList(optionDTO));
        assertEquals(100, sum);
        verify(salesMapper, times(1)).sumSales(anyList());
    }

    @Test
    void sumSalesByYear() {
        List<SalesMonthDTO> salesMonthDTOS = Arrays.asList(new SalesMonthDTO());
        when(salesMapper.sumSalesByYear(anyInt(), anyString())).thenReturn(salesMonthDTOS);
        List<SalesMonthDTO> results = salesService.sumSalesByYear(1, "2020");
        assertFalse(results.isEmpty());
        verify(salesMapper, times(1)).sumSalesByYear(1, "2020");
    }

    @Test
    void getAllyears() {
        // 가상의 연도 목록 데이터 준비
        List<String> mockYears = Arrays.asList("2024");

        // expenseMapper.getAllYears() 호출 시, 가상의 연도 목록을 반환하도록 설정
        when(salesMapper.getAllYears()).thenReturn(mockYears);

        // 테스트 실행
        List<String> resultYears = salesService.getAllYears();

        // 결과 검증
        assertFalse(resultYears.isEmpty(), "결과 목록이 비어 있지 않아야 합니다.");
        assertEquals(1, resultYears.size(), "결과 목록의 크기가 예상과 일치해야 합니다.");
        assertTrue(resultYears.containsAll(Arrays.asList("2024")), "예상된 연도가 모두 포함되어야 합니다.");

        // expenseMapper.getAllYears() 메소드 호출 검증
        verify(salesMapper, times(1)).getAllYears();
    }

    @Test
    public void testSettlement(){
        System.out.println(salesService.sumSettlementByYear(1, "2024"));
    }

    @Test
    public void testAllSettlement(){
        System.out.println(salesService.sumAllSettlementByYear("2024"));
    }
}
