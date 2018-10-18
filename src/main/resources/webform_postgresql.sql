-- Postgresql
-- Table: public.tb_author

-- DROP TABLE public.tb_author;
CREATE SEQUENCE mngmt_sq START 101;
CREATE SEQUENCE log_sq START 1;
CREATE TABLE public.tb_author
(
  id character varying(10) NOT NULL,
  name character varying(20),
  pwd character varying(20),
  alias_nm character varying(100),
  gender character varying(20),
  email character varying(50),
  close_flg character varying(1),
  lock_up character varying(20),
  CONSTRAINT tb_author_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_author
  OWNER TO "Shaun";


-- Table: public.tb_authority

-- DROP TABLE public.tb_authority;

CREATE TABLE public.tb_authority
(
  id character varying(100) NOT NULL,
  name character varying(100),
  close_flg character varying(1),
  CONSTRAINT tb_authority_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_authority
  OWNER TO "Shaun";


-- Table: public.tb_blog_dtl

-- DROP TABLE public.tb_blog_dtl;

CREATE TABLE public.tb_blog_dtl
(
  id character varying(10) NOT NULL,
  name character varying(100),
  header_type character varying(2),
  header character varying(60),
  content character varying(2000),
  post_time character varying(20),
  author_id character varying(10),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_blog_dtl_pkey PRIMARY KEY (id),
  CONSTRAINT tb_blog_dtl_author_id_fkey FOREIGN KEY (author_id)
      REFERENCES public.tb_author (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_blog_dtl
  OWNER TO "Shaun";

-- Table: public.tb_blog_page_map

-- DROP TABLE public.tb_blog_page_map;

CREATE TABLE public.tb_blog_page_map
(
  page_id character varying(10),
  page_type character varying(30),
  blog_id character varying(10),
  order_id character varying(3)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_blog_page_map
  OWNER TO "Shaun";

-- Table: public.tb_button

-- DROP TABLE public.tb_button;

CREATE TABLE public.tb_button
(
  id character varying(10) NOT NULL,
  name character varying(100),
  url character varying(50),
  css_class character varying(50),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_button_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_button
  OWNER TO "Shaun";

-- Table: public.tb_carousel

-- DROP TABLE public.tb_carousel;

CREATE TABLE public.tb_carousel
(
  id character varying(10) NOT NULL,
  name character varying(100),
  header character varying(50),
  content character varying(2000),
  img_id character varying(10),
  button_id character varying(10),
  post_time character varying(20),
  author_id character varying(10),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_carousel_pkey PRIMARY KEY (id),
  CONSTRAINT tb_carousel_author_id_fkey FOREIGN KEY (author_id)
      REFERENCES public.tb_author (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_carousel_button_id_fkey FOREIGN KEY (button_id)
      REFERENCES public.tb_button (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_carousel_img_id_fkey FOREIGN KEY (img_id)
      REFERENCES public.tb_image (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_carousel
  OWNER TO "Shaun";

-- Table: public.tb_dropdown_list

-- DROP TABLE public.tb_dropdown_list;

CREATE TABLE public.tb_dropdown_list
(
  id character varying(10) NOT NULL,
  name character varying(100),
  url character varying(50),
  type character varying(20),
  parent_id character varying(10),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_dropdown_list_pkey PRIMARY KEY (id),
  CONSTRAINT tb_dropdown_list_parent_id_fkey FOREIGN KEY (parent_id)
      REFERENCES public.tb_navigation_bar (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_dropdown_list
  OWNER TO "Shaun";

-- Table: public.tb_function

-- DROP TABLE public.tb_function;

CREATE TABLE public.tb_function
(
  id character varying(100) NOT NULL,
  name character varying(100),
  parent_id character varying(100),
  url character varying(100),
  close_flg character varying(1),
  tb_nm character varying(100),
  icon character varying(100) DEFAULT 'glyphicon glyphicon-th-list'::character varying,
  CONSTRAINT tb_function_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_function
  OWNER TO "Shaun";

-- Table: public.tb_image

-- DROP TABLE public.tb_image;

CREATE TABLE public.tb_image
(
  id character varying(10) NOT NULL,
  name character varying(100),
  url character varying(200),
  css_class character varying(50),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_image_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_image
  OWNER TO "Shaun";

-- Table: public.tb_market_info

-- DROP TABLE public.tb_market_info;

CREATE TABLE public.tb_market_info
(
  id character varying(10) NOT NULL,
  name character varying(100),
  header character varying(50),
  content character varying(2000),
  img_id character varying(10),
  button_id character varying(10),
  post_time character varying(20),
  author_id character varying(10),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_market_info_pkey PRIMARY KEY (id),
  CONSTRAINT tb_market_info_author_id_fkey FOREIGN KEY (author_id)
      REFERENCES public.tb_author (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_market_info_button_id_fkey FOREIGN KEY (button_id)
      REFERENCES public.tb_button (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_market_info_img_id_fkey FOREIGN KEY (img_id)
      REFERENCES public.tb_image (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_market_info
  OWNER TO "Shaun";

-- Table: public.tb_navigation_bar

-- DROP TABLE public.tb_navigation_bar;

CREATE TABLE public.tb_navigation_bar
(
  id character varying(10) NOT NULL,
  name character varying(100),
  type character varying(20),
  url character varying(50),
  active character varying(20),
  close_flg character varying(1) default 'N',
  CONSTRAINT tb_navigation_bar_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_navigation_bar
  OWNER TO "Shaun";

-- Table: public.tb_orgnization

-- DROP TABLE public.tb_orgnization;

CREATE TABLE public.tb_orgnization
(
  id character varying(100) NOT NULL,
  name character varying(100),
  parent_id character varying(100),
  path character varying(100),
  super_user character varying(100),
  close_flg character varying(1),
  CONSTRAINT tb_orgnization_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_orgnization
  OWNER TO "Shaun";

-- Table: public.tb_role

-- DROP TABLE public.tb_role;

CREATE TABLE public.tb_role
(
  id character varying(100) NOT NULL,
  name character varying(100),
  parent_id character varying(100),
  close_flg character varying(1),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  CONSTRAINT tb_role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_role
  OWNER TO "Shaun";

-- Table: public.tb_role_function_authority

-- DROP TABLE public.tb_role_function_authority;

CREATE TABLE public.tb_role_function_authority
(
  role_id character varying(100),
  function_id character varying(100),
  authority_id character varying(100),
  lock_up character varying(1),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  CONSTRAINT tb_role_function_authority_authority_id_fkey FOREIGN KEY (authority_id)
      REFERENCES public.tb_authority (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_role_function_authority_function_id_fkey FOREIGN KEY (function_id)
      REFERENCES public.tb_function (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_role_function_authority_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.tb_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_role_function_authority
  OWNER TO "Shaun";

-- Table: public.tb_source

-- DROP TABLE public.tb_source;

CREATE TABLE public.tb_source
(
  id character varying(10) NOT NULL,
  group_nm character varying(20),
  name character varying(100),
  type character varying(20),
  value character varying(100),
  close_flg character varying(1),
  validator character varying(20) default '0',
  CONSTRAINT tb_source_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_source
  OWNER TO "Shaun";

-- Table: public.tb_user

-- DROP TABLE public.tb_user;

CREATE TABLE public.tb_user
(
  id character varying(100) NOT NULL,
  login_name character varying(100),
  password character varying(100),
  org_path character varying(100),
  alias_nm character varying(100),
  gender character varying(20),
  email character varying(50),
  close_flg character varying(1),
  lock_up character varying(20),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  attempt_sign_times character varying(1),
  CONSTRAINT tb_user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_user
  OWNER TO "Shaun";

-- Table: public.tb_user_role

-- DROP TABLE public.tb_user_role;

CREATE TABLE public.tb_user_role
(
  user_id character varying(100),
  role_id character varying(100),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  CONSTRAINT tb_user_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.tb_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.tb_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_user_role
  OWNER TO "Shaun";
  
-- Table: public.tb_sys_log

-- DROP TABLE public.tb_sys_log;

CREATE TABLE public.tb_sys_log
(
  id character varying(100) NOT NULL,
  user_id character varying(100),
  function_id character varying(100),
  target_id character varying(100),
  opt_type character varying(10),
  opt_time timestamp without time zone,
  content character varying(2000),
  CONSTRAINT tb_sys_log_pkey PRIMARY KEY (id),
  CONSTRAINT tb_sys_log_function_id_fkey FOREIGN KEY (function_id)
      REFERENCES public.tb_function (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_sys_log_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.tb_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tb_sys_log
  OWNER TO "Shaun";


INSERT INTO webform.public.tb_author("id", "name", pwd, alias_nm, gender, email, close_flg, lock_up) VALUES ('1', 'Shaun', 'Shaun', 'Shaun Xiong Deng', 'MALE', 'dengxiong90@foxmail.com', 'N', 'UNLOCK');
INSERT INTO webform.public.tb_authority("id", "name", close_flg) VALUES ('1', 'add', 'N');
INSERT INTO webform.public.tb_authority("id", "name", close_flg) VALUES ('2', 'delete', 'N');
INSERT INTO webform.public.tb_authority("id", "name", close_flg) VALUES ('3', 'update', 'N');
INSERT INTO webform.public.tb_authority("id", "name", close_flg) VALUES ('4', 'query', 'N');
INSERT INTO webform.public.tb_blog_dtl("id", "name", header_type, "header", "content", post_time, author_id) VALUES ('1', 'lead blog', 'h1', 'The Bootstrap Blog', '<p class="lead blog-description">The official example template of creating a blog with Bootstrap.</p>', '2017-10-17 15:05 PM', '1');
INSERT INTO webform.public.tb_blog_dtl("id", "name", header_type, "header", "content", post_time, author_id) VALUES ('2', 'Sample blog', 'h2', 'Sample blog post', '<p>This blog post shows a few different types of content that''s supported and styled with Bootstrap. Basic typography, images, and code are all supported.</p>
<hr>
<p>Cum sociis natoque penatibus et magnis <a href="#">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>
<blockquote>
<p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
</blockquote>
<p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>', '2017-10-17 15:05 PM', '1');
INSERT INTO webform.public.tb_blog_dtl("id", "name", header_type, "header", "content", post_time, author_id) VALUES ('3', 'Sample blog', 'h2', 'Heading', '<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>', '2017-10-17 15:05 PM', '1');
INSERT INTO webform.public.tb_blog_dtl("id", "name", header_type, "header", "content", post_time, author_id) VALUES ('4', 'Sample blog', 'h3', 'Sub-heading', '<p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
<pre><code>Example code block</code></pre>
<p>Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>', '2017-10-17 15:05 PM', '1');
INSERT INTO webform.public.tb_blog_dtl("id", "name", header_type, "header", "content", post_time, author_id) VALUES ('5', 'Sample blog', 'h3', 'Sub-heading', '<p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
<ul>
<li>Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</li>
<li>Donec id elit non mi porta gravida at eget metus.</li>
<li>Nulla vitae elit libero, a pharetra augue.</li>
</ul>
<p>Donec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.</p>
<ol>
<li>Vestibulum id ligula porta felis euismod semper.</li>
<li>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</li>
<li>Maecenas sed diam eget risus varius blandit sit amet non magna.</li>
</ol>
<p>Cras mattis consectetur purus sit amet fermentum. Sed posuere consectetur est at lobortis.</p>', '2017-10-17 15:05 PM', '1');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('2', 'tb_navigation_bar', '1', '1');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('2', 'tb_navigation_bar', '2', '2');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('2', 'tb_navigation_bar', '3', '3');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('2', 'tb_navigation_bar', '4', '4');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('2', 'tb_navigation_bar', '5', '5');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_dropdown_list', '1', '1');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_dropdown_list', '2', '2');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_dropdown_list', '3', '3');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_dropdown_list', '4', '4');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_dropdown_list', '5', '5');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_market_info', '1', '1');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_market_info', '2', '2');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_market_info', '3', '3');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_market_info', '4', '4');
INSERT INTO webform.public.tb_blog_page_map(page_id, page_type, blog_id, order_id) VALUES ('1', 'tb_market_info', '5', '5');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('1', 'Sign up today', '#', 'btn btn-lg btn-primary');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('2', 'Learn more', '#', 'btn btn-lg btn-primary');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('3', 'Browse gallery', '#', 'btn btn-lg btn-primary');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('4', 'Browse gallery', '#', 'btn btn-lg btn-primary');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('5', 'View details', './marketInfo.html', 'btn btn-default');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('6', 'View details', './marketInfo.html', 'btn btn-default');
INSERT INTO webform.public.tb_button("id", "name", url, css_class) VALUES ('7', 'View details', './marketInfo.html', 'btn btn-default');
INSERT INTO webform.public.tb_carousel("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('1', 'First Carousel', 'First Carousel', 'Note: If you''re viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.', '1', '1', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_carousel("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('2', 'Second Carousel', 'Second Carousel', 'Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.', '2', '2', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_carousel("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('3', 'Third Carousel', 'Third Carousel', 'Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.', '3', '3', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_carousel("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('4', 'Forth Carousel', 'Forth Carousel', 'Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.', '4', '4', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('1', 'Action', './dropDownLst.html', 'normal', '4');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('2', 'Another action', './dropDownLst.html', 'normal', '4');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('3', 'Something else here', './dropDownLst.html', 'normal', '4');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('4', 'This is separator', './dropDownLst.html', 'separator', '4');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('5', 'Nav header', './dropDownLst.html', 'dropdown_header', '4');
INSERT INTO webform.public.tb_dropdown_list("id", "name", url, "type", parent_id) VALUES ('6', 'Separated link', './dropDownLst.html', 'normal', '4');

INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('1', 'AuthorityManagement', '1', '#', 'N','glyphicon glyphicon-credit-card');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('2', 'Account', '1', './mngpages/account_lst.html', 'N','glyphicon glyphicon-user','tb_user');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('3', 'Role', '1', './mngpages/role_lst.html', 'N','glyphicon glyphicon-registration-mark','tb_role');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('4', 'HomePageManagement', '4', '#', 'N','glyphicon glyphicon-home');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('5', 'NavigationBar', '4', './navigationbar/navigationbar_lst.html', 'N','glyphicon glyphicon-th-list','tb_navigation_bar');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('6', 'Market', '4', './market/market_lst.html', 'N','glyphicon glyphicon-shopping-cart','tb_market_info');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('7', 'Carousel', '4', './carousel/carousel_lst.html', 'N','glyphicon glyphicon-transfer','tb_carousel');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('8', 'MetaElement', '4', '#', 'N','glyphicon glyphicon-tag');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('9', 'Button', '8', './button/button_lst.html', 'N','glyphicon glyphicon-hand-down','tb_button');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('10', 'Image', '8', './image/image_lst.html', 'N','glyphicon glyphicon-picture','tb_image');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('11', 'Audit', '11', '#', 'N','glyphicon glyphicon-book');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('12', 'SystemLog', '11', './mngpages/syslog_lst.html', 'N','glyphicon glyphicon-tasks','tb_sys_log');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('13', 'Function', '1', './mngpages/function_lst.html', 'N','glyphicon glyphicon-menu-hamburger','tb_function');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('14', 'Author', '4', './author/author_lst.html', 'N','glyphicon glyphicon-user','tb_author');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('15', 'DropdownList', '4', './dropdownlist/dropdownlist_lst.html', 'N','glyphicon glyphicon-align-justify','tb_dropdown_list');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('16', 'Blog', '4', './blog/blog_lst.html', 'N','glyphicon glyphicon-bullhorn','tb_blog_dtl');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('17', 'FileManagement', '17', '#', 'N','glyphicon glyphicon-folder-open');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('18', 'Images', '17', './file/image_lst.html', 'N','glyphicon glyphicon-picture');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('19', 'Documents', '17', './file/document_lst.html', 'N','glyphicon glyphicon-file');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon) VALUES ('20', 'SystemManagement', '20', '#', 'N','glyphicon glyphicon-cog');
INSERT INTO webform.public.tb_function("id", "name", parent_id, url, close_flg,icon,tb_nm) VALUES ('21', 'SystemParameters', '20', './systemparam_lst.html', 'N','glyphicon glyphicon-align-left','tb_source');

INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('1', 'First slide', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'first-slide');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('2', 'Second slide', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'second-slide');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('3', 'Third slide', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'third-slide');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('4', 'Forth slide', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'forth-slide');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('5', 'Generic placeholder image', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'img-circle');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('6', 'Generic placeholder image', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'img-circle');
INSERT INTO webform.public.tb_image("id", "name", url, css_class) VALUES ('7', 'Generic placeholder image', 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==', 'img-circle');
INSERT INTO webform.public.tb_market_info("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('1', 'First Market', 'First Market', 'Note: If you''re viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.', '5', '5', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_market_info("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('2', 'Second Market', 'Second Market', 'Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.', '6', '6', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_market_info("id", "name", "header", "content", img_id, button_id, post_time, author_id) VALUES ('3', 'Third Market', 'Third Market', 'Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.', '7', '7', '2018-01-23 17:22:34', '1');
INSERT INTO webform.public.tb_navigation_bar("id", "name", "type", url, active) VALUES ('1', 'Home', 'normal', './index.html', 'active');
INSERT INTO webform.public.tb_navigation_bar("id", "name", "type", url, active) VALUES ('2', 'About', 'normal', './navBar.html', 'inactive');
INSERT INTO webform.public.tb_navigation_bar("id", "name", "type", url, active) VALUES ('3', 'Contact', 'normal', './navBar.html', 'inactive');
INSERT INTO webform.public.tb_navigation_bar("id", "name", "type", url, active) VALUES ('4', 'Dropdown', 'dropdown', '#', 'inactive');
INSERT INTO webform.public.tb_role("id", "name", parent_id, close_flg, start_time, end_time) VALUES ('1', 'admin', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '1', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '1', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '1', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '1', '4', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '2', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '2', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '2', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '2', '4', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '3', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '3', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '3', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '3', '4', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '11', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '11', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '11', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '11', '4', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '12', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '12', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '12', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '12', '4', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '13', '1', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '13', '2', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '13', '3', 'N', null, null);
INSERT INTO webform.public.tb_role_function_authority(role_id, function_id, authority_id, lock_up, start_time, end_time) VALUES ('1', '13', '4', 'N', null, null);
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('1', 'homepage', 'project_nm', 'value', 'WebForm', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('2', 'homepage', 'description', 'value', 'This is a WebForm', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('3', 'homepage', 'keywords', 'value', 'WebForm Template', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('4', 'homepage', 'icon_url', 'value', '/img/favicon.ico', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('6', 'homepage', 'company', 'value', 'Company,Inc.', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('5', 'homepage', 'home_url', 'value', './index.html', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('7', 'homepage', 'signin_url', 'value', './signIn.html', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('8', 'homepage', 'signup_url', 'value', './signUp.html', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('9', 'System', 'MaxSignAttemptsTime', 'value', '5', 'N');
INSERT INTO webform.public.tb_source("id", group_nm, "name", "type", "value", close_flg) VALUES ('10', 'System', 'AccountAutoLock', 'value', 'yes', 'N');
INSERT INTO webform.public.tb_user("id", login_name, "password", org_path, alias_nm, gender, email, close_flg, lock_up, start_time, end_time) VALUES ('1', 'admin', 'admin', '1', 'admin', 'Male', 'dengxiong90@foxmail.com', 'N', 'N', null, null);
INSERT INTO webform.public.tb_user("id", login_name, "password", org_path, alias_nm, gender, email, close_flg, lock_up, start_time, end_time) VALUES ('2', 'zhangsan', 'admin', '1', 'admin', 'Male', 'zhangsan@foxmail.com', 'N', 'N', null, to_timestamp('14-03-2018 16:40:38', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO webform.public.tb_user("id", login_name, "password", org_path, alias_nm, gender, email, close_flg, lock_up, start_time, end_time) VALUES ('3', 'lisi', 'admin', '1', 'admin', 'Male', 'lisi@foxmail.com', 'N', 'N', null, to_timestamp('14-03-2018 16:40:38', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO webform.public.tb_user_role(user_id, role_id, start_time, end_time) VALUES ('1', '1', null, null);
