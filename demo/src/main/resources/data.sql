DROP TABLE IF EXISTS Currency;

CREATE TABLE Currency (
  code VARCHAR(250) PRIMARY KEY NOT NULL,
  name VARCHAR(250) NOT NULL
);

INSERT INTO Currency (code, name) VALUES
  ('USD', '美金'),
  ('GBP', '英鎊'),
  ('EUR', '歐元');