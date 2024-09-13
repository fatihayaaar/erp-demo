package com.fayardev.erpdemo.controller;

import com.fayardev.erpdemo.dto.PurchaseOrderDTO;
import com.fayardev.erpdemo.service.PurchaseOrderService;
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
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<?> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        PurchaseOrderDTO createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
        return new ResponseEntity<>(createdPurchaseOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderById(@PathVariable Long id) {
        Optional<PurchaseOrderDTO> purchaseOrderDTO = purchaseOrderService.getPurchaseOrderById(id);
        return purchaseOrderDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
        List<PurchaseOrderDTO> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable Long id, @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        if (purchaseOrderService.getPurchaseOrderById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        purchaseOrderDTO.setId(id);
        PurchaseOrderDTO updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO);
        return new ResponseEntity<>(updatedPurchaseOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        if (purchaseOrderService.getPurchaseOrderById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        purchaseOrderService.deletePurchaseOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}