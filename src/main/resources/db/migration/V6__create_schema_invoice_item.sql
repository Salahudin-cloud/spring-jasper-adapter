CREATE TABLE invoice_items (
    id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_invoice_item_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    CONSTRAINT fk_invoice_item_product FOREIGN KEY (product_id) REFERENCES products(id)
);
