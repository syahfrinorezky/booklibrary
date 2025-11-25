CREATE TABLE
    members (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        phone VARCHAR(15) NOT NULL,
        address TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

CREATE TABLE
    categories (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

CREATE TABLE
    books (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        category_id BIGINT,
        title VARCHAR(100) NOT NULL,
        author VARCHAR(100) NOT NULL,
        publisher VARCHAR(100) NOT NULL,
        year YEAR,
        stock INT NOT NULL DEFAULT 0,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL,
        CONSTRAINT fk_book_category FOREIGN KEY (category_id) REFERENCES categories (id)
    );

CREATE TABLE
    loans (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        member_id BIGINT NOT NULL,
        book_id BIGINT NOT NULL,
        loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        due_date TIMESTAMP NULL,
        return_date TIMESTAMP NULL,
        status ENUM ('LOANED', 'RETURNED', 'LATE', 'LOST') DEFAULT 'LOANED',
        CONSTRAINT fk_loan_member FOREIGN KEY (member_id) REFERENCES members (id),
        CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES books (id)
    );