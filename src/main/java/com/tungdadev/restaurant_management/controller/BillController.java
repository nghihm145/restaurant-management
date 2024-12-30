package com.tungdadev.restaurant_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tungdadev.restaurant_management.exception.ApplicationRuntimeException;
import com.tungdadev.restaurant_management.exception.ObjectNotFoundException;
import com.tungdadev.restaurant_management.model.Bill;
import com.tungdadev.restaurant_management.model.BillDetail;
import com.tungdadev.restaurant_management.model.BillDetailKey;
import com.tungdadev.restaurant_management.model.MenuItem;
import com.tungdadev.restaurant_management.model.dto.*;
import com.tungdadev.restaurant_management.service.impl.BillDetailService;
import com.tungdadev.restaurant_management.service.impl.BillService;
import com.tungdadev.restaurant_management.service.impl.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:10
 */
@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Bill Management", description = "Bill Management")
public class BillController {
    
    
    private final BillService billService;
    
    private BillDetailService billDetailService;
    
    private MenuItemService menuItemService;
    
    private ModelMapper modelMapper;

    @Operation(summary = "Retrieve Bill")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BillReportDTO retrieveBill(
            @Parameter(description = "Bill id from which bill object will retrieve", required = true) @PathVariable("id") long id)
            throws JsonProcessingException {
        log.info("Update Bill with id {}", id);
        Bill currentBill = billService.findById(id);
        if (currentBill == null) {
            log.error("Unable to update build. Bill with id {} does not exist", id);
            throw new ObjectNotFoundException(String.valueOf(id));
        }

        BillReportDTO report = new BillReportDTO();
        report.setBillId(currentBill.getId());

        Double totalCost = 0.0;
        Set<BillDetail> billDetails = currentBill.getBillDetails();
        List<BillItemDTO> billItems = new ArrayList<BillItemDTO>();
        List<MenuItem> menus = menuItemService.findMenuByInIds(currentBill.getMenuIds());

        for (BillDetail billDetail : billDetails) {
            totalCost += billDetail.subTotal();
            MenuItem orderedMenu = menus.stream()
                    .filter(menu -> menu.getId().equals(billDetail.getId().getMenuItemId())).findFirst().get();
            BillItemDTO billItem = new BillItemDTO(orderedMenu.getName(), billDetail.getQuantities(),
                    billDetail.getOrderedTime().toGMTString());
            billItems.add(billItem);
        }
        report.setTotal(totalCost);
        report.setBillItems(billItems);

        return report;
    }

    @Operation(summary = "Create A Bill with Menu Item(s)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BillDTO createBill(
            @Parameter(description = "List of menus and their quantity", required = true) @RequestBody OrderDTO order) {

        Bill bill = new Bill();
        billService.saveBill(bill);

        Set<BillDetail> details = new HashSet<>();
        for (OrderedItemDTO item : order.getOrder()) {
            log.info("Create bill for {}, and quantity is {}", item.getName(), item.getQuantity());
            BillDetail billDetail = new BillDetail();
            MenuItem menuItem = menuItemService.findByName(item.getName());
            if (menuItem == null) {
                log.error("Unable to create bill. Item name {} does not exist", item.getName());
                throw new ObjectNotFoundException(item.getName());
            }
            if (item.getQuantity() == 0) {
                log.error("Unable to create bill. Quantity must greater than 0");
                throw new ApplicationRuntimeException("Quantity must greater than 0");
            }
            billDetail.setId(new BillDetailKey(bill.getId(), menuItem.getId()));
            billDetail.setQuantities(item.getQuantity());
            billDetail.setOrderedTime(new Date());
            billDetailService.saveBillDetail(billDetail);
            details.add(billDetail);
        }
        bill.setBillDetails(details);

        return convertToDTO(bill);
    }

    @Operation(summary = "Update Existing Bill")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BillDTO updateBill(
            @Parameter(description = "Bill id from which bill object will retrieve", required = true) @PathVariable("id") long id,
            @Parameter(description = "List of menus and their quantity", required = true) @RequestBody OrderDTO order) {

        log.info("Update Bill with id {}", id);
        Bill currentBill = billService.findById(id);
        if (currentBill == null) {
            log.error("Unable to update build. Bill with id {} does not exist", id);
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        Set<BillDetail> details = new HashSet<BillDetail>();
        List<MenuItem> menus = menuItemService.findMenuByInIds(currentBill.getMenuIds());

        for (OrderedItemDTO item : order.getOrder()) {
            log.info("Create Build for {}, and quantity is {}", item.getName(), item.getQuantity());
            BillDetail billDetail = new BillDetail();
            MenuItem orderedMenu = menus.stream().filter(menu -> menu.getName().equals(item.getName())).findFirst()
                    .get();
            billDetail.setId(new BillDetailKey(currentBill.getId(), orderedMenu.getId()));
            billDetail.setQuantities(item.getQuantity());
            billDetail.setOrderedTime(new Date());
            billDetailService.saveBillDetail(billDetail);
            details.add(billDetail);
        }
        currentBill.setBillDetails(details);

        return convertToDTO(currentBill);
    }

    private BillDTO convertToDTO(Bill bill) {

        Double totalCost = 0.0;
        BillDTO billDTO = modelMapper.map(bill, BillDTO.class);
        List<MenuItem> menus = menuItemService.findMenuByInIds(bill.getMenuIds());
        Set<BillDetailDTO> billDetailDTOs = new HashSet<>();
        for (BillDetail billDetail : bill.getBillDetails()) {
            BillDetailDTO billDetailDTO = modelMapper.map(billDetail, BillDetailDTO.class);
            MenuItem orderedMenu = menus.stream()
                    .filter(menu -> menu.getId().equals(billDetail.getId().getMenuItemId())).findFirst().get();
            Double subTotal = orderedMenu.getPrice() * billDetail.getQuantities();
            totalCost += subTotal;
            billDetailDTO.setBillId(bill.getId());
            billDetailDTO.setMenuItem(orderedMenu.getName());
            billDetailDTO.setQuantity(billDetail.getQuantities());
            billDetailDTO.setSubTotal(subTotal);
            billDetailDTOs.add(billDetailDTO);
        }
        billDTO.setBillDetails(billDetailDTOs);
        billDTO.setTotal(totalCost);
        return billDTO;
    }
}
