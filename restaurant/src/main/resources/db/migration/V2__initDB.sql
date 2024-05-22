INSERT INTO MEAL_TYPE(CODE, NAME) VALUES
('MEAL', 'Meal'),
('DRINK', 'Drink');

INSERT INTO MEAL_CATEGORY(CODE, NAME) VALUES
('MAIN_COURSE', 'Main course'),
('DESSERT', 'Dessert');

INSERT INTO CUSINE(NAME) VALUES
('Polish'),
('Mexican'),
('Italian');

INSERT INTO MEAL(NAME, PRICE, MEAL_TYPE_ID, MEAL_CATEGIRY_ID) VALUES
('Tea', 1.0, 2, null),
('Coffee', 2.0, 2, null),
('Golabki', 3.07, 1, 1),
('Pierogi', 2.6, 1, 1),
('Tacos', 6.4, 1, 1),
('Enchiladas', 10.7, 1, 1),
('Pozole', 7.4, 1, 1),
('Pizza', 10.0, 1, 1),
('Lasagna', 8.4, 1, 1),
('Pasta', 6.9, 1, 1),
('Panna Cotta', 8.5, 1, 2),
('Cream Cheese', 12.4, 1, 2),
('Ice cream', 4.3, 1, 2),
('Lemon', 0.5, 2, null),
('Ice cube', 0.01, 2, null );

INSERT INTO LUNCH(NAME, CUSINE_ID) VALUES
('Mexican lunch 1', 2),
('Mexican lunch 2', 2),
('Polish lunch', 1),
('Italian lunch 1', 3),
('Italian lunch 2', 3),
('Italian lunch 3', 3);

INSERT INTO LUNCH_MENU(LUNCH_ID, MEAL_ID) VALUES
(1, 5),
(1, 11),
(2, 6),
(2, 13),
(3, 3),
(3, 12),
(4, 7),
(4, 13),
(5, 9),
(5, 11),
(6, 8),
(6, 12);

