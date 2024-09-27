USE [master]
GO
/****** Object:  Database [ElectronicShop]    Script Date: 9/27/2024 8:01:26 PM ******/
CREATE DATABASE [ElectronicShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ElectronicShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\ElectronicShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ElectronicShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\ElectronicShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [ElectronicShop] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ElectronicShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ElectronicShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ElectronicShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ElectronicShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ElectronicShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ElectronicShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [ElectronicShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ElectronicShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ElectronicShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ElectronicShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ElectronicShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ElectronicShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ElectronicShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ElectronicShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ElectronicShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ElectronicShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ElectronicShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ElectronicShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ElectronicShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ElectronicShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ElectronicShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ElectronicShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ElectronicShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ElectronicShop] SET RECOVERY FULL 
GO
ALTER DATABASE [ElectronicShop] SET  MULTI_USER 
GO
ALTER DATABASE [ElectronicShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ElectronicShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ElectronicShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ElectronicShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ElectronicShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ElectronicShop] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'ElectronicShop', N'ON'
GO
ALTER DATABASE [ElectronicShop] SET QUERY_STORE = ON
GO
ALTER DATABASE [ElectronicShop] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [ElectronicShop]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 9/27/2024 8:01:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[order_detail_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NULL,
	[product_id] [int] NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 9/27/2024 8:01:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[order_date] [datetime] NULL,
	[total_amount] [decimal](10, 2) NOT NULL,
	[shipping_address] [nvarchar](255) NULL,
	[status] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 9/27/2024 8:01:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[product_name] [nvarchar](100) NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
	[stock_quantity] [int] NOT NULL,
	[category] [nvarchar](50) NULL,
	[created_at] [datetime] NULL,
 CONSTRAINT [PK__Products__47027DF5420D67AE] PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 9/27/2024 8:01:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[full_name] [nvarchar](100) NULL,
	[address] [nvarchar](255) NULL,
	[created_at] [datetime] NULL,
	[role] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Products] ON 
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (1, N'Sản phẩm', CAST(50000.00 AS Decimal(10, 2)), 10, N'Danh mục mới', CAST(N'2024-09-27T15:52:07.013' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (2, N'Samsung Galaxy S23', CAST(899.99 AS Decimal(10, 2)), 80, N'Smartphones', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (3, N'Google Pixel 7', CAST(799.99 AS Decimal(10, 2)), 60, N'Smartphones', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (4, N'Sony WH-1000XM5', CAST(349.99 AS Decimal(10, 2)), 50, N'Headphones', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (5, N'Bose QuietComfort 45', CAST(299.99 AS Decimal(10, 2)), 40, N'Headphones', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (6, N'Sản phẩm mới', CAST(50000.00 AS Decimal(10, 2)), 10, N'Danh mục mới', CAST(N'2024-09-27T15:38:40.443' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (7, N'Dell XPS 13', CAST(1199.99 AS Decimal(10, 2)), 25, N'Laptops', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (8, N'Asus ROG Strix', CAST(1999.99 AS Decimal(10, 2)), 15, N'Laptops', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (9, N'Logitech MX Master 3', CAST(99.99 AS Decimal(10, 2)), 150, N'Accessories', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (10, N'Razer DeathAdder V2', CAST(69.99 AS Decimal(10, 2)), 100, N'Accessories', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (11, N'Samsung 4K UHD Monitor', CAST(399.99 AS Decimal(10, 2)), 45, N'Monitors', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (12, N'LG UltraGear 32"', CAST(499.99 AS Decimal(10, 2)), 35, N'Monitors', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (13, N'Sony PlayStation 5', CAST(499.99 AS Decimal(10, 2)), 200, N'Gaming Consoles', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (14, N'Xbox Series X', CAST(499.99 AS Decimal(10, 2)), 180, N'Gaming Consoles', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (15, N'Nintendo Switch', CAST(299.99 AS Decimal(10, 2)), 220, N'Gaming Consoles', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (16, N'Apple AirPods Pro 2', CAST(249.99 AS Decimal(10, 2)), 120, N'Earbuds', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (17, N'Samsung Galaxy Buds Pro', CAST(199.99 AS Decimal(10, 2)), 100, N'Earbuds', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (18, N'JBL Flip 6', CAST(129.99 AS Decimal(10, 2)), 90, N'Speakers', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (19, N'Sonos One', CAST(199.99 AS Decimal(10, 2)), 80, N'Speakers', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (20, N'Canon EOS R5', CAST(3899.99 AS Decimal(10, 2)), 10, N'Cameras', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (21, N'Nikon Z6 II', CAST(1999.99 AS Decimal(10, 2)), 15, N'Cameras', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (22, N'GoPro Hero 11', CAST(499.99 AS Decimal(10, 2)), 50, N'Cameras', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (23, N'DJI Mavic Air 2', CAST(999.99 AS Decimal(10, 2)), 25, N'Drones', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (24, N'Apple Watch Series 8', CAST(399.99 AS Decimal(10, 2)), 100, N'Wearables', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (25, N'Fitbit Charge 5', CAST(179.99 AS Decimal(10, 2)), 120, N'Wearables', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (26, N'Garmin Fenix 6 Pro', CAST(699.99 AS Decimal(10, 2)), 40, N'Wearables', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (27, N'MSI GeForce RTX 3080', CAST(799.99 AS Decimal(10, 2)), 30, N'Components', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (28, N'AMD Ryzen 9 5900X', CAST(549.99 AS Decimal(10, 2)), 50, N'Components', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (29, N'Corsair Vengeance 32GB', CAST(159.99 AS Decimal(10, 2)), 70, N'Components', CAST(N'2024-09-20T01:09:17.293' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (31, N'Asus A15', CAST(144.00 AS Decimal(10, 2)), 6, N'laptop', CAST(N'2024-09-26T10:29:18.387' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (33, N'Asus F15', CAST(145.00 AS Decimal(10, 2)), 6, N'laptop', CAST(N'2024-09-26T11:05:52.527' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (35, N'Asus A15', CAST(100.00 AS Decimal(10, 2)), 85, N'laptop', CAST(N'2024-09-26T11:13:38.743' AS DateTime))
GO
INSERT [dbo].[Products] ([product_id], [product_name], [price], [stock_quantity], [category], [created_at]) VALUES (42, N'ád', CAST(123.00 AS Decimal(10, 2)), 12, N'Danh mục mới', CAST(N'2024-09-27T15:53:00.810' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 
GO
INSERT [dbo].[Users] ([user_id], [username], [email], [password], [full_name], [address], [created_at], [role]) VALUES (1, N'abc', N'john@example.com', N'1', N'John Doe', N'123 Main St', CAST(N'2024-09-20T01:09:17.293' AS DateTime), N'user      ')
GO
INSERT [dbo].[Users] ([user_id], [username], [email], [password], [full_name], [address], [created_at], [role]) VALUES (2, N'qwe', N'jane@example.com', N'2', N'Jane Smith', N'456 Elm St', CAST(N'2024-09-20T01:09:17.293' AS DateTime), N'admin     ')
GO
INSERT [dbo].[Users] ([user_id], [username], [email], [password], [full_name], [address], [created_at], [role]) VALUES (3, N'zxc', N'alice@example.com', N'3', N'Alice Jones', N'789 Pine St', CAST(N'2024-09-20T01:09:17.293' AS DateTime), N'user      ')
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__AB6E6164084A7FDF]    Script Date: 9/27/2024 8:01:26 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__F3DBC5721E01FC11]    Script Date: 9/27/2024 8:01:26 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (getdate()) FOR [order_date]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT ('Pending') FOR [status]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF__Products__create__3F466844]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_role]  DEFAULT (user_name()) FOR [role]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__produ__47DBAE45] FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK__OrderDeta__produ__47DBAE45]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__user_id__4222D4EF] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__user_id__4222D4EF]
GO
USE [master]
GO
ALTER DATABASE [ElectronicShop] SET  READ_WRITE 
GO
