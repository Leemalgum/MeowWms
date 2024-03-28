package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersStatusVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingOrdersStatusMapper {
    /**
     * 새로운 출고 지시서 상태 만들기 : 새로 요청된 출고 지시서의 배차 상태, 승인 상태를 포함하는 새로운 정보를 만듭니다.
     * */
    void insertShippingOrderStatus(ShippingOrdersStatusVO shippingOrdersStatusVO);
    /**
     * 출고지시서의 배차 상태 가져오기 : service의 filterStatus 메서드를 위한 mapper method로,
     * 출고 승인 버튼을 눌렀을 때 배차가 완료된 출고 지시서인지 확인해 주는 필터링 역할을 합니다.
     * */
    void updateAllocatedStatus(ShippingOrdersStatusVO shippingOrdersStatusVO);

    /**
     * 출고지시서의 배차 상태 가져오기 : service의 filterStatus 메서드를 위한 mapper method로,
     * 출고 승인 버튼을 눌렀을 때 배차가 완료된 출고 지시서인지 확인해 주는 필터링 역할을 합니다.
     * */
    int selectAllocatedStatusById(int shippingOrdersId);

    /**
     * 출고지시서의 승인 상태 가져오기 : service의 filterStatus 메서드를 위한 mapper method로,
     * 출고 승인 버튼을 눌렀을 때 배차가 완료된 출고 지시서인지 확인해 주는 필터링 역할을 합니다.
     * */
    int selectApprovedStatusById(int shippingOrdersId);

    /**
     * 출고 승인 업데이트 : 출고 승인을 확정했을 시 출고 지시서의 상태를 승인 완료로 바꿔줍니다.
     * */
    void updateApprovedStatus(ShippingOrdersStatusVO shippingOrdersStatusVO);

    void deleteByShippingOrdersId(int shippingOrdersId);
}
