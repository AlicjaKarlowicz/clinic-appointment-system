INSERT INTO specializations(id,specialization) VALUES
(1,'Cardiologist'),
(2,'Dentist'),
(3,'Dermatologist'),
(4,'Neurologist'),
(5,'Endocrinologist'),
(6,'Gynaecologist'),
(7,'Orthopedist'),
(8,'Internist'),
(9,'Pediatrician'),
(10,'ENT specialist')
;

INSERT INTO workplaces(workplace_name) VALUES
('Warsaw'),
('Cracow')
;

INSERT INTO doctors(doctor_name,specialization_id) VALUES
('Dr. Z. Burska',6),
('Dr G. House',4),
('Dr Oetker',9),
('Dr Lubicz',8),
('Dr P. Quinn',2),
('Dr D. Dre',10),
('Dr H. Lecter',1)
;


INSERT INTO appointments(doctor_id, workplace_id,app_date,start_time,status) VALUES
(1,1,curdate(),DATE_ADD(FROM_UNIXTIME(FLOOR(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 45 MINUTE),0),
(1,1,curdate(),FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600),0),
(1,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 15 MINUTE),0),
(1,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 30 MINUTE),0),
(3,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 20 MINUTE),0),
(3,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 40 MINUTE),0),
(2,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 10 MINUTE),0),
(2,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 20 MINUTE),0),
(2,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 30 MINUTE),0),
(2,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 40 MINUTE),0),
(4,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 10 MINUTE),0),
(4,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 30 MINUTE),0),
(5,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 15 MINUTE),0),
(5,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 30 MINUTE),0),
(6,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 15 MINUTE),0),
(6,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 30 MINUTE),0),
(6,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 45 MINUTE),0),
(7,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 25 MINUTE),0),
(7,1,curdate(),DATE_ADD(FROM_UNIXTIME(CEIL(UNIX_TIMESTAMP(now()) / 3600) * 3600), INTERVAL 45 MINUTE),0);

