package com.fayardev.erpdemo.service;

import com.fayardev.erpdemo.dto.SupplierDTO;
import com.fayardev.erpdemo.entity.Supplier;
import com.fayardev.erpdemo.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return modelMapper.map(savedSupplier, SupplierDTO.class);
    }

    public Optional<SupplierDTO> getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class));
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .toList();
    }

    public SupplierDTO updateSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return modelMapper.map(updatedSupplier, SupplierDTO.class);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
