package com.ssg.meowwms.controller.retrieval;

import org.springframework.security.access.prepost.PreAuthorize;
import com.ssg.meowwms.dto.dispatch.DispatchDTO;
import com.ssg.meowwms.dto.dispatch.VehicleDTO;
import com.ssg.meowwms.dto.retrieval.*;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.service.retrieval.ShippingOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/retrieval")
@RequiredArgsConstructor
public class ShippingOrdersController {
    private final ShippingOrdersService shippingOrdersService;

    @GetMapping("/data")
    @ResponseBody
    public List<ShippingOrdersListDTO> getShippingOrdersListData(
            @RequestParam(required = false) List<String> searchType,
            @RequestParam(required = false) List<String> searchKeyword) {
        OptionList options = new OptionList();
        System.out.println(searchType);
        System.out.println(searchKeyword);

        if (searchType != null && searchKeyword != null) {
            for (int i = 0; i < searchType.size(); i++) {
                options.add(new OptionDTO(searchType.get(i), searchKeyword.get(i)));
            }
        }

        System.out.println("options: " + options);
        List<ShippingOrdersListDTO> list = shippingOrdersService.getShippingOrdersByOptions(options.getOptionList());
        System.out.println(list);
        return list;
    }

    @GetMapping("/main")
    public String getList() {
        return "views/retrieval/retrieval-main";
    }


    @Transactional
    @PostMapping("/registerShippingOrder")
    public String registerShippingOrder(@RequestBody Map<String, Object> requestData) {
        ShippingOrdersDTO shippingOrdersDTO = extractShippingOrdersDTO(requestData);
        ShippingOrdersDetailDTO shippingOrdersDetailDTO = extractShippingOrdersDetailDTO(requestData);

        int shippingOrdersID = shippingOrdersService.registerShippingOrder(shippingOrdersDTO);

        shippingOrdersDetailDTO.setShippingOrdersId(shippingOrdersID);

        shippingOrdersService.registerShippingOrderDetail(shippingOrdersDetailDTO);
        shippingOrdersService.registerRequestTimeline(shippingOrdersID);
        shippingOrdersService.registerShippingOrderStatus(shippingOrdersID);

        return "redirect:/retrieval/main";
    }

    private ShippingOrdersDTO extractShippingOrdersDTO(Map<String, Object> requestData) {
        Map<String, Object> shippingOrderData = (Map<String, Object>) requestData.get("shippingOrder");
        ShippingOrdersDTO shippingOrdersDTO = new ShippingOrdersDTO();

        String uid = "user2";
        UserDetails userDetails = SecurityUtils.getCurrentUserDetails();
        if (userDetails != null && userDetails.getUsername() != null) {
            uid = userDetails.getUsername();
        }
        shippingOrdersDTO.setUid(uid);
        shippingOrdersDTO.setExpectedDate(LocalDate.parse((String) shippingOrderData.get("expectedDate")));
        shippingOrdersDTO.setPostcode((String) shippingOrderData.get("postcode"));
        shippingOrdersDTO.setStreetNameAddress((String) shippingOrderData.get("streetNameAddress"));
        shippingOrdersDTO.setStreetNumberAddress((String) shippingOrderData.get("streetNumberAddress"));
        shippingOrdersDTO.setDetailAddress((String) shippingOrderData.get("detailAddress"));
        return shippingOrdersDTO;
    }

    private ShippingOrdersDetailDTO extractShippingOrdersDetailDTO(Map<String, Object> requestData) {
        Map<String, Object> shippingOrderDetailData = (Map<String, Object>) requestData.get("shippingOrderDetail");
        ShippingOrdersDetailDTO shippingOrdersDetailDTO = new ShippingOrdersDetailDTO();

        shippingOrdersDetailDTO.setProductId(Integer.parseInt((String) shippingOrderDetailData.get("productId")));
        shippingOrdersDetailDTO.setQuantity(Integer.parseInt((String) shippingOrderDetailData.get("quantity")));
        System.out.println("detail productId : " + shippingOrdersDetailDTO.getProductId());
        System.out.println("detail quantity : " + shippingOrdersDetailDTO.getQuantity());

        return shippingOrdersDetailDTO;
    }

