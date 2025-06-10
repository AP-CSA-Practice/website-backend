# 使用官方 Maven + JDK 基礎映像
FROM maven:3.9.6-eclipse-temurin-21

# 建立工作目錄
WORKDIR /app

# 複製 pom.xml 並下載依賴
COPY pom.xml .
RUN mvn dependency:go-offline

# 複製源代碼
COPY src/ ./src/

# 默認命令：運行測試
CMD ["mvn", "test"]
