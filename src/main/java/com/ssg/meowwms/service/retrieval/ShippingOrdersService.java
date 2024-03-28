package com.ssg.meowwms.service.retrieval;

import com.ssg.meowwms.dto.dispatch.DispatchDTO;
import com.ssg.meowwms.dto.dispatch.VehicleDTO;
import com.ssg.meowwms.dto.retrieval.*;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.user.MemberDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 출고 지시서 서비스 : /retrieval/ 페이지에서 실행되는 서비스입니다.
 */

public interface ShippingOrdersService {
    /**
     * 출고 요청 : "신청" 버튼을 누르면 실행되는 서비스입니다.
     */
    int registerShippingOrder(ShippingOrdersDTO shippingOrdersDTO);
    void registerShippingOrderDetail(ShippingOrdersDetailDTO shippingOrdersDetailDTO);
    void registerRequestTimeline(int shippingOrderId);
    void registerShippingOrderStatus(int shippingOrderId);

    /**
     * 매장 정보 : 고객 아이디에 등록된 매장 이름을 선택하면 매장 주소가 나옵니다.
     */
    MemberDTO getMemberAddressByShopName(String uid);

    /**
     * option == null 일 때 ) 출고 지시서 리스트 : 전체 출고 지시서 리스트를 보여줍니다.
     * option != null 일 때 )출고리스트 검색 : 출고 희망일, 배차 여부, 승인 여부, 출고번호, 작성자, 제품 아이디, 제품 이름 의 검색조건을 바탕으로 리스트를 검색합니다.
     */
    List<ShippingOrdersListDTO> getShippingOrdersByOptions(@Param("options") List<OptionDTO> options);

    /**
     * 출고 지시서 단건 조회 : 출고번호를 누르면 뜨는 창으로 해당 건의 상세 정보 (페이지)를 보여줍니다.
     */
    ShippingOrderDetailsDTO getShippingOrderDetails(int id);

    /**
     * 출고 지시서 수정 : 출고 지시서 상세 페이지에서 출고 지시서 내용을 수정할 수 있는 메서드입니다.
     */
    void modifyShippingOrder(ShippingOrderDetailsDTO shippingOrderDetailsDTO);

    /**
     * 출고 지시서 수정 : 출고 지시서 상세 페이지에서 출고 지시서 내용을 수정할 수 있는 메서드입니다.
     */
    void modifyShippingOrdersDetail(ShippingOrderDetailsDTO shippingOrderDetailsDTO);

    /**
     * 출고 지시서 삭제 : 출고 지시서 상세 페이지에서 지시를 삭제 할 수 있는 메서드입니다.
     */
    void deleteShippingOrder(int id);

    /**
     * 배차 관리 : 출고 번호를 누르면 모달창에서 현재 배차 가능한 차량들의 리스트를 보여줍니다.
     */
    List<VehicleDTO> manageDispatch();

    /**
     * 배차 확정 : 배차 관리 창에서 확인 버튼을 누르면 배차가 확정됩니다.
     */
    void confirmDispatch(DispatchDTO dispatchDTO);

    /**
     * 출고 승인 (전 확인): 페이지에서 출고 승인 버튼을 누르면 모달 창을 띄우는 서비스입니다.
     * 배차 관리가 확정됐는지 필터링 하며 승인 최종 확정의 전 단계입니다.
     */
    List<Integer> filterStatus(int id);

    /**
     * 승인 확정 : 출고 승인을 확정합니다.
     */
    void confirmRetrieval(ShippingOrdersStatusDTO shippingOrdersStatusDTO);

    /**
     * 운송장 등록 : 운송장을 등록합니다.
     * */
    void registerWaybill(WaybillDTO waybillDTO);

    /**
     * 운송장 채우기 : shippingOrdersId 로 join 한 다른 테이블들의 값들을 이용해 waybill 을 채웁니다.
     * */
    WaybillDTO fillUpWaybill(WaybillDTO waybillDTO);

    WaybillDTO getWaybill(int shippingOrdersId);
}
