-- doctors
INSERT INTO doctors (id, birth_date, name, uic, deleted)
VALUES (1, '1974-01-01', 'Georgiev', '0001', false),
       (2, '1973-01-01', 'Petrov', '0002', false),
       (3, '1990-01-01', 'Dimitrov', '0003', false),
       (4, '1991-01-01', 'Ivanov', '0004', false),
       (5, '1984-01-01', 'Yordanov', '0005', false);

-- gp
INSERT INTO gps(id)
VALUES (1),
       (3);

-- specialties
INSERT INTO specialties(id, name, deleted)
VALUES (1, 'PEDIATRICS', false),
       (2, 'SURGERY', false),
       (3, 'CARDIOLOGY', false);

-- doctors_specialties
INSERT INTO doctors_specialties(doctor_id, specialty_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 3),
       (4, 1),
       (5, 2);
