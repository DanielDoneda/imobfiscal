# Backlog — Desenvolvimento Web III

Este documento organiza os cartões para reprodução no Trello. As movimentações devem demonstrar
o desenvolvimento incremental do ImobFiscal, inclusive atividades já concluídas.

## Concluído

### WEB-01 — Integrar frontend React com a API Spring Boot
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Descrição:** Integrar formulários e listagens aos endpoints REST.
- **Aceite:** operações retornam feedback e os erros da API são apresentados ao usuário.
- **Evidência:** `frontend/src/services/api.js` e controllers do backend.

### WEB-02 — Proteger a API com Spring Security e JWT
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Aceite:** login público; rotas privadas exigem token; ausência de token retorna 401.
- **Evidência:** commit `f364c46` e testes de segurança.

### WEB-03 — Isolar dados por imobiliária (multi-tenancy)
- **Responsável:** Líder técnico
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Aceite:** acesso aos dados de outro tenant retorna 403.
- **Evidência:** `JwtAuthenticationFilter` e `SecurityIntegrationTest`.

### WEB-04 — Aplicar Bean Validation
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Aceite:** requisições inválidas retornam 400 e erros por campo.
- **Evidência:** DTOs `*Request`, `@Valid` e `GlobalExceptionHandler`.

### WEB-05 — Corrigir o cadastro público de conta
- **Responsável:** Desenvolvedor
- **Informado por:** Usuário avaliador
- **Prioridade:** Alta
- **Descrição:** Criar a imobiliária e seu primeiro administrador sem depender da conta de demonstração.
- **Aceite:** recebe imobiliária ou proprietário, CPF/CNPJ, responsável, e-mail e senha; cria tenant
  e usuário `ADMIN` em uma transação; rejeita e-mail ou documento repetido.
- **Evidência:** commit `02237dc`, `CadastroPage`, `AuthController` e testes de integração.

### WEB-06 — Registrar atividades no MongoDB
- **Responsável:** DBA
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Média
- **Aceite:** logs separados por imobiliária e ordenados por data.
- **Evidência:** commit `0ea6c32` e pacote `backend/.../audit`.

## Em andamento

### WEB-07 — Consolidar testes e evidências
- **Responsável:** Líder técnico
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Aceite:** builds aprovados; capturas de 201, 400, 401 e 403; commits registrados.
- **Progresso:** frontend compilado e 16 testes do backend aprovados em 19/11/2026; faltam as
  capturas da versão publicada e os links finais dos commits.

### WEB-08 — Atualizar documentação técnica
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Líder técnico
- **Prioridade:** Média
- **Descrição:** Separar os documentos históricos do PI 2 da arquitetura atual.
- **Aceite:** a entrega final descreve corretamente JWT, JPA e MongoDB.

## Backlog futuro

### WEB-09 — Gestão de funcionários e perfis
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 13 pontos
- **Descrição:** Área administrativa para cadastrar, editar, ativar e desativar funcionários com os
  perfis `ADMIN`, `GERENTE`, `OPERADOR`, `FINANCEIRO` e `READONLY`.
- **Aceite:** somente administrador gerencia funcionários; funcionário não escolhe tenant; e-mail é
  único; senha usa BCrypt; alterações ficam na auditoria.
- **Dependências:** matriz de permissões e estratégia de convite ou senha provisória.

### WEB-10 — Aplicar autorização por perfil
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Aceite:** exclusão restrita a administradores; financeiro controla cobranças e notas; perfil de
  leitura não altera dados; cenários 403 possuem testes.

### WEB-11 — Recuperação de senha e convite seguro
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Média
- **Estimativa:** 8 pontos
- **Descrição:** Convite com token temporário e redefinição segura de senha.

### WEB-12 — Validar CPF e CNPJ pelos dígitos verificadores
- **Responsável:** Desenvolvedor
- **Informado por:** DBA
- **Prioridade:** Média
- **Estimativa:** 3 pontos

### WEB-13 — Criar testes automatizados do frontend
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Média
- **Estimativa:** 8 pontos
- **Descrição:** Cobrir autenticação, cadastro, validações e fluxos principais com Vitest.

### WEB-14 — Revogar sessões e limitar tentativas de login
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Média
- **Estimativa:** 8 pontos
- **Descrição:** Logout efetivo, revogação após troca de senha e bloqueio temporário.

### WEB-15 — Criar cadastro próprio de locatários
- **Responsável:** Desenvolvedor
- **Informado por:** Usuário avaliador
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Criar cadastro reutilizável de locatários PF e PJ, separado de imóveis e contratos.
- **Aceite:** locatário pertence ao tenant autenticado; CPF/CNPJ é único dentro da imobiliária;
  contratos utilizam apenas locatários previamente cadastrados.

### WEB-16 — Vincular locatário exclusivamente pelo contrato
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Remover qualquer escolha de locatário do cadastro do imóvel e centralizar o vínculo
  no contrato de locação.
- **Aceite:** contrato lista imóveis disponíveis e locatários cadastrados; imóvel exibe contrato e
  locatário vigentes apenas para consulta.
- **Dependência:** WEB-15.

### WEB-17 — Impedir contratos ativos simultâneos no mesmo imóvel
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Aplicar regra de negócio e teste de integração para impedir que um imóvel seja
  vinculado a mais de um contrato ativo.
- **Aceite:** imóvel ocupado não aparece como disponível; tentativa direta pela API é recusada;
  encerramento do contrato libera o imóvel.

### WEB-18 — Cadastrar proprietários e imóveis de terceiros
- **Responsável:** Líder técnico
- **Informado por:** Gerente de projetos
- **Prioridade:** Média
- **Estimativa:** 13 pontos
- **Descrição:** Criar cadastro de proprietários PF/PJ e identificar imóveis próprios ou administrados
  para terceiros.
- **Aceite:** vários imóveis podem pertencer ao mesmo proprietário; boletos e cálculos fiscais usam
  o proprietário correto; dados permanecem isolados por tenant.
