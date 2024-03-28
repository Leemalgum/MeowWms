package com.ssg.meowwms.service.retrieval;

import com.ssg.meowwms.domain.dispatch.DispatchVO;
import com.ssg.meowwms.domain.dispatch.VehicleVO;
import com.ssg.meowwms.domain.retrieval.*;
import com.ssg.meowwms.domain.user.MemberVO;
import com.ssg.meowwms.dto.dispatch.DispatchDTO;
import com.ssg.meowwms.dto.dispatch.VehicleDTO;
import com.ssg.meowwms.dto.retrieval.*;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.user.MemberDTO;
import com.ssg.meowwms.mapper.dispatch.DispatchMapper;
import com.ssg.meowwms.mapper.dispatch.VehicleMapper;
import com.ssg.meowwms.mapper.retrieval.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 출고 지시서 서비스 : /retrieval/ 페이지에서 실행되는 서비스입니다.
 */
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ShippingOrdersServiceImpl implements ShippingOrdersService {
    private final ShippingOrdersMapper shippingOrdersMapper;
    private final ShippingOrdersDetailMapper shippingOrdersDetailMapper;
    private final ShippingOrdersStatusMapper shippingOrdersStatusMapper;
    private final RetrievalTimelineMapper retrievalTimelineMapper;
    private final VehicleMapper vehicleMapper;
    private final DispatchMapper dispatchMapper;
    private final WaybillMapper waybillMapper;
    private final ModelMapper modelMapper;

    @Override
    public int registerShippingOrder(ShippingOrdersDTO shippingOrdersDTO) {
        System.out.println(shippingOrdersDTO.getUid());
        System.out.println(shippingOrdersDTO.getPostcode());
        System.out.println(shippingOrdersDTO.getDetailAddress());
        System.out.println(shippingOrdersDTO.getExpectedDate());
        System.out.println(shippingOrdersDTO.getStreetNameAddress());
        System.out.println(shippingOrdersDTO.getStreetNumberAddress());

        ShippingOrdersVO shippingOrdersVO = modelMapper.map(shippingOrdersDTO, ShippingOrdersVO.class);

        shippingOrdersMapper.insertShippingOrder(shippingOrdersVO);
        // In case if DB Driver doesn't offer GeneratedKeys=true option.
        int shippingOrdersId = shippingOrdersMapper.selectLastShippingOrderId();
        System.out.println("shippingOrdersId: " + shippingOrdersId);
        return shippingOrdersId;
    }

    @Override
    public void registerShippingOrderDetail(ShippingOrdersDetailDTO shippingOrdersDetailDTO) {
        System.out.println("수량:" + shippingOrdersDetailDTO.getQuantity());
        System.out.println("제품 아이디 : " + shippingOrdersDetailDTO.getProductId());
        ShippingOrdersDetailVO shippingOrdersDetailVO = modelMapper.map(shippingOrdersDetailDTO, ShippingOrdersDetailVO.class);
        shippingOrdersDetailMapper.insertShippingOrderDetail(shippingOrdersDetailVO);
    }

    @Override
    public void registerRequestTimeline(int shippingOrdersId) {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .shippingOrdersId(shippingOrdersId)
                .requestTime(LocalDateTime.now())
                .build();
        retrievalTimelineMapper.insertRequestTime(retrievalTimelineVO);
    }

    @Override
    public void registerShippingOrderStatus(int shippingOrderId) {
        ShippingOrdersStatusVO shippingOrdersStatusVO = ShippingOrdersStatusVO.builder()
                .shippingOrdersId(shippingOrderId)
                .build();
        shippingOrdersStatusMapper.insertShippingOrderStatus(shippingOrdersStatusVO);
    }

    @Override
    public MemberDTO getMemberAddressByShopName(String uid) {
        MemberVO memberVO = shippingOrdersMapper.selectShopAddressByShopName(uid);
        System.out.println(memberVO);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        System.out.println(memberDTO);
        return memberDTO;
    }

    @Override
    public List<ShippingOrdersListDTO> getShippingOrdersByOptions(List<OptionDTO> options) {
        List<ShippingOrdersListDTO> list = shippingOrdersMapper.selectByOptions(options);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIndex(i + 1);
        }

        return list;
    }

    @Override
    public ShippingOrderDetailsDTO getShippingOrderDetails(int id) {
        ShippingOrderDetailsDTO shippingOrderDetailsDTO = shippingOrdersMapper.selectOneById(id);
        System.out.println(shippingOrderDetailsDTO);

        return shippingOrderDetailsDTO;
    }

    @Override
    public void modifyShippingOrder(ShippingOrderDetailsDTO shippingOrderDetailsDTO) {
        ShippingOrdersVO shippingOrdersVO = ShippingOrdersVO.builder()
                .id(shippingOrderDetailsDTO.getId())
                .uid(shippingOrderDetailsDTO.getUid())
                .expectedDate(shippingOrderDetailsDTO.getExpectedDate())
                .postcode(shippingOrderDetailsDTO.getPostcode())
                .streetNameAddress(shippingOrderDetailsDTO.getStreetNameAddress())
                .streetNumberAddress(shippingOrderDetailsDTO.getStreetNumberAddress())
                .detailAddress(shippingOrderDetailsDTO.getDetailAddress())
                .build();
        System.out.println("order" + shippingOrdersVO);
        shippingOrdersMapper.updateShippingOrder(shippingOrdersVO);
    }

    @Override
    public void modifyShippingOrdersDetail(ShippingOrderDetailsDTO shippingOrderDetailsDTO) {
        ShippingOrdersDetailVO shippingOrdersDetailVO = ShippingOrdersDetailVO.builder()
                .shippingOrdersId(shippingOrderDetailsDTO.getId())
                .productId(shippingOrderDetailsDTO.getProductId())
                .quantity(shippingOrderDetailsDTO.getQuantity())
                .build();
        System.out.println("detail" + shippingOrdersDetailVO);
        shippingOrdersDetailMapper.updateByShippingOrderId(shippingOrdersDetailVO);
    }

    @Override
    public void deleteShippingOrder(int id) {
        retrievalTimelineMapper.deleteByShippingOrdersId(id);
        shippingOrdersStatusMapper.deleteByShippingOrdersId(id);
        shippingOrdersDetailMapper.deleteShippingOrderDetailById(id);
        shippingOrdersMapper.deleteShippingOrder(id);
    }

    @Override
    public List<VehicleDTO> manageDispatch() {
        List<OptionDTO> options = new ArrayList<>();
        options.add(new OptionDTO("status", 0));
        List<VehicleVO> list = vehicleMapper.selectByOptions(options);
        List<VehicleDTO> dtoList = list.stream().map(vo -> modelMapper.map(vo, VehicleDTO.class)).toList();
        return dtoList;
    }

    @Override
    public void confirmDispatch(DispatchDTO dispatchDTO) {
        DispatchVO dispatchVO = modelMapper.map(dispatchDTO, DispatchVO.class);
        dispatchMapper.insertDispatch(dispatchVO);

        ShippingOrdersStatusVO shippingOrdersStatusVO = ShippingOrdersStatusVO.builder()
                .shippingOrdersId(dispatchDTO.getShippingOrdersId())
                .allocatedStatus(1)
                .build();
        shippingOrdersStatusMapper.updateAllocatedStatus(shippingOrdersStatusVO);
    }

    @Override
    public List<Integer> filterStatus(int id) {
        List<Integer> list = new ArrayList<>();
        int allocatedStatus = shippingOrdersStatusMapper.selectAllocatedStatusById(id);
        list.add(allocatedStatus); // list idx : 0
        int approvedStatus = shippingOrdersStatusMapper.selectApprovedStatusById(id);
        list.add(approvedStatus); // list idx : 1
        ShippingOrdersDetailVO shippingOrdersDetailVO = shippingOrdersDetailMapper.selectOneByShippingOrderId(id);
        int requestedQuantity = shippingOrdersDetailVO.getQuantity();
        list.add(requestedQuantity); // list idx : 2
        return list;
    }

    @Override
    public void confirmRetrieval(ShippingOrdersStatusDTO shippingOrdersStatusDTO) {
        shippingOrdersStatusDTO.setApprovedStatus(1);
        ShippingOrdersStatusVO shippingOrdersStatusVO = modelMapper.map(shippingOrdersStatusDTO, ShippingOrdersStatusVO.class);
        shippingOrdersStatusMapper.updateApprovedStatus(shippingOrdersStatusVO);

        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .shippingOrdersId(shippingOrdersStatusDTO.getShippingOrdersId())
                .orderTime(LocalDateTime.now())
                .build();
        retrievalTimelineMapper.updateOrderTime(retrievalTimelineVO);

        DispatchVO dispatchVO = dispatchMapper.selectOneByShippingOrdersId(shippingOrdersStatusDTO.getShippingOrdersId());
        DispatchDTO dispatchDTO = modelMapper.map(dispatchVO, DispatchDTO.class);
        dispatchDTO.setRequestDate(LocalDateTime.now());
        System.out.println(dispatchDTO);
        dispatchVO = modelMapper.map(dispatchDTO, DispatchVO.class);

        dispatchMapper.updateByShippingOrdersId(dispatchVO);
    }

    @Override
    public void registerWaybill(WaybillDTO waybillDTO) {
        System.out.println("waybillDTO" + waybillDTO);
        WaybillVO waybillVO = modelMapper.map(waybillDTO, WaybillVO.class);
        waybillMapper.insertWaybill(waybillVO);

        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .shippingOrdersId(waybillDTO.getShippingOrdersId())
                .workingTime(LocalDateTime.now().plusHours(1))
                .build();

        retrievalTimelineMapper.updateWorkingTime(retrievalTimelineVO);
    }

    @Override
    public WaybillDTO fillUpWaybill(WaybillDTO waybillDTO) {
        return waybillMapper.selectForFillUpWaybill(waybillDTO.getShippingOrdersId());
    }

    @Override
    public WaybillDTO getWaybill(int shippingOrdersId) {
        return waybillMapper.selectForFillUpWaybill(shippingOrdersId);
    }

}
