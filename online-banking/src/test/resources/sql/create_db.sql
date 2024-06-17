DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS client;

CREATE TABLE `client` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(40) NOT NULL,
	`password` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`account_number` VARCHAR(50) NOT NULL,
	`client_id` INT NOT NULL,
	`account_type` VARCHAR(20) NOT NULL,
	`balance` DECIMAL(20,6) NOT NULL,
	`subscription_date` DATE NOT NULL,
	`subscription_end_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_CLIENT_ID` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
);

CREATE TABLE `transaction` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`account_id` INT NOT NULL,
	`transaction_type` VARCHAR(20) NOT NULL,
	`label` VARCHAR(60) NOT NULL,
	`amount` DECIMAL(20,6) NOT NULL,
	`transaction_date` DATE NOT NULL,
	`cancellation_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_ACCOUNT_ID` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);
