<h2> Cloud Parking </h2>
<br>
<p> Neste projeto do Spring Framework Experience, o desafio é desenvolver um conjunto de API's utilizando Spring Boot para controlar um estacionamento de veículos!</p>
<p> Serão controladas a entrada, a saída e o valor a ser cobrado do cliente, aplicando as boas práticas de desenvolvimento de API. </p>
<b>Ferramentas Utilizadas</b>
<ul>
    <li>Java 11</li>
    <li>Swagger</li>
    <li>Spring Security</li>
    <li>Docker</li>
    <li>Banco de Dados PostegreSQL</li>
    <li>RestAssured para testar endpoints</li>
    <li>Heroku para disponibilização da API</li>
</ul>

## Run Database
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine