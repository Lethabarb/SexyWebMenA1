DROP TABLE [User]
GO
DROP TABLE [Issue]
GO
DROP TABLE [Article]
GO
DROP TABLE [Comment]
GO
DROP TABLE [EntityComment]
GO

CREATE TABLE [User]
(
    [id] INT PRIMARY KEY IDENTITY (1,1) NOT NULL,
    [email] NVARCHAR(MAX),
    [passwordHash] NVARCHAR(MAX),
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
    [id] UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
    [title] NVARCHAR(MAX) NOT NULL,
    [description] NVARCHAR(MAX) NOT NULL,
    [solution] NVARCHAR(MAX) NOT NULL,
    [reporter] INT NOT NULL,
    [dateOpened] DATETIME NOT NULL,
    [dateClosed] DATETIME NULL,
    [catagory] NVARCHAR(MAX) NOT NULL,
    [subCatagory] NVARCHAR(MAX) NOT NULL,
    [status] INT NOT NULL,
    FOREIGN KEY (reporter) REFERENCES [dbo].[User] (id)
)
GO
CREATE TABLE [Article]
(
    [id] UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
    [title] NVARCHAR(MAX) NOT NULL,
    [description] NVARCHAR(MAX) NOT NULL,
    [solution] NVARCHAR(MAX) NOT NULL,
    [dateOpened] DATETIME NOT NULL,
    [catagory] NVARCHAR(MAX) NOT NULL,
    [subCatagory] NVARCHAR(MAX) NOT NULL
)
GO
CREATE TABLE [Comment]
(
    [id] UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
    [parent] UNIQUEIDENTIFIER NOT NULL,
    [relation] NVARCHAR(1) NOT NULL,
    [author] INT FOREIGN KEY REFERENCES [dbo].[User] (id) NOT NULL,
    [date] DATETIME NOT NULL,
    [content] NVARCHAR(MAX) NOT NULL
)

INSERT INTO [User] VALUES ('1@test',0,'test1','1','001','auth1','2022-05-01 12:00:00')
INSERT INTO [User] VALUES ('2@test',1,'test2','2','020','auth2','2022-05-02 12:00:00')
INSERT INTO [User] VALUES ('3@test',0,'test3','3','300','auth3','2022-05-03 12:00:00')
INSERT INTO [User] VALUES ('4@test',1,'test4','4','004','auth4','2022-05-04 12:00:00')
INSERT INTO [User] VALUES ('5@test',0,'test5','5','050','auth5','2022-05-25 12:00:00')

INSERT INTO [Issue] VALUES ('50c406d2-8659-406c-a732-422d81ae76f7' ,'issue1','1desc','1solut', 1, '2022-05-26 16:23:00', '1000-01-01 00:00:00', 'cat', 'subcat', 0)
INSERT INTO [Issue] VALUES ('6c94e55c-6929-4467-a9e9-189f16b2ba17' ,'issue2','2desc','2solut', 2, '2022-05-26 16:23:00', '1000-01-01 00:00:00', 'cat', 'subcat', 1)
INSERT INTO [Issue] VALUES ('594b6311-ba74-4622-ad6f-b55ecec4b82e' ,'issue3','3desc','3solut', 3, '2022-05-26 16:23:00', '1000-01-01 00:00:00', 'cat', 'subcat', 2)
INSERT INTO [Issue] VALUES ('c1d529da-f09f-4e3d-a4a5-81caff099145' ,'issue4','4desc','4solut', 4, '2022-05-26 16:23:00', '1000-01-01 00:00:00', 'cat', 'subcat', 3)
INSERT INTO [Issue] VALUES ('9c203c7a-46df-4a41-9d91-b0361c3f391c' ,'issue5','5desc','5solut', 5, '2022-05-26 16:23:00', '1000-01-01 00:00:00', 'cat', 'subcat', 4)

INSERT INTO [Article] VALUES ('0336ee3a-22b3-4000-9722-8fb3d166d8cb' ,'KB1', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')
INSERT INTO [Article] VALUES ('8ec9b58f-fbbd-45b6-bfe1-614f1e13af93' ,'KB2', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')
INSERT INTO [Article] VALUES ('336a2af2-8a36-4be1-92cf-8fac3c2d8f1c' ,'KB3', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')
INSERT INTO [Article] VALUES ('6b0e43eb-b43f-4ddc-9ee0-624f8d1767b9' ,'KB4', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')
INSERT INTO [Article] VALUES ('4169179f-5b08-4174-af61-8a3a5830a8ad' ,'KB5', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')
INSERT INTO [Article] VALUES ('796ca1f6-b855-490a-b17c-3b4ca3ae0b78' ,'KB6', 'desc', 'soln', '2022-05-26 21:41:00', 'cat', 'subcat')

INSERT INTO [Comment] VALUES ('113aff52-72ba-41fc-bd00-102d40e4a774','0336ee3a-22b3-4000-9722-8fb3d166d8cb','A','1',GETDATE(), 'comment1')
INSERT INTO [Comment] VALUES ('4504fc92-fa1b-4850-8455-f2ee1527aa2c','113aff52-72ba-41fc-bd00-102d40e4a774','C','2',GETDATE(), 'comment1.1')
INSERT INTO [Comment] VALUES ('10669759-3214-410f-8367-85fef884cd69','0336ee3a-22b3-4000-9722-8fb3d166d8cb','A','2',GETDATE(), 'comment2')
INSERT INTO [Comment] VALUES ('b7d72f37-38ff-45fc-95e7-eda8ae7f9393','4504fc92-fa1b-4850-8455-f2ee1527aa2c','C','1',GETDATE(), 'comment1.1.1')
