-- healthcare agency
INSERT INTO pricing_hisotry (id, issue_no, appointment_fees, from_date)
VALUES (1, '1', 10.00, '2020-01-01');

-- diagnoses
INSERT INTO diagnoses(id, name, deleted)
VALUES (1, 'HEALTHY', false),
       (2, 'FLU', false),
       (3, 'PNEUMONIA', false),
       (4, 'COLD', false);


INSERT INTO appointments(id, date, description, uic, doctor_uic, patient_uic, price_history_id)
VALUES (1, '2022-01-01', 'Generic description', '2301010001', '0001', '000001', 1),
       (2, '2022-01-04', 'Generic description', '2301040001', '0003', '000002', 1),
       (3, '2022-01-03', 'Generic description', '2301030001', '0005', '000004', 1),
       (4, '2022-01-02', 'Generic description', '2301020001', '0002', '000003', 1),
       (5, '2022-01-07', 'Generic description', '2301070001', '0004', '000005', 1),
       (6, '2022-01-01', 'Generic description', '2301010002', '0001', '000002', 1),
       (7, '2022-01-01', 'Generic description', '2301010003', '0001', '000001', 1);


INSERT INTO appointments_diagnoses(appointment_id, diagnose_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 4),
       (4, 3),
       (5, 1),
       (6, 1),
       (7, 1);
