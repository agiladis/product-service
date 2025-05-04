-- Categories
CREATE TABLE categories
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Products
CREATE TABLE products
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    category_id BIGINT REFERENCES categories (id),
    status      VARCHAR(20)  NOT NULL DEFAULT 'DRAFT',
    created_at  TIMESTAMP             DEFAULT NOW(),
    updated_at  TIMESTAMP             DEFAULT NOW()
);

-- Product Variants
CREATE TABLE product_variants
(
    id         BIGSERIAL PRIMARY KEY,
    sku        VARCHAR(100)   NOT NULL UNIQUE,
    size       VARCHAR(10)    NOT NULL,
    color      VARCHAR(50),
    price      NUMERIC(10, 2) NOT NULL,
    product_id BIGINT REFERENCES products (id) ON DELETE CASCADE
);