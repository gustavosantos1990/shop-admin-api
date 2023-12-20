DROP TABLE IF EXISTS financial_movement, component, product_component, request_product, request, product, customer;

CREATE TABLE component (
    cmp_id varchar(10) DEFAULT substring(MD5(random()::text), 0, 11) PRIMARY KEY,
    cmp_created_at timestamp with time zone NOT NULL,
    cmp_deleted_at timestamp with time zone,
    cmp_name varchar NOT NULL,
    photo_address varchar,
	measure varchar NOT NULL,
	base_buy_height decimal NOT NULL,
	base_buy_width decimal NOT NULL,
	base_buy_amount decimal NOT NULL,
	base_buy_paid_value decimal NOT NULL,
    CONSTRAINT component_name_uk UNIQUE(cmp_name)
);

CREATE TABLE product (
    pdt_id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    pdt_created_at timestamp with time zone NOT NULL,
    pdt_deleted_at timestamp with time zone,
    pdt_name varchar NOT NULL,
    description varchar,
    price decimal NOT NULL,
    photo_address varchar,
    production_duration_in_minutes int NOT NULL,
    CONSTRAINT product_name_uk UNIQUE(pdt_name)
);

CREATE TABLE product_component (
    pco_pdt_id uuid REFERENCES product(pdt_id),
    pco_cmp_id varchar REFERENCES component(cmp_id),
	height decimal NOT NULL,
	width decimal NOT NULL,
    amount decimal NOT NULL,
    PRIMARY KEY(pco_pdt_id, pco_cmp_id)
);

CREATE TABLE customer (
	ctm_id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    ctm_created_at timestamp with time zone NOT NULL,
    phone varchar NOT NULL,
	ctm_name varchar NOT NULL,
    facebook_chat_number varchar,
    rating int,
    CONSTRAINT customer_phone_uk UNIQUE(phone)
);

CREATE TABLE request (
	rqt_id serial PRIMARY KEY,
    rqt_ctm_id uuid REFERENCES customer(ctm_id) NOT NULL,
    rqt_created_at timestamp with time zone NOT NULL,
    rqt_canceled_at timestamp with time zone,
    due_date date NOT NULL,
    status varchar NOT NULL,
	notes varchar,
	rating int
);

CREATE TABLE request_product (
	rpd_rqt_id int REFERENCES request(rqt_id),
	rpd_pdt_id uuid REFERENCES product(pdt_id),
	rpd_created_at timestamp with time zone NOT NULL,
	calculated_production_cost decimal NOT NULL,
	declared_production_cost decimal NOT NULL,
	unitary_value decimal NOT NULL,
	amount decimal NOT NULL,
	notes varchar,
	file_path varchar,
	file_link varchar,
	PRIMARY KEY(rpd_rqt_id, rpd_pdt_id)
);

CREATE TABLE financial_movement (
	fim_id serial PRIMARY KEY,
	fim_date date NOT NULL,
    fim_created_at timestamp with time zone NOT NULL,
	wallet varchar NOT NULL,
	operation varchar NOT NULL,
	description varchar NOT NULL,
	fim_rqt_id int REFERENCES request(rqt_id),
	--mvt_acq_id int REFERENCES acquisition(acq_id),
	fim_value decimal NOT NULL,
	banking_operation bool NOT NULL,
	voucher_path varchar
);

SELECT * FROM customer;
SELECT * FROM request;
SELECT * FROM request_product;

INSERT INTO PRODUCT(pdt_name, price, production_duration_in_minutes, pdt_created_at) VALUES
('Agenda 1 dia por página', 10, 90, now()),
('Agenda 2 dias por página', 20, 90, now()),
('Planner', 30, 60, now());

INSERT INTO CUSTOMER (phone, ctm_name, ctm_created_at) VALUES
('41985365856', 'Gustavo', now());

INSERT INTO request(rqt_created_at, rqt_ctm_id, due_date, status) VALUES
(NOW(), (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo'), now()::date, 'CREATED');

INSERT INTO request_product(
	rpd_rqt_id, rpd_pdt_id,
	rpd_created_at,
	unitary_value,
	amount,
	calculated_production_cost,
	declared_production_cost) VALUES
(
	(SELECT rqt_id FROM request WHERE rqt_ctm_id = (SELECT ctm_id FROM customer WHERE ctm_name = 'Gustavo')),
	(SELECT pdt_id FROM product WHERE pdt_name = 'Planner'),
	now(),
	10.00,
	3,
	2.00,
	2.00
);
