# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src/ ./src/

# Compilar e copiar todas as dependências
RUN mvn clean package dependency:copy-dependencies -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar todo o target do builder para garantir que tudo está lá
COPY --from=builder /app/target/classes ./
COPY --from=builder /app/target/dependency/*.jar ./

# Espera banco estar pronto e executa a aplicação
CMD sh -c 'sleep 10 && java -cp /app:/app/* com.agenda.AgendaTeste'
