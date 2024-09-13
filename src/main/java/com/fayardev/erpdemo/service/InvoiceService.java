package com.fayardev.erpdemo.service;

import com.fayardev.erpdemo.dto.InvoiceDTO;
import com.fayardev.erpdemo.entity.Invoice;
import com.fayardev.erpdemo.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(savedInvoice, InvoiceDTO.class);
    }

    public Optional<InvoiceDTO> getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class));
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class))
                .toList();
    }

    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(updatedInvoice, InvoiceDTO.class);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
