INSERT INTO component(cmp_created_at, cmp_name, measure, base_buy_height, base_buy_width, base_buy_amount, base_buy_paid_value) VALUES
	(now()::timestamp, 'Oxford', 'CM2', 100.00, 150.00, 0.00, 8.90),
	(now()::timestamp, 'Zipper', 'CM', 0.00, 0.00, 500.00, 6.90),
	(now()::timestamp, 'Cursor', 'UNITY', 0.00, 0.00, 50.00, 13.50),
	(now()::timestamp, 'Saco Celoface 20x29cm', 'UNITY', 0.00, 0.00, 50.00, 11.50),
	(now()::timestamp, 'Papel Offset A4 90g', 'UNITY', 0.00, 0.00, 500.00, 50.00),
	(now()::timestamp, 'Nylon 600', 'CM2', 500.00, 150.00, 0.00, 74.50),
	(now()::timestamp, 'Bagun Fosco', 'CM2', 200.00, 100.00, 0.00, 27.80);

INSERT INTO product(pdt_name, pdt_created_at, status, price, production_duration_in_minutes) VALUES
('Necessaire P', now()::timestamp, 'CREATED', 15.00, 90);

INSERT INTO CUSTOMER (phone, ctm_name, ctm_created_at) VALUES
('31985971976', 'Gustavo', now()),
('22222222222', 'Maria', now()),
('33333333333', 'José', now());

INSERT INTO request(rqt_created_at, rqt_ctm_id, due_date, status) VALUES
(now()::timestamp, (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo'), now()::date, 'CREATED');

INSERT INTO request_product(
	rpd_rqt_id,
	rpd_pdt_id,
	rpd_created_at,
	unitary_value,
	amount,
	calculated_production_cost,
	declared_production_cost, document) VALUES
(
	(SELECT rqt_id FROM request WHERE rqt_ctm_id = (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo') ORDER BY rqt_created_at LIMIT 1),
	(SELECT pdt_id FROM product WHERE pdt_name = 'Necessaire P'),
	now(),
	10.00,
	3,
	2.00,
	2.00,
	'{"name":"Necessaire P"}'::jsonb
);