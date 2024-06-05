INSERT INTO Users (UserName, Email, PasswordHash)
VALUES ('JohnDoe', 'johndoe@example.com', 'hashedpassword1'),
       ('JaneSmith', 'janesmith@example.com', 'hashedpassword2'),
       ('AliceJones', 'alicejones@example.com', 'hashedpassword3'),
       ('BobBrown', 'bobbrown@example.com', 'hashedpassword4'),
       ('CharlieBlack', 'charlieblack@example.com', 'hashedpassword5'),
       ('DavidWhite', 'davidwhite@example.com', 'hashedpassword6'),
       ('EvaGreen', 'evagreen@example.com', 'hashedpassword7'),
       ('FrankBlue', 'frankblue@example.com', 'hashedpassword8'),
       ('GraceYellow', 'graceyellow@example.com', 'hashedpassword9'),
       ('HannahRed', 'hannahred@example.com', 'hashedpassword10');


INSERT INTO Products (ProductName, ProductDescription, Price, Stock, Category)
VALUES ('Laptop', 'High performance laptop', 999.99, 50, 'Electronics'),
       ('Smartphone', 'Latest model smartphone', 599.99, 150, 'Electronics'),
       ('Coffee Maker', 'Automatic coffee maker', 89.99, 75, 'Home Appliances'),
       ('Blender', 'High-speed blender', 49.99, 100, 'Home Appliances'),
       ('Book', 'Bestselling novel', 19.99, 200, 'Books'),
       ('Jeans', 'Comfortable jeans', 39.99, 80, 'Clothing'),
       ('Running Shoes', 'Lightweight running shoes', 79.99, 120, 'Sports'),
       ('Headphones', 'Noise-cancelling headphones', 129.99, 90, 'Electronics'),
       ('Desk Lamp', 'Adjustable desk lamp', 29.99, 60, 'Home Appliances'),
       ('Backpack', 'Durable backpack', 49.99, 110, 'Accessories');


INSERT INTO Orders (UserID, TotalAmount, Status)
VALUES (1, 1099.98, 'Completed'),
       (2, 599.99, 'Pending'),
       (3, 169.98, 'Completed'),
       (4, 39.99, 'Shipped'),
       (5, 119.98, 'Pending'),
       (6, 209.97, 'Cancelled'),
       (7, 259.98, 'Shipped'),
       (8, 399.99, 'Completed'),
       (9, 79.99, 'Pending'),
       (10, 89.98, 'Completed');


INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES (1, 1, 1, 999.99),
       (1, 3, 1, 89.99),
       (2, 2, 1, 599.99),
       (3, 5, 2, 19.99),
       (4, 6, 1, 39.99),
       (5, 4, 2, 49.99),
       (6, 7, 3, 79.99),
       (7, 8, 2, 129.99),
       (8, 1, 1, 999.99),
       (8, 9, 1, 29.99),
       (9, 7, 1, 79.99),
       (10, 10, 2, 49.99);


