-- -----------------------
-- USERS
-- -----------------------
INSERT INTO users (id, name, email, password, created_at) VALUES
(1, 'Alice Johnson', 'alice1@example.com', 'password1', NOW()),
(2, 'Bob Smith', 'bob2@example.com', 'password2', NOW()),
(3, 'Charlie Brown', 'charlie3@example.com', 'password3', NOW()),
(4, 'David Lee', 'david4@example.com', 'password4', NOW()),
(5, 'Eva Green', 'eva5@example.com', 'password5', NOW()),
(6, 'Frank White', 'frank6@example.com', 'password6', NOW()),
(7, 'Grace Kim', 'grace7@example.com', 'password7', NOW()),
(8, 'Henry Clark', 'henry8@example.com', 'password8', NOW()),
(9, 'Ivy Scott', 'ivy9@example.com', 'password9', NOW()),
(10, 'Jack Turner', 'jack10@example.com', 'password10', NOW()),
(11, 'Karen Hall', 'karen11@example.com', 'password11', NOW()),
(12, 'Leo Adams', 'leo12@example.com', 'password12', NOW()),
(13, 'Mia Hill', 'mia13@example.com', 'password13', NOW()),
(14, 'Nina Young', 'nina14@example.com', 'password14', NOW()),
(15, 'Oscar King', 'oscar15@example.com', 'password15', NOW()),
(16, 'Paula Wright', 'paula16@example.com', 'password16', NOW()),
(17, 'Quinn Baker', 'quinn17@example.com', 'password17', NOW()),
(18, 'Ryan Diaz', 'ryan18@example.com', 'password18', NOW()),
(19, 'Sophia Evans', 'sophia19@example.com', 'password19', NOW()),
(20, 'Tom Harris', 'tom20@example.com', 'password20', NOW());

-- -----------------------
-- BUDGETS
-- -----------------------
INSERT INTO budget (id, name, period, total_amount, user_id, created_at) VALUES
(1, 'Monthly Groceries', 'MONTHLY', 500.0, 1, NOW()),
(2, 'Entertainment', 'WEEKLY', 200.0, 1, NOW()),
(3, 'Transport', 'MONTHLY', 300.0, 2, NOW()),
(4, 'Monthly Groceries', 'MONTHLY', 450.0, 2, NOW()),
(5, 'Monthly Groceries', 'MONTHLY', 400.0, 3, NOW()),
(6, 'Entertainment', 'WEEKLY', 150.0, 3, NOW()),
(7, 'Transport', 'MONTHLY', 250.0, 4, NOW()),
(8, 'Monthly Groceries', 'MONTHLY', 600.0, 4, NOW()),
(9, 'Entertainment', 'WEEKLY', 100.0, 5, NOW()),
(10, 'Monthly Groceries', 'MONTHLY', 500.0, 5, NOW()),
(11, 'Transport', 'MONTHLY', 300.0, 6, NOW()),
(12, 'Monthly Groceries', 'MONTHLY', 450.0, 6, NOW()),
(13, 'Entertainment', 'WEEKLY', 150.0, 7, NOW()),
(14, 'Monthly Groceries', 'MONTHLY', 400.0, 7, NOW()),
(15, 'Transport', 'MONTHLY', 200.0, 8, NOW()),
(16, 'Monthly Groceries', 'MONTHLY', 500.0, 8, NOW()),
(17, 'Entertainment', 'WEEKLY', 120.0, 9, NOW()),
(18, 'Monthly Groceries', 'MONTHLY', 450.0, 9, NOW()),
(19, 'Transport', 'MONTHLY', 300.0, 10, NOW()),
(20, 'Monthly Groceries', 'MONTHLY', 400.0, 10, NOW());

-- -----------------------
-- EXPENSES (3–5 per budget)
-- -----------------------
INSERT INTO expense (id, category_name, amount, date, payment_mode, notes, user_id, budget_id) VALUES
-- Budget 1 (User 1)
(1, 'Vegetables', 50.0, '2025-12-01', 'CASH', 'Weekly veggies', 1, 1),
(2, 'Fruits', 40.0, '2025-12-02', 'CASH', 'Apples and bananas', 1, 1),
(3, 'Milk', 20.0, '2025-12-03', 'CARD', 'Dairy', 1, 1),
-- Budget 2 (User 1)
(4, 'Movies', 30.0, '2025-12-04', 'CARD', 'Cinema night', 1, 2),
(5, 'Concert', 50.0, '2025-12-05', 'CARD', 'Live concert', 1, 2),
-- Budget 3 (User 2)
(6, 'Bus Ticket', 25.0, '2025-12-01', 'CARD', 'Monthly pass', 2, 3),
(7, 'Taxi', 15.0, '2025-12-02', 'CASH', 'City taxi', 2, 3),
(8, 'Fuel', 30.0, '2025-12-03', 'CARD', 'Car fuel', 2, 3),
-- Budget 4 (User 2)
(9, 'Vegetables', 45.0, '2025-12-01', 'CASH', 'Weekly veggies', 2, 4),
(10, 'Fruits', 35.0, '2025-12-02', 'CASH', 'Bananas', 2, 4),
(11, 'Milk', 25.0, '2025-12-03', 'CARD', 'Dairy', 2, 4),
-- Budget 5 (User 3)
(12, 'Snacks', 20.0, '2025-12-01', 'CASH', 'Evening snacks', 3, 5),
(13, 'Juice', 10.0, '2025-12-02', 'CARD', 'Orange juice', 3, 5),
(14, 'Fruits', 30.0, '2025-12-03', 'CASH', 'Bananas', 3, 5),
-- Budget 6 (User 3)
(15, 'Concert', 50.0, '2025-12-04', 'CARD', 'Music concert', 3, 6),
(16, 'Movies', 30.0, '2025-12-05', 'CARD', 'Cinema night', 3, 6),
-- Budget 7 (User 4)
(17, 'Taxi', 40.0, '2025-12-01', 'CARD', 'Airport taxi', 4, 7),
(18, 'Fuel', 35.0, '2025-12-02', 'CASH', 'Car fuel', 4, 7),
(19, 'Parking', 20.0, '2025-12-03', 'CARD', 'Parking fee', 4, 7),
-- Budget 8 (User 4)
(20, 'Vegetables', 60.0, '2025-12-01', 'CASH', 'Weekly groceries', 4, 8),
(21, 'Fruits', 45.0, '2025-12-02', 'CASH', 'Oranges and apples', 4, 8),
(22, 'Milk', 25.0, '2025-12-03', 'CARD', 'Dairy', 4, 8),
-- Continue similarly for all budgets...
-- Budget 9 (User 5)
(23, 'Movies', 20.0, '2025-12-01', 'CARD', 'Netflix', 5, 9),
(24, 'Snacks', 15.0, '2025-12-02', 'CASH', 'Chips', 5, 9),
(25, 'Juice', 10.0, '2025-12-03', 'CARD', 'Orange juice', 5, 9),
-- Budget 10 (User 5)
(26, 'Vegetables', 50.0, '2025-12-01', 'CASH', 'Weekly veggies', 5, 10),
(27, 'Fruits', 35.0, '2025-12-02', 'CASH', 'Bananas', 5, 10),
(28, 'Milk', 25.0, '2025-12-03', 'CARD', 'Dairy', 5, 10);
