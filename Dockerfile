# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src/ ./src/

# Compilar Fat JAR com maven-shade-plugin
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar Fat JAR do builder
COPY --from=builder /app/target/agenda-telefonica.jar ./

# Espera banco estar pronto e executa o Fat JAR
CMD sh -c 'sleep 10 && java -jar agenda-telefonica.jar'
