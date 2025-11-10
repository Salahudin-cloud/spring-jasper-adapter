package com.jasper.learn.domain.service.impl;

import com.jasper.learn.common.util.ServiceUtils;
import com.jasper.learn.domain.entity.InvoiceItem;
import com.jasper.learn.domain.repository.InvoiceItemRepository;
import com.jasper.learn.domain.service.InvoiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    @Override
    public InvoiceItem getById(Long id) {
        return ServiceUtils.orNotFound(invoiceItemRepository.findById(id), "Invoice item not found");
    }

    @Override
    public void update(Long id, InvoiceItem invoiceItem) {
        InvoiceItem existing = getById(id);

        Optional.ofNullable(invoiceItem.getQuantity())
                .ifPresent(existing::setQuantity);

        invoiceItemRepository.save(existing);
    }

    @Override
    public Page<InvoiceItem> findAll(int page, int size) {
        return invoiceItemRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void delete(Long id) {
        InvoiceItem existing = getById(id);
        invoiceItemRepository.deleteInvoiceItem(existing);
    }
}
