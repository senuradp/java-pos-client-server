# ![My Skills](https://skills.thijs.gg/icons?i=java&theme=light)   Client Server Point Of Sale (POS) System with JAVA Swing

By : S K Senura Deshan Perera (CB008868) - Final Year Undergraduate at APIIT Sri Lanka

The project was developed for one of my assignments has used Object Oriented Programming (OOP) concepts and the following design patterns with the use of SOLID Principles

- Connection Pooling design pattern
- Strategy design pattern
- Facade design pattern
- Factory design pattern
- Repository design pattern
- Command design pattern
- MVC (Model View Controller)
- DTO (Data Transfer Object)
- DAO (Data Access Object)

## Installation

Use the below command to clone the project.

```bash
git clone https://github.com/senuradp/java-posclient-server.git
```

Start mysql in homebrew

```bash
brew services start mysql
```

Database connection

```bash
DB_NAME = syos
DB_USERNAME = root
DB_PASSWORD = 'none, leave it empty'
```

SQL scripts for tables
```bash
CREATE TABLE `product` (
  `product_code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` bigint NOT NULL,
  PRIMARY KEY (`product_code`)
);

CREATE TABLE `batch` (
  `batch_code` varchar(10) NOT NULL,
  `purchase_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `product_code` varchar(10) NOT NULL,
  `batch_qty` bigint NOT NULL,
  `available_qty` bigint NOT NULL,
  `is_sold` char(1) NOT NULL,
  PRIMARY KEY (`batch_code`),
  KEY `batch_to_product` (`product_code`),
  CONSTRAINT `batch_to_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
) ;

CREATE TABLE `bill_header` (
  `bill_serial_number` varchar(20) NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `total_bill_price` bigint NOT NULL,
  `amount_tendered` bigint NOT NULL,
  `discount` bigint NOT NULL,
  `balance` bigint NOT NULL,
  PRIMARY KEY (`bill_serial_number`)
);

CREATE TABLE `bill_detail` (
  `bill_serial_number` varchar(20) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_qty` bigint NOT NULL,
  `product_price` bigint NOT NULL,
  `total_item_price` bigint NOT NULL
);

CREATE TABLE `shelf` (
  `shelf_code` varchar(10) NOT NULL,
  `product_code` varchar(10) DEFAULT NULL,
  `capacity` bigint NOT NULL,
  `available_qty` bigint NOT NULL,
  PRIMARY KEY (`shelf_code`),
  KEY `shelf_to_product` (`product_code`),
  CONSTRAINT `shelf_to_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
);

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
);

```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.


