DROP TABLE IF EXISTS `purchases`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `suppliers`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `books`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE ,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL ,
    isbn VARCHAR(20) NOT NULL,
    price FLOAT NOT NULL DEFAULT 0,
    quantity INTEGER NOT NULL DEFAULT 0,
    units_sold INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);
CREATE TABLE `customers`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARBINARY(255) DEFAULT NULL,
    phone_number VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE `purchases`(
    book_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
CREATE TABLE `suppliers`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) DEFAULT NULL,
    phone_number VARCHAR(255) DEFAULT NULL,
    notes TEXT DEFAULT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE `roles`(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NUlL,
    UNIQUE (name),
    PRIMARY KEY (id)
);
CREATE TABLE `users`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) DEFAULT NULL,
    last_name VARCHAR(255) DEFAULT NULL,
    phone_number VARCHAR(255) DEFAULT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);
CREATE TABLE `user_roles`(
    user_id BIGINT NOT NULL ,
    role_id INTEGER NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id,role_id)
);

INSERT INTO roles(`name`) VALUES ('ROLE_SELLER'),('ROLE_ADMIN');

# SELECT * FROM roles;

