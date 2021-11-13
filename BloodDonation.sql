CREATE DATABASE BloodDonation
GO

USE BloodDonation
GO

CREATE TABLE Users(
	userId int primary key identity NOT NULL,
	fullName nvarchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	avatar varchar(200),
	birthday date CHECK (birthday < GETDATE()) NOT NULL,
	gender bit,
	telephone varchar(100) NOT NULL,
	address nvarchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	identityCard varchar(100) NOT NULL,
	job nvarchar(100),
	bloodType varchar(20) NOT NULL,
	permission int 
);
GO
ALTER TABLE Users ADD createDate date;
GO

CREATE TABLE DonationPlaces(
	idPlace int primary key identity NOT NULL,
	namePlace nvarchar(100),
	dayStart date NOT NULL,
	dayEnd date NOT NULL,
	timeOpen VARCHAR(100),
	address nvarchar(255) 
);
GO

CREATE TABLE History(
	idHis int primary key identity NOT NULL,
	times int,
	dayDonation date NOT NULL,
	userId int foreign key references Users(userId),
	weight int,
	amountOfBlood int,
	place int foreign key references DonationPlaces(idPlace),

	tinhTrang bit,
	macBenh bit,
	sutCan bit,
	phauThuat bit,
	xam bit,
	truyenMau bit,
	maTuy bit,
	quanHe bit,
	quanHeCungGioi bit,
	vaccine bit,
	vungDich bit,
	benh bit,
	thuocKS bit,
	diKham bit,
	tanTat bit,
	mangThai bit,

	status bit
);
GO

CREATE TABLE News(
	idNews int primary key identity NOT NULL,
	image varchar(100),
	publicDate date CHECK (publicDate < GETDATE()),
	title nvarchar(200),
	info nvarchar(2000)
);
GO

INSERT INTO Users (fullName,password,birthday,gender,telephone,address,email,identityCard,job,bloodType,permission) VALUES ( N'admin', '123456', '12-12-2000', 1, '03265444', N'Cổ Nhuế - Hà Nội', 'admin@gmail.com', '03430005564', N'Kĩ sư', 'O',1)
INSERT INTO Users (fullName,password,birthday,gender,telephone,address,email,identityCard,job,bloodType,permission) VALUES ( N'Minh Ly', '123456', '10-10-2000', 1, '03265444', N'Cổ Nhuế - Hà Nội', 'MinhLy@gmail.com', '03430005564', N'Kĩ sư', 'O',2)

INSERT INTO DonationPlaces VALUES ( N'Bệnh viện huyết học Trung ương', '08-10-2020', '10-01-2021', '7:30 - 20:00', '2QFQ+V9X - P. Phạm Văn Bạch - Yên Hoà - Cầu Giấy - Hà Nội')

INSERT INTO History VALUES ( 1, '12/12/2020', 1, '50', '350', 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)

INSERT INTO News(publicDate, title, info) VALUES ('2020-11-02', N'Ứng dụng công nghệ trong hiến máu tình nguyện', N'Viết thông tin bài báo')



