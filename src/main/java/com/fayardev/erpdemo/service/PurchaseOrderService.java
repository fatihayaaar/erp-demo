package com.fayardev.erpdemo.service;

import com.fayardev.erpdemo.dto.PurchaseOrderDTO;
import com.fayardev.erpdemo.entity.PurchaseOrder;
import com.fayardev.erpdemo.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ModelMapper modelMapper;

    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = modelMapper.map(purchaseOrderDTO, PurchaseOrder.class);
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return modelMapper.map(savedPurchaseOrder, PurchaseOrderDTO.class);
    }

    public Optional<PurchaseOrderDTO> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .map(purchaseOrder -> modelMapper.map(purchaseOrder, PurchaseOrderDTO.class));
    }

    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(purchaseOrder -> modelMapper.map(purchaseOrder, PurchaseOrderDTO.class))
                .toList();
    }

    public PurchaseOrderDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = modelMapper.map(purchaseOrderDTO, PurchaseOrder.class);
        PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return modelMapper.map(updatedPurchaseOrder, PurchaseOrderDTO.class);
    }

    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
