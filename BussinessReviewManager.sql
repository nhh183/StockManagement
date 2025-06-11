Create TABLE Rating(	
    userID VARCHAR(50)PRIMARY KEY ,-- Khóa ngoại từ Users                     -
    score INT CHECK (score >= 0 AND score <= 10),
	ticker VARCHAR(10)	,-- Mã công ty
    note NVARCHAR(255),
    createdAt DATETIME DEFAULT GETDATE(),   -- Thời gian tạo
)
INSERT INTO Rating (userID, ticker, score, note) 
VALUES
('user003', 'FPT', 8, N'Dịch vụ tốt'),
('user001', 'VNM', 9, N'Tăng trưởng ổn định');