--
-- PostgreSQL database dump
--

-- Dumped from database version 15.5
-- Dumped by pg_dump version 16.1

-- Started on 2024-01-03 21:29:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 57361)
-- Name: animal_vaccines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animal_vaccines (
    id bigint NOT NULL,
    protection_end date NOT NULL,
    protection_start date NOT NULL,
    animal_id bigint,
    vaccine_id bigint
);


ALTER TABLE public.animal_vaccines OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 57364)
-- Name: animal_vaccines_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animal_vaccines_id_seq OWNER TO postgres;

--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 215
-- Name: animal_vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_vaccines_id_seq OWNED BY public.animal_vaccines.id;


--
-- TOC entry 216 (class 1259 OID 57365)
-- Name: animals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animals (
    id bigint NOT NULL,
    birthday date,
    breed character varying(255),
    colour character varying(255),
    gender character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id bigint,
    CONSTRAINT animals_gender_check CHECK (((gender)::text = ANY (ARRAY[('MALE'::character varying)::text, ('FEMALE'::character varying)::text])))
);


ALTER TABLE public.animals OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 57371)
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animals_id_seq OWNER TO postgres;

--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 217
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;


--
-- TOC entry 218 (class 1259 OID 57372)
-- Name: appointments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    animal_id bigint NOT NULL,
    doctor_id bigint NOT NULL
);


ALTER TABLE public.appointments OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 57375)
-- Name: appointments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointments_id_seq OWNER TO postgres;

--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 219
-- Name: appointments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;


--
-- TOC entry 220 (class 1259 OID 57376)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    mail character varying(255),
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 57381)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customers_id_seq OWNER TO postgres;

--
-- TOC entry 3401 (class 0 OID 0)
-- Dependencies: 221
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- TOC entry 222 (class 1259 OID 57382)
-- Name: doctor_available_days; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor_available_days (
    id bigint NOT NULL,
    available_days date NOT NULL,
    doctor_id bigint
);


ALTER TABLE public.doctor_available_days OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 57385)
-- Name: doctor_available_days_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctor_available_days_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctor_available_days_id_seq OWNER TO postgres;

--
-- TOC entry 3402 (class 0 OID 0)
-- Dependencies: 223
-- Name: doctor_available_days_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctor_available_days_id_seq OWNED BY public.doctor_available_days.id;


--
-- TOC entry 224 (class 1259 OID 57386)
-- Name: doctors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctors (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    mail character varying(255),
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL
);


ALTER TABLE public.doctors OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 57391)
-- Name: doctors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctors_id_seq OWNER TO postgres;

--
-- TOC entry 3403 (class 0 OID 0)
-- Dependencies: 225
-- Name: doctors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;


--
-- TOC entry 226 (class 1259 OID 57392)
-- Name: vaccines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.vaccines OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 57397)
-- Name: vaccines_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaccines_id_seq OWNER TO postgres;

--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 227
-- Name: vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;


--
-- TOC entry 3203 (class 2604 OID 57398)
-- Name: animal_vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines ALTER COLUMN id SET DEFAULT nextval('public.animal_vaccines_id_seq'::regclass);


--
-- TOC entry 3204 (class 2604 OID 57399)
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- TOC entry 3205 (class 2604 OID 57400)
-- Name: appointments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);


--
-- TOC entry 3206 (class 2604 OID 57401)
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- TOC entry 3207 (class 2604 OID 57402)
-- Name: doctor_available_days id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_available_days ALTER COLUMN id SET DEFAULT nextval('public.doctor_available_days_id_seq'::regclass);


--
-- TOC entry 3208 (class 2604 OID 57403)
-- Name: doctors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 57404)
-- Name: vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);


--
-- TOC entry 3379 (class 0 OID 57361)
-- Dependencies: 214
-- Data for Name: animal_vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animal_vaccines (id, protection_end, protection_start, animal_id, vaccine_id) FROM stdin;
1	2020-11-01	2018-11-01	1	1
2	2023-10-15	2021-10-15	2	2
5	2023-01-06	2021-01-06	3	3
6	2023-06-20	2022-06-20	7	4
7	2024-02-21	2023-02-21	8	5
\.


--
-- TOC entry 3381 (class 0 OID 57365)
-- Dependencies: 216
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animals (id, birthday, breed, colour, gender, name, species, customer_id) FROM stdin;
1	2023-01-01	yes	white	MALE	barry	bichon frise	1
2	2000-04-09	yes	orange	FEMALE	madden	shiba	2
7	2022-07-01	no	black/white	FEMALE	yoshi	chihuahua	4
8	2023-02-10	yes	brown/white	MALE	larry	basset	5
3	2005-11-12	no\n	white	FEMALE	yogi	akita	3
\.


--
-- TOC entry 3383 (class 0 OID 57372)
-- Dependencies: 218
-- Data for Name: appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
19	2023-01-03 16:00:00	1	1
20	2023-11-01 15:00:00	2	2
21	2023-01-04 17:00:00	3	3
22	2023-11-01 12:00:00	7	4
23	2023-12-05 17:00:00	8	5
\.


