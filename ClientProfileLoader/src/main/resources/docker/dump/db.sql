--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

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
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    uuid uuid NOT NULL,
    addressname character varying(255),
    country character varying(255),
    icp character varying(100),
    notformaddrname character varying(255),
    zipcode character varying(255),
    individualuuid character varying(255)
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: contact_medium; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact_medium (
    uuid character varying(255) NOT NULL
);


ALTER TABLE public.contact_medium OWNER TO postgres;

--
-- Name: documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documents (
    uuid character varying(255) NOT NULL
);


ALTER TABLE public.documents OWNER TO postgres;

--
-- Name: email; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.email (
    uuid character varying(255) NOT NULL,
    value character varying(255),
    emails character varying(255) NOT NULL
);


ALTER TABLE public.email OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: individual; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.individual (
    uuid character varying(255) NOT NULL,
    birthdate timestamp without time zone,
    countryofbirth character varying(255),
    fullname character varying(255),
    gender character varying(255),
    icp character varying(100),
    name character varying(255),
    patronymic character varying(255),
    placeofbirth character varying(255),
    surname character varying(255),
    contacts character varying(255),
    documents character varying(255),
    rf_passport uuid
);


ALTER TABLE public.individual OWNER TO postgres;

--
-- Name: phone_number; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phone_number (
    uuid character varying(255) NOT NULL,
    value character varying(255),
    phone_numbers character varying(255) NOT NULL
);


ALTER TABLE public.phone_number OWNER TO postgres;

--
-- Name: rf_passports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rf_passports (
    uuid uuid NOT NULL,
    birthdate character varying(255),
    birthplace character varying(255),
    department_doc character varying(255),
    division character varying(255),
    gender character varying(255),
    invalidity_reason character varying(255),
    issued character varying(255),
    issued_by character varying(255),
    legal_force character varying(255),
    message character varying(255),
    name character varying(255),
    number character varying(255),
    passport_status character varying(255),
    patronymic character varying(255),
    receipt_doc_date character varying(255),
    series character varying(255),
    surname character varying(255),
    validate_date_doc character varying(255)
);


ALTER TABLE public.rf_passports OWNER TO postgres;

--
-- Name: wallet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wallet (
    uuid uuid NOT NULL,
    dollarwallet character varying(255),
    eurowallet character varying(255),
    icp character varying(100),
    rubwallet character varying(255),
    individualuuid character varying(255)
);


ALTER TABLE public.wallet OWNER TO postgres;

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (uuid, addressname, country, icp, notformaddrname, zipcode, individualuuid) FROM stdin;
\.


--
-- Data for Name: contact_medium; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contact_medium (uuid) FROM stdin;
\.


--
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.documents (uuid) FROM stdin;
\.


--
-- Data for Name: email; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.email (uuid, value, emails) FROM stdin;
\.


--
-- Data for Name: individual; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.individual (uuid, birthdate, countryofbirth, fullname, gender, icp, name, patronymic, placeofbirth, surname, contacts, documents, rf_passport) FROM stdin;
4800c301-50a5-46f9-8c5f-6d6b3fbc55nf	1967-02-16 23:32:02	USA	Bruce Willis	m	312	Bruce	\N	LA	Willis	\N	\N	\N
\.


--
-- Data for Name: phone_number; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.phone_number (uuid, value, phone_numbers) FROM stdin;
\.


--
-- Data for Name: rf_passports; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rf_passports (uuid, birthdate, birthplace, department_doc, division, gender, invalidity_reason, issued, issued_by, legal_force, message, name, number, passport_status, patronymic, receipt_doc_date, series, surname, validate_date_doc) FROM stdin;
\.


--
-- Data for Name: wallet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wallet (uuid, dollarwallet, eurowallet, icp, rubwallet, individualuuid) FROM stdin;
4800c301-50a5-46f9-8c5f-6d6b3fbc55ca	\N	\N	"1"	"1000"	4800c301-50a5-46f9-8c5f-6d6b3fbc55nf
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (uuid);


--
-- Name: contact_medium contact_medium_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_medium
    ADD CONSTRAINT contact_medium_pkey PRIMARY KEY (uuid);


--
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (uuid);


--
-- Name: email email_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email
    ADD CONSTRAINT email_pkey PRIMARY KEY (uuid);


--
-- Name: individual individual_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.individual
    ADD CONSTRAINT individual_pkey PRIMARY KEY (uuid);


--
-- Name: phone_number phone_number_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT phone_number_pkey PRIMARY KEY (uuid);


--
-- Name: rf_passports rf_passports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rf_passports
    ADD CONSTRAINT rf_passports_pkey PRIMARY KEY (uuid);


--
-- Name: wallet wallet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT wallet_pkey PRIMARY KEY (uuid);


--
-- Name: wallet fk2dd6f0k3hh7u5je7sft4lfa2b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT fk2dd6f0k3hh7u5je7sft4lfa2b FOREIGN KEY (individualuuid) REFERENCES public.individual(uuid);


--
-- Name: individual fkaqjaegcdxrdogjmjbbrxris0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.individual
    ADD CONSTRAINT fkaqjaegcdxrdogjmjbbrxris0 FOREIGN KEY (rf_passport) REFERENCES public.rf_passports(uuid);


--
-- Name: address fkfvb7aou1i3cl1qt1rb31x6fuq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fkfvb7aou1i3cl1qt1rb31x6fuq FOREIGN KEY (individualuuid) REFERENCES public.individual(uuid);


--
-- Name: individual fkgukweituikc062dyys6xcm2pw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.individual
    ADD CONSTRAINT fkgukweituikc062dyys6xcm2pw FOREIGN KEY (contacts) REFERENCES public.contact_medium(uuid);


--
-- Name: phone_number fkh30ucqlh7yhnqrqi50jq2wme7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT fkh30ucqlh7yhnqrqi50jq2wme7 FOREIGN KEY (phone_numbers) REFERENCES public.contact_medium(uuid);


--
-- Name: individual fkkrdia7mdtxhwo3vgfwsa9ah4s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.individual
    ADD CONSTRAINT fkkrdia7mdtxhwo3vgfwsa9ah4s FOREIGN KEY (documents) REFERENCES public.documents(uuid);


--
-- Name: email fkprxs4jnjx1oef55sf8la3q66y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email
    ADD CONSTRAINT fkprxs4jnjx1oef55sf8la3q66y FOREIGN KEY (emails) REFERENCES public.contact_medium(uuid);


--
-- PostgreSQL database dump complete
--

