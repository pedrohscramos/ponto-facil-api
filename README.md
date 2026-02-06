# ⏱️ PontoFácil — Sistema de Controle de Ponto

Plataforma para controle de ponto de funcionários, com arquitetura moderna, multiempresa (multi-tenant), autenticação JWT e foco em escalabilidade.

Projeto pensado desde o início para:
- Web (Angular)
- Mobile (futuro com Capacitor/Ionic)
- Modelo de negócio por planos (FREE/PRO)

---

## 🚀 Tecnologias

### Backend
- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- Bean Validation
- PostgreSQL

### Frontend (em desenvolvimento)
- Angular 19
- PrimeNG

### Infra (futuro)
- Docker
- GCP
- CI/CD

---

## 🧱 Arquitetura
Angular (SPA) -> REST + JWT -> Spring Boot API -> JPA PostgreSQL

### Conceitos principais:
- Multiempresa (Tenant = Empresa)
- Roles: ADMIN / USER
- Segurança Stateless
- Regras SaaS centralizadas

---

## 🔐 Segurança

- Autenticação JWT
- Proteção por roles
- Filtro JWT customizado
- Endpoints segregados por perfil

---

## 📦 Funcionalidades Implementadas

### 👤 Autenticação
- Login JWT
- Endpoint /me para dados do usuário

### ⏰ Registro de ponto
- Entrada / Saída
- Histórico do usuário

### 🧑‍💼 Admin (por empresa)
- CRUD de usuários
- Listagem de pontos da empresa
- Paginação e filtros por mês/ano

### 📋 SaaS
- Multiempresa (tenant)
- Planos FREE / PRO
- Limites automáticos por plano:
    - Quantidade de usuários
    - Registros mensais

### 🛡️ Qualidade
- Bean Validation
- Tratamento global de erros (ControllerAdvice)
- Auditoria automática:
    - createdAt
    - updatedAt
    - createdBy
    - updatedBy

---

## 📊 Planos (atual)

| Recurso | FREE | PRO |
|--------|------|-----|
| Usuários | 5 | Ilimitado |
| Registros/mês | 1000 | Ilimitado |
| Exportação | ❌ | ✅ (futuro) |

---

## ▶️ Como rodar localmente

### Pré-requisitos
- Java 21
- PostgreSQL
- Maven

### Banco

```sql
CREATE DATABASE ponto_facil;
```
### Configure em application.properties:

```properties
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ponto_facil
    username: postgres
    password: postgres
```

### Rodar backend

```bash
mvn spring-boot:run
```