package com.fayardev.erpdemo.controller;

import com.fayardev.erpdemo.dto.InvoiceDTO;
import com.fayardev.erpdemo.service.InvoiceService;
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
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        Optional<InvoiceDTO> invoiceDTO = invoiceService.getInvoiceById(id);
        return invoiceDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);
        }
        if (invoiceService.getInvoiceById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoiceDTO.setId(id);
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(invoiceDTO);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        if (invoiceService.getInvoiceById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}