CREATE DATABASE wsm
GO
CREATE LOGIN adminLogin WITH PASSWORD = 'admin'
GO

CREATE TABLE [User]
(
    [email] NVARCHAR(MAX) PRIMARY KEY,
    [role] INT CHECK (role = 0 OR role = 1),
    [firstName] NVARCHAR(MAX),
    [lastName] NVARCHAR(MAX),
    [contactNumber] NVARCHAR(MAX),
    [authToken] NVARCHAR(MAX),
    [tokenExp] DATETIME
);
GO


CREATE TABLE [Issue]
(
    [id] INT PRIMARY KEY IDENTITY (1,1),
    [title] NVARCHAR(MAX),
    [description] NVARCHAR(MAX),
    [solution] NVARCHAR(MAX),
    [reporter] NVARCHAR(MAX) FOREIGN KEY REFERENCES [User].[email],
    [dateOpened] DATETIME,
    [dateClosed] DATETIME,
    [catagory] NVARCHAR(MAX),
    [subCatagory] NVARCHAR(MAX)
)
GO
CREATE TABLE [Article]
(
    [id] UNIQUEIDENTIFIER PRIMARY KEY,
    [title] NVARCHAR(MAX),
    [description] NVARCHAR(MAX),
    [solution] NVARCHAR(MAX),
    [dateOpened] DATETIME,
    [catagory] NVARCHAR(MAX),
    [subCatagory] NVARCHAR(MAX)
)
GO
CREATE TABLE [Comment]
(
    [id] UNIQUEIDENTIFIER PRIMARY KEY,
    [parent] NVARCHAR(MAX) FOREIGN KEY REFERENCES [Comment].[id],
    [author] NVARCHAR(MAX) FOREIGN KEY REFERENCES [User].[email],
    [date] DATETIME,
    [content] NVARCHAR(MAX)
)
CREATE TABLE [EntityComment]
(
    [comment] UNIQUEIDENTIFIER,
    [entity] UNIQUEIDENTIFIER,
    [type] NVARCHAR(1)
    -- I for issue, A for KB and C for comment
)
CREATE TABLE [Issue]
(
    
)

INSERT INTO [User] VALUES ('1@test',0,'test1','1','001','auth1','2022-05-01 12:00:00')
INSERT INTO [User] VALUES ('2@test',1,'test2','2','020','auth2','2022-05-02 12:00:00')
INSERT INTO [User] VALUES ('3@test',0,'test3','3','300','auth3','2022-05-03 12:00:00')
INSERT INTO [User] VALUES ('4@test',1,'test4','4','004','auth4','2022-05-04 12:00:00')
INSERT INTO [User] VALUES ('5@test',0,'test5','5','050','auth5','2022-05-25 12:00:00')