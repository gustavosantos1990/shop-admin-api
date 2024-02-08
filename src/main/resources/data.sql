INSERT INTO component(cmp_created_at, cmp_name, measure, base_buy_height, base_buy_width, base_buy_amount, base_buy_paid_value) VALUES
	(now()::timestamp, 'Papel Offset A4 75g', 'UNITY', 0.00, 0.00, 500, 28.90),
	(now()::timestamp, 'Papel Offset A4 90g', 'UNITY', 0.00, 0.00, 500, 34.90),
	(now()::timestamp, 'Papel Offset A4 120g', 'UNITY', 0.00, 0.00, 500, 40.90),
	(now()::timestamp, 'Papel Offset A4 180g', 'UNITY', 0.00, 0.00, 500, 42.90);

/*INSERT INTO component_acquisition(cac_cmp_id, height, width, amount, paid_value) VALUES
((SELECT cmp_id FROM component WHERE cmp_name = 'Papel Offset A4 75g'), 0.00, 0.00, 500, 29.00),
((SELECT cmp_id FROM component WHERE cmp_name = 'Papel Offset A4 90g'), 0.00, 0.00, 500, 35.00),
((SELECT cmp_id FROM component WHERE cmp_name = 'Papel Offset A4 120g'), 0.00, 0.00, 500, 43.00),
((SELECT cmp_id FROM component WHERE cmp_name = 'Papel Offset A4 180g'), 0.00, 0.00, 500, 50.00);

INSERT INTO component_reference_acquisition(cra_cmp_id, cra_cac_id) VALUES((SELECT cmp_id FROM component WHERE cmp_name = 'Papel Offset A4 90g'), 1);

INSERT INTO product(pdt_name) VALUES
('Agenda 1 dia por página'),
('Agenda 2 dias por página'),
('Planner'),
('Caderneta de Vacina');

INSERT INTO CUSTOMER (phone, ctm_name, ctm_created_at) VALUES
('11111111111', 'João', now()),
('22222222222', 'Maria', now()),
('33333333333', 'José', now());

INSERT INTO request(rqt_created_at, rqt_ctm_id, due_date, status) VALUES
(now()::timestamp, (SELECT ctm_id FROM customer WHERE ctm_name = 'João'), now()::date, 'CREATED');

INSERT INTO request_product(
	rpd_rqt_id,
	rpd_pdt_id,
	rpd_created_at,
	unitary_value,
	amount,
	calculated_production_cost,
	declared_production_cost, components) VALUES
(
	(SELECT rqt_id FROM request WHERE rqt_ctm_id = (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo')),
	(SELECT pdt_id FROM product WHERE pdt_name = 'Planner'),
	now(),
	10.00,
	3,
	2.00,
	2.00,
	'[]'::jsonb
);

INSERT INTO product_version(pvr_pdt_id, pvr_version) VALUES
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Caderneta de Vacina'), '30/01/2024-A');

INSERT INTO product_version_info(pvi_pvr_pdt_id, pvi_pvr_version, pvi_created_at, pvi_name, price, production_duration_in_minutes) VALUES
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Caderneta de Vacina'), '30/01/2024-A', now()::timestamp, 'Caderneta de Vacina', 99.99, 40);

INSERT INTO version_component(vco_pvi_pvr_pdt_id, vco_pvi_pvr_version, vco_cmp_id, height, width, amount) VALUES
((SELECT pdt_id FROM product WHERE pdt_name LIKE 'Caderneta de Vacina'), '30/01/2024-A', (SELECT cmp_id FROM component WHERE cmp_name LIKE 'Papel Offset A4 75g'), 0.0, 0.0, 100);
*/
