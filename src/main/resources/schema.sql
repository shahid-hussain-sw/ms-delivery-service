CREATE TABLE IF NOT EXISTS DELIVERY(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    product VARCHAR(255) NOT NULL,
    supplier VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    expected_date VARCHAR(100) NOT NULL,
    expected_warehouse VARCHAR(100) NOT NULL,
    status VARCHAR(100) DEFAULT 'NOT_RECEIVED'
);