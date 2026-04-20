FROM eclipse-temurin:25-jdk

WORKDIR /app

# Copia los archivos del proyecto para compilar
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Compila el JAR
RUN ./gradlew build -x test

# Expone el puerto
EXPOSE 6001

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "build/libs/backend_fairticket-0.0.1-SNAPSHOT.jar"]