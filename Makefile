build:
	docker compose build postgres && mvn package;

database:
	docker compose build postgres && docker compose up -d postgres;

app:
	mvn package && java -jar ./target/todo_app_list.jar;

run-all:
	make database && make app;
