INSERT INTO component(cmp_created_at, cmp_name, measure, base_buy_height, base_buy_width, base_buy_amount, base_buy_paid_value) VALUES
	(now()::timestamp, 'Oxford', 'CM2', 100.00, 150.00, 0.00, 8.90),
	(now()::timestamp, 'Zipper', 'CM', 0.00, 0.00, 500.00, 6.90),
	(now()::timestamp, 'Cursor', 'UNITY', 0.00, 0.00, 50.00, 13.50),
	(now()::timestamp, 'Saco Celofane 20x29cm', 'UNITY', 0.00, 0.00, 50.00, 11.50),
	(now()::timestamp, 'Papel Offset A4 90g', 'UNITY', 0.00, 0.00, 500.00, 50.00),
	(now()::timestamp, 'Nylon 600', 'CM2', 500.00, 150.00, 0.00, 74.50),
	(now()::timestamp, 'Bagun Fosco', 'CM2', 200.00, 140.00, 0.00, 27.80),
	(now()::timestamp, 'Sacola Boca de Palhaço 30x40cm', 'UNITY', 0.00, 0.00, 100.00, 33.92);

INSERT INTO product(pdt_name, pdt_created_at, status, price, production_duration_in_minutes) VALUES
('Necessaire P', now()::timestamp, 'CREATED', 15.00, 90);

INSERT INTO product_component(pco_pdt_id, pco_cmp_id, height, width, amount) VALUES
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Oxford'), 30.00, 21.00, 0.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Zipper'), 0.00, 0.00, 30.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Cursor'), 0.00, 0.00, 1.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Saco Celofane 20x29cm'), 0.00, 0.00, 1.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Papel Offset A4 90g'), 0.00, 0.00, 1.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Nylon 600'), 30.00, 21.00, 0.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Bagun Fosco'), 30.00, 21.00, 0.00),
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Necessaire P'), (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Sacola Boca de Palhaço 30x40cm'), 0.00, 0.00, 1.00);

INSERT INTO CUSTOMER (phone, ctm_name, ctm_created_at) VALUES
('31985971976', 'Gustavo', now()),
('22222222222', 'Maria', now()),
('33333333333', 'José', now());

INSERT INTO request(rqt_created_at, rqt_ctm_id, due_date, status) VALUES
(now()::timestamp, (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo'), now()::date, 'ESTIMATE');

INSERT INTO request_product(
	rpd_rqt_id,
	rpd_pdt_id,
	rpd_created_at,
	unitary_value,
	amount,
	calculated_production_cost,
	declared_production_cost,
	product_document) VALUES
(
	(SELECT rqt_id FROM request WHERE rqt_ctm_id = (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo') ORDER BY rqt_created_at LIMIT 1),
	(SELECT pdt_id FROM product WHERE pdt_name = 'Necessaire P'),
	now(),
	10.00,
	3.00,
	2.98,
	2.98,
	'{"name": "Necessaire P", "price": 15.00, "components": [{"cost": 0.37, "width": 21.00, "amount": 630.00, "height": 30.00, "measure": "CM2", "component": "Oxford", "paid_value": 8.90, "boughtWidth": 150.00, "boughtAmount": 15000.00, "boughtHeight": 100.00}, {"cost": 0.41, "width": 0.00, "amount": 30.00, "height": 0.00, "measure": "CM", "component": "Zipper", "paid_value": 6.90, "boughtWidth": 0.00, "boughtAmount": 500.00, "boughtHeight": 0.00}, {"cost": 0.27, "width": 0.00, "amount": 1.00, "height": 0.00, "measure": "UNITY", "component": "Cursor", "paid_value": 13.50, "boughtWidth": 0.00, "boughtAmount": 50.00, "boughtHeight": 0.00}, {"cost": 0.23, "width": 0.00, "amount": 1.00, "height": 0.00, "measure": "UNITY", "component": "Saco Celofane 20x29cm", "paid_value": 11.50, "boughtWidth": 0.00, "boughtAmount": 50.00, "boughtHeight": 0.00}, {"cost": 0.10, "width": 0.00, "amount": 1.00, "height": 0.00, "measure": "UNITY", "component": "Papel Offset A4 90g", "paid_value": 50.00, "boughtWidth": 0.00, "boughtAmount": 500.00, "boughtHeight": 0.00}, {"cost": 0.63, "width": 21.00, "amount": 630.00, "height": 30.00, "measure": "CM2", "component": "Nylon 600", "paid_value": 74.50, "boughtWidth": 150.00, "boughtAmount": 75000.00, "boughtHeight": 500.00}, {"cost": 0.63, "width": 21.00, "amount": 630.00, "height": 30.00, "measure": "CM2", "component": "Bagun Fosco", "paid_value": 27.80, "boughtWidth": 140.00, "boughtAmount": 28000.00, "boughtHeight": 200.00}, {"cost": 0.34, "width": 0.00, "amount": 1.00, "height": 0.00, "measure": "UNITY", "component": "Sacola Boca de Palhaço 30x40cm", "paid_value": 33.92, "boughtWidth": 0.00, "boughtAmount": 100.00, "boughtHeight": 0.00}]}'::jsonb
);