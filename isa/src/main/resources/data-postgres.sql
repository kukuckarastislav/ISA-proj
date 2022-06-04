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
insert into item_price(description, name, price)
values ('Owner will grace you with their presence','Captain', 0.0);


-- Admin
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+admin@gmail.com','$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG','2022-01-03 21:02:17.307', true, true, true);   -- password: admin
insert into user_role (user_id, role_id)
values (1, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (1, 'Novi Sad', 'Serbia', 45.2471591374095, 19.818258855810267, 10, 'Bulevar evrope', 'isaprojectftn+admin@gmail.com', '$2a$10$3xqAvzqpGKgBJrzIzpBQf.lm3RaR3R0.SAZsE6JovkCKv.9.9QgPG', 'Admin', 'Admin', '030345');

-- Admin2
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+administrator@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true, false);   -- password: pass
insert into user_role (user_id, role_id)
values (2, 1);
insert into admin (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (2, 'Beograd', 'Serbia', 44.77609210891258, 20.549114382934537, 13, 'Bulevar evrope', 'isaprojectftn+administrator@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Admin', 'Admin', '030345');

-- Client Mirija
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+marijaC@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true,false);   -- password: pass
insert into user_role (user_id, role_id)
values (3, 3);
insert into client(id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (3, 0, 0, 'Novi Sad', 'Serbia', 45.257120944214186, 19.847844898140064, 33, 'Zmaj Jovina', 'isaprojectftn+marijaC@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Marija', 'Maric', '030348');

-- Cottage owner milan
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+milanCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', false, false,false);   -- password: pass
insert into user_role (user_id, role_id)
values (4, 4);
insert into cottage_owner (id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation, average_grade)
values (4,0,0, 'Novi Sad', 'Serbia', 45.247249780971536, 19.818205211634332, 10, 'Bulevar evrope', 'isaprojectftn+milanCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'milan', 'milanovic', '030345', 'imao sam zelju', 0);
insert into cottage (name, description, behaviour_rules, price,price_type, owner_id, city, country, latitude, longitude, number, street, average_grade, capacity)
values ('Stan', 'Lep stan', 'zabranjeno glasna muzika', 1000,'PER_DAY', 4, 'Zlatibor', 'Serbia', 43.73244496341901, 19.69687467198646, 33, 'Cara Dusana', 0, 5);


-- Cottage owner petar
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+petarCO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true,false);   -- password: pass
insert into user_role (user_id, role_id)
values (5, 4);
insert into cottage_owner (id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation, average_grade)
values (5,0,0, 'Beograd', 'Serbia', 44.817766374150516, 20.456455013470055, 35, 'Knez', 'isaprojectftn+petarCO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'petar', 'petrovic', '030345', 'imao sam zelju da iznamljujem sobe', 0);
insert into cottage (name, description, behaviour_rules, price, price_type, owner_id, city, country, latitude, longitude, number, street, average_grade, capacity)
values ('Kuca', 'mala kuca', 'hrana', 500, 'PER_DAY', 5, 'Kopaonik', 'Serbia', 43.2760831163471, 20.76000198459797, 33, 'Cara Lazara', 0, 4);

insert into cottage_terms(cottage_id, term_availability, start_time, end_time)
values (2, 'AVAILABILE', '2022-04-05T8:00:06.235098700', '2022-07-25T8:00:06.235098700');
insert into cottage_reservation(start_time, end_time, status_of_reservation, cottage_id, client_id,price, is_revised, is_complained_of, income)
values ('2022-03-11T8:00:06.235098700', '2022-03-15T12:00:06.235098700', 'ACTIVE', 2, 3,170, false, false, 119); -- cottage Kuca ima rezervaciju sa clientom: Marijom
insert into cottage_reservation_additional_services(cottage_reservation_id, additional_service_id)
values (1,3);
insert into cottage_reservation_additional_services(cottage_reservation_id, additional_service_id)
values (1,4);
insert into cottage_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, cottage_id)
values ('Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 4, 100, '2022-07-11T14:00:06.235098700', '2022-07-12T16:00:06.235098700', 2);
insert into cottage_fast_res_history(status_of_fast_reservation, client_id, cottage_fast_res_id, is_revised, is_complained_of, price, income)
values ('TAKEN', 3, 1, false, false, 200, 140);



-- Instructor Stefan
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+stefanI@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true,false);   -- password: pass
insert into user_role (user_id, role_id)
values (6, 5);
insert into instructor (id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, biography, registration_motivation, average_grade)
values (6, 0,0, 'Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 'isaprojectftn+stefanI@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Stefan', 'Stefanovic', '030345', 'moja biografija, ja sam super lik', 'Hoho instruktor',0);
insert into adventure (name, city, country, latitude, longitude, number, street, price, price_type, description, biography, max_number_of_people, behaviour_rules, reservation_cancellation_conditions, instructor_id, average_grade)
values ('Penjanje na planinu', 'Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 5,'PER_HOUR','Pecacemo Ribe', 'moja biografija, ja sam super lik', 4, 'Nema skakanja u vodu', 'FREE', 6, 0);
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
values ('Krstarenje', 'Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 5,'PER_HOUR' ,'Pecacemo Ribe', 'moja biografija, ja sam super lik', 4, 'Nema skakanja u vodu', 'FREE', 6, 0);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (2,1);
insert into entity_image (name, path)
values ('img1.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Krstarenje/img1.jpg');
insert into entity_image (name, path)
values ('img2.jpg', 'images/Instructors/isaprojectftn+stefanI@gmail.com/Krstarenje/img2.jpg');
insert into adventure_images (adventure_id, images_id)
values (2, 4);
insert into adventure_images (adventure_id, images_id)
values (2, 5);
insert into instructor_terms(instructor_id, term_availability, start_time, end_time)
values (6, 'AVAILABILE', '2022-04-05T8:00:06.235098700', '2022-07-25T8:00:06.235098700');
insert into instructor_terms(instructor_id, term_availability, start_time, end_time)
values (6, 'UNAVAILABLE', '2022-03-25T8:00:06.235098700', '2022-04-04T8:00:06.235098700');
--admin_respons_date, admin_response, admin_username
insert into report(client_email, client_showed_up, created_at, id_client, owner_email, reservation_id, reservation_type, sanction_client, status_of_report, term_type, text)
values ('isaprojectftn+marijaC@gmail.com', true, '2022-06-02 16:30:04.094904', 3, 'isaprojectftn+stefanI@gmail.com',1,2,true,0,1,'Nisam bas zadovoljan kako se marija ponasala na rezervaciji dajte joj penal');
--
insert into instructor_reservation(start_time, end_time, status_of_reservation, adventure_id, client_id, instructor_username,price, is_revised, is_complained_of, report_id, income)
values ('2022-03-10T8:00:06.235098700', '2022-03-10T12:00:06.235098700', 'ACTIVE', 1, 3, 'isaprojectftn+stefanI@gmail.com',170, false, false, 1, 119); -- instruktor Stefan ima adventuru:"Penjanje na planinu" sa clientom: Marijom
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (1,3);
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (1,4);
insert into instructor_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, adventure_id, instructor_username)
values ('Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 4, 5, '2022-07-11T14:00:06.235098700', '2022-07-11T16:00:06.235098700', 2, 'isaprojectftn+stefanI@gmail.com');
insert into ins_fast_res_history(status_of_fast_reservation, client_id, ins_fast_res_id, is_revised, is_complained_of, price, income)
values ('CANCELLED', 3, 1, false, false, 200, 140);
insert into instructor_fast_reservation_additional_services(instructor_fast_reservation_id,additional_service_id)
values (1,1);


-- cottage owner petar, izvuceni entity_image da se gore kod stefana ne bi menjalo
insert into entity_image (name, path)
values ('img1.jpg', 'images/CottageOwners/isaprojectftn+petarCO@gmail.com/Kuca/img1.jpg');
insert into cottage_images (cottage_id, images_id)
values (2, 6);

-- cottage owner Milan, slike
insert into entity_image (name, path)
values ('img1.jpg', 'images/CottageOwners/isaprojectftn+milanCO@gmail.com/Stan/img1.jpg');
insert into cottage_images (cottage_id, images_id)
values (1, 7);


-- Boat owner Nikola
insert into USERS (username, password, last_password_reset_date, enabled, approved,admin_ready)
values ('isaprojectftn+nikolaBO@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true,false);   -- password: pass
insert into user_role (user_id, role_id)
values (7, 2);
insert into boat_owner (id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation, average_grade)
values (7, 0,0,'Zrenjanin', 'Serbia', 45.37603402791083, 20.393630711637407, 33, 'Zmaj Jovina', 'isaprojectftn+nikolaBO@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Nikola', 'Nikolic', '030346', 'imao sam zelju', 0);
insert into boat (city, country, latitude, longitude, number, street,price, price_type, behaviour_rules, capacity, engine_number, engine_power, length, max_speed, name, promotional_description, type, owner_id,reservation_cancellation_conditions,average_grade)
values ('Beograd', 'Serbia', 44.81773309538738, 20.456885226964513, 33, 'Knez Mihajlova', 10, 'PER_HOUR', 'Nema brze voznje', 3, '123', 100, 50, 70, 'Laser 16', 'Najbolji brod', 'Jedrilica', 7, 'FREE',0);
insert into entity_image (name, path)
values ('img1.jpg', 'images/BoatOwners/isaprojectftn+nikolaBO@gmail.com/Laser_16/img1.jpg');
insert into boat_images(boat_id,images_id)
values (1,8);


insert into navigational_equipment (name)
values ('GPS');

insert into boat_navigation_equipment(boat_id, navigational_equipment_id)
values (1,1);

insert into boat_additional_services(boat_id, additional_service_id)
values (1,1);


insert into boat (city, country, latitude, longitude, number, street,price, price_type, behaviour_rules, capacity, engine_number, engine_power, length, max_speed, name, promotional_description, type, owner_id,reservation_cancellation_conditions,average_grade)
values ('Novi Sad', 'Serbia', 45.267136, 19.833549, 33, 'Vidovdanska', 20, 'PER_HOUR', 'Nema brze voznje', 5, '123', 100, 50, 70, 'Serenity', 'Oaza mira', 'Jahta', 7, 'FREE',0);
insert into entity_image (name, path)
values ('img1.jpg', 'images/BoatOwners/isaprojectftn+nikolaBO@gmail.com/Serenity/img1.jpg');
insert into boat_images(boat_id,images_id)
values (2,9);
insert into boat_navigation_equipment(boat_id, navigational_equipment_id)
values (2,1);
insert into boat_additional_services(boat_id, additional_service_id)
values (2,4);
insert into boat_terms(boat_id, term_availability, start_time, end_time)
values (2, 'AVAILABILE', '2022-04-05T8:00:06.235098700', '2022-07-25T8:00:06.235098700');
insert into boat_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, boat_id)
values ('Novi Sad', 'Serbia', 45.279115064645225, 19.833173111634995, 35, 'Vidovdanska', 4, 20, '2022-07-11T14:00:06.235098700', '2022-07-11T16:00:06.235098700', 2);
insert into boat_fast_reservation_additional_services(boat_fast_reservation_id,additional_service_id)
values (1,1);
insert into boat_fast_reservation_additional_services(boat_fast_reservation_id,additional_service_id)
values (1,6);




insert into additional_equipment(name)
values ('stap');
insert into additional_equipment(name)
values ('mreza');

insert into adventure_additional_equipment(adventure_id, additional_equipment_id)
values (1,1);



insert into cottage_additional_services(cottage_id, additional_service_id)
values (1,1);


insert into account_delete_request(id,  delete_request_status, reason, user_typeisa, username)
values (1, 'PENDING', 'Ne treba mi vise nalog', 'INSTRUCTOR', 'isaprojectftn+stefanI@gmail.com');
insert into account_delete_request(id, admin_response, admin_username, delete_request_status, reason, user_typeisa, username)
values (2, 'Ne mozete obrisat vas nalog', 'isaprojectftn+admin@gmail.com', 'REJECTED','i want to delete my acc', 'INSTRUCTOR','isaprojectftn+stefanI@gmail.com');
insert into account_delete_request(id, admin_response, admin_username, delete_request_status, reason, user_typeisa, username)
values (3, 'Odobreno Vam je brisanje', 'isaprojectftn+admin@gmail.com', 'APPROVED','i want to delete my acc', 'CLIENT','isaprojectftn+marijaC@gmail.com');


insert into revision(comment, created_at, grade, status_of_revision, user_id, admin_respons_date, admin_username) --admin_response_date, admin_username,
values ('Bilo je bas cool na ovoj avanturi', '2022-05-16 19:35:30.336347', 4, 2, 3, '2022-06-5 19:35:30.336347', 'isaprojectftn+admin@gmail.com');
insert into adventure_revisions (adventure_id, revisions_id)
values (1,1);

insert into revision(comment, created_at, grade, status_of_revision, user_id) --admin_response_date, admin_username,
values ('Instruktor je prava legenda jako zanimljiv lik brutalan', '2022-05-16 19:40:30.336347', 5, 0, 3);
insert into instructor_revisions (instructor_id, revisions_id)
values (6,2);

insert into revision(comment, created_at, grade, status_of_revision, user_id) --admin_response_date, admin_username,
values ('Kuca je bas luksuzna uzivao sam jako', '2022-05-16 19:36:30.336347', 5, 0, 3);
insert into cottage_revisions (cottage_id, revisions_id)
values (2,3);

insert into complaint(comment, created_at, entity_id, provider_type, provider_email, revision_type, status_of_complaint, user_email, id_reservation, is_fast_reservation, is_for_client)
values ('Ova avantura nije bila toliko zanimljiva', '2022-05-16 19:36:30.336347', 1,3,'isaprojectftn+stefanI@gmail.com', 1,1,'isaprojectftn+marijaC@gmail.com',0,false,false);
insert into adventure_complaints(adventure_id, complaints_id)
values (1,1);
insert into complaint(admin_email, admin_respons_date, admin_response, comment, created_at, entity_id, provider_type, provider_email, revision_type, status_of_complaint, user_email, id_reservation, is_fast_reservation, is_for_client)
values ('isaprojectftn+admin@gmail.com','2022-05-16 19:36:30.336347','Zao nam je zbog toga, imacemo u vidu ... :(','Instruktor mi nije dao jasne instrukcije kako da se penjem na planinu, skoro sam pao sa planine', '2022-05-16 19:36:30.336347', -1,3,'isaprojectftn+stefanI@gmail.com', 0,0,'isaprojectftn+marijaC@gmail.com',0,false,false);
insert into instructor_complaints(instructor_id, complaints_id)
values (6,2);

insert into complaint(comment, created_at, entity_id, provider_type, provider_email, revision_type, status_of_complaint, user_email, id_reservation, is_fast_reservation, is_for_client)
values ('Vikendica nije bila spremljena kad sam dosla, morala sam ja da cistim prvih sat vremena', '2022-05-16 19:36:30.336347', 2,1,'isaprojectftn+petarCO@gmail.com', 1,1,'isaprojectftn+marijaC@gmail.com',0,false, false);
insert into cottage_complaints(cottage_id, complaints_id)
values (2,3);
insert into complaint(comment, created_at, entity_id, provider_type, provider_email, revision_type, status_of_complaint, user_email, id_reservation, is_fast_reservation, is_for_client)
values ('Vlasnik vikendice pri mom odlasku je proveravao sve zivo kao da sam lopov', '2022-05-16 19:36:30.336347', -1,1,'isaprojectftn+petarCO@gmail.com', 0,1,'isaprojectftn+marijaC@gmail.com',0,false, false);
insert into cottage_owner_complaints(cottage_owner_id, complaints_id)
values (5,4);

insert into loyalty_settings(client_score_for_reservation, owner_score_for_reservation, minimum_score_forsilver, minimum_score_forgold, client_discount_percentagesilver, client_discount_percentagegold, owner_discount_percentagesilver, owner_discount_percentagegold, system_percentage)
values (30, 20, 120, 420, 5, 10, 5, 15, 20);

insert into complaint(comment, created_at, entity_id, provider_type, provider_email, revision_type, status_of_complaint, user_email, id_reservation, is_fast_reservation, is_for_client)
values ('Marija se nije lepo ponasala, vodila je u opasnost svoj i zivot ostalih ljudi', '2022-06-1 19:36:30.336347', -1,3,'isaprojectftn+stefanI@gmail.com', 0,1,'isaprojectftn+marijaC@gmail.com',1,false, true);
insert into client_complaints(client_id, complaints_id)
values (3,5);








--------------------------------------------------------------------------------------------------------------------------
-- Instructor Alex
insert into USERS (username, password, last_password_reset_date, enabled, approved, admin_ready)
values ('isaprojectftn+alexI@gmail.com','$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC','2022-01-03 21:02:17.307', true, true, false);   -- password: pass
insert into user_role (user_id, role_id)
values (8, 5);
insert into instructor (id, score, loyalty_type, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, biography, registration_motivation, average_grade)
values (8, 0,0, 'Novi Sad', 'Srbija', 45.26050908726751, 19.832872269305035, 35, 'Bulevar oslobodjenja', 'isaprojectftn+alexI@gmail.com', '$2a$10$OnaRYDYNzDYjx6iju8sK9eGMkmicGSnSPETdBqLKLYVRjyJSw5/QC', 'Alexandar', 'Lux', '030345', 'Odlican instrutktor, naucicu te svim cakama i vestinama, jedva cekam', 'To mi je San od malena, da ucim druge ',0);
insert into adventure (name, city, country, latitude, longitude, number, street, price, price_type, description, biography, max_number_of_people, behaviour_rules, reservation_cancellation_conditions, instructor_id, average_grade)
values ('Obilazak piramide', 'Aleksandrija', 'Egipt', 31.224646848528113, 29.951699984354544, 35, 'piro', 5,'PER_HOUR','Penjamo se na piramidu, budimo faraona', 'Odlican instrutktor, naucicu te svim cakama i vestinama, jedva cekam', 4, 'Nema kradje zlata, i cupova iz piramide', 'FREE', 8, 0);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (3,1);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (3,3);
insert into adventure_additional_services(adventure_id, additional_service_id)
values (3,4);
insert into entity_image (name, path)
values ('img1.jpg', 'images/Instructors/isaprojectftn+alexI@gmail.com/Obilazak_piramide/img1.jpg');
insert into entity_image (name, path)
values ('img2.jpg', 'images/Instructors/isaprojectftn+alexI@gmail.com/Obilazak_piramide/img2.jpg');
insert into entity_image (name, path)
values ('img3.jpg', 'images/Instructors/isaprojectftn+alexI@gmail.com/Obilazak_piramide/img3.jpg');
insert into adventure_images (adventure_id, images_id)
values (3, 10);
insert into adventure_images (adventure_id, images_id)
values (3, 11);
insert into adventure_images (adventure_id, images_id)
values (3, 12);
insert into instructor_terms(instructor_id, term_availability, start_time, end_time)
values (8, 'AVAILABILE', '2022-05-30T8:00:06.235098700', '2022-05-31T16:00:06.235098700');
insert into instructor_terms(instructor_id, term_availability, start_time, end_time)
values (8, 'UNAVAILABLE', '2022-06-1T0:00:14.235098700', '2022-06-03T23:00:06.235098700');
insert into instructor_reservation(start_time, end_time, status_of_reservation, adventure_id, client_id, instructor_username,price, is_revised, is_complained_of, income)
values ('2022-05-30T10:00:06.235098700', '2022-05-30T16:00:06.235098700', 'ACTIVE', 3, 3, 'isaprojectftn+alexI@gmail.com',170, false, false, 119);
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (2,3);
insert into instructor_reservation_additional_services(instructor_reservation_id, additional_service_id)
values (2,4);
--insert into instructor_fast_reservation(city, country, latitude, longitude, number, street, max_number_of_people, price, start_time, end_time, adventure_id, instructor_username)
--values ('Bratislava', 'Slovakia', 48.1524021968794, 17.102130827048573, 35, 'Miskova', 4, 5, '2022-07-11T14:00:06.235098700', '2022-07-11T16:00:06.235098700', 2, 'isaprojectftn+stefanI@gmail.com');
--insert into ins_fast_res_history(status_of_fast_reservation, client_id, ins_fast_res_id, is_revised, is_complained_of)
--values ('CANCELLED', 3, 1, false, false);
--insert into instructor_fast_reservation_additional_services(instructor_fast_reservation_id,additional_service_id)
--values (1,1);
--------------------------------------------------------------------------------------------------------------------------
