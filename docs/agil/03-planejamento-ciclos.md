# Planejamento dos ciclos de entrega

O processo foi reconstruído a partir dos commits, documentos e publicações existentes. Os ciclos
representam incrementos verificáveis e podem ser reproduzidos como listas, etiquetas ou sprints no
Trello.

## Ciclo 1 — Fundação e publicação inicial

**Período de referência:** até 19/10/2026  
**Objetivo:** importar a base do PI, publicar o frontend e preparar uma alternativa ao Railway.

Entregas:

- Projeto inicial versionado (`ea799c6`);
- Plano de saída do Railway (`5c8be01`);
- Imagem Docker do backend (`2d6f51d`);
- Configuração de publicação (`9841e24`).

Retrospectiva: a dependência de um serviço com trial revelou a necessidade de separar frontend,
backend e banco e manter um plano de migração.

## Ciclo 2 — Interface e segurança

**Período de referência:** 27/10 a 31/10/2026  
**Objetivo:** melhorar a navegação e atender à atividade de Spring Security e Validation.

Entregas:

- Menu lateral (`d90647a`);
- Spring Security, JWT, BCrypt e Validation (`f364c46`);
- Testes de autenticação, 401, 403 e tenant.

Retrospectiva: a segurança passou a determinar o tenant confiável, corrigindo a limitação da API
anterior. Documentos legados precisaram ser identificados como históricos.

## Ciclo 3 — Infraestrutura sustentável

**Período de referência:** 19/11/2026  
**Objetivo:** substituir o ambiente expirado e restabelecer o sistema online.

Entregas:

- PostgreSQL no Neon;
- Backend no Render;
- Frontend apontando para a nova API;
- Configurações por variáveis de ambiente (`6e86b2a`).

Retrospectiva: a publicação automática do Render não acompanhou um commit e exigiu deploy manual.
O health check e a conferência do commit ativo tornaram-se parte da validação.

## Ciclo 4 — Auditoria NoSQL e cadastro público

**Período de referência:** 19/11/2026  
**Objetivo:** atender Banco de Dados Não Relacional e eliminar a dependência da conta demo.

Entregas:

- Logs de atividades com MongoDB Atlas (`0ea6c32`);
- Índice por tenant e data;
- Tela e endpoint paginado de auditoria;
- Cadastro que cria tenant e administrador e aceita CPF/CNPJ (`02237dc`);
- 16 testes automatizados aprovados.

Retrospectiva: o erro “ID da imobiliária obrigatório” mostrou que push não garante publicação. Foi
necessário verificar separadamente código, backend ativo e frontend publicado.

## Ciclo 5 — Documentação e experiência

**Período de referência:** 19 e 20/11/2026  
**Objetivo:** consolidar evidências das disciplinas e alinhar portfólio e marca.

Entregas:

- Backlogs de Web III e NoSQL (`91b24ef`);
- Personas, jornada, fluxo, plano de teste e inspeção IHC (`3fea73a`);
- Backlog de IHC (`04f050e`);
- Portfólio atualizado (`435f290` no repositório `dsmfatec`);
- Identidade visual aplicada e documentada.

Retrospectiva: foi necessário separar inspeção especializada de teste com participantes para não
transformar uma evidência parcial em resultado humano inexistente.

## Próximo ciclo — Validação e entrega final

Objetivo:

- Executar o teste de usabilidade com participantes;
- Organizar `/database` e capturas do Atlas;
- Criar Trello com cartões e evidências;
- Consolidar capítulos no Word final;
- Preparar apresentação e demonstração.