    @GetMapping("/shippingorder/{shippingOrdersId}")
    public String readDetailPage(@PathVariable Integer shippingOrdersId, Model model) {
        model.addAttribute("shippingOrderDetails", shippingOrdersService.getShippingOrderDetails(shippingOrdersId));
        return "views/retrieval/read-shippingorderdetail";
    }

    @GetMapping("/modifyshippingorder/{shippingOrdersId}")
    public String readModifyPage(@PathVariable(required = false) Integer shippingOrdersId, Model model) {
        if (shippingOrdersId != null) {
            model.addAttribute("shippingOrderDetails", shippingOrdersService.getShippingOrderDetails(shippingOrdersId));
        } else {
            model.addAttribute("shippingOrderDetails", new ShippingOrderDetailsDTO());
        }

        return "/views/retrieval/modify-shippingorderdetail";
    }

    @PostMapping("/modifyshippingorder")
    public String modifySubmitForm(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("shippingOrderDetails");
        ShippingOrderDetailsDTO shippingOrderDetailsDTO = new ShippingOrderDetailsDTO();

        shippingOrderDetailsDTO.setId(Integer.parseInt((String) data.get("id")));
        shippingOrderDetailsDTO.setUid((String) data.get("uid"));
        shippingOrderDetailsDTO.setExpectedDate(LocalDate.parse((String) data.get("expectedDate")));
        shippingOrderDetailsDTO.setPostcode((String) data.get("postcode"));
        shippingOrderDetailsDTO.setStreetNameAddress((String) data.get("streetNameAddress"));
        shippingOrderDetailsDTO.setStreetNumberAddress((String) data.get("streetNumberAddress"));
        shippingOrderDetailsDTO.setDetailAddress((String) data.get("detailAddress"));
        shippingOrderDetailsDTO.setProductId(Integer.parseInt((String) data.get("productId")));
        shippingOrderDetailsDTO.setQuantity(Integer.parseInt((String) data.get("quantity")));

        System.out.println("modify post : " + shippingOrderDetailsDTO);
        shippingOrdersService.modifyShippingOrder(shippingOrderDetailsDTO);
        shippingOrdersService.modifyShippingOrdersDetail(shippingOrderDetailsDTO);

        return "redirect:/retrieval/main";
    }

