# Backlog — Banco de Dados Não Relacional

Este backlog organiza os cartões da disciplina Banco de Dados Não Relacional para reprodução no
Trello. O incremento escolhido para o ImobFiscal foi a auditoria de atividades com MongoDB Atlas,
mantendo os dados transacionais no PostgreSQL.

## Concluído

### BDN-01 — Definir o caso de uso não relacional
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Avaliar os módulos e escolher os logs de auditoria como caso de uso documental.
- **Aceite:** decisão justifica flexibilidade do documento, alto volume de escrita e consultas por
  tenant e data.
- **Evidência:** relatório acadêmico da Matéria 4.

### BDN-02 — Modelar a coleção de logs de atividades
- **Responsável:** DBA
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Definir a estrutura conceitual e física da coleção `logs_atividade`.
- **Aceite:** documento contém usuário, imobiliária, ação, método, recurso, status, IP, agente e data.
- **Evidência:** `LogAtividade.java` e documentação de modelagem.

### BDN-03 — Configurar a conexão com MongoDB Atlas
- **Responsável:** DBA
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Configurar Spring Data MongoDB e conexão externa por variável de ambiente.
- **Aceite:** credencial não aparece no repositório; conexão é configurável por ambiente; aplicação
  funciona com auditoria desativada quando o MongoDB não estiver disponível.
- **Evidência:** `pom.xml`, propriedades da aplicação e `render.yaml`.

### BDN-04 — Persistir logs automaticamente
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Interceptar requisições autenticadas e registrar atividades sem duplicar código nos
  controllers.
- **Aceite:** login e operações protegidas geram documentos; falha da auditoria não interrompe a
  operação principal.
- **Evidência:** `AuditoriaFilter`, `MongoAuditoriaService` e commit `0ea6c32`.

### BDN-05 — Criar índice composto por tenant e data
- **Responsável:** DBA
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Média
- **Estimativa:** 3 pontos
- **Descrição:** Otimizar a consulta cronológica dos logs de cada imobiliária.
- **Aceite:** índice composto usa `imobiliariaId` ascendente e `dataHora` descendente.
- **Evidência:** anotação `@CompoundIndex` em `LogAtividade`.

### BDN-06 — Disponibilizar consulta paginada dos logs
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Média
- **Estimativa:** 5 pontos
- **Descrição:** Criar endpoint protegido e tela de auditoria com paginação.
- **Aceite:** usuário vê somente os logs do próprio tenant; registros aparecem do mais recente para
  o mais antigo; acesso exige JWT.
- **Evidência:** `AuditoriaController`, `AuditoriaPage` e `listarAuditoria`.

### BDN-07 — Testar o filtro de auditoria
- **Responsável:** Desenvolvedor
- **Informado por:** DBA
- **Prioridade:** Média
- **Estimativa:** 3 pontos
- **Descrição:** Validar que as informações da requisição são convertidas no registro documental.
- **Aceite:** teste automatizado aprovado e auditoria desativada não exige MongoDB durante os demais
  testes.
- **Evidência:** `AuditoriaFilterTest`; suíte com 16 testes aprovada em 19/11/2026.

### BDN-08 — Documentar modelagem, persistência e integração
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Produzir capítulo acadêmico com justificativa, JSON, índice, código e evidências.
- **Aceite:** documento cobre modelagem conceitual, estrutura do banco e persistência, conforme o
  manual do PI.
- **Evidência:** `Relatorio_Banco_Dados_Nao_Relacional_ImobFiscal.docx`.

## Em andamento

### BDN-09 — Organizar evidências no diretório `/database`
- **Responsável:** DBA
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Atender literalmente à estrutura de evidências solicitada pelo manual do PI.
- **Aceite:** pasta contém modelo da coleção, exemplo JSON, definição do índice e instruções de
  configuração, sem credenciais reais.

### BDN-10 — Registrar evidências visuais do MongoDB Atlas
- **Responsável:** DBA
- **Informado por:** Líder técnico
- **Prioridade:** Média
- **Estimativa:** 2 pontos
- **Descrição:** Capturar coleção, documento de exemplo e índice usando dados não sensíveis.
- **Aceite:** imagens legíveis são incluídas no capítulo final e identificadas por legenda.

## Backlog futuro

### BDN-11 — Registrar tentativas de acesso negadas
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Auditar respostas 401 e 403 sem armazenar senhas, tokens ou conteúdo sensível.

### BDN-12 — Definir retenção e descarte automático de logs
- **Responsável:** DBA
- **Informado por:** Gerente de projetos
- **Prioridade:** Média
- **Estimativa:** 5 pontos
- **Descrição:** Definir prazo de retenção e índice TTL conforme finalidade e privacidade.
- **Aceite:** política documentada; descarte automático testado; registros essenciais podem ser
  preservados conforme obrigação aplicável.

### BDN-13 — Criar filtros avançados na auditoria
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Média
- **Estimativa:** 5 pontos
- **Descrição:** Pesquisar logs por usuário, ação, período, recurso e código de resposta.

### BDN-14 — Criar painel de indicadores de segurança
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Baixa
- **Estimativa:** 8 pontos
- **Descrição:** Agregar erros, acessos negados e operações críticas sem misturar tenants.

### BDN-15 — Exportar trilha de auditoria
- **Responsável:** Desenvolvedor
- **Informado por:** DBA
- **Prioridade:** Baixa
- **Estimativa:** 5 pontos
- **Descrição:** Permitir exportação CSV/PDF por período, com autorização e registro da própria
  exportação.

## Critério de conclusão da disciplina

A implementação principal está concluída. Para fechar a entrega acadêmica, faltam apenas a pasta
`/database`, as capturas do Atlas e a incorporação do capítulo já produzido ao documento final.
