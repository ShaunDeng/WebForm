CREATE SEQUENCE transfer_sq START 101;

CREATE TABLE public.tb_driver
(
  id character varying(10) NOT NULL,
  name character varying(100) NOT NULL,
  gender character varying(20) NOT NULL,
  day_of_birth timestamp without time zone NOT NULL,
  phone_number character varying(20) NOT NULL,
  id_number character varying(20) NOT NULL,
  drive_license_number character varying(20) NOT NULL,
  driving_years character varying(3) NOT NULL,
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_driver_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_vehicle
(
  id character varying(10) NOT NULL,
  driver_id character varying(10),
  brand character varying(50) NOT NULL,
  model character varying(20) NOT NULL,
  color character varying(20) NOT NULL,
  plate_number character varying(20) NOT NULL,
  capacity character varying(3) NOT NULL,
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_vehicle_pkey PRIMARY KEY (id),
  CONSTRAINT tb_vehicle_driver_id_fkey FOREIGN KEY (driver_id)
      REFERENCES public.tb_driver (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_passenger
(
  id character varying(10) NOT NULL,
  name character varying(100)  NOT NULL,
  phone_number character varying(20) NOT NULL,
  gender character varying(20),
  day_of_birth timestamp without time zone,
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_passenger_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_passenger_q
(
  id character varying(10) NOT NULL,
  passenger_id character varying(10) NOT NULL,
  departure_place character varying(50) NOT NULL,
  destination character varying(50) NOT NULL,
  register_time timestamp without time zone NOT NULL,
  departure_time timestamp without time zone NOT NULL,
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_passenger_q_pkey PRIMARY KEY (id),
  CONSTRAINT tb_passenger_q_passenger_id_fkey FOREIGN KEY (passenger_id)
      REFERENCES public.tb_passenger (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_transfer_q
(
  id character varying(10) NOT NULL,
  vehicle_id character varying(10) NOT NULL,
  departure_place character varying(50),
  destination character varying(50),
  register_time timestamp without time zone NOT NULL,
  departure_time timestamp without time zone,
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_transfer_q_pkey PRIMARY KEY (id),
  CONSTRAINT tb_transfer_q_vehicle_id_fkey FOREIGN KEY (vehicle_id)
      REFERENCES public.tb_vehicle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_order_hdr
(
  id character varying(10) NOT NULL,
  vehicle_id character varying(10) NOT NULL,
  departure_place character varying(50) NOT NULL,
  destination character varying(50) NOT NULL,
  departure_time timestamp without time zone,
  arrive_time timestamp without time zone,
  total_income character varying(10) default '0',
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_order_hdr_q_pkey PRIMARY KEY (id),
  CONSTRAINT tb_order_hdr_vehicle_id_fkey FOREIGN KEY (vehicle_id)
      REFERENCES public.tb_vehicle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.tb_order_dtl
(
  id character varying(10) NOT NULL,
  hdr_id character varying(10) NOT NULL,
  passenger_id character varying(10) NOT NULL,
  departure_place character varying(50),
  destination character varying(50),
  income character varying(10) default '0',
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_order_dtl_pkey PRIMARY KEY (id),
  CONSTRAINT tb_order_dtl_hdr_id_fkey FOREIGN KEY (hdr_id)
      REFERENCES public.tb_order_hdr (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_order_dtl_passenger_id_fkey FOREIGN KEY (passenger_id)
      REFERENCES public.tb_passenger (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);





INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('22', 'TransferSystem', '22', '#', 'N','glyphicon glyphicon-transfer');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('23', 'VehicleManagement', '22', './vehicle/vehicle_lst.html', 'N','glyphicon glyphicon-plane','tb_vehicle');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('24', 'DriverManagement', '22', './driver/driver_lst.html', 'N','glyphicon glyphicon-user','tb_driver');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('25', 'PassengerManagement', '22', './passenger/passenger_lst.html', 'N','glyphicon glyphicon-briefcase','tb_passenger');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('26', 'PassengerQueue', '22', './passengerq/passengerq_lst.html', 'N','glyphicon glyphicon-indent-left','tb_passenger_q');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('27', 'TransferQueue', '22', './transferq/transferq_lst.html', 'N','glyphicon glyphicon-indent-right','tb_transfer_q');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('28', 'TransferOrder', '22', './transferorder/transferorder_lst.html', 'N','glyphicon glyphicon-list-alt','tb_order_hdr');