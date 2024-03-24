package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.user.MemberVO;
import com.ssg.meowwms.domain.dispatch.DispatchVO;
import com.ssg.meowwms.domain.retrieval.RetrievalTimelineVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersStatusVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.retrieval.ShippingOrdersListDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShippingOrdersMapper {
    /**
     * 출고 지시서 등록 : 새로운 출고 지시서를 요청하며 등록합니다.
     * */
    void insertShippingOrder(ShippingOrdersVO shippingOrdersVO);

    /**
     * 출고 지시서 조회 : 출고지시서 아이디로 단건을 조회합니다.
     * */
    ShippingOrdersVO selectOneById(int id);

    /**
     * 출고 지시서 업데이트 : 운송장을 수정해줍니다.
     * */
    void updateShippingOrder(ShippingOrdersVO shippingOrdersVO);

    /**
     * 출고 지시서 삭제 : 운송장을 삭제합니다
     * */
    void deleteShippingOrder(int id);

    /**
     * 출고 지시서 리스트 출력 + 검색 옵션에 따라 출력 : 조건을 주지 않을 때는 전체 리스트가 출력되고 조건을 주면 그에 맞는 리스트가 출력됩니다.
     * */
    List<ShippingOrdersListDTO> selectByOptions(@Param("options") List<OptionDTO> options);

    /**
     *  매장 이름으로 매장 주소 검색 : 아이디에 해당하는 매장 이름을 선택하면 매장 주소를 배송 주소 칸에 입력해줍니다.
     * */
    MemberVO selectShopAddressByShopName(String uid);

    /**
     * 배차 가능한 차량 리스트 가져오기 : 배차가 가능한 차량 리스트를 가져옵니다.
     * */
    List<DispatchVO> selectAvailableDispatch();

    /**
     * 배차 상태 업데이트 : 배차 지정을 완료했을 시 출고지시서의 상태를 배차 완료로 바꿔줍니다.
     * */
    void updateAllocatedStatus(ShippingOrdersStatusVO shippingOrdersStatusVO);

    /**
     * 출고지시서의 배차 상태 가져오기 : service의 approveRetrieval 메서드를 위한 mapper method로,
     * 출고 승인 버튼을 눌렀을 때 배차가 완료된 출고 지시서인지 확인해 주는 필터링 역할을 합니다.
     * */
    void selectAllocatedStatusById(int id);

    /**
     * 출고 승인 업데이트 : 출고 승인을 확정했을 시 출고 지시서의 상태를 승인 완료로 바꿔줍니다.
     * */
    void updateApprovedStatus(ShippingOrdersStatusVO shippingOrdersStatusVO);

    /**
     * 출고 지시서 타임라인 만들기 : 출고지시서의 첫 번째 타임라인인 출고 요청 시간과 함께 출고 타임라인을 생성합니다.
     * */
    void updateOrderTime(RetrievalTimelineVO retrievalTimelineVO);


}
