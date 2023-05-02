-- patients
INSERT INTO patients (id, name, uic, gp_uic, deleted)
VALUES (1, 'Petar', '000001', null, false),
       (2, 'Georgi', '000002', null, false),
       (3, 'Dimitar', '000003', null, false),
       (4, 'Stefan', '000004', null, false),
       (5, 'Ivan', '000005', null, false);

-- insurance history
INSERT INTO insurances(id, start_date, end_date)
VALUES (1, '2020-01-02', '2023-01-01');

INSERT INTO patients_insurances(patient_id, insurance_id)
VALUES (1, 1);
