create database bookSystem charactor set utf8;

create table `bookcatalog`(
`bookid` varchar(10) primary key,
`title` varchar(30),
`author` varchar(30),
`translator` varchar(30),
`publisher` varchar(30),
`publishingdata` date,
`codeid` varchar(30),
`memo` varchar(30),
`keyword` varchar(30),
`statusid` varchar(30),
`datacreator` varchar(30),
`datacreateddate` timestamp
);


insert into `bookcatalog` values('bookid','title','author','translator','publisher','publishingdata',
									'codeid','memo','keyword','statusid','datacreator','datacreateddate');




