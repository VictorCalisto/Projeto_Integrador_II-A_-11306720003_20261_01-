# Usa imagem do Java 17
FROM openjdk:17-slim

# Define diretório de trabalho no container
WORKDIR /app

# Instala Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copia arquivo pom.xml
COPY pom.xml .

# Copia código-fonte
COPY src/ ./src/

# Baixa dependências e compila o projeto
RUN mvn clean package -DskipTests

# Define comando padrão para executar a aplicação
# Aguarda o banco estar disponível antes de rodar
CMD sleep 5 && java -cp target/classes:target/lib/* com.agenda.AgendaTeste
