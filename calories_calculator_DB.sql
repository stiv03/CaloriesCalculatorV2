CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    height INTEGER NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    userType VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    calories_per_100_grams DECIMAL(10, 2) NOT NULL,
    protein_per_100_grams DECIMAL(10, 2) NOT NULL,
    fat_per_100_grams DECIMAL(10, 2) NOT NULL,
    carbs_per_100_grams DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS meals (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL,
    consumed_at DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS weight_records (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS measurements_records (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    shoulder DECIMAL(5, 2) NOT NULL,
    chest DECIMAL(5, 2) NOT NULL,
    biceps DECIMAL(5, 2) NOT NULL,
    waist DECIMAL(5, 2) NOT NULL,
    hips DECIMAL(5, 2) NOT NULL,
    thigh DECIMAL(5, 2) NOT NULL,
    calf DECIMAL(5, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
