-- Query to create books table
CREATE TABLE books (
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	rating DECIMAL(2,1) NOT NULL,
	description TEXT NOT NULL,
	language VARCHAR(255) NOT NULL,
	isbn VARCHAR(255) NOT NULL,
	book_format VARCHAR(255) NOT NULL,
	edition VARCHAR(255) NOT NULL,
	pages INTEGER NOT NULL,
	publisher VARCHAR(255) NOT NULL,
	publish_date DATE NOT NULL,
	first_publish_date DATE NOT NULL,
	linked_percent DECIMAL(5,2) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by VARCHAR(255) NOT NULL,
	last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modified_by VARCHAR(255) NOT NULL,
	is_deleted BOOLEAN DEFAULT FALSE
);

-- Query to create author table
CREATE TABLE author (
	author_id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	bio TEXT,
	created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	created_by VARCHAR(255) NOT NULL,
	last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modified_by VARCHAR(255) NOT NULL,
	is_deleted BOOLEAN DEFAULT FALSE
);

-- Query to create book_author table
CREATE TABLE book_author (
	book_id INTEGER NOT NULL,
	author_id INTEGER NOT NULL,
	PRIMARY KEY (book_id, author_id),
	FOREIGN KEY (book_id) REFERENCES books(book_id),
	FOREIGN KEY (author_id) REFERENCES author(author_id)
);
