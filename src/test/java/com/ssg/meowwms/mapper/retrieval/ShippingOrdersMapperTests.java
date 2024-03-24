package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.user.MemberVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersListDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Log4j2
@SpringBootTest
public class ShippingOrdersMapperTests {
    @Autowired
    private ShippingOrdersMapper shippingOrdersMapper;
    private OptionList options;

    @BeforeEach
    void setUp() {
        options = new OptionList();
        options.add(new OptionDTO("id","1"));
        options.add(new OptionDTO("uid", "user2"));
    }

    @Test
    void insertShippingOrderTest() {
        ShippingOrdersVO shippingOrdersVO = ShippingOrdersVO.builder()
                .uid("user5")
                .postcode("02395")
                .streetNameAddress("서울시 강동구 신현로")
                .detailAddress("송일빌딩 B105호")
                .expectedDate(LocalDate.of(2024, 04, 05))
                .build();
        shippingOrdersMapper.insertShippingOrder(shippingOrdersVO);
    }

    @Test
    void selectOneByIdTest() {
        ShippingOrdersVO shippingOrdersVO = shippingOrdersMapper.selectOneById(5);
        assertThat(shippingOrdersVO.getUid()).isEqualTo("user3");
    }

    @Test
    void updateShippingOrderTest() {
        ShippingOrdersMapper shippingOrdersMapperMock = mock(ShippingOrdersMapper.class);
        doNothing().when(shippingOrdersMapperMock).updateShippingOrder(any(ShippingOrdersVO.class));

        ShippingOrdersVO shippingOrdersVO = ShippingOrdersVO.builder()
                .id(1)
                .postcode("07285")
                .streetNameAddress("서울시 성북고 중앙로")
                .detailAddress("한성빌딩 B108호")
                .expectedDate(LocalDate.of(2024,4,12))
                .build();

        shippingOrdersMapperMock.updateShippingOrder(shippingOrdersVO);

        verify(shippingOrdersMapperMock, times(1)).updateShippingOrder(any(ShippingOrdersVO.class));
    }

    @Test
    void deleteShippingOrderTest() {
        ShippingOrdersMapper shippingOrdersMapperMock = mock(ShippingOrdersMapper.class);

        doNothing().when(shippingOrdersMapperMock).deleteShippingOrder(2);

        shippingOrdersMapperMock.deleteShippingOrder(2);

        verify(shippingOrdersMapperMock, times(1)).deleteShippingOrder(2);
    }

    @Test
    void selectByOptionsTest() {
        List<ShippingOrdersListDTO> results = shippingOrdersMapper.selectByOptions(options.getOptionList());
        System.out.println("results : " + results);
        assertNotNull(results);
        assertTrue(results.size() > 0);

        for (ShippingOrdersListDTO shippingOrdersListDTO : results) {
            assertThat(shippingOrdersListDTO.getUid()).isEqualTo("user2");
        }
    }

    @Test
    void selectShopAddressByShopNameTest() {
        MemberVO memberVO = shippingOrdersMapper.selectShopAddressByShopName("user2");
        assertThat(memberVO.getUid()).isEqualTo("user2");
        assertThat(memberVO.getShopName()).isEqualTo("가게2");
        assertThat(memberVO.getShopAddress()).isEqualTo("서울시 강북구 번동 456번지");
    }

}
