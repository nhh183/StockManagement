CREATE DATABASE StockManagement;
GO

USE StockManagement;
GO

CREATE TABLE tblUsers (
    userID VARCHAR(50) NOT NULL PRIMARY KEY,
    fullName NVARCHAR(255) NOT NULL,
    roleID VARCHAR(5) NOT NULL,
    password VARCHAR(50) NOT NULL
);
GO

CREATE TABLE tblStocks (
    ticker CHAR(6) NOT NULL PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    sector NVARCHAR(50) NOT NULL,
    price FLOAT NOT NULL,
    status BIT NOT NULL
);
GO

CREATE TABLE tblTransactions (
    id INT IDENTITY(1,1) PRIMARY KEY,
    userID VARCHAR(50) NOT NULL,
    ticker CHAR(6) NOT NULL,
    type VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    price FLOAT NOT NULL,
    status VARCHAR(20) NOT NULL
);
GO

CREATE TABLE tblAlerts (
    alertID INT IDENTITY(1,1) PRIMARY KEY,
    userID VARCHAR(50) NOT NULL,
    ticker CHAR(6) NOT NULL,
    threshold FLOAT NOT NULL,
    direction VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'inactive'
);
GO

-- Dữ liệu mẫu
INSERT INTO tblUsers (userID, fullName, roleID, password) VALUES
('user001', 'Nguyen Van A', 'AD', '123456'),
('user002', 'Tran Thi B', 'NV', 'abcdef'),
('user003', 'Le Minh C', 'NV', 'qwerty');

INSERT INTO tblStocks (ticker, name, sector, price, status) VALUES
('VNM', 'Vietnam Dairy Products JSC', 'Food & Beverage', 85.5, 1),
('HPG', 'Hoa Phat Group JSC', 'Metals & Mining', 28.0, 1),
('VIC', 'Vingroup JSC', 'Real Estate', 70.2, 1);

INSERT INTO tblTransactions (userID, ticker, type, quantity, price, status) VALUES
('user001', 'VNM', 'buy', 100, 85.0, 'executed'),
('user002', 'HPG', 'buy', 200, 27.5, 'pending');

INSERT INTO tblAlerts (userID, ticker, threshold, direction, status) VALUES
('user001', 'VNM', 90.0, 'increase', 'active'),
('user002', 'HPG', 30.0, 'decrease', 'inactive');
