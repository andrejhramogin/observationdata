# Observationdata
Данное многопользовательское приложение позволяет создавать, хранить в базе данных и получать по запросам данные орнитологических наблюденияй. 
При создании наблюдения возможно сохранить информацию по следующим критериям:
- Дата наблюдения
- Место наблюдения
- Наблюдатель
- Вид птиц
- Количество особей
- Комментарии (суть наблюдения)
- Фото и видео материал 
- Параметры гнезд (биотоп, место расположения, тип гнезда, количество птенцов и/или яиц, комментарии)

This multi-user application allows you to create, store in a database 
and receive on-demand ornithological observation data. 
When creating an observation, it is possible to save information according 
to the following criteria:

- Date of observation
- Place of observation
- Observer
- bird species
- Number of individuals
- Comments (substance of observation)
- Photo and video material
- Nest parameters (biotope, location, nest type, number of chicks and/or eggs, comments)

# Создание docker-образа (docker image)
Для создания docker-образа (далее - образа) зайдите в консоль и перйдите в директрию,
в которой находится данный проект. Затем выполните команду:

sudo mvn spring-boot:build-image

После окончания процесса создания образа можно с помощью комманды:

sudo docker image ls

просмотреть список имеющихся образов. Образ данного приложения имеет имя
andrejhr/observation (tag spring-plugin).

# Создание и запуск JAR-файла с помощью Maven

Для создания JAR-файла с помощью maven используйте команду:

mvn package

Созданный архив observationdata-1.0-SNAPSHOT.jar будет находиться 
в папке target/maven-status

Для запуска JAR-файла используйте команду:

java -jar observationdata-1.0-SNAPSHOT.jar


