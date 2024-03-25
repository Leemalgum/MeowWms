package com.ssg.meowwms.service.retrieval;

import com.ssg.meowwms.dto.dispatch.VehicleDTO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersDTO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersDetailDTO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersListDTO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersStatusDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.user.MemberDTO;
import com.ssg.meowwms.mapper.retrieval.ShippingOrdersMapper;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ShippingOrdersServiceTests {
    @Autowired
    ShippingOrdersService shippingOrdersService;
    OptionDTO optionDTO;
    @Autowired
    private ShippingOrdersMapper shippingOrdersMapper;

    @Test
    void registerShippingOrderTest() {
        ShippingOrdersService shippingOrdersServiceMock = mock(ShippingOrdersService.class);

        doNothing().when(shippingOrdersServiceMock).registerShippingOrder(any(ShippingOrdersDTO.class), any(ShippingOrdersDetailDTO.class));

        int shippingOrdersId = 4;
        ShippingOrdersDTO shippingOrdersDTO = ShippingOrdersDTO.builder()
                .id(shippingOrdersId)
                .uid("user4")
                .postcode("03584")
                .streetNumberAddress("서울특별시 강남구 역삼동 123-456")
                .detailAddress("세한빌딩 B105호")
                .expectedDate(LocalDate.now().plusDays(7))
                .build();

        ShippingOrdersDetailDTO shippingOrdersDetailDTO = ShippingOrdersDetailDTO.builder()
                .shippingOrdersId(shippingOrdersId)
                .productId(2)
                .quantity(30)
                .build();

        shippingOrdersServiceMock.registerShippingOrder(shippingOrdersDTO, shippingOrdersDetailDTO);

        verify(shippingOrdersServiceMock, times(1)).registerShippingOrder(any(ShippingOrdersDTO.class), any(ShippingOrdersDetailDTO.class));
    }


    @Test
    void getMemberAddressByShopNameTest() {
        MemberDTO memberDTO = shippingOrdersService.getMemberAddressByShopName("user2");
        assertThat(memberDTO.getShopName()).isEqualTo("가게2");
    }

    @Test
    void getShippingOrdersByOptionsTest() {
        List<OptionDTO> options = new ArrayList<>();
        List<ShippingOrdersListDTO> list = shippingOrdersService.getShippingOrdersByOptions(options);
    }

    @Test
    void getShippingOrderTest() {
        ShippingOrdersDTO shippingOrdersDTO = shippingOrdersService.getShippingOrder(1);
        assertThat(shippingOrdersDTO.getUid()).isEqualTo("user2");
    }

    @Test
    void modifyShippingOrderTest() {
        ShippingOrdersDTO shippingOrdersDTO = ShippingOrdersDTO.builder()
                .id(3)
                .postcode("03584")
                .streetNameAddress("서울시 강남구 역삼로 502")
                .detailAddress("사랑빌딩 101호")
                .expectedDate(LocalDate.now().plusDays(7))
                .build();

        shippingOrdersService.modifyShippingOrder(shippingOrdersDTO);
    }

    @Test
    void deleteShippingOrderTest() {
        int id = 8;
        shippingOrdersService.deleteShippingOrder(id);
        AssertionsForClassTypes.assertThat(shippingOrdersMapper.selectOneById(id)).isNull();
    }

    @Test
    void manageDispatchTest() {
        List<OptionDTO> options = new ArrayList<>();
        List<VehicleDTO> list = shippingOrdersService.manageDispatch(options);
        assertThat(list).isNotNull();
    }

    @Test
    void confirmDispatchTest() {
        ShippingOrdersStatusDTO shippingOrdersStatusDTO = ShippingOrdersStatusDTO.builder()
                .id(1)
                .allocatedStatus(1)
                .approvedStatus(1)
                .build();

        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .num("123가4567")
                .type("경형")
                .dname("홍길동")
                .dphone("01012345678")
                .status(0)
                .build();

        shippingOrdersService.confirmDispatch(shippingOrdersStatusDTO, vehicleDTO);
    }

    @Test
    void approveRetrievalTest() {
        List<Integer> list = shippingOrdersService.approveRetrieval(1);
        assertThat(list).isNotNull();
        System.out.println(list);
    }

    @Test
    void confirmRetrievalTest() {
        ShippingOrdersService shippingOrdersServiceMock = mock(ShippingOrdersService.class);

        doNothing().when(shippingOrdersServiceMock).confirmRetrieval(any(ShippingOrdersStatusDTO.class));

        int id = 1;
        ShippingOrdersStatusDTO shippingOrdersStatusDTO = ShippingOrdersStatusDTO.builder()
                .id(id)
                .approvedStatus(1)
                .build();

        shippingOrdersServiceMock.confirmRetrieval(shippingOrdersStatusDTO);

        verify(shippingOrdersServiceMock, times(1)).confirmRetrieval(any(ShippingOrdersStatusDTO.class));
    }
}
