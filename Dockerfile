FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar source e pom.xml
COPY pom.xml ./
COPY src/ ./src/

# Baixar dependências e compilar
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre

# Instalar ferramentas necessárias via apt-get
RUN apt-get update && apt-get install -y \
    mysql-client \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar arquivos compilados do builder
COPY --from=builder /app/target/classes ./bin/
COPY --from=builder /app/target/dependency/*.jar ./lib/

# Copiar scripts e SQL
COPY sql/ ./sql/
COPY .env ./
COPY entrypoint.sh ./

RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]



# Dar permissão de execução ao entrypoint
RUN chmod +x entrypoint.sh

# Comando padrão
ENTRYPOINT ["./entrypoint.sh"]

