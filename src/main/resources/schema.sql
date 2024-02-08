DROP TABLE IF EXISTS
    financial_event,
    --component_reference_acquisition,
    --component_acquisition,
    component,
    --acquisition,
    --version_component,
	product_component,
    request_product,
    request,
    --product_version_info,
    --product_version,
    product,
    customer;

CREATE TABLE component ( --cmp
    cmp_id varchar(10) DEFAULT substring(MD5(random()::text), 0, 11) PRIMARY KEY,
    cmp_created_at timestamp with time zone NOT NULL,
    cmp_deleted_at timestamp with time zone,
    cmp_name varchar NOT NULL,
    measure varchar NOT NULL,
    base_buy_height decimal NOT NULL, --*new
    base_buy_width decimal NOT NULL, --*new
    base_buy_amount decimal NOT NULL, --*new
    base_buy_paid_value decimal NOT NULL, --*new
    photo_address varchar,
	CONSTRAINT component_name_uk UNIQUE(cmp_name)
);

--*standby
/*CREATE TABLE acquisition ( --acq
    acq_id serial PRIMARY KEY,
    acq_date date NOT NULL
);*/

--*standby
/*CREATE TABLE component_acquisition ( --cac
    cac_id serial PRIMARY KEY,
    cac_acq_id int REFERENCES acquisition(acq_id),
    cac_cmp_id varchar(10) REFERENCES component(cmp_id),
	height decimal NOT NULL,
	width decimal NOT NULL,
	amount decimal NOT NULL,
	paid_value decimal NOT NULL
);*/

--*deprecated
-- TODO: how enforce relationship between component and acquisition?
-- (ensure that given cac_id in fact belongs to the same cmp_id (cra_cmp_id vs. cmp_id)
/*CREATE TABLE component_reference_acquisition ( --cra
    cra_cmp_id varchar(10) REFERENCES component(cmp_id),
    cra_cac_id int REFERENCES component_acquisition(cac_id),
    PRIMARY KEY(cra_cmp_id, cra_cac_id)
);*/

CREATE TABLE product ( --pdt
    pdt_id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    pdt_created_at timestamp with time zone NOT NULL, --*new
    pdt_deleted_at timestamp with time zone, --*new
    pdt_name varchar NOT NULL UNIQUE,
    status varchar NOT NULL,
    description varchar, --*new
    price decimal NOT NULL, --*new
    photo_address varchar, --*new
    production_duration_in_minutes int NOT NULL --*new
    --default_version varchar(10) REFERENCES product_version_info(pvr_version),
    --CONSTRAINT product_name_uk UNIQUE(pdt_name),
);

--*new
CREATE TABLE product_component ( --pco
    pco_pdt_id uuid NOT NULL REFERENCES product(pdt_id),
    pco_cmp_id varchar REFERENCES component(cmp_id),
	height decimal NOT NULL,
	width decimal NOT NULL,
    amount decimal NOT NULL,
    PRIMARY KEY(pco_pdt_id, pco_cmp_id)
);

--*deprecated
/*CREATE TABLE product_version ( --pvr
    pvr_pdt_id uuid REFERENCES product(pdt_id) NOT NULL,
    pvr_version varchar(12) NOT NULL, --> dd/MM/yyyy-X
    CONSTRAINT product_version_uk UNIQUE(pvr_pdt_id, pvr_version)
);*/

--*deprecated
/*CREATE TABLE product_version_info ( --pvi
    --pvi_id varchar(10) DEFAULT substring(MD5(random()::text), 0, 11),
    pvi_pvr_pdt_id uuid NOT NULL,
    pvi_pvr_version varchar(12) NOT NULL,
    pvi_created_at timestamp with time zone NOT NULL,
    pvi_deleted_at timestamp with time zone,
    pvi_name varchar NOT NULL,
    description varchar,
    price decimal NOT NULL,
    photo_address varchar,
    production_duration_in_minutes int NOT NULL,
	--CONSTRAINT product_version_info_uk UNIQUE(pvi_id, pvi_pvr_pdt_id, pvi_pvr_version),
    PRIMARY KEY(pvi_pvr_pdt_id, pvi_pvr_version),
    FOREIGN KEY(pvi_pvr_pdt_id, pvi_pvr_version) REFERENCES product_version(pvr_pdt_id, pvr_version)
);*/

/*CREATE TABLE product_default_version ( --pdv
    cra_cmp_id varchar(10) REFERENCES component(cmp_id),
    cra_cac_id int REFERENCES component_acquisition(cac_id),
    PRIMARY KEY(cra_cmp_id, cra_cac_id)
);*/

--*deprecated
/*CREATE TABLE version_component ( --vco
    vco_pvi_pvr_pdt_id uuid NOT NULL,
    vco_pvi_pvr_version varchar(12) NOT NULL,
    vco_cmp_id varchar REFERENCES component(cmp_id),
	height decimal NOT NULL,
	width decimal NOT NULL,
    amount decimal NOT NULL,
    PRIMARY KEY(vco_pvi_pvr_pdt_id, vco_pvi_pvr_version, vco_cmp_id),
	FOREIGN KEY(vco_pvi_pvr_pdt_id, vco_pvi_pvr_version) REFERENCES product_version_info(pvi_pvr_pdt_id, pvi_pvr_version)
);*/

CREATE TABLE customer ( --ctm
	ctm_id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    ctm_created_at timestamp with time zone NOT NULL,
    phone varchar NOT NULL,
	ctm_name varchar NOT NULL,
    facebook_chat_number varchar,
    rating int,
    CONSTRAINT customer_phone_uk UNIQUE(phone)
);

CREATE TABLE request ( --rqt
	rqt_id serial PRIMARY KEY,
    rqt_ctm_id uuid REFERENCES customer(ctm_id) NOT NULL,
    rqt_created_at timestamp with time zone NOT NULL,
    rqt_canceled_at timestamp with time zone,
    due_date date NOT NULL,
    status varchar NOT NULL,
	notes varchar,
	rating int
);

CREATE TABLE request_product ( --rpd
	rpd_rqt_id int REFERENCES request(rqt_id),
	rpd_pdt_id uuid REFERENCES product(pdt_id),
	rpd_created_at timestamp with time zone NOT NULL,
	components jsonb NOT NULL, --properties[component,measure,width,height,amount,paid_value]
	calculated_production_cost decimal NOT NULL,
	declared_production_cost decimal NOT NULL,
	unitary_value decimal NOT NULL,
	amount decimal NOT NULL,
	notes varchar,
	file_path varchar,
	file_link varchar,
	PRIMARY KEY(rpd_rqt_id, rpd_pdt_id)
);

--standby
/*CREATE TABLE financial_event ( --fev
	fev_id serial PRIMARY KEY,
	fev_date date NOT NULL,
    fev_created_at timestamp with time zone NOT NULL,
	wallet varchar NOT NULL,
	operation varchar NOT NULL,
	description varchar NOT NULL,
	fev_rqt_id int REFERENCES request(rqt_id),
	mvt_acq_id int REFERENCES acquisition(acq_id),
	fev_value decimal NOT NULL,
	banking_operation bool NOT NULL,
	voucher_path varchar
);*/