--
-- TOC entry 3385 (class 0 OID 57376)
-- Dependencies: 220
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (id, address, city, mail, name, phone) FROM stdin;
1	38348 Harvey Dam, Wuckertmouth	CO	nataniel@mail.com	Nataniel Robertson	5538534412
2	Apt. 655 81029 Metz Ports, New Longmouth	KY	bloggs@mail.com	Miranda Bloggs	5129841243
3	35878 Rutherford Street, New Laverne	IN	giogio@mail.com	Giovanni Schaefer	5614326475
4	6903 Alfredo Fields, Robville	SC	marcintoshhow@mail.com	Marc Howell	5424127429
5	Apt. 918 2368 Lucien Road, Port Rubinburgh	NM	finirwin@mail.com	Finnley Irwin	5385321212
\.


--
-- TOC entry 3387 (class 0 OID 57382)
-- Dependencies: 222
-- Data for Name: doctor_available_days; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctor_available_days (id, available_days, doctor_id) FROM stdin;
1	2024-01-03	1
6	2024-01-02	2
7	2024-01-01	3
8	2024-01-05	4
9	2024-01-04	5
\.


--
-- TOC entry 3389 (class 0 OID 57386)
-- Dependencies: 224
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctors (id, address, city, mail, name, phone) FROM stdin;
1	97692 Bobby Courts, Freddiebury	IN	carr@vetapp.com	Oscar Carr	5129841254
2	620 Mohr Meadow, New Lynelle	MS	cole@vetapp.com	Alvin Cole	5614329121
3	8169 Schultz Heights, West Corene	TX	fletcher@vetapp.com	Jaden Fletcher	5551226318
4	921 Cormier Mills, Kerlukeburgh	NE	chase@vetapp.com	Reggie Chase	5456125391
5	883 Wehner Mountains, Homenickview	WA	rudy@vetapp.com	Rudy Cunningham	5325323232
\.


--
-- TOC entry 3391 (class 0 OID 57392)
-- Dependencies: 226
-- Data for Name: vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccines (id, code, name) FROM stdin;
1	123	parvovirus
2	124	parainfluenza
3	122	leptospira
4	121	H3N2/H3N8
5	120	crotalux atrox
\.


--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 215
-- Name: animal_vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_vaccines_id_seq', 7, true);


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 217
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animals_id_seq', 8, true);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 219
-- Name: appointments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointments_id_seq', 23, true);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 221
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 5, true);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 223
-- Name: doctor_available_days_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctor_available_days_id_seq', 9, true);


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 225
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctors_id_seq', 5, true);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 227
-- Name: vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccines_id_seq', 5, true);


--
-- TOC entry 3212 (class 2606 OID 57406)
-- Name: animal_vaccines animal_vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT animal_vaccines_pkey PRIMARY KEY (id);


--
-- TOC entry 3214 (class 2606 OID 57408)
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- TOC entry 3216 (class 2606 OID 57410)
-- Name: appointments appointments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);


--
-- TOC entry 3218 (class 2606 OID 57412)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 57414)
-- Name: doctor_available_days doctor_available_days_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_available_days
    ADD CONSTRAINT doctor_available_days_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 57416)
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 2606 OID 57418)
-- Name: customers uk_2hxsqcqvp2pjgxw00gn7inubj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_2hxsqcqvp2pjgxw00gn7inubj UNIQUE (mail);


--
-- TOC entry 3228 (class 2606 OID 57420)
-- Name: vaccines uk_7o57f9urdeta76xljqdidcsck; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT uk_7o57f9urdeta76xljqdidcsck UNIQUE (code);


--
-- TOC entry 3222 (class 2606 OID 57422)
-- Name: customers uk_m3iom37efaxd5eucmxjqqcbe9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk_m3iom37efaxd5eucmxjqqcbe9 UNIQUE (phone);


--
-- TOC entry 3230 (class 2606 OID 57424)
-- Name: vaccines vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 57425)
-- Name: appointments fk95vepu86o8syqtux9gkr71bhy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- TOC entry 3233 (class 2606 OID 57430)
-- Name: animals fkb36lt3kj4mrbdx5btxmp4j60n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 3236 (class 2606 OID 57435)
-- Name: doctor_available_days fkhhnbfqustrh46vuehatdsv53r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor_available_days
    ADD CONSTRAINT fkhhnbfqustrh46vuehatdsv53r FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- TOC entry 3231 (class 2606 OID 57440)
-- Name: animal_vaccines fkiwvg30h9kqewspm3hj6xl2kn9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fkiwvg30h9kqewspm3hj6xl2kn9 FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- TOC entry 3235 (class 2606 OID 57445)
-- Name: appointments fkmujeo4tymoo98cmf7uj3vsv76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- TOC entry 3232 (class 2606 OID 57450)
-- Name: animal_vaccines fktx6d054a6qgimiblyxm4f5ue; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fktx6d054a6qgimiblyxm4f5ue FOREIGN KEY (vaccine_id) REFERENCES public.vaccines(id);


-- Completed on 2024-01-03 21:29:28

--
-- PostgreSQL database dump complete
--