    @PostMapping("/removeshippingorder")
    public String removeSubmitForm(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("shippingOrderDetails");

        int shippingOrdersId = Integer.parseInt((String) data.get("id"));

        System.out.println("post" + shippingOrdersId);
        shippingOrdersService.deleteShippingOrder(shippingOrdersId);

        return "redirect:/retrieval/main";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerwaybill/{shippingOrdersId}")
    public String registerWaybill(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("waybill");

        WaybillDTO waybillDTO = new WaybillDTO();

        waybillDTO.setShippingOrdersId(Integer.parseInt((String) data.get("shippingOrdersId")));
        String uid = "user2";
        UserDetails userDetails = SecurityUtils.getCurrentUserDetails();
        if (userDetails != null && userDetails.getUsername() != null) {
            uid = userDetails.getUsername();
        }

      /*  waybillDTO.setId(Integer.parseInt((String) data.get("id")));
        waybillDTO.setProductId(Integer.parseInt((String) data.get("productId")));
        waybillDTO.setVehicleNum((String) data.get("vehicleNum"));
        waybillDTO.setWarehouseId(Integer.parseInt((String) data.get("warehouseId")));
        waybillDTO.setShippingOrdersDetailId(Integer.parseInt((String) data.get("shippingOrdersDetailId")));*/

        shippingOrdersService.registerWaybill(waybillDTO);
        waybillDTO = fillUpWaybill(waybillDTO);
        System.out.println("waybill post : " + waybillDTO);

        return "redirect:/retrieval/main";
    }
    private WaybillDTO fillUpWaybill(WaybillDTO waybillDTO) {
        waybillDTO = shippingOrdersService.fillUpWaybill(waybillDTO);
        return waybillDTO;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @GetMapping("/managedispatch")
    public List<VehicleDTO> manageDispatch() {
        return shippingOrdersService.manageDispatch();
    }

    @PostMapping("/confirmdispatch")
    public String confirmDispatch(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("dispatch");
        DispatchDTO dispatchDTO = new DispatchDTO();
        dispatchDTO.setShippingOrdersId(Integer.parseInt((String) data.get("shippingOrdersId")));
        dispatchDTO.setVehicleNum((String) data.get("vehicleNum"));

        System.out.println("dispatch post : " + dispatchDTO);
        shippingOrdersService.confirmDispatch(dispatchDTO);
        return "redirect:/retrieval/main";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approveretrieval/{shippingOrdersId}")
    public ResponseEntity<Map<String, Integer>> approveRetrieval(@PathVariable Integer shippingOrdersId) {
        int allocatedStatus = shippingOrdersService.filterStatus(shippingOrdersId).get(0);
        Map<String, Integer> response = new HashMap<>();
        response.put("allocatedStatus", allocatedStatus);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmretrieval")
    public String confirmRetrieval(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("retrieval");

        ShippingOrdersStatusDTO shippingOrdersStatusDTO = new ShippingOrdersStatusDTO();
        shippingOrdersStatusDTO.setShippingOrdersId(Integer.parseInt((String) data.get("shippingOrdersId")));
        shippingOrdersStatusDTO.setApprovedStatus((Integer) data.get("approvedStatus"));

        System.out.println("approve retrieval post : " + shippingOrdersStatusDTO);
        shippingOrdersService.confirmRetrieval(shippingOrdersStatusDTO);
        return "redirect:/retrieval/main";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registerwaybill/{shippingOrdersId}")
    public ResponseEntity<Map<String, Integer>> approveWaybill(@PathVariable Integer shippingOrdersId) {
        int approvedStatus = shippingOrdersService.filterStatus(shippingOrdersId).get(1);
        System.out.println("approvedStatus" + approvedStatus);
        Map<String, Integer> response = new HashMap<>();
        response.put("approvedStatus", approvedStatus);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmwaybill")
    public String confirmWaybill(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("waybill");

        WaybillDTO waybillDTO = new WaybillDTO();
        waybillDTO.setShippingOrdersId(Integer.parseInt((String) data.get("shippingOrdersId")));

        String uid = "user2";
        UserDetails userDetails = SecurityUtils.getCurrentUserDetails();
        if (userDetails != null && userDetails.getUsername() != null) {
            uid = userDetails.getUsername();
        }
        waybillDTO.setUid(uid);

        System.out.println("waybill retrieval post : " + waybillDTO);
        shippingOrdersService.registerWaybill(waybillDTO);
        waybillDTO = shippingOrdersService.fillUpWaybill(waybillDTO);

        return "redirect:/retrieval/main";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/printwaybill/{shippingOrdersId}")
    public ResponseEntity<Map<String, Integer>> printWaybill(@PathVariable Integer shippingOrdersId) {
        //int approvedStatus = shippingOrdersService.filterStatus(shippingOrdersId).get(1);
        //System.out.println("approvedStatus" + approvedStatus);
        Map<String, Integer> response = new HashMap<>();
        //response.put("approvedStatus", approvedStatus);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirmprintwaybill")
    public ResponseEntity<?> confirmPrintWaybill(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> data = (Map<String, Object>) requestData.get("waybillPrint");

        WaybillDTO waybillDTO = new WaybillDTO();
        waybillDTO.setShippingOrdersId(Integer.parseInt((String) data.get("shippingOrdersId")));

        waybillDTO = shippingOrdersService.fillUpWaybill(waybillDTO);

        return ResponseEntity.ok(waybillDTO);
    }
}
