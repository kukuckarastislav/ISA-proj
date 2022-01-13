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


-- aditional service tj itemPrice klasa u modelu
insert into item_price(description, name, price)
values ('Have fun with 5G','Wi-Fi', 10.0);
insert into item_price(description, name, price)
values ('Be cool','Air Condition', 15.0);
insert into item_price(description, name, price)
values ('Instructor will sing to you','Singing', 7.0);
insert into item_price(description, name, price)
values ('Food included', 'Food', 20.0);
insert into item_price(description, name, price)
values ('Parking for your car','Parking', 25.0);


-- Admin
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+admin@gmail.com','$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG','2022-01-03 21:02:17.307', true, true);   -- password: admin
insert into user_role (user_id, role_id)
values (1, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (1, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+admin@gmail.com', '$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG', 'Admin', 'Admin', '030345');

-- Admin2
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+administrator@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true);   -- password: pass
insert into user_role (user_id, role_id)
values (2, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (2, 'Beograd', 'Serbia', 35, 564, 13, 'Bulevar evrope', 'isaprojectftn+administrator@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Admin', 'Admin', '030345');

-- Client Mirija
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+marijaC@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true);   -- password: pass
insert into user_role (user_id, role_id)
values (3, 3);
insert into client(id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (3, 'Novi Sad', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+marijaC@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Marija', 'Maric', '030348');

-- Cottage owner milan
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+milanCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', false, false);   -- password: pass
insert into user_role (user_id, role_id)
values (4, 4);
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (4, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+milanCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'milan', 'milanovic', '030345', 'imao sam zelju');
insert into cottage (name, description, behaviour_rules, price,price_type, owner_id, city, country, latitude, longitude, number, street, average_grade)
values ('Stan', 'Lep stan', 'zabranjeno glasna muzika', 1000,'PER_DAY', 4, 'Zlatibor', 'Serbia', 11, 13, 33, 'Cara Dusana', 0);


-- Cottage owner petar
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+petarCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true);   -- password: pass
insert into user_role (user_id, role_id)
values (5, 4);
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (5, 'Beograd', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+petarCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'petar', 'petrovic', '030345', 'imao sam zelju da iznamljujem sobe');
insert into cottage (name, description, behaviour_rules, price, price_type, owner_id, city, country, latitude, longitude, number, street, average_grade)
values ('Kuca', 'mala kuca', 'hrana', 500, 'PER_DAY', 5, 'Kopaonik', 'Serbia', 11, 13, 33, 'Cara Lazara', 0);

insert into cottage_terms(cottage_id, term_availability, start_time, end_time)
values (2, 'AVAILABILE', '2022-03-07T8:00:06.235098700', '2022-03-20T8:00:06.235098700');
insert into cottage_reservation(start_time, end_time, status_of_reservation, cottage_id, client_id)
values ('2022-03-11T8:00:06.235098700', '2022-03-15T12:00:06.235098700', 'ACTIVE', 2, 3); -- cottage Kuca ima rezervaciju sa clientom: Marijom
insert into cottage_reservation_additional_services(cottage_reservation_id, additional_service_id)
values (1,3);
insert into cottage_reservation_additional_services(cottage_reservation_id, additional_service_id)
values (1,4);
insert into cottage_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, cottage_id)
values ('Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 4, 100, '2022-03-11T14:00:06.235098700', '2022-03-11T16:00:06.235098700', 2);
insert into cottage_fast_res_history(status_of_fast_reservation, client_id, cottage_fast_res_id)
values ('TAKEN', 3, 1);



-- Instructor Stefan
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+stefanI@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true);   -- password: pass
insert into user_role (user_id, role_id)
values (6, 5);
insert into instructor (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation, average_grade)
values (6, 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'isaprojectftn+stefanI@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Stefan', 'Stefanovic', '030345', 'Hoho instruktor',0);
insert into adventure (name, city, country, latitude, longitude, number, street, price, price_type, description, biography, max_number_of_people, behaviour_rules, reservation_cancellation_conditions, instructor_id, average_grade)
values ('Penjanje na planinu', 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 5,'PER_HOUR','Pecacemo Ribe', 'Odlican Pecaros', 4, 'Nema skakanja u vodu', 'FREE', 6, 0);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (1,1);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (1,3);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (1,4);
insert into entity_image (name, path)
values ('img1.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Penjanje_na_planinu/img1.jpg');
insert into entity_image (name, path)
values ('img2.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Penjanje_na_planinu/img2.jpg');
insert into entity_image (name, path)
values ('img3.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Penjanje_na_planinu/img3.jpg');
insert into adventure_images (adventure_id, images_id)
values (1, 1);
insert into adventure_images (adventure_id, images_id)
values (1, 2);
insert into adventure_images (adventure_id, images_id)
values (1, 3);
insert into adventure (name, city, country, latitude, longitude, number, street, price, price_type, description, biography, max_number_of_people, behaviour_rules, reservation_cancellation_conditions, instructor_id, average_grade)
values ('Krstarenje', 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 5,'PER_HOUR' ,'Pecacemo Ribe', 'Odlican Pecaros', 4, 'Nema skakanja u vodu', 'FREE', 6, 0);
insert into entity_image (name, path)
values ('img1.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Krstarenje/img1.jpg');
insert into entity_image (name, path)
values ('img2.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Krstarenje/img2.jpg');
insert into adventure_images (adventure_id, images_id)
values (2, 4);
insert into adventure_images (adventure_id, images_id)
values (2, 5);
insert into instructor_terms(instructor_id, term_availability, start_time, end_time)
values (6, 'AVAILABILE', '2022-03-05T8:00:06.235098700', '2022-03-25T8:00:06.235098700');
insert into instructor_reservation(start_time, end_time, status_of_reservation, adventure_id, client_id)
values ('2022-03-10T8:00:06.235098700', '2022-03-10T12:00:06.235098700', 'ACTIVE', 1, 3); -- instruktor Stefan ima adventuru:"Penjanje na planinu" sa clientom: Marijom
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (1,3);
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (1,4);
insert into instructor_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, adventure_id)
values ('Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 4, 100, '2022-03-11T14:00:06.235098700', '2022-03-11T16:00:06.235098700', 2);
insert into ins_fast_res_history(status_of_fast_reservation, client_id, ins_fast_res_id)
values ('TAKEN', 3, 1);


-- cottage owner petar, izvuceni entity_image da se gore kod stefana ne bi menjalo
insert into entity_image (name, path)
values ('img1.jpg', 'images/CottageOwners/isaprojectftn+petarCO@gmail.com/Kuca/img1.jpg');
insert into cottage_images (cottage_id, images_id)
values (2, 6);

-- Boat owner Nikola
insert into USERS (username, password, last_password_reset_date, enabled, approved)
values ('isaprojectftn+nikolaBO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true);   -- password: pass
insert into user_role (user_id, role_id)
values (7, 2);
insert into boat_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (7, 'Zrenjanin', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+nikolaBO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Nikola', 'Nikolic', '030346', 'imao sam zelju');
insert into boat (city, country, latitude, longitude, number, street,price, price_type, behaviour_rules, capacity, engine_number, engine_power, length, max_speed, name, promotional_description, type, owner_id,reservation_cancellation_conditions)
values ('Beograd', 'Serbia', 11, 13, 33, 'Knez Mihajlova', 10, 'PER_HOUR', 'Nema brze voznje', 3, '123', 100, 50, 70, 'Laser 16', 'Najbolji brod', 'Jedrilica', 7, 'FREE');



insert into navigational_equipment (name)
values ('GPS');

insert into boat_navigation_equipment(boat_id, navigational_equipment_id)
values (1,1);



insert into boat_additional_services(boat_id, additional_service_id)
values (1,1);  

insert into additional_equipment(name)
values ('stap');
insert into additional_equipment(name)
values ('mreza');

insert into adventure_additional_equipment(adventure_id, additional_equipment_id)
values (1,1);



insert into cottage_additional_services(cottage_id, additional_service_id)
values (1,1);







