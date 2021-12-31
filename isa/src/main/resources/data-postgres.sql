
insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (1, 'Novi Sad', 'Serbia', 34, 54, 10, 'Bulevar evrope', 'isaprojectftn+1@gmail.com', 'pass', 'milan', 'milanovic', '030345', 'imao sam zelju');

insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (2, 'Beograd', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+2@gmail.com', 'pass', 'milan', 'milanovic', '030345', 'imao sam zelju');

insert into cottage_owner (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation) 
values (3, 'Backi Petrovac', 'Serbia', 14, 24, 35, 'Knez', 'isaprojectftn+3@gmail.com', 'pass', 'Rastisalv', 'Kukucka', '030345', 'imao sam zelju');

insert into instructor (id, city, country, latitude, longitude, number, street, email, password, first_name, last_name, phone_number, registration_motivation)
values (4, 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'isaprojectftn+4@gmail.com', 'pass', 'Ivan', 'Citanov', '030345', 'Hoho instruktor');



insert into cottage (name, description, behaviour_rules, price, owner_id) values ('Stan', 'Lep stan', 'zabranjeno glasna muzika', 1000, 1);
insert into cottage (name, description, behaviour_rules, price, owner_id) values ('Kuca', 'mala kuca', 'hrana', 500, 1);

insert into adventure (name, city, country, latitude, longitude, number, street, description, biography, max_number_of_people, behaviour_rules, terms_of_termination, instrucotr_id)
values ('Super Pecanje', 'Bratislava', 'Slovakia', 14, 24, 35, 'Miskova', 'Pecacemo Ribe', 'Odlican Pivnicki Pecaros', 4, 'Nema skakanja u vodu', 'Zadrzavam', 4);

