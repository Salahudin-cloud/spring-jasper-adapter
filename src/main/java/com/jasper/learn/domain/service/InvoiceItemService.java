package com.jasper.learn.domain.service;

import com.jasper.learn.domain.entity.InvoiceItem;
import org.springframework.data.domain.Page;

public interface InvoiceItemService {

    InvoiceItem getById(Long id);
    void update(Long id, InvoiceItem invoiceItem);
    Page<InvoiceItem> findAll(int page, int size);
    void delete(Long id);

}







