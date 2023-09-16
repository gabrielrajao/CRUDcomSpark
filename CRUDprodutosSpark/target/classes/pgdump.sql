--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-09-16 18:26:48

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

--
-- TOC entry 215 (class 1259 OID 16444)
-- Name: id-planta; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

CREATE SEQUENCE public."id-planta"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public."id-planta" OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16437)
-- Name: planta; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.planta (
    idade integer,
    nome text,
    descricao text,
    id integer DEFAULT nextval('public."id-planta"'::regclass) NOT NULL,
    preco real
);


ALTER TABLE public.planta OWNER TO ti2cc;

--
-- TOC entry 3318 (class 0 OID 16437)
-- Dependencies: 214
-- Data for Name: planta; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public.planta (idade, nome, descricao, id, preco) FROM stdin;
2	Girassol	Belo Girassol	5	234
1	Narciso	Bela flor	7	98
\.


--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 215
-- Name: id-planta; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."id-planta"', 7, true);


--
-- TOC entry 3175 (class 2606 OID 16443)
-- Name: planta planta_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.planta
    ADD CONSTRAINT planta_pkey PRIMARY KEY (id);


-- Completed on 2023-09-16 18:26:48

--
-- PostgreSQL database dump complete
--

