# Publicação gratuita com Render e Neon

Esta configuração substitui o Railway após o encerramento do período gratuito.

## Arquitetura

- Frontend React/Vite: Vercel
- Backend Spring Boot: Render Web Service Free
- Banco PostgreSQL: Neon Free

## 1. Criar o banco no Neon

1. Criar um projeto PostgreSQL no Neon.
2. Na tela de conexão, copiar host, banco, usuário e senha.
3. Montar a URL JDBC:

```text
jdbc:postgresql://HOST/BANCO?sslmode=require
```

## 2. Criar o backend no Render

O arquivo `render.yaml` permite criar o serviço como Blueprint.

Variáveis obrigatórias:

| Variável | Valor |
|---|---|
| `SPRING_PROFILES_ACTIVE` | `prod` |
| `JDBC_DATABASE_URL` | URL JDBC do Neon |
| `DB_USERNAME` | usuário do Neon |
| `DB_PASSWORD` | senha do Neon |
| `JWT_SECRET` | segredo aleatório com pelo menos 32 caracteres |
| `CORS_ORIGINS` | URLs dos frontends na Vercel separadas por vírgula |

Depois do deploy, validar:

```text
https://SERVICO.onrender.com/api/health
```

Resposta esperada:

```json
{"status":"ok"}
```

## 3. Atualizar a Vercel

Criar ou atualizar a variável:

```text
VITE_API_URL=https://SERVICO.onrender.com/api
```

Executar um novo deploy do frontend após alterar a variável.

## Observação sobre o plano gratuito

O Render suspende o backend após um período sem acessos. A primeira requisição após a suspensão pode demorar. O banco Neon também pode suspender o compute quando não utilizado, mas os dados permanecem armazenados dentro dos limites do plano.
