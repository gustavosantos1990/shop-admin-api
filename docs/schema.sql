--DROP TABLE IF EXISTS financial_event, component, product_component, request_product, request, product, customer;

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
    CONSTRAINT customer_phone_uk UNIQUE(phone)
);

CREATE TABLE request (
	rqt_id serial PRIMARY KEY,
    rqt_ctm_id uuid REFERENCES customer(ctm_id) NOT NULL,
    rqt_created_at timestamp with time zone NOT NULL,
    rqt_canceled_at timestamp with time zone,
    due_date date NOT NULL,
    status varchar NOT NULL,
	notes varchar
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