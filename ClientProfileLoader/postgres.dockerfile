FROM postgres

LABEL author="Arseny"
LABEL desciption="enjoy the database"
LABEL version="1.0"

COPY init_db.sql /docker-entrypoint-initdb.d/