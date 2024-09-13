package com.fayardev.erpdemo.service;

import com.fayardev.erpdemo.dto.SalesOrderDTO;
import com.fayardev.erpdemo.entity.SalesOrder;
import com.fayardev.erpdemo.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final ModelMapper modelMapper;

    public SalesOrderDTO createSalesOrder(SalesOrderDTO salesOrderDTO) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDTO, SalesOrder.class);
        SalesOrder savedSalesOrder = salesOrderRepository.save(salesOrder);
        return modelMapper.map(savedSalesOrder, SalesOrderDTO.class);
    }

    public Optional<SalesOrderDTO> getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .map(salesOrder -> modelMapper.map(salesOrder, SalesOrderDTO.class));
    }

    public List<SalesOrderDTO> getAllSalesOrders() {
        return salesOrderRepository.findAll()
                .stream()
                .map(salesOrder -> modelMapper.map(salesOrder, SalesOrderDTO.class))
                .toList();
    }

    public SalesOrderDTO updateSalesOrder(SalesOrderDTO salesOrderDTO) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDTO, SalesOrder.class);
        SalesOrder updatedSalesOrder = salesOrderRepository.save(salesOrder);
        return modelMapper.map(updatedSalesOrder, SalesOrderDTO.class);
    }

    public void deleteSalesOrder(Long id) {
        salesOrderRepository.deleteById(id);
    }
}
