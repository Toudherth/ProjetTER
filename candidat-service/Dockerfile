FROM openjdk:17

# Copier l'application compilée dans l'image Docker
ADD  target/candidat-service.jar /app.jar

# Exécuter l'application Spring Boot lorsque le conteneur est démarré
ENTRYPOINT [ "java", "-jar", "/app.jar"]

# Exposer le port sur lequel l'application écoute
EXPOSE 9091