#!/bin/bash
DB_PASSWORD="yrgoP4ssword"
SQL_SCRIPT="boardGameClub_TestDB.sql"

/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "$DB_PASSWORD" -C -i /usr/"$SQL_SCRIPT"
