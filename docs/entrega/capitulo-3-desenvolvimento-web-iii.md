# Desenvolvimento Web III

## 1. Objetivo da disciplina no Projeto Integrador

A disciplina de Desenvolvimento Web III contribuiu para a construção do MVP funcional do
ImobFiscal, contemplando funcionalidades dinâmicas, integração entre frontend e backend, segurança,
validação e persistência. A aplicação foi organizada como uma SPA em React que consome uma API REST
desenvolvida com Spring Boot.

## 2. Arquitetura e integração

O frontend utiliza React, React Router, Axios e Vite. O backend utiliza Java 17, Spring Boot 3.3,
Spring Web, Spring Security, Jakarta Validation e Spring Data. Os dados transacionais são persistidos
no PostgreSQL/Neon, enquanto os logs de atividades são enviados ao MongoDB Atlas.

Fluxo principal:

1. O usuário interage com os formulários React.
2. O Axios envia requisições JSON à API REST.
3. O Spring Security valida o JWT.
4. O Bean Validation verifica os dados recebidos.
5. A camada de serviço aplica regras de negócio.
6. Os repositórios persistem os dados.
7. A API devolve respostas HTTP padronizadas ao frontend.

## 3. Funcionalidades implementadas

- Cadastro público de conta para proprietário PF ou empresa PJ;
- Criação automática da organização e do primeiro usuário administrador;
- Login protegido por senha com hash BCrypt;
- Cadastro e manutenção de imóveis;
- Cadastro de locadores utilizado pela versão atual;
- Contratos de locação com dados do locatário;
- Geração acadêmica de boletos e notas fiscais;
- Motor de cálculo tributário;
- Dashboard e navegação lateral;
- Auditoria de atividades;
- Separação dos dados por imobiliária.

## 4. Spring Security e JWT

A API utiliza autenticação stateless. Após validar e-mail e senha, o backend emite um JWT assinado
com identificação do usuário, perfil e tenant. O frontend armazena o token e o envia no cabeçalho
`Authorization: Bearer <token>`.

As rotas de login, cadastro e verificação de disponibilidade são públicas. As demais rotas da API
exigem autenticação. Uma requisição sem token recebe HTTP 401, enquanto a tentativa de acessar dados
de outra imobiliária recebe HTTP 403.

## 5. Isolamento multiusuário

Cada usuário pertence a uma imobiliária, tratada como tenant. O identificador confiável do tenant é
obtido a partir do usuário autenticado. O filtro de segurança compara esse valor com o identificador
presente na rota, impedindo a leitura ou alteração de informações de outra conta.

Essa estratégia é denominada **multi-tenancy com isolamento lógico por tenant**.

## 6. Validação

Os objetos de entrada utilizam Jakarta Bean Validation, incluindo `@NotBlank`, `@NotNull`, `@Email`,
`@Size`, `@Pattern`, `@Positive` e `@Future`. Os controllers executam as regras com `@Valid`. Quando
uma requisição é inválida, o `GlobalExceptionHandler` retorna HTTP 400, uma mensagem geral e os
erros associados a cada campo.

O cadastro público foi corrigido durante uma iteração do MVP. A versão anterior dependia do UUID da
imobiliária de demonstração. A versão atual cria uma nova organização e seu primeiro administrador
na mesma transação, aceitando CPF com 11 dígitos ou CNPJ com 14 dígitos.

## 7. Testes e resultados

Em 19 de novembro de 2026 foram executados 16 testes automatizados, sem falhas. A cobertura inclui:

- Geração e validação do JWT;
- Rejeição de chave JWT fraca;
- Rota pública de disponibilidade;
- HTTP 401 sem autenticação;
- Login e acesso ao próprio tenant;
- HTTP 403 no acesso entre tenants;
- Erros de Bean Validation por campo;
- Cadastro público de organização e administrador;
- Cadastro com CPF;
- Recusa de documento duplicado;
- Motor tributário;
- Filtro de auditoria.

O frontend também foi compilado para produção com sucesso. A correção do cadastro foi publicada no
Render e validada na API online.

## 8. Evidências

| Evidência | Localização |
|---|---|
| Configuração do Spring Security | `backend/src/main/java/br/fatec/imobfiscal/config/SecurityConfig.java` |
| Emissão de JWT | `backend/src/main/java/br/fatec/imobfiscal/security/JwtService.java` |
| Filtro de autenticação | `backend/src/main/java/br/fatec/imobfiscal/security/JwtAuthenticationFilter.java` |
| Validação das entradas | `backend/src/main/java/br/fatec/imobfiscal/model/*Request.java` |
| Tratamento de erros | `backend/src/main/java/br/fatec/imobfiscal/exception/GlobalExceptionHandler.java` |
| Testes de integração | `backend/src/test/java/br/fatec/imobfiscal/security/SecurityIntegrationTest.java` |
| Integração do frontend | `frontend/src/services/api.js` |
| Documentação de segurança | `docs/SEGURANCA-SPRING.md` |
| Backlog da disciplina | `docs/agil/backlog-desenvolvimento-web-iii.md` |

Commits principais:

- `f364c46` — Spring Security, JWT e Validation;
- `d90647a` — menu lateral;
- `0ea6c32` — auditoria com MongoDB;
- `02237dc` — correção do cadastro público com CPF/CNPJ.

## 9. Limitações conhecidas e trabalho futuro

O MVP registra os dados do locatário diretamente no contrato. Foi priorizada como evolução a criação
de cadastro próprio de locatários, seleção apenas no contrato, listagem de imóveis disponíveis e
bloqueio de contratos ativos simultâneos. Também estão planejados cadastro de proprietários,
administração de imóveis de terceiros, gestão de funcionários, autorização por perfil, recuperação
de senha, revogação de sessões e testes automatizados do frontend.

Esses itens foram mantidos no backlog por representarem incrementos maiores e permitirem demonstrar
a continuidade da gestão ágil após a entrega do MVP.

## 10. Conclusão

Os requisitos centrais de Desenvolvimento Web III foram atendidos: funcionalidades do MVP,
integração entre camadas e código-fonte funcional. A implementação também incorporou autenticação,
autorização, validação, testes e isolamento multiusuário, ampliando a confiabilidade do sistema.
