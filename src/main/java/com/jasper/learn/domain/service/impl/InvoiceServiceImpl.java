package com.jasper.learn.domain.service.impl;

import com.jasper.learn.common.util.ServiceUtils;
import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.entity.Invoice;
import com.jasper.learn.domain.entity.Product;
import com.jasper.learn.domain.repository.CustomerRepository;
import com.jasper.learn.domain.repository.InvoiceRepository;
import com.jasper.learn.domain.repository.ProductRepository;
import com.jasper.learn.domain.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void create(Invoice invoice) {

        Customer customer = ServiceUtils.orNotFound(
                customerRepository.findById(invoice.getCustomer().getId()),
                "Customer not found"
        );
        invoice.setCustomer(customer);

        invoice.getItems().forEach(item -> {
            Long productId = item.getProduct().getId();
            Product product = ServiceUtils.orNotFound(
                    productRepository.findById(productId),
                    "Product not found"
            );
            item.setProduct(product);
            item.setInvoice(invoice);
        });

        invoice.calculateTotal();
        invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getById(Long id) {
        return ServiceUtils.orNotFound(invoiceRepository.findById(id), "Invoice not found");
    }

    @Override
    public void update(Long id, Invoice invoice) {
        Invoice existing = getById(id);

        Optional.ofNullable(invoice.getInvoiceNumber())
                .ifPresent(existing::setInvoiceNumber);

        Optional.ofNullable(invoice.getInvoiceDate())
                .ifPresent(existing::setInvoiceDate);

        Optional.ofNullable(invoice.getDueDate())
                .ifPresent(existing::setDueDate);

        Optional.ofNullable(invoice.getTotalAmount())
                .ifPresent(existing::setTotalAmount);

        invoiceRepository.save(existing);
    }

    @Override
    public Page<Invoice> findAll(int page, int size) {
        return invoiceRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        Invoice existing = getById(id);
        invoiceRepository.deleteInvoice(existing);
    }
}
