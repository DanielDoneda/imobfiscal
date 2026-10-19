# Plano de saída do Railway

## Objetivo

Manter o Railway apenas como solução temporária para recuperar o acesso ao
ImobFiscal e remover a dependência da plataforma sem interromper o sistema.

## Arquitetura-alvo

- Frontend React/Vite hospedado na Vercel.
- Backend Spring Boot hospedado em um serviço compatível com contêiner, com o
  Render como primeira opção de transição.
- PostgreSQL gerenciado no Supabase.
- Configuração exclusivamente por variáveis de ambiente, sem URLs, senhas ou
  nomes específicos de provedor no código.

## Etapas

### 1. Estabilizar o ambiente temporário

- Manter o backend e o MySQL no Railway.
- Configurar healthcheck, domínio público e logs.
- Validar login e operações essenciais.
- Registrar as variáveis necessárias sem copiar segredos para o Git.

### 2. Tornar o backend portável

- Criar uma imagem Docker reproduzível para o backend.
- Padronizar `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`,
  `PORT`, `CORS_ORIGINS` e `SPRING_PROFILES_ACTIVE`.
- Remover arquivos e nomes de variáveis exclusivos do Railway.
- Adicionar healthcheck e testes de inicialização.

### 3. Migrar MySQL para PostgreSQL

- Adicionar o driver PostgreSQL e um perfil de execução próprio.
- Revisar tipos, funções SQL e geração de UUID.
- Consolidar o schema em migrações versionadas com Flyway ou Liquibase.
- Criar o banco no Supabase com RLS habilitada nas tabelas expostas.
- Migrar e conferir contagem, integridade e relacionamentos dos dados.

### 4. Publicar o ambiente substituto

- Implantar o contêiner do backend no Render.
- Conectar o backend ao PostgreSQL do Supabase usando conexão segura.
- Executar smoke tests de saúde, login e CRUD.
- Restringir o CORS ao domínio oficial do frontend.

### 5. Fazer a troca

- Alterar `VITE_API_URL` na Vercel para o novo backend.
- Publicar o frontend e testar o fluxo completo.
- Manter o Railway disponível durante uma janela curta de rollback.
- Após a validação, exportar o banco final e desativar os serviços antigos.

## Critérios para desligar o Railway

- Backend alternativo saudável e monitorado.
- Login e fluxos críticos aprovados.
- Dados migrados e conferidos.
- Backup restaurável testado.
- Variável da Vercel apontando para o novo backend.
- Procedimento de rollback documentado.

## Ordem recomendada

1. Recuperar o acesso usando Railway e MySQL.
2. Estabilizar e adicionar testes mínimos.
3. Containerizar o backend.
4. Migrar o banco para PostgreSQL/Supabase.
5. Publicar no Render e executar a troca.
6. Remover o Railway somente depois da validação final.
