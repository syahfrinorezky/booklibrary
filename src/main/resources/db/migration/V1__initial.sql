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

CREATE INDEX idx_members_deleted_at ON members (deleted_at);

CREATE TABLE
    categories (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL
    );

CREATE INDEX idx_categories_deleted_at ON categories (deleted_at);

CREATE TABLE
    books (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        category_id BIGINT NULL,
        title VARCHAR(100) NOT NULL,
        author VARCHAR(100) NOT NULL,
        publisher VARCHAR(100) NOT NULL,
        year INT (4),
        stock INT NOT NULL DEFAULT 0,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL,
        CONSTRAINT fk_book_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE SET NULL
    );

CREATE INDEX idx_books_category_id ON books (category_id);

CREATE INDEX idx_books_deleted_at ON books (deleted_at);

CREATE TABLE
    loans (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        member_id BIGINT NULL,
        book_id BIGINT NULL,
        loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        due_date TIMESTAMP NULL,
        return_date TIMESTAMP NULL,
        status ENUM ('LOANED', 'RETURNED', 'LATE', 'LOST') DEFAULT 'LOANED',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        deleted_at TIMESTAMP NULL,
        CONSTRAINT fk_loan_member FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE SET NULL,
        CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE SET NULL
    );

CREATE INDEX idx_loans_member_id ON loans (member_id);

CREATE INDEX idx_loans_book_id ON loans (book_id);

CREATE INDEX idx_loans_deleted_at ON loans (deleted_at);