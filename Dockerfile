# Utilizando Java 8
FROM openjdk:8-jdk-alpine
 
# Nome do pacote gerado pelo Maven
ARG JAR_FILE=target/carlosmelo-api-1.0.0-SNAPSHOT.jar
 
# Altera internamente para o diretório /opt/app
WORKDIR /opt/app
 
# Copia a aplicação com um nome diferente para WORKDIR 
COPY ${JAR_FILE} app.jar
 
# Executa o comando java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]


