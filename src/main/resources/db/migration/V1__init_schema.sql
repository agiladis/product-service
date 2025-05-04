-- Categories
CREATE TABLE categories
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

-- Products
CREATE TABLE products
(
    id          UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    category_id UUID REFERENCES categories (id),
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(20)  NOT NULL DEFAULT 'DRAFT',
    created_at  TIMESTAMPTZ           DEFAULT NOW(),
    updated_at  TIMESTAMPTZ           DEFAULT NOW()
);

-- Product Variants
CREATE TABLE product_variants
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID REFERENCES products (id) ON DELETE CASCADE,
    sku        VARCHAR(100)   NOT NULL UNIQUE,
    size       VARCHAR(10)    NOT NULL,
    color      VARCHAR(50),
    price      NUMERIC(10, 2) NOT NULL
);

-- Optimasi index untuk query yang sering digunakan
CREATE INDEX idx_products_category_id ON products (category_id);
CREATE INDEX idx_product_variants_product_id ON product_variants (product_id);