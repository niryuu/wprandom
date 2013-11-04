# wprandom #

## Build & Run ##

```sh
$ cd wprandom
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## setup tables ##

wget http://dumps.wikimedia.org/jawiki/latest/jawiki-latest-all-titles-in-ns0.gz
gunzip jawiki-latest-all-titles-in-ns0.gz
createdb -U postgres wikipedia
create table title (id serial primary key, title text);
create table import_title (title text);
copy import_title from '/YOUR DIRECTORY/jawiki-latest-all-titles-in-ns0';
insert into title (id, title) select nextval('title_id_seq'), title from import_title;
drop table import_title;
