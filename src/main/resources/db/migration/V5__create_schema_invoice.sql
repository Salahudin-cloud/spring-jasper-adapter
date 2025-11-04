CREATE TABLE invoices (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    invoice_number VARCHAR(50) UNIQUE NOT NULL,
    invoice_date DATE NOT NULL,
    due_date DATE,
    total_amount DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_invoice_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);