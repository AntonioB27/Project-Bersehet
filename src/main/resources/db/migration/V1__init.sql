CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password) VALUES
  ('alice', 'alice@example.com', 'password123'),
  ('bob', 'bob@example.com', 'securepass456'),
  ('carol', 'carol@example.com', 'mysecret789');