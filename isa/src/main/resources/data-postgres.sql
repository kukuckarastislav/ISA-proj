
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (1, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+1@gmail.com', 'pass', 'milan', 'milanovic', '030345', 'imao sam zelju');

insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (2, 'Beograd', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+2@gmail.com', 'pass', 'milan', 'milanovic', '030345', 'imao sam zelju');

insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (3, 'Backi Petrovac', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+3@gmail.com', 'pass', 'Rastisalv', 'Kukucka', '030345', 'imao sam zelju');

insert into instructor (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (4, 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'isaprojectftn+4@gmail.com', 'pass', 'Ivan', 'Citanov', '030345', 'Hoho instruktor');

insert into boat_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (5, 'Zrenjanin', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+5@gmail.com', 'pass', 'Nikola', 'Nikolic', '030346', 'imao sam zelju');

insert into boat (city, country, latitude, longitude, number, street, behaviour_rules, capacity, engine_number, engine_power, length, max_speed, name, promotional_description, type, owner_id,reservation_cancellation_conditions) 
values ('Beograd', 'Serbia', 11, 13, 33, 'Knez Mihajlova', 'Nema brze voznje', 3, '123', 100, 50, 70, 'Laser 16', 'Najbolji brod', 'Jedrilica', 5, 'FREE');

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


insert into cottage (name, description, behaviour_rules, price, owner_id, city, country, latitude, longitude, number, street, average_grade) values ('Stan', 'Lep stan', 'zabranjeno glasna muzika', 1000, 1, 'Zlatibor', 'Serbia', 11, 13, 33, 'Cara Dusana', null);
insert into cottage (name, description, behaviour_rules, price, owner_id, city, country, latitude, longitude, number, street, average_grade) values ('Kuca', 'mala kuca', 'hrana', 500, 1, 'Kopaonik', 'Serbia', 11, 13, 33, 'Cara Lazara', null);

insert into adventure (name, city, country, latitude, longitude, number, street, description, biography, max_number_of_people, behaviour_rules, terms_of_termination, instrucotr_id)
values ('Super Pecanje', 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'Pecacemo Ribe', 'Odlican Pivnicki Pecaros', 4, 'Nema skakanja u vodu', 'Zadrzavam', 4);


insert into adventure_additional_equipment(adventure_id, additional_equipment_id)
values (1,1);

insert into adventure_additional_services(adventure_id, additional_service_id)
values (1,1);

insert into cottage_additional_services(cottage_id, additional_service_id)
values (1,1);

insert into client(id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (6, 'Novi Sad', 'Serbia', 11, 13, 33, 'Zmaj Jovina', 'isaprojectftn+6@gmail.com', 'pass', 'Marija', 'Maric', '030348');

insert into admin(id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number)
values (7, 'Nis', 'Serbia', 11, 13, 33, 'Cara Stefana', 'isaprojectftn+7@gmail.com', 'pass', 'Admin', 'Adminovic', '030349');

