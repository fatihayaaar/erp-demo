package com.fayardev.erpdemo.controller;

import com.fayardev.erpdemo.dto.SalesOrderDTO;
import com.fayardev.erpdemo.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.fayardev.erpdemo.util.ValidationUtils.getValidationErrors;

@RestController
@RequestMapping("/api/sales-orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    @PostMapping
    public ResponseEntity<?> createSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrderDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        SalesOrderDTO createdSalesOrder = salesOrderService.createSalesOrder(salesOrderDTO);
        return new ResponseEntity<>(createdSalesOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesOrderDTO> getSalesOrderById(@PathVariable Long id) {
        Optional<SalesOrderDTO> salesOrderDTO = salesOrderService.getSalesOrderById(id);
        return salesOrderDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<SalesOrderDTO>> getAllSalesOrders() {
        List<SalesOrderDTO> salesOrders = salesOrderService.getAllSalesOrders();
        return new ResponseEntity<>(salesOrders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSalesOrder(@PathVariable Long id, @Valid @RequestBody SalesOrderDTO salesOrderDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        if (salesOrderService.getSalesOrderById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        salesOrderDTO.setId(id);
        SalesOrderDTO updatedSalesOrder = salesOrderService.updateSalesOrder(salesOrderDTO);
        return new ResponseEntity<>(updatedSalesOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesOrder(@PathVariable Long id) {
        if (salesOrderService.getSalesOrderById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        salesOrderService.deleteSalesOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}