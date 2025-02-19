CREATE TABLE IF NOT EXISTS articles (
    id INTEGER AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    description text,
    status VARCHAR(255) NOT NULL,
    -- created_at TIMESTAMP NOT NULL,
    primary key (id)
);

-- INSERT INTO articles (title, slug, description, status, created_at)
-- VALUES ("super title", "super-title", "une description", "REVIEW", CURRENT_TIMESTAMP);