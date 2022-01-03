insert into role(name)
values ('ROLE_ADMIN');

insert into role(name)
values ('ROLE_BOAT_OWNER');

insert into role(name)
values ('ROLE_CUSTOMER');

insert into role(name)
values ('ROLE_COTTAGE_OWNER');

insert into role(name)
values ('ROLE_INSTRUCTOR');


-- Admin
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+admin@gmail.com','$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG','2022-01-03 21:02:17.307', true);   -- password: admin
insert into user_role (user_id, role_id)
values (1, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (1, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+admin@gmail.com', '$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG', 'Admin', 'Admin', '030345');

-- Admin2
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+administrator@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (2, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (2, 'Beograd', 'Serbia', 35, 564, 13, 'Bulevar evrope', 'isaprojectftn+administrator@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Admin', 'Admin', '030345');

-- Cottage owner milan
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+milanCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (3, 4);
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (3, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+milanCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'milan', 'milanovic', '030345', 'imao sam zelju');
insert into cottage (name, description, behaviour_rules, price, owner_id, city, country, latitude, longitude, number, street, average_grade)
values ('Stan', 'Lep stan', 'zabranjeno glasna muzika', 1000, 3, 'Zlatibor', 'Serbia', 11, 13, 33, 'Cara Dusana', null);


-- Cottage owner petar
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+petarCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (4, 4);
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (4, 'Beograd', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+petarCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'petar', 'petrovic', '030345', 'imao sam zelju da iznamljujem sobe');
insert into cottage (name, description, behaviour_rules, price, owner_id, city, country, latitude, longitude, number, street, average_grade)
values ('Kuca', 'mala kuca', 'hrana', 500, 4, 'Kopaonik', 'Serbia', 11, 13, 33, 'Cara Lazara', null);


-- Instructor Stefan
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+stefanI@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (5, 5);
insert into instructor (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (5, 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'isaprojectftn+stefanI@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Stefan', 'Stefanovic', '030345', 'Hoho instruktor');
insert into adventure (name, city, country, latitude, longitude, number, street, description, biography, max_number_of_people, behaviour_rules, terms_of_termination, instrucotr_id)
values ('Super Pecanje', 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'Pecacemo Ribe', 'Odlican Pecaros', 4, 'Nema skakanja u vodu', 'FREE', 5);

-- Boat owner Nikola
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+nikolaBO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (6, 2);
insert into boat_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (6, 'Zrenjanin', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+nikolaBO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Nikola', 'Nikolic', '030346', 'imao sam zelju');
insert into boat (city, country, latitude, longitude, number, street, behaviour_rules, capacity, engine_number, engine_power, length, max_speed, name, promotional_description, type, owner_id,reservation_cancellation_conditions)
values ('Beograd', 'Serbia', 11, 13, 33, 'Knez Mihajlova', 'Nema brze voznje', 3, '123', 100, 50, 70, 'Laser 16', 'Najbolji brod', 'Jedrilica', 6, 'FREE');

-- Client Mirija
insert into USERS (username, password, last_password_reset_date, enabled)
values ('isaprojectftn+marijaC@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true);   -- password: pass
insert into user_role (user_id, role_id)
values (7, 3);
insert into client(id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (7, 'Novi Sad', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+marijaC@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Marija', 'Maric', '030348');


insert into navigational_equipment (name)
values ('GPS');

insert into boat_navigation_equipment(boat_id, navigational_equipment_id)
values (1,1);

insert into item_price(description, name, price)
values ('euros per day','Wi-Fi', 10.0);

insert into boat_additional_services(boat_id, additional_service_id)
values (1,1);  

insert into additional_equipment(name)
values ('stap');
insert into additional_equipment(name)
values ('mreza');

insert into adventure_additional_equipment(adventure_id, additional_equipment_id)
values (1,1);

insert into adventure_additional_services(adventure_id, additional_service_id)
values (1,1);

insert into cottage_additional_services(cottage_id, additional_service_id)
values (1,1);











