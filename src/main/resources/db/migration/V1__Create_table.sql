CREATE TABLE Users(
    id INT PRIMARY KEY,
    name varchar(255)
) engine=InnoDB DEFAULT CHARSET = gbk;

CREATE TABLE Orders(
    id INT,
    userId INT,
    status VARCHAR(255) NOT NULL,
    buyCount INT NOT NULL,
    buyTime VARCHAR(255),
    totalPrice DOUBLE(20,2) NOT NULL,
    orderDetail varchar(255) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES Users(id)
) engine=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Products(
    id INT ,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE(10,2) NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY(id)
) engine=InnoDB DEFAULT CHARSET = utf8;