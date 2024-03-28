package com.ssg.meowwms.mapper.retrieval;


import com.ssg.meowwms.domain.retrieval.ShippingOrdersDetailVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Log4j2
@SpringBootTest
public class ShippingOrdersDetailMapperTests {
    @Autowired
    private ShippingOrdersDetailMapper shippingOrdersDetailMapper;

    @Test
    void insertShippingOrderDetailTest(){
        ShippingOrdersDetailVO shippingOrdersDetailVO = ShippingOrdersDetailVO.builder()
                .id(2)
                .productId(3)
                .quantity(150)
                .build();
    }
    @Test
    void selectOneByShippingOrderIdTest(){
        ShippingOrdersDetailVO shippingOrdersDetailVO = shippingOrdersDetailMapper.selectOneByShippingOrderId(1);
        assertThat(shippingOrdersDetailVO.getProductId()).isEqualTo(1);
    }

    @Test
    void updateShippingOrderDetailTest() {
        ShippingOrdersDetailMapper shippingOrdersDetailMapperMock = mock(ShippingOrdersDetailMapper.class);
        doNothing().when(shippingOrdersDetailMapperMock).updateShippingOrderDetailById(any(ShippingOrdersDetailVO.class));

        ShippingOrdersDetailVO shippingOrdersDetailVO = ShippingOrdersDetailVO.builder()
                .id(2)
                .productId(2)
                .quantity(100)
                .build();

        shippingOrdersDetailMapperMock.updateShippingOrderDetailById(shippingOrdersDetailVO);

        verify(shippingOrdersDetailMapperMock, times(1)).updateShippingOrderDetailById(any(ShippingOrdersDetailVO.class));
    }

    @Test
    void deleteShippingOrderDetailByIdTest() {
        ShippingOrdersDetailMapper shippingOrdersDetailMapperMock = mock(ShippingOrdersDetailMapper.class);

        doNothing().when(shippingOrdersDetailMapperMock).deleteShippingOrderDetailById(2);

        shippingOrdersDetailMapperMock.deleteShippingOrderDetailById(2);

        verify(shippingOrdersDetailMapperMock, times(1)).deleteShippingOrderDetailById(2);
    }
}
