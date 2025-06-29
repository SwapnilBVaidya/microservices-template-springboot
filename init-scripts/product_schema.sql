CREATE SCHEMA IF NOT EXISTS product_service;

CREATE TABLE IF NOT EXISTS product_service.products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    price NUMERIC(10, 2),
    stock INT,
    active BOOLEAN DEFAULT TRUE
);

INSERT INTO product_service.products (name, price, stock, active) VALUES
('Wireless Mouse', 599.99, 50, true),
('Mechanical Keyboard', 2299.00, 30, true),
('HD Monitor 24-inch', 7499.50, 15, true),
('USB-C Hub', 999.00, 20, true),
('Laptop Stand', 799.00, 25, true),
('Noise Cancelling Headphones', 4999.99, 10, true),
('External SSD 1TB', 6899.00, 18, true),
('Webcam Full HD', 1999.00, 22, true),
('Ergonomic Chair', 12499.00, 5, true),
('Desk Lamp', 499.00, 40, true);
