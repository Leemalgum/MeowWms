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

}
