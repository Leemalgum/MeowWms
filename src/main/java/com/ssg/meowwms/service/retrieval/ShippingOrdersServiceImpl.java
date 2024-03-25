package com.ssg.meowwms.service.retrieval;

import com.ssg.meowwms.domain.dispatch.DispatchVO;
import com.ssg.meowwms.domain.dispatch.VehicleVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersStatusVO;
import com.ssg.meowwms.domain.user.MemberVO;
import com.ssg.meowwms.domain.retrieval.RetrievalTimelineVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersDetailVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.dispatch.VehicleDTO;
import com.ssg.meowwms.dto.retrieval.*;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.user.MemberDTO;
import com.ssg.meowwms.mapper.dispatch.DispatchMapper;
import com.ssg.meowwms.mapper.dispatch.VehicleMapper;
import com.ssg.meowwms.mapper.retrieval.RetrievalTimelineMapper;
import com.ssg.meowwms.mapper.retrieval.ShippingOrdersDetailMapper;
import com.ssg.meowwms.mapper.retrieval.ShippingOrdersMapper;
import com.ssg.meowwms.mapper.retrieval.ShippingOrdersStatusMapper;
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
    private final ModelMapper modelMapper;

    @Override
    public void registerShippingOrder(ShippingOrdersDTO shippingOrdersDTO, ShippingOrdersDetailDTO shippingOrdersDetailDTO) {
        ShippingOrdersVO shippingOrdersVO = modelMapper.map(shippingOrdersDTO, ShippingOrdersVO.class);
        shippingOrdersMapper.insertShippingOrder(shippingOrdersVO);
        int shippingOrdersId = shippingOrdersDTO.getId();

        shippingOrdersDetailDTO.setShippingOrdersId(shippingOrdersId);
        insertShippingOrderDetail(shippingOrdersDetailDTO);
        insertRequestTime(shippingOrdersId);
    }

    private void insertShippingOrderDetail(ShippingOrdersDetailDTO shippingOrdersDetailDTO) {
        ShippingOrdersDetailVO shippingOrdersDetailVO = modelMapper.map(shippingOrdersDetailDTO, ShippingOrdersDetailVO.class);
        shippingOrdersDetailMapper.insertShippingOrderDetail(shippingOrdersDetailVO);
    }

    private void insertRequestTime(int id) {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(id)
                .requestTime(LocalDateTime.now())
                .build();
        retrievalTimelineMapper.insertRequestTime(retrievalTimelineVO);
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
        return list;
    }

    @Override
    public ShippingOrdersDTO getShippingOrder(int id) {
        ShippingOrdersVO shippingOrdersVO = shippingOrdersMapper.selectOneById(id);
        System.out.println(shippingOrdersVO);
        ShippingOrdersDTO shippingOrdersDTO = modelMapper.map(shippingOrdersVO, ShippingOrdersDTO.class);
        System.out.println(shippingOrdersDTO);

        return shippingOrdersDTO;
    }

    @Override
    public void modifyShippingOrder(ShippingOrdersDTO shippingOrdersDTO) {
        ShippingOrdersVO shippingOrdersVO = modelMapper.map(shippingOrdersDTO, ShippingOrdersVO.class);
        shippingOrdersMapper.updateShippingOrder(shippingOrdersVO);
    }

    @Override
    public void deleteShippingOrder(int id) {
        shippingOrdersMapper.deleteShippingOrder(id);
    }

    @Override
    public List<VehicleDTO> manageDispatch(List<OptionDTO> options) {
        options.add(new OptionDTO("status", 0));
        List<VehicleVO> list = vehicleMapper.selectByOptions(options);
        List<VehicleDTO> dtoList = list.stream().map(vo -> modelMapper.map(vo, VehicleDTO.class)).toList();
        return dtoList;
    }

    @Override
    public void confirmDispatch(ShippingOrdersStatusDTO shippingOrdersStatusDTO, VehicleDTO vehicleDTO) {
        ShippingOrdersStatusVO shippingOrdersStatusVO = modelMapper.map(shippingOrdersStatusDTO, ShippingOrdersStatusVO.class);
        shippingOrdersStatusMapper.updateAllocatedStatus(shippingOrdersStatusVO);

        DispatchVO dispatchVO = DispatchVO.builder()
                .vehicleNum(vehicleDTO.getNum())
                .warehouseId(2)
                .requestDate(LocalDateTime.now())
                .dispatchDate(LocalDateTime.now().plusHours(3))
                .build();
        dispatchMapper.insertDispatch(dispatchVO);
    }

    @Override
    public List<Integer> approveRetrieval(int id) {
        List<Integer> list = new ArrayList<>();
        int allocatedStatus = shippingOrdersStatusMapper.selectAllocatedStatusById(id);
        list.add(allocatedStatus);
        ShippingOrdersDetailVO shippingOrdersDetailVO = shippingOrdersDetailMapper.selectOneByShippingOrderId(id);
        int requestedQuantity = shippingOrdersDetailVO.getQuantity();
        list.add(requestedQuantity);
        return list;
    }

    @Override
    public void confirmRetrieval(ShippingOrdersStatusDTO shippingOrdersStatusDTO) {
        ShippingOrdersStatusVO shippingOrdersStatusVO = modelMapper.map(shippingOrdersStatusDTO, ShippingOrdersStatusVO.class);
        shippingOrdersStatusMapper.updateApprovedStatus(shippingOrdersStatusVO);

        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(shippingOrdersStatusDTO.getId())
                .orderTime(LocalDateTime.now())
                .build();
        retrievalTimelineMapper.updateOrderTime(retrievalTimelineVO);
    }

}
