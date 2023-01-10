begin transaction;
drop table if exists series, author, book cascade;
create table series(
	series_id serial,
	series_name varchar (150) not null,
	constraint pk_series primary key (series_id)
);
create table author (
	author_id serial,
	author_name varchar (100) not null,
	creator_type varchar (30) not null,
	constraint pk_author primary key (author_id)
);
create table book (
	book_id serial,
	book_title varchar(100) not null,
	author_id int not null,
	format varchar(100) not null,
	demographic varchar(50),
	number_pages int not null,
	highly_recommend boolean,
	have_read boolean not null,
	series_id int,
	constraint pk_book primary key (book_id),
	constraint fk_book_series foreign key (series_id) references series (series_id),
	constraint fk_book_author foreign key (author_id) references author (author_id)
);
commit transaction;

insert into series(series_name)values('The Mysterious Benedict Society'),
('The Tarot Sequence'),
('Our Dreams At Dusk'),
('Welcome to Night Vale'),
('Aristotle and Dante'),
('Chaos Walking');



insert into author (author_name, creator_type)
values ('Trenton Lee Stewart', 'writer'),
('Yuhki Kamatani', 'writer and illustrator'),
('ND Stevenson', 'writer and illustrator'),
('KD Edwards', 'writer'),
('Benjamin Alire Saenz', 'writer'),
('Virginia Euwer Wolff', 'writer'),
('Koushun Takami', 'writer'),
('Patrick Ness', 'writer');

insert into book (book_title, author_id, format, demographic, number_pages, highly_recommend, have_read, series_id)
values ('The Knife of Never Letting Go', (select author_id from author where author_name = 'Patrick Ness'), 'novel', 'young adult', 479, true, true, (select series_id from series where series_name ='Chaos Walking')),
('The Ask and the Answer', (select author_id from author where author_name = 'Patrick Ness'), 'novel', 'young adult', 519, true, true, (select series_id from series where series_name ='Chaos Walking')),
('Monsters of Men', (select author_id from author where author_name = 'Patrick Ness'), 'novel', 'young adult', 603, true, true, (select series_id from series where series_name ='Chaos Walking')),
('Battle Royale', (select author_id from author where author_name = 'Koushun Takami'), 'novel', 'young adult', 576, false, true, null),

('Aristotle and Dante Discover the Secrets of the Universe', (select author_id from author where author_name = 'Benjamin Alire Saenz'), 'novel', 'young adult', 359, false, true, (select series_id from series where series_name ='Aristotle and Dante')),
('Aristotle and Dante Dive into the Waters of the World', (select author_id from author where author_name = 'Benjamin Alire Saenz'), 'novel', 'young adult', 485, false, true, (select series_id from series where series_name ='Aristotle and Dante')),

('The Mysterious Benedict Society', (select author_id from author where author_name = 'Trenton Lee Stewart'), 'novel', 'childrens', 485, true, true, (select series_id from series where series_name ='The Mysterious Benedict Society')),
('The Mysterious Benedict Society and the Perilous Journey', (select author_id from author where author_name = 'Trenton Lee Stewart'), 'novel', 'childrens', 440, false, true, (select series_id from series where series_name ='The Mysterious Benedict Society')),
('The Mysterious Benedict Society and the Prisoners Dilema', (select author_id from author where author_name = 'Trenton Lee Stewart'), 'novel', 'childrens', 391, false, true, (select series_id from series where series_name ='The Mysterious Benedict Society')),
('The Mysterious Benedict Society and the Riddle of the Ages', (select author_id from author where author_name = 'Trenton Lee Stewart'), 'novel', 'childrens', 391, false, true, (select series_id from series where series_name ='The Mysterious Benedict Society')),

('Our Dreams At Dusk Vol 1', (select author_id from author where author_name = 'Yuhki Kamatani'), 'manga', 'young adult', 176, true, true, (select series_id from series where series_name ='Our Dreams At Dusk')),
('Our Dreams At Dusk Vol 2', (select author_id from author where author_name = 'Yuhki Kamatani'), 'manga', 'young adult', 162, true, true, (select series_id from series where series_name ='Our Dreams At Dusk')),
('Our Dreams At Dusk Vol 3', (select author_id from author where author_name = 'Yuhki Kamatani'), 'manga', 'young adult', 168, true, true, (select series_id from series where series_name ='Our Dreams At Dusk')),
('Our Dreams At Dusk Vol 4', (select author_id from author where author_name = 'Yuhki Kamatani'), 'manga', 'young adult', 236, true, true, (select series_id from series where series_name ='Our Dreams At Dusk')),

('The Last Sun', (select author_id from author where author_name = 'KD Edwards'), 'novel', 'adult', 368, true, true, (select series_id from series where series_name ='The Tarot Sequence')),
('The Hanged Man', (select author_id from author where author_name = 'KD Edwards'), 'novel', 'adult', 387, true, true, (select series_id from series where series_name ='The Tarot Sequence')),
('The Hourglass Throne', (select author_id from author where author_name = 'KD Edwards'), 'novel', 'adult', 379, true, true, (select series_id from series where series_name ='The Tarot Sequence')),

('True Believer', (select author_id from author where author_name = 'Virginia Euwer Wolff'), 'novel', 'young adult', 264, false, true, null);


