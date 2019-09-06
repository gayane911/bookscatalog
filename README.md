**Running instructions**

1. Import the project.

2. Make sure you have installed _Maven_, _MySQL_ server and _ElasticSearch v6.4.3_.

3. Open `elasticsearch.yml` file (/etc/elasticsearch/elasticsearch.yml if it is installed as service) and change `cluster.name` to `bookscatalog`.

4. Enable _ElasticSearch_ service or server.

5. Update `spring.data.elasticsearch.cluster-nodes` regarding to your configurations.

6. Connect to _MySQL_.

7. Update `spring.datasource.url` regarding to your configuration.

8. Change the `spring.datasource.username` and `spring.datasource.password` in application.properties file regarding your _username_ and _password_ of _MySQl_ server.   

9. Execute the following query:

        CREATE DATABASE bookscatalog;
        
10. Run the project as SpringBoot application.

11. Execute the following query: 

        USE bookscatalog;
        INSERT INTO roles(name) VALUES('ROLE_READER');
        INSERT INTO roles(name) VALUES('ROLE_ADMIN');
        
API is now ready to answer your requests.


**For testing**

1. Open `Postman` application.

2. Import `bookscatalog.postman_collection.json` and `es-bookscatalog.postman_collection.json` collections and `bookscatalog.postman_environment.json` environment file (you can find this files in root folder of the project).

3. Sign up and sign in to Books catalog. Make sure to send JWT token as request Authorization header with each request to API - in following format:

        Barear: {token}
        
4. For sending token with each request just change the value of `token` field in environment that you imported to Postman.