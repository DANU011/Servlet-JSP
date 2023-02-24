CREATE TABLE member (
  id varchar(10) NOT NULL,
  pass varchar(10) NOT NULL,
  name varchar(30) NOT NULL,
  regidate timestamp NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (id)
);

create table board (
    num int not null auto_increment,
    title varchar(200) not null,
    content varchar(2000) not null,
    id varchar(10) not null, 
    postdate timestamp default current_timestamp not null,
    visitcount decimal(6),
    primary key (num)
);

